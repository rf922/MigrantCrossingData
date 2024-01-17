/*
 * 
 * 
 * 
 */
package dataprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Various utilities for making different operations on the data
 * @author rf922
 */
public class MigrationDataUtils {

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
