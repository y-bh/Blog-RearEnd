package com.ybh.blog.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Getter
@Setter
@TableName("blog_article")
@ApiModel(value = "Article对象", description = "文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类")
    private String categoryId;

    @ApiModelProperty("标签")
    private Long tagsId;

    @ApiModelProperty("文章内容")
    private Long body;

    @ApiModelProperty("用户账号")
    private String accountId;

    @ApiModelProperty("创建时间")
    private LocalDateTime gmtCreated;

    @ApiModelProperty("修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty("是否删除")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("创建人")
    private String creater;

    @ApiModelProperty("修改人")
    private String modifier;


}
