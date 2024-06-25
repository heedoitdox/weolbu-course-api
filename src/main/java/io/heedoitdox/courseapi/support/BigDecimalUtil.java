package io.heedoitdox.courseapi.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public final class BigDecimalUtil {
  private static DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

  public static String formatWithCommas(BigDecimal value) {
    if (value == null) {
      throw new IllegalArgumentException("Input value must not be null");
    }

    return MONEY_FORMAT.format(value);
  }

  public static BigDecimal RoundToTwoDecimalPlace(BigDecimal value){
    if (value == null) {
      throw new IllegalArgumentException("Input value must not be null");
    }
    return value.setScale(2, RoundingMode.HALF_UP);
  }

}
