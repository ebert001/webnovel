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
import com.aswishes.wn.common.DateUtil;
import com.aswishes.wn.mvc.model.WnUser;
import com.aswishes.wn.mvc.service.UserService;

public class UserActionBean extends AbstractActionBean {
	
	/** 用户列表 
	 * @throws SQLException */
	public ModelAndView list() throws SQLException {
		logger.debug("enter user list page......");
		int startNo = 0;
		int perNo = 20;
		
		List<WnUser> userList = userService.queryList(startNo, perNo);
		request.setAttribute("userList", userList);
		return new ModelAndView("/config/user/list_user.jsp");
	}
	
	/** 用户详细信息 
	 * @throws SQLException 
	 * */
	public ModelAndView queryOne() throws SQLException {
		String username = request.getParameter("username");
		WnUser user = userService.getUser(username);
		request.setAttribute("user", user);
		return new ModelAndView("/config/user/edit_user.jsp");
	}
	
	public ModelAndView search() {
		return new ModelAndView("/config/user/list_user.jsp");
	}
	
	/** 检查用户信息，判断登录 
	 * @throws SQLException */
	public ModelAndView checkUser() throws SQLException {
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		WnUser user = userService.getUser(username);
		if (user == null) {
			setResponseMessage("登陆失败，用户名或密码错误!");
		} else {
			boolean isSucess = userService.login(user, password);
			if (isSucess == false) {
				setResponseMessage("登陆失败，用户名或密码错误!");
			} else {
				session.setAttribute(Codes.SESSION_USER, user);
			}
		}
		return new ModelAndView("/config/user/edit_password.jsp");
	}
	
	/** 更新密码 
	 * @throws SQLException */
	public ModelAndView updatePassword() throws SQLException {
		String oldPassword = request.getParameter("old_password");
		String newPassword = request.getParameter("new_password");
		WnUser user = getAttribute(session, Codes.SESSION_USER);
		String tpwd = AppUtil.getPwd(user.getName(), oldPassword);
		if (tpwd.equals(user.getPwd())) {
			userService.updatePassword(user, newPassword);
			setResponseMessage("修改密码成功");
		} else {
			setResponseMessage("您输入的密码和原始密码不相同，请重新输入。");
		}
		return new ModelAndView("/config/user/edit_password.jsp");
	}
	
	public ModelAndView register(ModelAndView mv, String wnUsername, String wnEmail, String password) {
		WnUser user = new WnUser();
		user.setName(wnUsername);
		user.setEmail(wnEmail);
		userService.save(user);
		return mv;
	}
	
	/** 增加新用户 
	 * @throws SQLException */
	public ModelAndView addUser() throws SQLException {
		WnUser user = new WnUser();
		String username = request.getParameter("username");
		user.setName(username);
		user.setAlias(username);
		user.setPwd(AppUtil.getPwd(username, Codes.INIT_PASSWORD));
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setBirthday(DateUtil.parseDate(request.getParameter("birthday"), DateUtil.PATTERN_DAY));
		user.setRegTime(new Date());
		userService.save(user);
		return list();
	}
	
	/** 更新用户信息 
	 * @throws SQLException */
	public ModelAndView updateUser() throws SQLException {
		String username = request.getParameter("username");
		WnUser user = userService.getUser(username);
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		user.setBirthday(DateUtil.parseDate(request.getParameter("birthday"), DateUtil.PATTERN_DAY));
		user.setSex(Integer.valueOf(request.getParameter("sex")));
		user.setRemark(request.getParameter("remark"));
		userService.update(user);
		return list();
	}
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserActionBean.class);
}
