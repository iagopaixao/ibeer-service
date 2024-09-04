package com.ipaixao.ibeer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = IBeerApplication.class)
class IBeerApplicationTests {

  @Test
  @DisplayName("should load the application context successfully when the app starts")
  void shouldLoadsContextSuccessfully_whenAppStarts(ApplicationContext context) {
      IBeerApplication.main(new String[]{});

      assertThat(context).isNotNull();
  }
}
