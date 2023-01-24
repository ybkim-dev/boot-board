package com.zecongbi.bootboard.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Setter @Column(nullable = false) private String title;

  @Setter @Column(nullable = false, length = 10000) private String content;

  @Setter private String hashtag;

  @OrderBy("id")
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Exclude
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>();




  protected Article() {}

  private Article(String title, String content, String hashtag) {
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Article of(String title, String content, String hashtag) {
    return new Article(title, content, hashtag);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Article article)) {
      return false;
    }
    return id != null && id.equals(article.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
