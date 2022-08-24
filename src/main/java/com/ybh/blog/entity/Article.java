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
 * æ–‡ç« è¡¨
 * </p>
 *
 * @author Altria
 * @since 2022-08-24
 */
@Getter
@Setter
@TableName("blog_article")
@ApiModel(value = "Article对象", description = "æ–‡ç« è¡¨")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("æ ‡é¢˜")
    private String title;

    @ApiModelProperty("åˆ†ç±»id")
    private String categoryId;

    @ApiModelProperty("æ ‡ç­¾id")
    private Long tagsId;

    @ApiModelProperty("æ–‡ç« å†…å®¹")
    private Long body;

    @ApiModelProperty("è´¦å·")
    private String accountId;

    @ApiModelProperty("åˆ›å»ºæ—¶é—´")
    private LocalDateTime gmtCreated;

    @ApiModelProperty("ä¿®æ”¹æ—¶é—´")
    private LocalDateTime gmtModified;

    @ApiModelProperty("æ˜¯å¦åˆ é™¤")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("åˆ›å»ºäºº")
    private String creater;

    @ApiModelProperty("ä¿®æ”¹äºº")
    private String modifier;


}
