package com.ybh.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybh.blog.DO.ArticleDO;
import com.ybh.blog.DTO.ArticleDTO;
import com.ybh.blog.VO.ArticleVO;
import com.ybh.blog.convert.ArticleConvertMapper;
import com.ybh.blog.manager.ArticleManager;
import com.ybh.blog.mapper.ArticleMapper;
import com.ybh.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    ArticleManager articleManager;
    @Override
    public ArticleDTO selectArticleById(String id) {
        return articleManager.selectArticleById(id);
    }

    @Override
    public Page<ArticleVO> selectArticlePage(Page<ArticleDTO> articleDTOPage) {
        Page<ArticleDO> articleDOPage = ArticleConvertMapper.INSTANCE.articlePageDTOtoArticlePageDO(articleDTOPage);
        Page<ArticleDTO> articleDTOList = articleManager.selectArticlePage(articleDOPage);
        return ArticleConvertMapper.INSTANCE.articlePageDTOtoArticlePageVO(articleDTOList);

    }

    @Override
    public Integer addArticle(ArticleDTO articleDTO) {
        return articleManager.addArticle(articleDTO);
    }

    @Override
    public Integer updateArticleById(ArticleDTO articleDTO) {
        return articleManager.updateArticleById(articleDTO);
    }

    @Override
    public Integer deleteArticle(String id) {
        return articleManager.deleteArticle(id);
    }
}
