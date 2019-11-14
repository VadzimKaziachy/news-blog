package news.blog.com.model.dto;

import lombok.Data;

@Data
public class ArticleDto
{
    private Long id;
    private String tags;
    private String title;
    private String imageName;
    private String fullDescription;
    private String shortDescription;
}
