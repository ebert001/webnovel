package com.aswishes.wn.mvc.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.wn.common.AppUtil;
import com.aswishes.wn.common.Codes;
import com.aswishes.wn.common.DateUtil;
import com.aswishes.wn.common.web.SessionUtils;
import com.aswishes.wn.mvc.model.WnUser;
import com.aswishes.wn.mvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(String wnUsername, String wnPassword) {
		return new ModelAndView("/login.jsp");
	}
	
	/** 
	 * 检查用户信息，判断登录 
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String login(String wnUsername, String wnPassword) {
		UsernamePasswordToken token = new UsernamePasswordToken(wnUsername, wnPassword.toCharArray());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(ModelAndView mv) {
		SessionUtils.invalidate();
		mv.setViewName("redirect:/config/user/edit_password.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/register", method = {RequestMethod.POST})
	public ModelAndView register(ModelAndView mv, String wnUsername, String wnEmail, String password) {
		WnUser user = new WnUser();
		user.setName(wnUsername);
		user.setEmail(wnEmail);
		userService.save(user);
		return mv;
	}
	
	/** 
	 * 更新密码 
	 */
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.POST})
	public ModelAndView updatePassword(String oldPassword, String newPassword) {
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
	
	/** 用户列表 
	 * */
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public ModelAndView list() {
		logger.debug("enter user list page......");
		int startNo = 0;
		int perNo = 20;
		
		List<WnUser> userList = userService.queryList(startNo, perNo);
		request.setAttribute("userList", userList);
		return new ModelAndView("/config/user/list_user.jsp");
	}
	
	/**  
	 * 用户详细信息
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ModelAndView queryOne() {
		String username = request.getParameter("username");
		WnUser user = userService.getUser(username);
		request.setAttribute("user", user);
		return new ModelAndView("/config/user/edit_user.jsp");
	}
	
	public ModelAndView search() {
		return new ModelAndView("/config/user/list_user.jsp");
	}
	
	/** 增加新用户 
	 * */
	public ModelAndView addUser() {
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
	 * */
	public ModelAndView updateUser() {
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
}
