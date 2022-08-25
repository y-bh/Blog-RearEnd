package com.ybh.blog.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Data
@TableName("blog_user")
@ApiModel(value = "User对象", description = "")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("头像")
    private String avator;

    @ApiModelProperty("简介")
    private String description;

    @ApiModelProperty("是否删除")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreated;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty("创建人")
    private String creater;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("账号")
    private String accountId;

    @ApiModelProperty("密码")
    private String password;


}
