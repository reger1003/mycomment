package org.mai.service;

import org.mai.dto.UserDto;

public interface UserService {

	/**
	 * 校验用户名/密码是否正确
	 * @param userDto 待校验dto对象
	 * @return   true:用户名/密码正确，如果正确，将dto对象里其他属性补齐
	 *           false:用户名/密码错误
	 */
	boolean loginValidate(UserDto userdto);

	/**
	 * 新增用户
	 * @param userDto
	 * @return true:新增成功;false:因已存在相同用户名新增失败
	 */
	boolean add(UserDto userDto);
}
