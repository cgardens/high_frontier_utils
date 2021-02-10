package dev.cgardens.high_frontier;

public class FuelStepTuple {

  private final String name;
  private final String nextAddFuelStep;
  private final String nextBurnStep;

  public FuelStepTuple(String name, String nextAddStep, String nextBurnStep) {
    this.name = name;
    this.nextAddFuelStep = nextAddStep;
    this.nextBurnStep = nextBurnStep;
  }

  public String getName() {
    return name;
  }

  public String getNextAddFuelStep() {
    return nextAddFuelStep;
  }

  public String getNextBurnStep() {
    return nextBurnStep;
  }
}
