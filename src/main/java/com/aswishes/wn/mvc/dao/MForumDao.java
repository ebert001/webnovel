package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.MForum;

/**
 * 对应的数据库表为 wn_book
 */
@Repository
@Transactional
public class MForumDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_forum";
	}
	
	public MForum queryForum(Long id) {
		return getObjectBy(MapperHelper.getMapper(MForum.class), Restriction.eq("id", id));
	}

	public List<MForum> queryForumList(Long userId) {
		return getList(MapperHelper.getMapper(MForum.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public List<MForum> queryForumList(Long userId, int startNo, int perNo) {
		return getList(MapperHelper.getMapper(MForum.class), Restriction.eq("user_id", userId), Restriction.orderByDesc("create_time"));
	}
	
	public int queryForumListCount() {
		return getCount();
	}

	public void deleteForum(Long id) {
		delete(Restriction.eq("id", id));
	}
}
