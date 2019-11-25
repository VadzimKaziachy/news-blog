package news.blog.com.repository;

import news.blog.com.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long>
{
    UserEntity findByLogin(String login);

    boolean existsByLogin(String login);

    List<UserEntity> findAll();
}
