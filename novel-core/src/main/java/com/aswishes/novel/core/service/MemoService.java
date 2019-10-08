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

	public MMemo getMemo(Long id) {
		return memoDao.getMemo(id);
	}

	public List<MMemo> queryList(Long userId) {
		return memoDao.queryList(userId);
	}

	public void save(MMemo memo) {
		memoDao.save(memo);
	}

	public void update(MMemo memo) {
		memoDao.updateByPK(memo, true);
	}

	public void delete(Long id) {
		memoDao.delete(id);
	}

	@Autowired
	private MMemoDao memoDao;

	@Autowired
	@Override
	public void setDao() {
		this.dao = memoDao;
	}
}
