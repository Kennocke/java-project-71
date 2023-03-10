package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> diff, String mode) {
        switch (mode) {
            case "stylish":
                return Stylish.format(diff);
            case "plain":
                return Plain.format(diff);
            default:
                throw new RuntimeException("Format not supported");
        }
    }

}
