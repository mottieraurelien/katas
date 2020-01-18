package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationHistoryTest {

    private static final OperationType OPERATION_TYPE = OperationType.DEPOSIT;
    private static final String DATE = "2018-01-01";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final BigDecimal BALANCE = BigDecimal.TEN;

    private OperationHistory operationHistory;

    @BeforeEach
    public void beforeEach() {
        this.operationHistory = new OperationHistory();
    }

    @Test
    public void given_an_operation_to_add_then_add_the_operation_to_the_history_and_returns_the_operation()
            throws Exception {

        Operation operation = new Operation(OPERATION_TYPE, DATE_FORMAT.parse(DATE), AMOUNT, BALANCE);

        operationHistory.addOperation(operation);

        assertEquals(operation, operationHistory.getAllOperations().get(0));

    }

}