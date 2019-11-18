package news.blog.com.model;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

import static news.blog.com.util.Constants.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article", schema = "public")
public class ArticleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ARTICLE_ID, nullable = false)
    private Long id;
    @Column(name = ARTICLE_TAGS)
    private String tags;
    @Column(name = ARTICLE_TITLE)
    private String title;
    @Column(name = ARTICLE_IMAGE_NAME)
    private String imageName;
    @Column(name = ARTICLE_FULL_DESCRIPTION)
    private String fullDescription;
    @Column(name = ARTICLE_SHORT_DESCRIPTION)
    private String shortDescription;
}
