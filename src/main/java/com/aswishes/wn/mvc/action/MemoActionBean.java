package com.aswishes.wn.mvc.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.common.Codes;
import com.aswishes.wn.mvc.model.WnMemo;
import com.aswishes.wn.mvc.service.MemoService;

/**
 * 备忘录的入口方法
 */
public class MemoActionBean extends AbstractActionBean {
	
	/** 备忘录列表 
	 * @throws SQLException */
	public ModelAndView list() throws SQLException {
		log.debug("enter memo list page......");
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		
		List<WnMemo> memoList = memoService.queryList(userId);
		request.setAttribute("memoList", memoList);
		return new ModelAndView("/config/memo/create_memo.jsp");
	}
	
	/** 备忘录详细信息 
	 * @throws SQLException */
	public ModelAndView queryOne() throws SQLException {
		String id = request.getParameter("id");
		WnMemo memo = memoService.getMemo(id);
		request.setAttribute("memo", memo);
		return new ModelAndView("/config/memo/edit_memo.jsp");
	}
	
	/** 增加备忘 
	 * @throws SQLException */
	public ModelAndView addMemo() throws SQLException {
		WnMemo memo = new WnMemo();
		memo.setId(AppUtil.getUuid());
		memo.setTitle(request.getParameter("title"));
		memo.setContent(request.getParameter("content"));
		
		Date cdate = new Date();
		memo.setCreateTime(cdate);
		memo.setUpdateTime(cdate);
		
		String userId = (String) session.getAttribute(Codes.SESSION_USER);
		memo.setUserId(userId);
		
		memoService.save(memo);
		return list();
	}
	
	/** 更新备忘 
	 * @throws SQLException */
	public ModelAndView updateMemo() throws SQLException {
		WnMemo memo = new WnMemo();
		
		memo.setId(request.getParameter("id"));
		memo.setTitle(request.getParameter("title"));
		memo.setContent(request.getParameter("content"));
		memo.setUpdateTime(new Date());
		
		memoService.update(memo);
		return list();
	}
	
	/** 删除备忘 
	 * @throws SQLException */
	public ModelAndView deleteMemo() throws SQLException {
		memoService.delete(request.getParameter("id"));
		return list();
	}
	
	@Autowired
	private MemoService memoService;

	private static final Logger log = LoggerFactory.getLogger(MemoActionBean.class);
}
