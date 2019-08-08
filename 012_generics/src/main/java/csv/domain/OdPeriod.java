package csv.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class OdPeriod {

    private final LocalDate startDate;
    private final LocalDate endDate;

}