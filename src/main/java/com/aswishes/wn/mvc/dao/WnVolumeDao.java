package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.WnVolume;

/**
 * 对应的数据库表为 wn_volume
 */
@Repository
@Transactional
public class WnVolumeDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "wn_volume";
	}
	
	public List<WnVolume> getVolumeList(String bookId) {
		return getList(MapperHelper.getMapper(WnVolume.class), Restriction.eq("book_id", bookId));
	}
	
	public WnVolume getVolume(String id) {
		return getObjectBy(MapperHelper.getMapper(WnVolume.class), Restriction.eq("id", id));
	}
	
}
