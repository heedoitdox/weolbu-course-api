package io.heedoitdox.courseapi.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.heedoitdox.courseapi.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@Import({SecurityConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  protected ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

}
