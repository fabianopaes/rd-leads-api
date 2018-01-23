package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class DateTimeSerializer extends JsonSerializer<DateTime> {
    private static final DateTimeFormatter ISO8601_FORMATTER =
            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z");

    @Override
    public void serialize(DateTime value, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
        generator.writeString(ISO8601_FORMATTER.print(value));
    }
}
