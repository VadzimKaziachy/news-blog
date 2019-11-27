package news.blog.com.service.dto;

import lombok.Data;

@Data
public class ArticleDto
{
    private Long id;
    private String tag;
    private String title;
    private String imageName;
    private String fullDescription;
    private String shortDescription;
}
