package com.zecongbi.bootboard.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @ManyToOne(optional = false) private Article article;
  @Setter @Column(nullable = false, length = 500) private String content;

  @CreatedDate
  @Column(nullable = false) private LocalDateTime createdAt;
  @CreatedBy
  @Column(nullable = false, length = 100) private String createdBy;
  @LastModifiedDate
  @Column(nullable = false) private LocalDateTime modifiedAt;
  @LastModifiedBy
  @Column(nullable = false, length = 100) private String modifiedBy;

  protected ArticleComment() {}

  private ArticleComment(Article article, String content) {
    this.article = article;
    this.content = content;
  }

  public static ArticleComment of(Article article, String content) {
    return new ArticleComment(article, content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArticleComment that)) {
      return false;
    }
    return id != null && id.equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
