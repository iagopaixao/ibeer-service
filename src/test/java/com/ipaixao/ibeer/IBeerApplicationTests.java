package com.ipaixao.ibeer;

import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IBeerApplicationTests {

  @Test
  @DisplayName("should check if application context loaded successfully")
  void shouldVerifycontextLoads(ApplicationContext appContext) {

    assertThat(appContext).isNotNull();
  }
}
