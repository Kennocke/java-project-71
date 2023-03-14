package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;

public class Comparator {
    public static List<Map<String, Object>> compare(
            Map<String, Object> firstDataObject,
            Map<String, Object> secondDataObject
    ) {
        List<Map<String, Object>> comparisonResult = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>(firstDataObject.keySet());
        keys.addAll(secondDataObject.keySet());

        for (String key : keys) {
            if (firstDataObject.containsKey(key) && secondDataObject.containsKey(key)) {
                if (firstDataObject.get(key) == null && secondDataObject.get(key) == null) {
                    comparisonResult.add(insertDiffRow(key, firstDataObject.get(key)));
                } else if (!(firstDataObject.get(key) == null || secondDataObject.get(key) == null)
                        && firstDataObject.get(key).equals(secondDataObject.get(key))) {
                    comparisonResult.add(insertDiffRow(key, firstDataObject.get(key)));
                } else {
                    comparisonResult.add(updateRow(key, firstDataObject.get(key), secondDataObject.get(key)));
                }
            } else if (firstDataObject.containsKey(key) && !secondDataObject.containsKey(key)) {
                comparisonResult.add(deleteRow(key, firstDataObject.get(key)));
            } else {
                comparisonResult.add(addRow(key, secondDataObject.get(key)));
            }
        }

        return comparisonResult;
    }

    private static Map<String, Object> insertDiffRow(String key, Object newValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("oldValue", newValue);
        row.put("operation", "none");
        return row;
    }

    private static Map<String, Object> addRow(String key, Object newValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("newValue", newValue);
        row.put("operation", "add");

        return row;
    }

    private static Map<String, Object> deleteRow(String key, Object oldValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("oldValue", oldValue);
        row.put("operation", "delete");

        return row;
    }

    private static Map<String, Object> updateRow(String key, Object oldValue, Object newValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("oldValue", oldValue);
        row.put("newValue", newValue);
        row.put("operation", "update");

        return row;
    }
}
