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
 * åˆ†ç±»è¡¨
 * </p>
 *
 * @author Altria
 * @since 2022-08-24
 */
@Getter
@Setter
@TableName("blog_categories")
@ApiModel(value = "Categories对象", description = "åˆ†ç±»è¡¨")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("ä¸Šçº§åˆ†ç±»")
    private String categoryParent;

    @ApiModelProperty("åˆ†ç±»")
    private String category;

    @ApiModelProperty("åˆ›å»ºäºº")
    private String creater;

    @ApiModelProperty("ä¿®æ”¹äºº")
    private String modifier;

    @ApiModelProperty("åˆ›å»ºæ—¶é—´")
    private LocalDateTime gmtCreated;

    @ApiModelProperty("ä¿®æ”¹æ—¶é—´")
    private LocalDateTime gmtModified;

    @ApiModelProperty("æ˜¯å¦åˆ é™¤")
    @TableLogic
    private String isDeleted;


}
