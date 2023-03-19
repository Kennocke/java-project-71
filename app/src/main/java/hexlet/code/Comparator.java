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
            if (!secondDataObject.containsKey(key)) {
                comparisonResult.add(deleteRow(key, firstDataObject.get(key)));
            } else if (!firstDataObject.containsKey(key)) {
                comparisonResult.add(addRow(key, secondDataObject.get(key)));
            } else if (String.valueOf(firstDataObject.get(key)).equals(String.valueOf(secondDataObject.get(key)))) {
                comparisonResult.add(addRowWithoutChanges(key, firstDataObject.get(key)));
            } else {
                comparisonResult.add(updateRow(key, firstDataObject.get(key), secondDataObject.get(key)));
            }
        }

        return comparisonResult;
    }

    private static Map<String, Object> createDiffRow(String operation, String key, Object oldValue, Object newValue) {
        Map<String, Object> row = new HashMap<>();
        row.put("operation", operation);
        row.put("key", key);
        row.put("oldValue", oldValue);
        row.put("newValue", newValue);
        return row;
    }

    private static Map<String, Object> addRowWithoutChanges(String key, Object oldValue) {
        return createDiffRow("none", key, oldValue, null);
    }

    private static Map<String, Object> addRow(String key, Object newValue) {
        return createDiffRow("add", key, null, newValue);
    }

    private static Map<String, Object> deleteRow(String key, Object oldValue) {
        return createDiffRow("delete", key, oldValue, null);
    }

    private static Map<String, Object> updateRow(String key, Object oldValue, Object newValue) {
        return createDiffRow("update", key, oldValue, newValue);
    }
}
