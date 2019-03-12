package com.aswishes.wn.mvc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.spring.PageResultWrapper;
import com.aswishes.spring.service.AbstractService;
import com.aswishes.wn.common.WnStatus;
import com.aswishes.wn.common.web.SessionUtils;
import com.aswishes.wn.exception.ServiceException;
import com.aswishes.wn.mvc.dao.WnBookshelfDao;
import com.aswishes.wn.mvc.model.WnBook;
import com.aswishes.wn.mvc.model.WnBookshelf;
import com.aswishes.wn.mvc.model.WnUser;

@Service
@Transactional
public class BookshelfService extends AbstractService {

	public WnBookshelf getBook(Long id) {
		return bookshelfDao.getBook(id);
	}

	public PageResultWrapper<WnBookshelf> getBooks(int pageNo, int pageSize, Long userId) {
		return bookshelfDao.getBooks(pageNo, pageSize, userId);
	}
	
	@Transactional
	public void addBook(Long bookId, String bookName) {
		WnUser user = SessionUtils.getUser();
		if (user == null) {
			throw new ServiceException(WnStatus.USER_NOT_LOGIN);
		}
		WnBookshelf tbean = bookshelfDao.getBook(user.getId(), bookId);
		if (tbean != null) {
			throw new ServiceException(WnStatus.BOOK_IS_FAVORiTE);
		}
		
		WnBookshelf bookshelf = new WnBookshelf();
		bookshelf.setBookId(bookId);
		bookshelf.setBookName(bookName);
		
		Date date = new Date();
		bookshelf.setUpdateTime(date);
		bookshelf.setCreateTime(date);
		bookshelf.setUserId(user.getId());
		bookshelfDao.save(bookshelf);
	}
	
	@Transactional
	public WnBook readBook(Long id) {
		WnBookshelf fbook = getBook(id);
		WnBook book = bookService.getBook(fbook.getBookId());
		if (book == null) {
			return null;
		}
		fbook.setUpdateTime(new Date());
		update(fbook);
		
		return book;
	}
	
	public void update(WnBookshelf memo) {
		bookshelfDao.updateByPK(memo, true);
	}

	public void delete(Long id) {
		bookshelfDao.delete(id);
	}

	@Autowired
	private WnBookshelfDao bookshelfDao;
	@Autowired
	private BookService bookService;

	@Override
	public void setDao() {
		this.dao = bookshelfDao;
	}
}
