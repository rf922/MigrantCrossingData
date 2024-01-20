/*
 * 
 * 
 * 
 */
package dataprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * Various utilities for making different operations on the data
 *
 * @author rf922
 */
public class MigrationDataUtils {

    public static List<MigrationData> loadDataFromCSV(String dataSet) {
        List<MigrationData> dataList = new LinkedList<>();
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CompletionService<MigrationData> completionService = new ExecutorCompletionService<>(executor);
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(dataSet))) {
            int taskCount = 0;
            
            for (String line; (line = csvReader.readLine()) != null;) {
                String finalLine = line;
                completionService.submit(() -> MigrationData.parseLine(finalLine));
                taskCount++;
            }

            for (int i = 0; i < taskCount; i++) {
                Future<MigrationData> future = completionService.take();
                dataList.add(future.get());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            executor.shutdown();
        }

        return dataList;
    }

    /**
     * Method to collect and group data entries by a field. Accepts a list of
     * migration data entries, a function for key extraction. The function
     * returns a map of keys and a list of corresponding migration data entries.
     *
     * @param <K>
     * @param dataList
     * @param keyExtractor
     * @return
     */
    public static <K extends Comparable<K>> Map<K, List<MigrationData>> groupByField(
        List<MigrationData> dataList,
        Function<MigrationData, K> keyExtractor) {
        Map<K, List<MigrationData>> groupedData = new HashMap<>();
        for (MigrationData data : dataList) {
            K key = keyExtractor.apply(data);
            groupedData.computeIfAbsent(key, k -> new ArrayList<>()).add(data);
        }
        return groupedData;
    }
}
