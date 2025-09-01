package cn.zpoet.blog.service.impl;

import cn.zpoet.blog.entity.Article;
import cn.zpoet.blog.mapper.ArticleMapper;
import cn.zpoet.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public Article deleteArticle(Long id) {
        Article article = getById(id);
        article.setDeleted(1);
        updateById(article);

        return getById(id);
    }
}
