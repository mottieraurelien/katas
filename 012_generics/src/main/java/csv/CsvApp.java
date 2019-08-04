package csv;

import csv.domain.OdNoHst;
import csv.loader.JacksonCsvLoader;

import java.io.IOException;
import java.util.List;

public class CsvApp {

    public static void main(String[] args) {

        try {
            JacksonCsvLoader<OdNoHst> loader = new JacksonCsvLoader<>(OdNoHst.class);
            List<OdNoHst> liste = loader.load();
            liste.forEach(System.out::println);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }

}
