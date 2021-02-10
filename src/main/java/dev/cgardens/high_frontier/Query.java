package dev.cgardens.high_frontier;

public class Query {

  private final String dryMass;
  private final String wetMass;
  private final String fuelPerBurn;

  public Query(String dryMass, String wetMass, String fuelPerBurn) {
    this.dryMass = dryMass;
    this.wetMass = wetMass;
    this.fuelPerBurn = fuelPerBurn;
  }

  public String getDryMass() {
    return dryMass;
  }

  public String getWetMass() {
    return wetMass;
  }

  public String getFuelPerBurn() {
    return fuelPerBurn;
  }

  @Override
  public String toString() {
    return "Query{" +
        "dryMass='" + dryMass + '\'' +
        ", wetMass='" + wetMass + '\'' +
        ", fuelPerBurn='" + fuelPerBurn + '\'' +
        '}';
  }
}
