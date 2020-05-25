package com.aswishes.novel.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.dao.MMemoDao;
import com.aswishes.novel.core.model.MMemo;

@Service
@Transactional
public class MemoService extends SimpleService<MMemo> {
	@Autowired
	private MMemoDao memoDao;
	
	public MMemo getMemo(Long id) {
		return memoDao.getById(id);
	}

	public List<MMemo> queryList(Long userId) {
		return memoDao.queryList(userId);
	}

	public void save(MMemo memo) {
		memoDao.save(memo);
	}

	public void update(MMemo memo) {
		memoDao.update(memo);
	}

	public void delete(Long id) {
		memoDao.deleteById(id);
	}

	

}
