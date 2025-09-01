package cn.zpoet.blog.service;

import cn.zpoet.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService extends IService<Article> {
    Article deleteArticle(Long id);
}
