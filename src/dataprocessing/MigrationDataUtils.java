/*
 * File : MigrationDataUtils.java
 * 
 * 
 */
package dataprocessing;

import dataprocessing.model.MigrationDataEntry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Various utilities for making different operations on the data
 * @author rf922
 */
public class MigrationDataUtils {
    
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String LINE_REGEX = "(?<=^|;)(\"(?:[^\"]|\"\")*\"|[^;]*)";
    private static final String DELIMITER = ";";
    private static final int NUM_FIELDS = 23;
    
    public static LocalDate parseDate(String dateString) {
        try { 
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            return LocalDate.MIN; // or a default date
        }
    }
    
    public static Integer parseInteger(String intString) {
        try {
            return Integer.valueOf(intString);
        } catch (NumberFormatException e) {
            return -1; // or a default value
        }
    }

    public static Double parseDouble(String doubleString) {
        try {
            return Double.valueOf(doubleString);
        } catch (NumberFormatException e) {
            return -1.0; // or a default value
        }
    }

    public static Long parseLong(String doubleString) {
        try {
            return Long.valueOf(doubleString);
        } catch (NumberFormatException e) {
            return -1L; // or a default value
        }
    }

    /**
     * Method to collect and group data entries by a field. Accepts a list of 
     * migration data entries, a function for key extraction. The function returns a map
     * of keys and a list of corresponding migration data entries.
     * 
     * @param <K>
     * @param dataList
     * @param keyExtractor
     * @return 
     */
    public static <K extends Comparable<K>> Map<K, List<MigrationDataEntry>> groupByField(
        List<MigrationDataEntry> dataList,
        Function<MigrationDataEntry, K> keyExtractor) {
        Map<K, List<MigrationDataEntry>> groupedData = new HashMap<>();
        for (MigrationDataEntry data : dataList) {
            K key = keyExtractor.apply(data);
            groupedData.computeIfAbsent(key, k -> new ArrayList<>()).add(data);
        }
        return groupedData;
    }
    
    
    /**
     * Parses lines into MigrantDataEntries
     * @param line
     * @return 
     */
    public static MigrationDataEntry parseLine(String line) {
        String[] entries = splitLine(line);

        MigrationDataEntry entry = new MigrationDataEntry.MigrationDataEntryBuilder()
            .id(entries[0])
            .date(entries[2])
            .region(entries[3])
            .migrationPath(entries[4])
            .causeOfDeath(entries[5])
            .dead(entries[6])
            .missing(entries[7])
            .women(entries[8])
            .men(entries[9])
            .minors(entries[10])
            .survivors(entries[11])
            .regionIncident(entries[12])
            .incidentCountry(entries[13])
            .regionOrigin(entries[14])
            .countryOrigin(entries[15])
            .holder(entries[16])
            .informationSource(entries[17])
            .location(entries[18])
            .urls(entries[19])
            .detailUrl(entries[20])
            .latitude(entries[21])
            .longitude(entries[22])
            .build();

        return entry;
    }
    
    /**
     * Handles splitting lines , cleaning data
     * @param line
     * @return 
     */
    public static String[] splitLine(String line) {
        List<String> fields = new ArrayList<>();
        Matcher matcher = Pattern.compile(LINE_REGEX).matcher(line);
        while (matcher.find()) {
            fields.add(matcher.group(1)
                .replaceAll("^\"|\"$", "")
                .replace("\"\"", "\""));
        }
        return fields.toArray(String[]::new);

    }
    
    
    public static boolean malformedEntryTest(String line){
        return line.split(DELIMITER, -1).length < NUM_FIELDS;
    }
}
