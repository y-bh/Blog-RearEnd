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
 * è¯„è®ºè¡¨
 * </p>
 *
 * @author Altria
 * @since 2022-08-24
 */
@Getter
@Setter
@TableName("blog_comment")
@ApiModel(value = "Comment对象", description = "è¯„è®ºè¡¨")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("çˆ¶è¯„è®ºid")
    private String parentcommentId;

    @ApiModelProperty("å­è¯„è®ºid")
    private String subComment;

    @ApiModelProperty("æ˜¯å¦åˆ é™¤")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("åˆ›å»ºäºº")
    private String creater;

    @ApiModelProperty("ä¿®æ”¹äºº")
    private String modifier;

    @ApiModelProperty("åˆ›å»ºæ—¶é—´")
    private LocalDateTime gmtCreated;


}
