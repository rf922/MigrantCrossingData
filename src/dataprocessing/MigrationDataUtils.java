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
