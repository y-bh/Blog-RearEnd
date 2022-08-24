package com.ybh.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * åˆ†ç±»è¡¨
 * </p>
 *
 * @author Altria
 * @since 2022-08-24
 */
@Getter
@Setter
@TableName("blog_tags")
@ApiModel(value = "Tags对象", description = "åˆ†ç±»è¡¨")
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("æ ‡ç­¾")
    private String tag;

    @ApiModelProperty("æ˜¯å¦åˆ é™¤")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("åˆ›å»ºäºº")
    private String creater;

    @ApiModelProperty("ä¿®æ”¹äºº")
    private LocalDateTime gmtCreated;


}
