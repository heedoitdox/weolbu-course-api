package io.heedoitdox.courseapi.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.heedoitdox.courseapi.application.auth.UserService;
import io.heedoitdox.courseapi.config.TestSecurityConfig;
import io.heedoitdox.courseapi.support.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@Import({TestSecurityConfig.class})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("test")
public abstract class AbstractControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  protected ObjectMapper objectMapper;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private UserService userService;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

}
