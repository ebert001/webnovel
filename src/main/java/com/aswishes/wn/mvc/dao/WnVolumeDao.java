package com.aswishes.wn.mvc.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.wn.mvc.model.WnVolume;

import spring.persist.helper.AbstractDaoTemplate;

/**
 * 对应的数据库表为 wn_volume
 */
@Repository
@Transactional
public class WnVolumeDao extends AbstractDaoTemplate<WnVolume> {

	@Override
	protected void setEntity() {
		this.clazz = WnVolume.class;
	}

	@Override
	protected void setTableName() {
		this.tableName = "wn_volume";
	}
	
}
