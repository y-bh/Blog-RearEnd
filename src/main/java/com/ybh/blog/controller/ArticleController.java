package com.ybh.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybh.blog.DTO.ArticleDTO;
import com.ybh.blog.Enum.PlatformCodeEnum;
import com.ybh.blog.VO.ArticleVO;
import com.ybh.blog.VO.Result;
import com.ybh.blog.convert.ArticleConvertMapper;
import com.ybh.blog.exception.BaseException;
import com.ybh.blog.service.ArticleService;
import com.ybh.blog.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {
    @Resource
    JwtUtil jwtUtil;
    @Resource
    ArticleService articleService;
    /**
     * @Description: 根据文章id获取文章内容
     * @Author: za-yubohan
     **/
    @PostMapping("/getArticleInfo")
    public Result<ArticleVO> getArticleInfo(@RequestParam String id){
        if (id==null){
            throw new BaseException("id为空");
        }
        ArticleDTO articleDTO = articleService.selectArticleById(id);
        ArticleVO articleVO = ArticleConvertMapper.INSTANCE.ArticleDTOtoArticleVO(articleDTO);
        return Result.ok(articleVO);
    }

    /**
     * @Description: 分页查询文章列表
     * @Author: za-yubohan
     **/
    @PostMapping("/getPageArticleInfo")
    public Result<Page<ArticleVO>> getPageArticleInfo(@RequestParam Integer pageNum,Integer pageSize){
        Page<ArticleDTO> articleDTOPage = new Page<>(pageNum,pageSize);
        return Result.ok(articleService.selectArticlePage(articleDTOPage));
    }

    /**
     * @Description: 新增文章
     * @Author: za-yubohan
     **/
    @PostMapping("/addArticle")
    public Result<Void> addArticle(@RequestBody ArticleVO articleVO){
        ArticleDTO articleDTO = ArticleConvertMapper.INSTANCE.articleVOtoArticleDTO(articleVO);
        Integer result = articleService.addArticle(articleDTO);
        if (result!=0){
            return Result.ok();
        }else {
            return Result.error(PlatformCodeEnum.SAVE_ERROR);
        }
    }

    /**
     * @Description: 编辑文章
     * @Author: za-yubohan
     **/
    @PostMapping("/updateArticle")
    public Result<Void> updateArticle(@RequestBody ArticleVO articleVO){
        ArticleDTO articleDTO = ArticleConvertMapper.INSTANCE.articleVOtoArticleDTO(articleVO);
        Integer result = articleService.updateArticleById(articleDTO);
        if (result!=0){
            return Result.ok();
        }else {
            return Result.error(PlatformCodeEnum.SAVE_ERROR);
        }
    }

    /**
     * @Description: 删除文章
     * @Author: za-yubohan
     **/
    @PostMapping("/deleteArticle")
    public Result<Void> deleteArticle(@RequestParam String id){
        Integer result = articleService.deleteArticle(id);
        if (result!=0){
            return Result.ok();
        }else {
            return Result.error(PlatformCodeEnum.DELETE_ERROR);
        }
    }

}
