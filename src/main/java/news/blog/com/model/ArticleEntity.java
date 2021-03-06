package news.blog.com.model;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article", schema = "articles")
public class ArticleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "tag")
    private String tag;
    @Column(name = "title")
    private String title;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "full_description")
    private String fullDescription;
    @Column(name = "short_description")
    private String shortDescription;
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;
}
