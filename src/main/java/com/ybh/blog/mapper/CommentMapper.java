package com.ybh.blog.mapper;

import com.ybh.blog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
