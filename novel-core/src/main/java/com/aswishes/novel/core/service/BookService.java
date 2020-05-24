package com.aswishes.novel.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.core.common.db.PageResult;
import com.aswishes.novel.core.dao.MBookDao;
import com.aswishes.novel.core.dao.MVolumeDao;
import com.aswishes.novel.core.model.MBook;
import com.aswishes.novel.core.model.MVolume;
import com.aswishes.spring.QueryProperty;

/**
 * 书籍相关的业务处理类
 */
@Service
@Transactional
public class BookService extends SimpleService<MBook> {
	@Autowired
	private MBookDao bookDao;
	@Autowired
	private MVolumeDao volumeDao;

	public List<MBook> getBookList(Long userId) {
		return bookDao.getBookList(userId);
	}
	
	public MBook getBook(String name) {
		return bookDao.getByName(name);
	}
	
	public MBook getBook(String name, Long websiteId) {
		return bookDao.getBook(name, websiteId);
	}

	public MBook getBook(Long bookId) {
		return bookDao.getById(bookId);
	}
	
	public PageResult<MBook> findUnauditBooks(int pageNo, int pageSize) {
		return bookDao.getUnauditBooks(pageNo, pageSize);
	}
	
	public PageResult<MBook> findReadTop(int pageNo, int pageSize) {
		return bookDao.getClickTop(pageNo, pageSize);
	}
	
	public void addBook(MBook book) {
		
//		bookDao.save(book);
	}

	public void deleteBook(Long bookId) {
		bookDao.deleteById(bookId);
	}
	
	public List<MVolume> getVolumeList(Long bookId) {
		return volumeDao.getVolumeList(bookId);
	}
	
	public void addVolume(MVolume volume) {
		volumeDao.save(volume);
	}
	
	public MVolume getVolume(Long id) {
		return volumeDao.getVolume(id);
	}
	
	public void updateVolume(MVolume volume) {
		volumeDao.updateByPK(volume, true);
	}
	
	@Autowired
	@Override
	public void setDao() {
		this.dao = bookDao;
	}
}
