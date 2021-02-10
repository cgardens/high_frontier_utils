package dev.cgardens.high_frontier;

public class Result {

  private final int maxFuelSteps;
  private final int maxBurns;
  private final int thrustModifier;

  public Result(int maxFuelSteps, int maxBurns, int thrustModifier) {
    this.maxFuelSteps = maxFuelSteps;
    this.maxBurns = maxBurns;
    this.thrustModifier = thrustModifier;
  }

  public int getMaxBurns() {
    return maxBurns;
  }

  public int getThrustModifier() {
    return thrustModifier;
  }

  public int getMaxFuelSteps() {
    return maxFuelSteps;
  }

  @Override
  public String toString() {
    return "Result{" +
        "maxFuelSteps=" + maxFuelSteps +
        ", maxBurns=" + maxBurns +
        ", thrustModifier=" + thrustModifier +
        '}';
  }
}
