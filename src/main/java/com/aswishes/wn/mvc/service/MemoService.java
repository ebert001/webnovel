package com.aswishes.wn.mvc.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.dao.WnMemoDao;
import com.aswishes.wn.mvc.model.WnMemo;

import spring.persist.helper.Restriction;

@Service
@Transactional
public class MemoService {

	public WnMemo getMemo(String id) throws SQLException {
		return memoDao.selectById(Arrays.asList(Restriction.eq("id", id)));
	}

	public List<WnMemo> queryList(String userId) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
		return memoDao.select(restrictions);
	}

	public void save(WnMemo memo) throws SQLException {
		memoDao.save(memo);
	}

	public void update(WnMemo memo) throws SQLException {
		memoDao.update(memo);
	}

	public void delete(String id) throws SQLException {
		List<Restriction> restrictions = Arrays.asList(Restriction.eq("id", id));
		memoDao.delete(restrictions);
	}

	@Autowired
	private WnMemoDao memoDao;
}
