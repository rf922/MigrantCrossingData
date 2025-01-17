/**
 * 
 * File : MigrationDataLoader.java
 * 
 * 
*/
package dataprocessing;

import dataprocessing.model.MigrationDataEntry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class to load data from csv 
 *
 * @author rf922
 */
public class MigrationDataLoader {
    
    private String MALFORMED_DATA = "malformedEntries.csv";

    public static List<MigrationDataEntry> loadData(String csv) {
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CompletionService<MigrationDataEntry> completionService = new ExecutorCompletionService<>(executor);
        List<MigrationDataEntry> dataList = new LinkedList<>();
        List<String> malformedList = new ArrayList<>();
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csv))) {
            String header = csvReader.readLine();
            int taskCount = 0;
            for (String line; (line = csvReader.readLine()) != null;) {

                if (MigrationDataUtils.malformedEntryTest(line)) {
                    malformedList.add(line);
                    continue;
                }
                
                String finalLine = line;
                completionService.submit(() -> MigrationDataUtils.parseLine(finalLine));
                taskCount++;
            }

            for (int i = 0; i < taskCount; i++) {
                Future<MigrationDataEntry> future = completionService.take();
                dataList.add(future.get());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            executor.shutdown();
            System.out.println(" [ MAIN ] : DataList Size " + dataList.size());
            System.out.println(" [ MAIN ] : Malformed Size " + malformedList.size());

              long lackingFields =
              malformedList.stream().filter(x -> x.split(";", -1).length < 23).count();
              long surplusFields = malformedList.size() - lackingFields;
              System.out.println("[ MAIN ] : Lacking Fields "+lackingFields);
              System.out.println("[ MAIN ] : Surplus Fields "+surplusFields);
//              malformedList.stream().filter(x -> x.split(";", -1).length >
    //          23).map(x -> x.split(";",
      //        -1).length).distinct().forEach(System.out::println);
              malformedList.stream().filter(x -> x.split(";", -1).length >
              23).limit(15).forEach(System.out::println);
             return dataList;
        }
    }
   

}
