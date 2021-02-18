package dev.cgardens.high_frontier;

import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuelStepCalculator {

  private static final List<FuelStepTuple> FUEL_STEPS = ImmutableList.<FuelStepTuple>builder()
      .add(new FuelStepTuple("1", "2", null))
      .add(new FuelStepTuple("1 8/9", "2 5/6", "1 7/9"))
      .add(new FuelStepTuple("1 7/9", "2 5/6", "1 2/3"))
      .add(new FuelStepTuple("1 2/3", "2 2/3", "1 5/9"))
      .add(new FuelStepTuple("1 5/9", "2 1/2", "1 4/9"))
      .add(new FuelStepTuple("1 4/9", "2 1/2", "1 1/3"))
      .add(new FuelStepTuple("1 1/3", "2 1/3", "1 2/9"))
      .add(new FuelStepTuple("1 2/9", "2 1/6", "1 1/9"))
      .add(new FuelStepTuple("1 1/9", "2 1/6", "1"))
      .add(new FuelStepTuple("2", "3", "1 8/9"))
      .add(new FuelStepTuple("2 5/6", "3 3/4", "2 2/3"))
      .add(new FuelStepTuple("2 2/3", "3 3/4", "2 1/2"))
      .add(new FuelStepTuple("2 1/2", "3 1/2", "2 1/3"))
      .add(new FuelStepTuple("2 1/3", "3 1/4", "2 1/6"))
      .add(new FuelStepTuple("2 1/6", "3 1/4", "2"))
      .add(new FuelStepTuple("3", "4", "2 5/6"))
      .add(new FuelStepTuple("3 3/4", "4 2/3", "3 1/2"))
      .add(new FuelStepTuple("3 1/2", "4 2/3", "3 1/4"))
      .add(new FuelStepTuple("3 1/4", "4 1/3", "3"))
      .add(new FuelStepTuple("4", "5", "3 3/4"))
      .add(new FuelStepTuple("4 2/3", "5 2/3", "4 1/3"))
      .add(new FuelStepTuple("4 1/3", "5 1/3", "4"))
      .add(new FuelStepTuple("5", "6", "4 2/3"))
      .add(new FuelStepTuple("5 2/3", "6 1/3", "5 1/3"))
      .add(new FuelStepTuple("5 1/3", "6", "5"))
      .add(new FuelStepTuple("6", "7", "5 2/3"))
      .add(new FuelStepTuple("6 1/2", "7 1/2", "6"))
      .add(new FuelStepTuple("7", "8", "6 1/2"))
      .add(new FuelStepTuple("7 1/2", "8 1/2", "7"))
      .add(new FuelStepTuple("8", "9", "7 1/2"))
      .add(new FuelStepTuple("8 1/2", "9 1/2", "8"))
      .add(new FuelStepTuple("9", "10", "8 1/2"))
      .add(new FuelStepTuple("9 1/2", "10 1/2", "9"))
      .add(new FuelStepTuple("10", "11", "9 1/2"))
      .add(new FuelStepTuple("10 1/2", "11", "10"))
      .add(new FuelStepTuple("11", "12", "10 1/2"))
      .add(new FuelStepTuple("12", "13", "11"))
      .add(new FuelStepTuple("13", "14", "12"))
      .add(new FuelStepTuple("14", "15", "13"))
      .add(new FuelStepTuple("15", "16", "14"))
      .add(new FuelStepTuple("16", "17", "15"))
      .add(new FuelStepTuple("17", "18", "16"))
      .add(new FuelStepTuple("18", "19", "17"))
      .add(new FuelStepTuple("19", "20", "18"))
      .add(new FuelStepTuple("20", "21", "19"))
      .add(new FuelStepTuple("21", "22", "20"))
      .add(new FuelStepTuple("22", "23", "21"))
      .add(new FuelStepTuple("23", "24", "22"))
      .add(new FuelStepTuple("24", "25", "23"))
      .add(new FuelStepTuple("25", "26", "23"))
      .add(new FuelStepTuple("26", "27", "24"))
      .add(new FuelStepTuple("27", "28", "25"))
      .add(new FuelStepTuple("28", "29", "26"))
      .add(new FuelStepTuple("29", "30", "27"))
      .add(new FuelStepTuple("30", "31", "28"))
      .add(new FuelStepTuple("31", "32", "29"))
      .add(new FuelStepTuple("32", null, "30"))
      .build();

  private static final FuelStep ROOT = constructGraph();

  private static FuelStep constructGraph() {
    final Map<String, FuelStep> nameToFuelStep = new HashMap<>();
    for (FuelStepTuple fuelStepTuple : FUEL_STEPS) {
      nameToFuelStep.put(fuelStepTuple.getName(), new FuelStep(fuelStepTuple.getName()));
    }

    for (FuelStepTuple fuelStepTuple : FUEL_STEPS) {
      final FuelStep fuelStep = nameToFuelStep.get(fuelStepTuple.getName());
      final FuelStep nextAddFuelStep = nameToFuelStep.get(fuelStepTuple.getNextAddFuelStep());
      fuelStep.setNextAddFuelStep(nextAddFuelStep);
      final FuelStep nextBurnStep = nameToFuelStep.get(fuelStepTuple.getNextBurnStep());
      fuelStep.setNextBurnStep(nextBurnStep);
    }

    return nameToFuelStep.get(FUEL_STEPS.get(0).getName());
  }


  public FuelStepCalculator() {
  }

  public Result calculate(Query query) {
    FuelStep dryMassStep = ROOT;
    while (!dryMassStep.getName().equals(query.getDryMass())) {
      dryMassStep = dryMassStep.getNextAddFuelStep();
    }

    FuelStep wetMassStep = dryMassStep;
    while (!wetMassStep.getName().equals(query.getWetMass())) {
      wetMassStep = wetMassStep.getNextAddFuelStep();
    }

    int count = 0;
    FuelStep currentStep = wetMassStep;
    while (!currentStep.getName().equals(dryMassStep.getName())) {
      currentStep = currentStep.getNextBurnStep();
//      System.out.println("currentStep = " + currentStep);
      count += 1;
    }

    final int thrustModifier;
    if (wetMassStep.getStep().intValue() < 2) {
      thrustModifier = 2;
    } else if (wetMassStep.getStep().intValue() < 5) {
      thrustModifier = 1;
    } else if (wetMassStep.getStep().intValue() < 9) {
      thrustModifier = 0;
    } else if (wetMassStep.getStep().intValue() < 17) {
      thrustModifier = -1;
    } else {
      thrustModifier = -2;
    }

    return new Result(count, Long.valueOf(Math.round(Math.floor(count / Commons.fractionOf(query.getFuelPerBurn()).doubleValue()))).intValue(),
        thrustModifier);
  }

  public static void main(String[] args) {
    // todo (cgardens) - parse args via cli prompt or yaml
    final FuelStepCalculator calculator = new FuelStepCalculator();
    printQueryAndResult(calculator, new Query("1", "2", "1"));
    printQueryAndResult(calculator, new Query("23", "32", "1"));
    printQueryAndResult(calculator, new Query("5", "32", "2"));
    printQueryAndResult(calculator, new Query("9", "32", "1"));
    printQueryAndResult(calculator, new Query("9", "12", "1"));
    printQueryAndResult(calculator, new Query("9", "12", "2"));
    printQueryAndResult(calculator, new Query("9", "12", "1/2"));
  }

  private static void printQueryAndResult(FuelStepCalculator calculator, Query query) {
    System.out.printf("query: %s. result: %s%n", query, calculator.calculate(query));
  }


}
