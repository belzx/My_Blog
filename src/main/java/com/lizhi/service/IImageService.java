package com.lizhi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizhi.bean.TFile;
import com.lizhi.bean.TFileExample;
import com.lizhi.bean.TFileExample.Criteria;
import com.lizhi.mybatis.mapper.TFileMapper;


public interface IImageService {
	
	public TFile getTFileById(int fileId) ;
	
	public int insert(TFile record) ;
	
	public int tfileInsertOrUpdate(TFile record) ;
	
	public TFile getTFileByCreatorIdAndType(int userId,String fileType);
	
	public int updateByExample(TFile record,TFileExample example);
}
