package com.example.demo.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Sql
@ContextConfiguration()
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class IntegrationTest {

  @Autowired
  protected MockMvc mvc;

  protected String toJson(final Object value) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(value);
  }

  protected ResultMatcher jsonEquals(final Object expected) {
    return result -> {
      final String content = result.getResponse().getContentAsString();
      final Object response = new ObjectMapper().readValue(content, expected.getClass());
      Assert.assertEquals(expected, response);
    };
  }

  protected MockHttpServletRequestBuilder get(final String url, final Object... params) {
    return MockMvcRequestBuilders.get(url, params);
  }

  protected MockHttpServletRequestBuilder post(final String url, final Object... params) {
    return MockMvcRequestBuilders.post(url, params);
  }

  protected MockHttpServletRequestBuilder put(final String url, final Object... params) {
    return MockMvcRequestBuilders.put(url, params);
  }

  protected MockHttpServletRequestBuilder delete(final String url, final Object... params) {
    return MockMvcRequestBuilders.delete(url, params);
  }
  
}
