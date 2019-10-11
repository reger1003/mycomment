package org.mai.service.impl;

import java.util.List;

import org.mai.bean.User;
import org.mai.dao.UserDao;
import org.mai.dto.UserDto;
import org.mai.service.UserService;
import org.mai.util.CommonUtil;
import org.mai.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public boolean loginValidate(UserDto userDto) {
		if(userDto !=null&& !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			List<User>list=userDao.findUser(user);
			if(list.size()==1) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean add(UserDto userDto) {
		if(userDto !=null&& !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			List<User>list=userDao.findUser(user);
			if(list.size()==0) {
				user.setPassword(MD5Util.getMD5(userDto.getPassword()));
				return userDao.insert(user) == 1;
			}
			return false;
		}
		return false;
	}



	





}
