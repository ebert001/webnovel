package com.aswishes.wn.mvc.controller;

import java.util.Date;

import javax.mail.Multipart;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.spring.PageResultWrapper;
import com.aswishes.wn.common.AppConstants;
import com.aswishes.wn.common.Codes;
import com.aswishes.wn.common.web.SessionUtils;
import com.aswishes.wn.mvc.model.MUser;
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
		MUser user = new MUser();
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
	public ModelAndView updatePassword(ModelAndView mv, String oldPassword, String newPassword, String confirmPassword) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
			mv.setViewName("frame/homepage");
			return mv;
		}
		String tpwd = userService.calPassword(user, oldPassword);
		if (tpwd.equals(user.getPwd())) {
			userService.updatePassword(user, newPassword);
			setResponseMessage("修改密码成功");
		} else {
			setResponseMessage("您输入的密码和原始密码不相同，请重新输入。");
		}
		mv.setViewName("/config/user/edit_password");
		return mv;
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView mv, 
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
			String name) {
		PageResultWrapper<MUser> page = userService.queryPage(pageNo, pageSize);
		mv.addObject("page", page);
		mv.setViewName("config/user/list_user");
		return mv;
	}
	
	/**  
	 * 用户详细信息
	 */
	@RequestMapping(value = "/info")
	public ModelAndView queryOne(ModelAndView mv, String username) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
			mv.setViewName("redirect:homepage");
			return mv;
		}
		mv.addObject("user", user);
		mv.setViewName("config/user/edit_user");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(ModelAndView mv, Long userId) {
		MUser user = userService.getUser(userId);
		if (user == null) {
			mv.setViewName("redirect:homepage");
			return mv;
		}
		mv.addObject("user", user);
		mv.setViewName("config/user/edit_user");
		return mv;
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	public ModelAndView updateUser(ModelAndView mv, Long id, 
			String username, String email, String phone,
			Date birthday, int sex, String remark) {
		MUser user = userService.getUser(id);
		user.setEmail(email);
		user.setPhone(phone);
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setRemark(remark);
		userService.update(user);
		return list(mv, 1, 10, null);
	}
	
	@RequestMapping(value = "/toUploadAvatar")
	public ModelAndView toUploadAvatar(ModelAndView mv) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
			mv.setViewName("redirect:/homepage");
			return mv;
		}
		mv.addObject("user", user);
		mv.setViewName("config/user/upload_avatar");
		return mv;
	}
	
	@RequestMapping(value = "/uploadAvatar")
	public ModelAndView uploadAvatar(ModelAndView mv, Multipart avatar) {
		
		mv.setViewName("/config/user/list_user.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/toCreateAuthor")
	public ModelAndView toCreateAuthor(ModelAndView mv) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
			mv.setViewName("redirect:/homepage");
			return mv;
		}
		mv.addObject("user", user);
		mv.setViewName("config/user/create_author");
		return mv;
	}
	
	@RequestMapping(value = "/toAuthorPrincple")
	public ModelAndView toAuthorPrincple(ModelAndView mv) {
		mv.setViewName("surface/self/author_princple_board");
		return mv;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(ModelAndView mv) {
		mv.setViewName("/config/user/list_user.jsp");
		return mv;
	}
	
	@RequestMapping(value = "/toAddUser")
	public ModelAndView toAddUser(ModelAndView mv) {
		mv.setViewName("/config/user/create_user");
		return mv;
	}

	@RequestMapping(value = "/add")
	public ModelAndView addUser(ModelAndView mv, MUser user) {
		String salt = RandomStringUtils.random(16);
		user.setAlg(AppConstants.ALG_SHA256);
		user.setSalt(salt);
		user.setPwd(userService.calPassword(salt, Codes.INIT_PASSWORD));
		user.setRegTime(new Date());
		userService.save(user);
		return list(mv, 1, 10, null);
	}
	
	
}
