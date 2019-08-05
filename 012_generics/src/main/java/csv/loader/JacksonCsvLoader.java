package csv.loader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import csv.data.FilesRefStaticMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonCsvLoader<D> implements CsvLoader {

    private static final char CSV_SEPARATOR = ';';

    private final CsvMapper mapper;
    private final CsvSchema schema;
    private final File file;
    private final Class beanClass;

    public JacksonCsvLoader(final FilesRefStaticMapping data) {
        this.mapper = new CsvMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.schema = this.mapper.schemaFor(data.getBeanClass()).withHeader().withColumnSeparator(CSV_SEPARATOR);
        this.file = new File(data.getFilename());
        this.beanClass = data.getBeanClass();
    }

    @Override
    public List<D> load() throws IOException {
        MappingIterator<D> it = mapper
                .readerFor(beanClass)
                .with(schema)
                .readValues(file);
        return it.readAll();
    }

}