package com.zecongbi.bootboard.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("Data REST - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

  private final MockMvc mvc;

  public DataRestTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[api] - 게시글 리스트 조회")
  @Test
  void given_whenRequestingArticles_thenReturnArticlesJsonResponse() throws Exception {
    //given

    // when
    mvc.perform(get("/api/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    //then
  }

  @DisplayName("[api] - 게시글 단건 조회")
  @Test
  void given_whenRequestingArticle_thenReturnArticleJsonResponse() throws Exception {
    //given

    // when
    mvc.perform(get("/api/articles/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    //then
  }

  @DisplayName("[api] - 게시글 -> 댓글 리스트 조회")
  @Test
  void given_whenRequestingArticle_thenReturnArticleCommentsJsonResponse() throws Exception {
    //given

    // when
    mvc.perform(get("/api/articles/1/articleComments"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    //then
  }

  @DisplayName("[api] - 댓글 리스트 조회")
  @Test
  void given_whenRequestingArticleComments_thenReturnArticleCommentsJsonResponse() throws Exception {
    //given

    // when
    mvc.perform(get("/api/articleComments"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    //then
  }

  @DisplayName("[api] - 댓글 단건 조회")
  @Test
  void given_whenRequestingArticleComment_thenReturnArticleCommentJsonResponse() throws Exception {
    //given

    // when
    mvc.perform(get("/api/articleComments/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    //then
  }
}
