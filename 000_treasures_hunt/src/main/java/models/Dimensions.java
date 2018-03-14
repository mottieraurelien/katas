package models;

public class Dimensions {

  private Coordinates maxCoordinates;

  public Dimensions(Coordinates maxCoordinates) {
    this.maxCoordinates = maxCoordinates;
  }

  public Coordinates getMaxCoordinates() {
    return maxCoordinates;
  }

  public void setMaxCoordinates(Coordinates maxCoordinates) {
    this.maxCoordinates = maxCoordinates;
  }

}
