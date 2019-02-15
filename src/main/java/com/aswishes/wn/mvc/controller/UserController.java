package com.aswishes.wn.mvc.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(String wnUsername, String wnPassword) {
		return new ModelAndView("/login.jsp");
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String login(String wnUsername, String wnPassword) {
		try { 
			boolean result = userService.login(wnUsername, wnPassword);
			if (result) {
				return "redirect:/index";
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(ModelAndView mv) {
		SessionUtils.invalidate();
		mv.setViewName("redirect:config/user/edit_password.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/toRegister")
	public ModelAndView toRegister(ModelAndView mv) {
		mv.setViewName("surface/user/register");
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
	
	@RequestMapping(value = "/toConfig")
	public ModelAndView toConfig(ModelAndView mv) {
		userService.login("admin", "111111");
		
		mv.setViewName("config/user_setup");
		return mv;
	}
	
	@RequestMapping(value = "/toUpdatePwd")
	public ModelAndView toUpdatePwd(ModelAndView mv) {
		mv.setViewName("config/user/edit_password");
		return mv;
	}
	
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.POST})
	public ModelAndView updatePassword(ModelAndView mv, String oldPassword, String newPassword) {
		WnUser user = SessionUtils.getUser();
		String tpwd = AppUtil.getPwd(user.getName(), oldPassword);
		if (tpwd.equals(user.getPwd())) {
			userService.updatePassword(user, newPassword);
			setResponseMessage("修改密码成功");
		} else {
			setResponseMessage("您输入的密码和原始密码不相同，请重新输入。");
		}
		mv.setViewName("/config/user/edit_password.jsp");
		return mv;
	}
	
	/** 用户列表 
	 * */
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public ModelAndView list(ModelAndView mv, int pageNo, int pageSize) {
		List<WnUser> userList = userService.queryList(pageNo, pageSize);
		request.setAttribute("userList", userList);
		mv.setViewName("config/user/list_user.jsp");
		return mv;
	}
	
	/**  
	 * 用户详细信息
	 */
	@RequestMapping(value = "/queryOne")
	public ModelAndView queryOne(ModelAndView mv, String username) {
		WnUser user = userService.getUser(username);
		request.setAttribute("user", user);
		mv.setViewName("config/user/edit_user");
		return mv;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(ModelAndView mv) {
		mv.setViewName("/config/user/list_user.jsp");
		return mv;
	}

	@RequestMapping(value = "/add")
	public ModelAndView addUser(ModelAndView mv) {
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
		return list(mv, 1, 10);
	}
	
	public ModelAndView updateUser(ModelAndView mv, String username, String email, String phone) {
		WnUser user = userService.getUser(username);
		user.setEmail(email);
		user.setPhone(phone);
		user.setBirthday(DateUtil.parseDate(request.getParameter("birthday"), DateUtil.PATTERN_DAY));
		user.setSex(Integer.valueOf(request.getParameter("sex")));
		user.setRemark(request.getParameter("remark"));
		userService.update(user);
		return list(mv, 1, 10);
	}
}
