package dev.cgardens.high_frontier;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Commons {
  public static BigDecimal fractionOf(String fraction) {
    Preconditions.checkNotNull(fraction);

    final String[] parts1 = fraction.split(" ");
    Preconditions.checkState(parts1.length == 1 || parts1.length == 2);

    BigDecimal wholeNumber = BigDecimal.ZERO;
    final String fractionPart;
    if(parts1.length == 1) {
      fractionPart = parts1[0];
    } else {
      wholeNumber = new BigDecimal(parts1[0]).setScale(4, RoundingMode.HALF_UP);
      fractionPart = parts1[1];
    }

    final String[] parts2 = fractionPart.split("/");
    Preconditions.checkState(parts2.length == 1 || parts2.length == 2);

    // not actually a fraction, whole number.
    final BigDecimal fractionPartAsBigDecimal;
    if(parts2.length == 1) {
      fractionPartAsBigDecimal = new BigDecimal(fraction).setScale(4, RoundingMode.HALF_UP);
      // fraction
    } else {
      fractionPartAsBigDecimal = new BigDecimal( parts2[0]).setScale(4, RoundingMode.HALF_UP).divide(new BigDecimal(parts2[1]), RoundingMode.HALF_UP);
    }

    return wholeNumber.add(fractionPartAsBigDecimal);
  }
}
