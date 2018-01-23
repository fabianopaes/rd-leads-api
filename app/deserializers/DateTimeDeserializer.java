package deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class DateTimeDeserializer  extends JsonDeserializer<DateTime> {

    private static final DateTimeFormatter ISO8601_FORMATTER = ISODateTimeFormat.dateTime().withZoneUTC();

    private static final DateTimeFormatter ISO8601_SINGLE_FORMATTER = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String date = jsonParser.getText();

        if (date != null && !date.isEmpty()) {
            try {
                return ISO8601_FORMATTER.parseDateTime(date);
            } catch (Exception e) {
                return ISO8601_SINGLE_FORMATTER.parseDateTime(date);
            }
        }
        return null;
    }
}
