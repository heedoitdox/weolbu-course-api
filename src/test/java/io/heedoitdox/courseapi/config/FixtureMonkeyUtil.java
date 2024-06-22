package io.heedoitdox.courseapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.plugin.JacksonPlugin;

public class FixtureMonkeyUtil {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
      .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
      .defaultNotNull(true)
      .plugin(new JacksonPlugin(objectMapper))
      .build();

  public static FixtureMonkey monkey() {
    return fixtureMonkey;
  }

  private FixtureMonkeyUtil() {

  }
}