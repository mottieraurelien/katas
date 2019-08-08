package csv.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import csv.domain.OdPeriod;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

public class OdPeriodDeserializer extends StdDeserializer<OdPeriod> {

    private static final DateTimeFormatter FORMATTER = ofPattern("dd/MM/yyyy");

    public OdPeriodDeserializer() {
        this(OdPeriod.class);
    }

    private OdPeriodDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public OdPeriod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        String periodStr = jsonParser.readValueAs(String.class);
        String[] periodDates = periodStr.split(">");
        return new OdPeriod(parse(periodDates[0], FORMATTER), parse(periodDates[1], FORMATTER));
    }

}