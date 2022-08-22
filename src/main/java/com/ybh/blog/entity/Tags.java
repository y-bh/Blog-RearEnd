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
 * 
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Getter
@Setter
@TableName("blog_tags")
@ApiModel(value = "Tags对象", description = "")
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("是否删除")
    @TableLogic
    private String isDeleted;

    @ApiModelProperty("创建人")
    private String creater;

    @ApiModelProperty("创建时间")
    private LocalDateTime gmtCreated;


}
