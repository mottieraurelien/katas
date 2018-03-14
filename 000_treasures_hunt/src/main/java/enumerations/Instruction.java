package enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Instruction {

    /**
     * Move (forward).
     */
    A("M"),

    /**
     * Move (backward).
     */
    R("M"),

    /**
     * Orientate to right side.
     */
    D("O"),

    /**
     * Orientate to left side.
     */
    G("O");

    /**
     * Type of the instruction : move instruction ("M") or orientate instrcution ("O").
     */
    private String typeOfInstruction;

    Instruction(String typeOfInstruction) {
        this.typeOfInstruction = typeOfInstruction;
    }

    /**
     * Provides the list of orientate instructions.
     *
     * @return The list of orientate instructions.
     */
    public static List<Instruction> getOrientateInstruction() {
        return Arrays.stream(values()).filter(i -> "O".equals(i.getTypeOfInstruction()))
                .collect(Collectors.toList());
    }

    /**
     * Provides the list of move instructions.
     *
     * @return The list of move instructions.
     */
    public static List<Instruction> getMoveInstruction() {
        return Arrays.stream(values()).filter(i -> "M".equals(i.getTypeOfInstruction()))
                .collect(Collectors.toList());
    }

    public String getTypeOfInstruction() {
        return typeOfInstruction;
    }

}
