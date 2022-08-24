package com.ybh.blog.service.impl;

import com.ybh.blog.DO.ArticleDO;
import com.ybh.blog.mapper.ArticleMapper;
import com.ybh.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {

}
