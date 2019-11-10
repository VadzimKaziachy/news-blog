package news.blog.com.repository;

import news.blog.com.model.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>
{
}
