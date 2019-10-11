package org.mai.dao;


import java.util.List;

import org.mai.bean.Business;



public interface BusinessDao {
	/**
	 * 添加商户
	 * @param business
	 * @return
	 */
	Integer insert(Business business);
	
	/**
	 * 删除商户
	 * @param id
	 * @return
	 */
	Integer remove(Long id);
	
	/**
	 * 修改用户
	 * @param business
	 * @return
	 */
	Integer update(Business business);
	
	Integer updateNumber();
	
    /**
     *  根据主键查询商户
     * @param id 主键
     * @return 商户对象
     */
	Business getById(Long id);
	
	List<Business> searchByPage(Business business);
	
	/**
     *  根据查询条件分页查询商户列表 : 
     *  标题、副标题、描述三个过滤条件为模糊查询
     *  并且这三个过滤条件之间为或者的关系，用 OR 连接
     *  这三个过滤条件与其他过滤条件依然是并且关系，用 AND 连接
     * @param business 查询条件
     * @return 商户列表
     */
    List<Business> selectLikeByPage(Business business);
}
