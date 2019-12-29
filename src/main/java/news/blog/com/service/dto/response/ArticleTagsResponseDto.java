package news.blog.com.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ArticleTagsResponseDto
{
    private String tag;
    private Long quantity;
}
