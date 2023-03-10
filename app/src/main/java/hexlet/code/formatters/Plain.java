package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diffData) {
        StringBuilder builder = new StringBuilder();
        for (Map<String, Object> row : diffData) {
            if (row.get("operation").equals("add")) {
                Object newValue = row.get("newValue") instanceof ArrayList
                        || row.get("newValue") instanceof HashMap ? "[complex value]" : row.get("newValue");
                builder.append("Property '");
                builder.append(row.get("key"));
                builder.append("' was added with value: ");
                builder.append(newValue);
                builder.append("\n");
            } else if (row.get("operation").equals("delete")) {
                builder.append("Property '");
                builder.append(row.get("key"));
                builder.append("' was removed");
                builder.append("\n");
            } else if (row.get("operation").equals("update")) {
                Object oldValue = row.get("oldValue") instanceof ArrayList
                        || row.get("oldValue") instanceof HashMap ? "[complex value]" : row.get("oldValue");
                Object newValue = row.get("newValue") instanceof ArrayList
                        || row.get("newValue") instanceof HashMap ? "[complex value]" : row.get("newValue");
                builder.append("Property '");
                builder.append(row.get("key"));
                builder.append("' was updated. From ");
                builder.append(oldValue);
                builder.append(" to ");
                builder.append(newValue);
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
