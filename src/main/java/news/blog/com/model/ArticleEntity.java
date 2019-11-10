package news.blog.com.model;

import javax.persistence.*;

@Entity
@Table(name = "article", schema = "public")
public class ArticleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tags;
    private String title;
    private String imageName;
    private String fullDescription;
    private String shortDescription;

    public ArticleEntity(){}

    public ArticleEntity(String tags, String title, String imageName, String fullDescription, String shortDescription)
    {
        this.tags = tags;
        this.title = title;
        this.imageName = imageName;
        this.fullDescription = fullDescription;
        this.shortDescription = shortDescription;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public void setFullDescription(String fullDescription)
    {
        this.fullDescription = fullDescription;
    }

    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getTags()
    {
        return this.tags;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getImageName()
    {
        return this.imageName;
    }

    public String getFullDescription()
    {
        return this.fullDescription;
    }

    public String getShortDescription()
    {
        return this.shortDescription;
    }
}
