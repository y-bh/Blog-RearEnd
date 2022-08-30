package com.ybh.blog.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class JwtUserVO {

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
    private Date gmtCreated;

    @ApiModelProperty("修改时间")
    private Date gmtModified;

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
