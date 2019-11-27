package news.blog.com.repository;

import news.blog.com.model.ArticleEntity;
import news.blog.com.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>
{
    List<ArticleEntity> findAll();

    List<ArticleEntity> findByTag(String string);

    Optional<ArticleEntity> findById(Long id);

    Page<ArticleEntity> findAll(Pageable pageable);

    Page<ArticleEntity> findArticleEntitiesByUser(UserEntity user, Pageable pageable);
}
