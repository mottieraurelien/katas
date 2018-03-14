package models;

import java.util.Objects;

public class Coordinates {

    static final String TO_STRING_TEMPLATE = "%d - %d";

    private int abscissa;
    private int ordinate;

    public Coordinates(int abscissa, int ordinate) {
        this.abscissa = abscissa;
        this.ordinate = ordinate;
    }

    public int getAbscissa() {
        return abscissa;
    }

    public void setAbscissa(int abscissa) {
        this.abscissa = abscissa;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public void setOrdinate(int ordinate) {
        this.ordinate = ordinate;
    }

    @Override
    public boolean equals(Object coordinates) {

        // Basic checks :
        if (coordinates == null) {
            return false;
        }
        if (this == coordinates) {
            return true;
        }
        if (!(coordinates instanceof Coordinates)) {
            return false;
        }

        // We must compare attributes :
        Coordinates castCoordinates = (Coordinates) coordinates;
        return this.abscissa == castCoordinates.getAbscissa()
                && this.ordinate == castCoordinates.getOrdinate();

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.abscissa, this.ordinate);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_TEMPLATE, this.abscissa, this.ordinate);
    }

}
