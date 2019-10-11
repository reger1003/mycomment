package org.mai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mai.bean.Ad;



public interface AdDao {
	Integer insert(Ad ad);
	
	Integer delete(Long id);
	
	Integer update(Ad ad);
	
	/**
     *  根据查询条件分页查询广告列表 :
     * @param ad 查询条件
     * @return 商户列表
     */
	List<Ad> selectByPage(Ad ad);
	
	Ad selectById(Long id);
}
