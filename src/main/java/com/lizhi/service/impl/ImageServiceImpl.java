package com.lizhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizhi.bean.TFile;
import com.lizhi.bean.TFileExample;
import com.lizhi.bean.TFileExample.Criteria;
import com.lizhi.mybatis.mapper.TFileMapper;
import com.lizhi.service.IImageService;


@Service
public class ImageServiceImpl implements IImageService{
	
	@Autowired
	private TFileMapper tFileMapper;
	
	public TFile getTFileById(int fileId) {
		TFileExample tFileExample = new TFileExample();
		Criteria createCriteria = tFileExample.createCriteria();
		//设置查询条件
		createCriteria.andFileIdEqualTo(fileId);
		//执行查询
		List<TFile> selectByExample = tFileMapper.selectByExample(tFileExample);
		for(TFile tf : selectByExample) {
			return tf;
		}
		return null;
	}
	/**
	 * 有就是更新 没有就是插入
	 */
	@Override
	public int insert(TFile record) {
		return tFileMapper.insert(record);
	}
	
	@Override
	public int updateByExample(TFile record, TFileExample example) {
		return tFileMapper.updateByExample(record, example);
	}

	public int tfileInsertOrUpdate(TFile record) {
		TFileExample tFileExample = new TFileExample();
		Criteria createCriteria = tFileExample.createCriteria();
		//设置查询条件
		createCriteria.andCreatorUseridEqualTo(record.getCreatorUserid());
		createCriteria.andFileTypeEqualTo(record.getFileType());
		List<TFile> selectByExample = tFileMapper.selectByExample(tFileExample);
		if(selectByExample.size() >= 1) {
			record.setFileId(selectByExample.get(0).getFileId());
			return this.updateByExample(record, tFileExample);
		}
		return this.insert(record);
	}
	
	
	@Override
	public TFile getTFileByCreatorIdAndType(int userId,String fileType) {
		TFileExample tFileExample = new TFileExample();
		Criteria createCriteria = tFileExample.createCriteria();
		//设置查询条件
		createCriteria.andCreatorUseridEqualTo(userId);
		createCriteria.andFileTypeEqualTo(fileType);
		//执行查询
		List<TFile> selectByExample = tFileMapper.selectByExample(tFileExample);
		for(TFile tf : selectByExample) {
			return tf;
		}
		return null;
	}
	
	
}
