package org.mai.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.mai.constant.PageCodeEnum;
import org.mai.constant.SessionKeyConst;
import org.mai.dto.UserDto;
import org.mai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping
	public String IndexController() {
		return "/system/login";
	}
	
	/**
	 * session超时
	 * @return
	 */
	@RequestMapping("/sessionTimeout")
	public String sessionTimeout(RedirectAttributes attr) {
		attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.SESSION_TIMEOUT);
		return "redirect:/login";
	}
	
	/**
	 * 验证用户名/密码是否正确 验证通过跳转至后台管理首页，验证失败，返回至登录页。
	 */
	@RequestMapping("/validate")
	public String login(UserDto userDto,HttpSession session,RedirectAttributes attr) {
		if(userService.loginValidate(userDto)) {
			session.setAttribute(SessionKeyConst.USER_INFO, userDto);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String date = sdf.format(new Date());
			session.setAttribute("time",date);
			session.setAttribute("chName", userDto.getChName());
			System.out.println(userDto.getChName());
			return "redirect:/index";
		}
		attr.addFlashAttribute(PageCodeEnum.KEY,PageCodeEnum.LOGIN_FAIL);
		return "redirect:/login";
	}
}
