package io.heedoitdox.courseapi.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.heedoitdox.courseapi.application.auth.UserServiceImpl;
import io.heedoitdox.courseapi.config.SecurityConfig;
import io.heedoitdox.courseapi.support.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@Import({SecurityConfig.class})
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
  private UserDetailsService userDetailsService;

  @MockBean
  private UserServiceImpl userServiceImpl;

  @MockBean
  private AuthenticationProvider authenticationProvider;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

}
