package io.heedoitdox.courseapi.support;

public final class StringUtil {

  public static String removeCommas(String value) {
    if(value == null) return null;

    return value.replaceAll(",", "");
  }
}
