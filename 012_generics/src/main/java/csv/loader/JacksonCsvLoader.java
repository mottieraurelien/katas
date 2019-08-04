package csv.loader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import csv.data.FilesRefStaticMapping;
import csv.domain.OdNoHst;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonCsvLoader {

    public static void main(String[] args) {

        FilesRefStaticMapping fileRefStatic = FilesRefStaticMapping.OD_NO_HST;

        CsvMapper mapper = new CsvMapper();
        mapper.registerModule(new JavaTimeModule());
        CsvSchema schema = mapper.schemaFor(OdNoHst.class)
                .withHeader()
                .withColumnSeparator(';');
        File file = new File(fileRefStatic.getFilename());

        try {

            MappingIterator<OdNoHst> it = mapper
                    .readerFor(OdNoHst.class)
                    .with(schema)
                    .readValues(file);

            List<OdNoHst> liste = it.readAll();

            liste.forEach(OdNoHst::toString);

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }

}
