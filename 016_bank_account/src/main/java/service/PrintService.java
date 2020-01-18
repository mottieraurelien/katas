package service;

import domain.OperationHistory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static java.lang.String.format;

public class PrintService {

    static final String OPERATION_FORMAT = "Operation = %s, date = %s, amount = %s and balance = %s.";
    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public String getPrintableStatement(final OperationHistory operationHistory) {
        StringBuilder statement = new StringBuilder();

        operationHistory.getAllOperations().forEach(t -> {
                    String operation = format(OPERATION_FORMAT, t.getOperationType(), DATE_FORMAT.format(t.getDate()),
                            t.getAmount(), t.getBalance());

                    statement.append(operation).append(LINE_SEPARATOR);
                }
        );

        return statement.toString();
    }

}