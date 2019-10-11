package org.mai.dao;

import java.util.List;

import org.mai.bean.User;

public interface UserDao {
	/**
	 * 根据查询条件查询用户列表
	 * @param user 查询条件
	 * @return 用户列表
	 */
	List<User> findUser(User user);

	/**
	 * 根据用户id查用户信息
	 * @param id 用户id
	 * @return 封装了用户数据的User对象 或 null
	 */
	User findById(Long id);

	/**
	 * 新增用户
	 * @param user
	 * @return 影响行数：如果用户名已存在，影响行数为0，新增成功，影响行数为1
	 */
	Integer insert(User user);
}
