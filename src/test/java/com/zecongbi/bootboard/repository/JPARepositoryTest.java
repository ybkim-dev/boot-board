package com.zecongbi.bootboard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.zecongbi.bootboard.config.JpaConfig;
import com.zecongbi.bootboard.domain.Article;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JPARepositoryTest {

  private final ArticleRepository articleRepository;
  private final ArticleCommentRepository articleCommentRepository;

  public JPARepositoryTest(@Autowired ArticleRepository articleRepository,
      @Autowired ArticleCommentRepository articleCommentRepository) {
    this.articleRepository = articleRepository;
    this.articleCommentRepository = articleCommentRepository;
  }

  @DisplayName("select 테스트")
  @Test
  void givenTestData_whenSelecting_thenWorksFine() throws Exception {
    //given

    // when
    List<Article> articles = articleRepository.findAll();
    //then
    assertThat(articles).isNotNull().hasSize(123);
  }

  @DisplayName("insert 테스트")
  @Test
  void givenTestData_whenInserting_thenWorksFine() throws Exception {
    //given
    long prevCount = articleRepository.count();

    // when
    articleRepository.save(Article.of("new article", "new content", "#spring"));

    //then
    assertThat(articleRepository.count()).isEqualTo(prevCount + 1);
  }

  @DisplayName("update 테스트")
  @Test
  void givenTestData_whenUpdating_thenWorksFine() throws Exception {
    //given
    Article article = articleRepository.findById(1L).orElseThrow();
    String updatedHashtag = "#springboot";

    // when
    article.setHashtag(updatedHashtag);
    Article savedArticle = articleRepository.findById(1L).orElseThrow();

    //then
    assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
  }

  @DisplayName("delete 테스트")
  @Test
  void givenTestData_whenDeleting_thenWorksFine() throws Exception {
    //given
    Article article = articleRepository.findById(1L).orElseThrow();
    long previousArticleCount = articleRepository.count();
    long previousArticleCommentCount = articleCommentRepository.count();
    int deletedCommentsSize = article.getArticleComments().size();

    // when
    articleRepository.delete(article);

    //then
    assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
    assertThat(articleCommentRepository.count()).isEqualTo(
        previousArticleCommentCount - deletedCommentsSize);

  }

}