package com.lizhi.service.impl;

import com.lizhi.dao.CustomMapper;
import com.lizhi.service.ITFileService;
import org.springframework.stereotype.Service;

import com.lizhi.bean.TFile;
import com.lizhi.mapper.TFileMapper;

import javax.annotation.Resource;


@Service
public class TFileServiceImpl implements ITFileService {

	@Resource
	private TFileMapper tFileMapper;

	@Override
	public CustomMapper<TFile, String> getMapper() {
		return tFileMapper;
	}
//
//	public TFile getTFileById(int fileId) {
//		TFileExample tFileExample = new TFileExample();
//		Example.Criteria createCriteria = tFileExample.createCriteria();
//		//设置查询条件
//		createCriteria.andFileIdEqualTo(fileId);
//		//执行查询
//		List<TFile> selectByExample = tFileMapper.selectByExample(tFileExample);
//		for(TFile tf : selectByExample) {
//			return tf;
//		}
//		return null;
//	}
//
//
//
//	/**
//	 * 有就是更新 没有就是插入
//	 */
//	@Override
//	public int insert(TFile record) {
//		return tFileMapper.insert(record);
//	}
//
//	@Override
//	public int updateByExample(TFile record, TFileExample example) {
//		return tFileMapper.updateByExample(record, example);
//	}
//
//	public int tfileInsertOrUpdate(TFile record) {
//		TFileExample tFileExample = new TFileExample();
//		Criteria createCriteria = tFileExample.createCriteria();
//		//设置查询条件
//		createCriteria.andCreatorUseridEqualTo(record.getCreatorUserid());
//		createCriteria.andFileTypeEqualTo(record.getFileType());
//		List<TFile> selectByExample = tFileMapper.selectByExample(tFileExample);
//		if(selectByExample.size() >= 1) {
//			record.setFileId(selectByExample.get(0).getFileId());
//			return this.updateByExample(record, tFileExample);
//		}
//		return this.insert(record);
//	}
//
//
//	@Override
//	public TFile getTFileByCreatorIdAndType(int userId,String fileType) {
//		TFileExample tFileExample = new TFileExample();
//		Criteria createCriteria = tFileExample.createCriteria();
//		//设置查询条件
//		createCriteria.andCreatorUseridEqualTo(userId);
//		createCriteria.andFileTypeEqualTo(fileType);
//		//执行查询
//		List<TFile> selectByExample = tFileMapper.selectByExample(tFileExample);
//		for(TFile tf : selectByExample) {
//			return tf;
//		}
//		return null;
//	}
//
	
}
