package com.lizhi.mapper;



import com.lizhi.dao.CustomMapper;
import org.apache.ibatis.annotations.Mapper;

import com.lizhi.bean.TFile;
@Mapper
public interface TFileMapper extends CustomMapper<TFile,String> {

}