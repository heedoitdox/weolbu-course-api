package io.heedoitdox.courseapi.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class CryptoStringConverter implements AttributeConverter<String, String> {

  private final String ALGORITHM = "AES/ECB/PKCS5Padding";
  private final String KEY = "IDNvMy1lbmNyeXB0aW9ucw==";

  @Override
  public String convertToDatabaseColumn(String attribute) {
    SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
    try {
      Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encrypted = cipher.doFinal(attribute.getBytes("UTF-8"));
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
    try {
      Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);

      byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(dbData));
      return new String(bytes, StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new IllegalStateException(e);
    }
  }
}