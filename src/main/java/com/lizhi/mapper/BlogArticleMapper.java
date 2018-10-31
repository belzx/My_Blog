package com.lizhi.mapper;


import com.lizhi.bean.BlogArticle;
import com.lizhi.dao.CustomMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface BlogArticleMapper extends CustomMapper<BlogArticle,String> {
    List<Map<String,Object>> selectByDateYM();

    List<BlogArticle> selectByJoin(@Param("createTimeByMonth")String createTimeByMonth , @Param("labelparentId") String labelparentId,@Param("pageIndex") int pageIndex,@Param("pageSize") int pageSize);

    int countByJoin(@Param("createTimeByMonth")String createTimeByMonth , @Param("labelparentId") String labelparentId);
}
