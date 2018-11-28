package com.aswishes.wn.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.dao.WnMemoDao;
import com.aswishes.wn.mvc.model.WnMemo;

@Service
@Transactional
public class MemoService extends AbstractService {

	public WnMemo getMemo(String id) {
		return memoDao.getMemo(id);
	}

	public List<WnMemo> queryList(String userId) {
		return memoDao.queryList(userId);
	}

	public void save(WnMemo memo) {
		memoDao.save(memo);
	}

	public void update(WnMemo memo) {
		memoDao.updateByPK(memo);
	}

	public void delete(String id) {
		memoDao.delete(id);
	}

	@Autowired
	private WnMemoDao memoDao;
}
