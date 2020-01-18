package domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {

    private static final String DATE = "2018-01-01";
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final BigDecimal BALANCE = BigDecimal.TEN;
    private static final OperationType OPERATION_TYPE = OperationType.DEPOSIT;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void given_an_operation_then_returns_its_properties_through_its_getters() throws Exception {

        Date date = DATE_FORMAT.parse(DATE);

        Operation operation = new Operation(OPERATION_TYPE, date, AMOUNT, BALANCE);

        assertEquals(OPERATION_TYPE, operation.getOperationType());
        assertEquals(date, operation.getDate());
        assertEquals(AMOUNT, operation.getAmount());
        assertEquals(BALANCE, operation.getBalance());

    }

}