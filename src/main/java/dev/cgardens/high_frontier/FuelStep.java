package dev.cgardens.high_frontier;

import java.math.BigDecimal;

public class FuelStep {

  private final String name;
  private final BigDecimal step;
  private FuelStep nextAddFuelStep;
  private FuelStep nextBurnStep;

  public FuelStep(String name) {
    this.name = name;
    this.step = Commons.fractionOf(name);
  }

  public void setNextAddFuelStep(FuelStep nextAddFuelStep) {
    this.nextAddFuelStep = nextAddFuelStep;
  }

  public void setNextBurnStep(FuelStep nextBurnStep) {
    this.nextBurnStep = nextBurnStep;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getStep() {
    return step;
  }

  public FuelStep getNextAddFuelStep() {
    return nextAddFuelStep;
  }

  public FuelStep getNextBurnStep() {
    return nextBurnStep;
  }

  @Override
  public String toString() {
    return "FuelStep{" +
        "name='" + name + '\'' +
        '}';
  }
}
