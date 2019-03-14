package com.aswishes.wn.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.Restriction;
import com.aswishes.spring.dao.AbstractJdbcDao;
import com.aswishes.spring.mapper.MapperHelper;
import com.aswishes.wn.mvc.model.MVolume;

/**
 * 对应的数据库表为 wn_volume
 */
@Repository
@Transactional
public class MVolumeDao extends AbstractJdbcDao {

	@Override
	protected void setTableName() {
		this.tableName = "m_volume";
	}
	
	public List<MVolume> getVolumeList(Long bookId) {
		return getList(MapperHelper.getMapper(MVolume.class), Restriction.eq("book_id", bookId));
	}
	
	public MVolume getVolume(Long id) {
		return getObjectBy(MapperHelper.getMapper(MVolume.class), Restriction.eq("id", id));
	}
	
}
