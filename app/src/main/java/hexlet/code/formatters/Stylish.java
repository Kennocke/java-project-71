package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class   Stylish {
    public static String format(List<Map<String, Object>> diffData) {
        StringBuilder builder = new StringBuilder("{\n");
        for (Map<String, Object> row : diffData) {
            builder.append("    ");
            if (row.get("operation").equals("add")) {
                builder.append("+ ");
                builder.append(row.get("key"));
                builder.append(" : ");
                builder.append(row.get("newValue"));
            } else if (row.get("operation").equals("delete")) {
                builder.append("- ");
                builder.append(row.get("key"));
                builder.append(" : ");
                builder.append(row.get("oldValue"));
            } else if (row.get("operation").equals("update")) {
                builder.append("- ");
                builder.append(row.get("key"));
                builder.append(" : ");
                builder.append(row.get("oldValue"));
                builder.append("\n");

                builder.append("    ");
                builder.append("+ ");
                builder.append(row.get("key"));
                builder.append(" : ");
                builder.append(row.get("newValue"));
            } else {
                builder.append("  ");
                builder.append(row.get("key"));
                builder.append(" : ");
                builder.append(row.get("oldValue"));
            }
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
