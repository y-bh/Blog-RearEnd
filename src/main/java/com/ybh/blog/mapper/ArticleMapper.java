package com.ybh.blog.mapper;

import com.ybh.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
