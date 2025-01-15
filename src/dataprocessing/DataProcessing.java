/*
 * 
 * File : DataProcessing.java
 * 
 */
package dataprocessing;

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
 * This is the main entry and driver for the program
 * @author rf922
 */
public class DataProcessing {
    private static final String DATA_SET = "data/migrantsMissing08112021.csv";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MigrationData.loadData(DATA_SET);

    }

    
    
    
}
