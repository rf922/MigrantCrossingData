/*
 * 
 * 
 * 
 */
package dataprocessing;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This is the main entry and driver for the program
 * @author rf922
 */
public class DataProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dataSet = "data/migrantsMissing08112021.csv";
        String malformedData = "malformedEntries.csv";

        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CompletionService<MigrationData> completionService = new ExecutorCompletionService<>(executor);
        List<MigrationData> dataList = new LinkedList<>();
        List<String> malformedList = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader(dataSet))) {
            String header = csvReader.readLine();
            int taskCount = 0;

            /**
             * Listing the Col Entries *
             */
            /**
             * System.out.println(header.split(";").length); for (var col :
             * header.split(";")) { System.out.println(col); }
            *
             */
            for (String line; (line = csvReader.readLine()) != null;) {

                /* Testing / finding malformed entries
                if(line.split(";", -1).length != 23){
                    System.out.println("[ MAIN ] : MALFORMED ENTRY FOUND \n"+line);
                    System.out.println("[ MAIN ] : FIELDS FOUND  \n"+line.split(";").length);
                    malformedList.add(line);
                    continue;
                }
                 */
                String finalLine = line;
                completionService.submit(() -> MigrationData.parseLine(finalLine));
                taskCount++;
            }

            for (int i = 0; i < taskCount; i++) {
                Future<MigrationData> future = completionService.take();
                dataList.add(future.get());
            }

            Map<String, List<MigrationData>> incidentsByCountry
                = MigrationDataUtils.groupByField(dataList, MigrationData::paisIncidente);
            //incidentsByCountry.forEach((x, y) -> {System.out.println(x + " " +y.size());});

            int deaths = incidentsByCountry.get("Mexico").stream().mapToInt(x -> x.muertos()).sum();

            incidentsByCountry.get("Mexico").stream().filter(x -> x.mujeres() > 1).forEach(System.out::println);

            System.out.println(deaths);

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            executor.shutdown();
            System.out.println(" [ MAIN ] : DataList Size " + dataList.size());
            System.out.println(" [ MAIN ] : Malformed Size " + malformedList.size());

            /**
             * DEBUGGING entries long lackingFields =
             * malformedList.stream().filter(x -> x.split(";", -1).length < 23).count();
             * long surplusFields = malformedList.size() - lackingFields;
             * System.out.println("[ MAIN ] : Lacking Fields "+lackingFields);
             * System.out.println("[ MAIN ] : Surplus Fields "+surplusFields);
             * malformedList.stream().filter(x -> x.split(";", -1).length >
             * 23).map(x -> x.split(";",
             * -1).length).distinct().forEach(System.out::println);
             * malformedList.stream().filter(x -> x.split(";", -1).length >
             * 23).limit(15).forEach(System.out::println);
             */
        }
    }

}
