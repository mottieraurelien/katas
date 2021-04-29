package service;

import java.util.Date;

import static java.time.Instant.now;
import static java.util.Date.from;

public class DateService {

    public Date getCurrentDate() {
        return from(now());
    }

}