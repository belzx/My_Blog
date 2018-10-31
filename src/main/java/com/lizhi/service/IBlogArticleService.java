package com.lizhi.service;


import com.lizhi.bean.BlogArticle;
import com.lizhi.bean.PagerResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IBlogArticleService extends CustomService<BlogArticle, String> {

    PagerResult<Map<String, Object>> selectByDateYM();

    PagerResult selectByJoin(String createTimeByMonth ,String labelparentId,int pageIndex, int pageSize);
}