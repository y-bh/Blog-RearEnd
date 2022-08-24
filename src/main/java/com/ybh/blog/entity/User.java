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
 * ç”¨æˆ·ä¿¡æ¯è¡¨
 * </p>
 *
 * @author Altria
 * @since 2022-08-24
 */
@Getter
@Setter
@TableName("blog_user")
@ApiModel(value = "User对象", description = "ç”¨æˆ·ä¿¡æ¯è¡¨")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("å§“å")
    private String name;

    @ApiModelProperty("æ€§åˆ«")
    private String sex;

    @ApiModelProperty("å¤´åƒ")
    private String avator;

    @ApiModelProperty("æè¿°")
    private String description;

    @ApiModelProperty("æ˜¯å¦åˆ é™¤")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("åˆ›å»ºæ—¶é—´")
    private LocalDateTime gmtCreated;

    @ApiModelProperty("ä¿®æ”¹æ—¶é—´")
    private LocalDateTime gmtModified;

    @ApiModelProperty("åˆ›å»ºäºº")
    private String creater;

    @ApiModelProperty("ä¿®æ”¹äºº")
    private String modifier;

    @ApiModelProperty("æ‰‹æœºå·")
    private String phone;

    @ApiModelProperty("è´¦å·")
    private String accountId;


}
