package com.lizhi.service.impl;

import javax.annotation.Resource;

import com.lizhi.bean.BlogArticle;
import com.lizhi.bean.PagerResult;
import com.lizhi.dao.CustomMapper;
import com.lizhi.mapper.BlogArticleMapper;
import org.springframework.stereotype.Service;
import com.lizhi.service.IBlogArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogArticleServiceImpl implements IBlogArticleService {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Override
    public CustomMapper<BlogArticle, String> getMapper() {
        return blogArticleMapper;
    }


    @Override
    public PagerResult<Map<String, Object>> selectByDateYM() {
        PagerResult<Map<String, Object>> result = new PagerResult<>();
        List<Map<String, Object>> stringObjectMap = blogArticleMapper.selectByDateYM();
        result.setData(stringObjectMap);
        result.setTotal(stringObjectMap.size());
        return result;
    }

    @Override
    public PagerResult selectByJoin(String createTimeByMonth, String labelparentId, int pageIndex, int pageSize) {
        PagerResult result = new PagerResult<>();
        int i = blogArticleMapper.countByJoin(createTimeByMonth, labelparentId);
        if (i == 0) {
            result.setTotal(0);
            result.setData(new ArrayList());
        } else {
            result.setTotal(i);
            result.setData(blogArticleMapper.selectByJoin(createTimeByMonth, labelparentId, pageIndex * pageSize, pageSize));
        }
        return result;
    }

}