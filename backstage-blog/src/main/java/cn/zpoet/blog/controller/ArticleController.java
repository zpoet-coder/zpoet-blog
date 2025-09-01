package cn.zpoet.blog.controller;

import cn.zpoet.blog.entity.Article;
import cn.zpoet.blog.entity.common.Result;
import cn.zpoet.blog.service.ArticleService;
import cn.zpoet.blog.util.SnowflakeIdGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    @PostMapping("/add")
    public Result<Article> addArticle(Article article) {
        article.setId(snowflakeIdGenerator.nextId());
        article.setCreatedTime(LocalDateTime.now());
        article.setLastUpdatedTime(LocalDateTime.now());
        articleService.save(article);
        return Result.success(article);
    }

    @GetMapping("/{id}")
    public Result<Article> getArticleById(@PathVariable Long id) {
        return Result.success(articleService.getById(id));
    }

    @GetMapping
    public Result<Page<Article>> getAtriclePage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<Article> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreatedTime);
        return Result.success(articleService.page(pageInfo, queryWrapper));
    }

    @PutMapping("/{id}")
    public Result<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id);
        article.setLastUpdatedTime(LocalDateTime.now());
        articleService.updateById(article);
        article = articleService.getById(id);
        return Result.success(article);
    }

    @DeleteMapping("/{id}")
    public Result<Article> deleteArticle(@PathVariable Long id) {
        return Result.success(articleService.deleteArticle(id));
    }
}
