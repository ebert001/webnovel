package com.aswishes.novel.core.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.SqlAppender;
import com.aswishes.novel.core.model.MVolume;

/**
 * 对应的数据库表为 novel_volume
 */
@Repository
@Transactional
public class MVolumeDao extends SimpleJdbcDao<MVolume> {

	public MVolumeDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<MVolume> getVolumeList(Long bookId) {
		SqlAppender appender = SqlAppender.namedModel()
				.append("select * from ").append(tableName)
				.append("where book_id = :bookId", bookId);
		return getList(appender, MVolume.class);
	}
	
	public void save(MVolume entity) {
		
	}
	
	public void update(MVolume entity) {
		
	}
}
