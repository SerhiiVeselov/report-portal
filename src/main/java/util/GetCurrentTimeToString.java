package util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GetCurrentTimeToString {

    public String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
