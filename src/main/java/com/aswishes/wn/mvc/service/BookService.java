package com.aswishes.wn.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.mvc.dao.WnBookDao;
import com.aswishes.wn.mvc.dao.WnVolumeDao;
import com.aswishes.wn.mvc.model.WnBook;
import com.aswishes.wn.mvc.model.WnVolume;

/**
 * 书籍相关的业务处理类
 */
@Service
@Transactional
public class BookService extends AbstractService {
	@Autowired
	private WnBookDao bookDao;
	@Autowired
	private WnVolumeDao volumeDao;

	public List<WnBook> getBookList(Long userId) {
		return bookDao.getBookList(userId);
	}
	
	public WnBook getBook(String bookName) {
		return bookDao.getBook(bookName);
	}

	public WnBook getBook(Long bookId) {
		return bookDao.getBook(bookId);
	}
	
	public void addBook(WnBook book) {
		bookDao.save(book);
	}

	public void deleteBook(Long bookId) {
		bookDao.deleteBook(bookId);
	}
	
	public List<WnVolume> getVolumeList(Long bookId) {
		return volumeDao.getVolumeList(bookId);
	}
	
	public void addVolume(WnVolume volume) {
		volumeDao.save(volume);
	}
	
	public WnVolume getVolume(Long id) {
		return volumeDao.getVolume(id);
	}
	
	public void updateVolume(WnVolume volume) {
		volumeDao.updateByPK(volume);
	}
	
	@Override
	public void setDao() {
		this.dao = bookDao;
	}
}
