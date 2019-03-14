package com.aswishes.novel.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.service.AbstractService;
import com.aswishes.novel.mvc.dao.MMemoDao;
import com.aswishes.novel.mvc.model.MMemo;

@Service
@Transactional
public class MemoService extends AbstractService {

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

	@Override
	public void setDao() {
		this.dao = memoDao;
	}
}
