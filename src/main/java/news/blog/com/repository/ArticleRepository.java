package news.blog.com.repository;

import news.blog.com.model.ArticleEntity;
import news.blog.com.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>
{
    Optional<ArticleEntity> findById(Long id);

    List<ArticleEntity> findAll();

    List<ArticleEntity> findArticleEntitiesByUser(UserEntity user);
}
