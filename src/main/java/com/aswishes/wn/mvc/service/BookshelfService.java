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
import com.aswishes.wn.mvc.dao.MBookshelfDao;
import com.aswishes.wn.mvc.model.MBook;
import com.aswishes.wn.mvc.model.MBookshelf;
import com.aswishes.wn.mvc.model.MUser;

@Service
@Transactional
public class BookshelfService extends AbstractService {

	public MBookshelf getBook(Long id) {
		return bookshelfDao.getBook(id);
	}

	public PageResultWrapper<MBookshelf> getBooks(int pageNo, int pageSize, Long userId) {
		return bookshelfDao.getBooks(pageNo, pageSize, userId);
	}
	
	@Transactional
	public void addBook(Long bookId, String bookName) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
			throw new ServiceException(WnStatus.USER_NOT_LOGIN);
		}
		MBookshelf tbean = bookshelfDao.getBook(user.getId(), bookId);
		if (tbean != null) {
			throw new ServiceException(WnStatus.BOOK_IS_FAVORiTE);
		}
		
		MBookshelf bookshelf = new MBookshelf();
		bookshelf.setBookId(bookId);
		bookshelf.setBookName(bookName);
		
		Date date = new Date();
		bookshelf.setUpdateTime(date);
		bookshelf.setCreateTime(date);
		bookshelf.setUserId(user.getId());
		bookshelfDao.save(bookshelf);
	}
	
	@Transactional
	public MBook readBook(Long id) {
		MBookshelf fbook = getBook(id);
		MBook book = bookService.getBook(fbook.getBookId());
		if (book == null) {
			return null;
		}
		fbook.setUpdateTime(new Date());
		update(fbook);
		
		return book;
	}
	
	public void update(MBookshelf memo) {
		bookshelfDao.updateByPK(memo, true);
	}

	public void delete(Long id) {
		bookshelfDao.delete(id);
	}

	@Autowired
	private MBookshelfDao bookshelfDao;
	@Autowired
	private BookService bookService;

	@Override
	public void setDao() {
		this.dao = bookshelfDao;
	}
}
