package io.heedoitdox.courseapi.ui;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.heedoitdox.courseapi.application.auth.UserService;
import io.heedoitdox.courseapi.config.TestSecurityConfig;
import io.heedoitdox.courseapi.support.security.JwtService;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

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
  void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
    objectMapper = new ObjectMapper();

    TestingAuthenticationToken authentication =
        new TestingAuthenticationToken("user", "password", "ROLE_INSTRUCTOR");
    SecurityContextHolder.getContext().setAuthentication(authentication);

    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(document("{class-name}/{method-name}/",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint())))
        .build();
  }

  protected MultiValueMap<String, String> convertQueryParams(Object dto) {
    var params = new LinkedMultiValueMap<String, String>();
    var map = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
    });
    map.entrySet().stream()
        .filter(entry -> ObjectUtils.isNotEmpty(entry.getValue()))
        .forEach(entry -> {
              var key = entry.getKey();
              var value = entry.getValue();
              if (value instanceof List) {
                ((List<?>) value).forEach(v -> params.add(key, String.valueOf(v)));
                return;
              }
              params.add(key, String.valueOf(value));
            }
        );
    return params;
  }
}
