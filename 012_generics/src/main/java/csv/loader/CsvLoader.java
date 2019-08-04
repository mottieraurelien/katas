package csv.loader;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
interface CsvLoader<D> {

    List<D> load() throws IOException;

}