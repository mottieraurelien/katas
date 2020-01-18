package domain;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class OperationHistory {

    private final List<Operation> operations = new LinkedList<>();

    public void addOperation(final Operation operation) {
        operations.add(operation);
    }

    public List<Operation> getAllOperations() {
        return unmodifiableList(operations);
    }

}