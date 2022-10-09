package com.ybh.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybh.blog.DO.ArticleDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybh.blog.DTO.ArticleDTO;
import com.ybh.blog.VO.ArticleVO;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
public interface ArticleService extends IService<ArticleDO> {

    /**
     * @Description: 根据id查询文章
     * @Author: za-yubohan
     **/
    ArticleDTO selectArticleById(String id);

    /**
     * @Description: 分页查询文章
     * @Author: za-yubohan
     **/
    Page<ArticleVO> selectArticlePage(Page<ArticleDTO> articleDTOPage);

    /**
     * @Description: 新增文章
     * @Author: za-yubohan
     **/
    Integer addArticle(ArticleDTO articleDTO);

    /**
     * @Description: 根据id编辑文章
     * @Author: za-yubohan
     **/
    Integer updateArticleById(ArticleDTO articleDTO);

    /**
     * @Description: 删除文章
     * @Author: za-yubohan
     **/
    Integer deleteArticle(String id);

}
