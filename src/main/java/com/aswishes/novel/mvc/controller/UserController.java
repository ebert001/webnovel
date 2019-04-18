package com.aswishes.novel.mvc.controller;

import java.util.Date;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswishes.spring.PageResult;
import com.aswishes.novel.common.AppConstants;
import com.aswishes.novel.common.Codes;
import com.aswishes.novel.common.web.SessionUtils;
import com.aswishes.novel.mvc.model.MUser;
import com.aswishes.novel.mvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(String novelUsername, String novelPassword) {
		return new ModelAndView("/login.jsp");
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String login(String novelUsername, String novelPassword) {
		try { 
			boolean result = userService.login(novelUsername, novelPassword);
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
	public ModelAndView register(ModelAndView mv, String novelUsername, String novelEmail, String password) {
		MUser user = new MUser();
		user.setName(novelUsername);
		user.setEmail(novelEmail);
		userService.save(user);
		return mv;
	}
	
	@RequestMapping(value = "/toConfig")
	public ModelAndView toConfig(ModelAndView mv) {
		MUser user = SessionUtils.getUser();
		if (user == null) {
//			mv.setViewName("redirect:/login.jsp");
			userService.login("admin", "111111");
//			return mv;
		}
//		userService.login("admin", "111111");
		
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
	public ModelAndView list(ModelAndView mv, HttpServletRequest request,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, 
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
		PageResult<MUser> page = userService.getPage(pageNo, pageSize, toQueryPropertyList(request));
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
		MUser user = userService.getById(userId);
		if (user == null) {
			mv.setViewName("redirect:homepage");
			return mv;
		}
		mv.addObject("user", user);
		mv.setViewName("config/user/edit_user");
		return mv;
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	public ModelAndView updateUser(ModelAndView mv, HttpServletRequest request, Long id, 
			String username, String email, String phone,
			Date birthday, int sex, String remark) {
		MUser user = userService.getById(id);
		user.setEmail(email);
		user.setPhone(phone);
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setRemark(remark);
		userService.update(user);
		return list(mv, request, pageNo, pageSize);
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
	public ModelAndView addUser(ModelAndView mv, HttpServletRequest request, MUser user) {
		String salt = RandomStringUtils.random(16);
		user.setAlg(AppConstants.ALG_SHA256);
		user.setSalt(salt);
		user.setPwd(userService.calPassword(salt, Codes.INIT_PASSWORD));
		user.setRegTime(new Date());
		userService.save(user);
		return list(mv, request, pageNo, pageSize);
	}
	
	
}
