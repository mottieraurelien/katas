package service;

import domain.Operation;
import domain.OperationHistory;
import domain.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.PrintService.LINE_SEPARATOR;
import static service.PrintService.OPERATION_FORMAT;

public class PrintServiceTest {

    private static final String DATE = "2018-01-01";
    private static final BigDecimal AMOUNT = BigDecimal.ONE;
    private static final BigDecimal BALANCE = BigDecimal.TEN;
    private static final OperationType OPERATION_TYPE = OperationType.DEPOSIT;
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    private PrintService printService;

    @BeforeEach
    public void beforeEach() {
        printService = new PrintService();
    }

    @Test
    public void given_an_operation_history_then_returns_all_formatted_operations() throws Exception {

        final OperationHistory operationHistory = createBankAccountWithFewOperations();
        final String expectedPrintedStatement = formalizeExpectedPrintedStatement(operationHistory);

        String actualPrintedStatement = printService.getPrintableStatement(operationHistory);

        assertEquals(expectedPrintedStatement, actualPrintedStatement);

    }

    private OperationHistory createBankAccountWithFewOperations() throws Exception {
        Date date = DATE_FORMAT.parse(DATE);
        Operation firstOperation = new Operation(OPERATION_TYPE, date, AMOUNT, BALANCE);
        Operation secondOperation = new Operation(OPERATION_TYPE, date, AMOUNT, BALANCE);
        OperationHistory operationHistory = new OperationHistory();
        operationHistory.addOperation(firstOperation);
        operationHistory.addOperation(secondOperation);
        return operationHistory;
    }

    private String formalizeExpectedPrintedStatement(final OperationHistory operationHistory) {
        String expected = String.format(OPERATION_FORMAT, OPERATION_TYPE, DATE, AMOUNT, BALANCE) + LINE_SEPARATOR;
        expected += String.format(OPERATION_FORMAT, OPERATION_TYPE, DATE, AMOUNT, BALANCE) + LINE_SEPARATOR;
        return expected;
    }

}