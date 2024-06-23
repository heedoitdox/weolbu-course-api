package io.heedoitdox.courseapi.support;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringUtilTest {

  @Test
  void 문자열의_콤마를_제거한다() {
    // given
    String value = "550,000";

    // when
    String result = StringUtil.removeCommas(value);

    // then
    assertThat(result).isEqualTo("550000");
  }
}