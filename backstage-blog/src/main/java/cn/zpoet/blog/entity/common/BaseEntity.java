package cn.zpoet.blog.entity.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity implements java.io.Serializable{
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdatedTime;

    /**
     * 删除状态
     */
    private Integer deleted;
}
