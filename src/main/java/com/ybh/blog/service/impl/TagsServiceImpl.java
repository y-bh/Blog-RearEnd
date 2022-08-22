package com.ybh.blog.service.impl;

import com.ybh.blog.entity.Tags;
import com.ybh.blog.mapper.TagsMapper;
import com.ybh.blog.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

}
