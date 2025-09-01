package cn.zpoet.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@TableName("articles")
public class Article {
    @TableId
    /**
     * 文章id
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdatedTime;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String content;

    /**
     * 删除状态
     */
    private Integer deleted;
}
