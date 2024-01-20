/*
 * 
 * 
 * 
 */
package dataprocessing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.time.chrono.ChronoLocalDate;
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
        List<MigrationData> dataList = MigrationDataUtils.loadDataFromCSV(dataSet);
        System.out.println(dataList.size());
    }

}
