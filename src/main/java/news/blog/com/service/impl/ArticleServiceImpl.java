package news.blog.com.service.impl;

import news.blog.com.model.ArticleEntity;
import news.blog.com.repository.ArticleRepository;
import news.blog.com.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService
{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Iterable<ArticleEntity> getArticles()
    {
        return articleRepository.findAll();
    }

    @Override
    public ArticleEntity getArticle(Long id)
    {
            return articleRepository.findById(id).get();
    }

    @Override
    public void saveArticle(String tags, String title, String imageName, String fullDescription, String shortDescription)
    {
        articleRepository.save(new ArticleEntity(tags, title, imageName, fullDescription, shortDescription));
    }
}
