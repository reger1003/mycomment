package org.mai.dao;

import java.util.List;

import org.mai.bean.Orders;

public interface OrdersDao {
	/**
	 * 买单
	 * @param orders 订单对象
	 * @return 受影响行数
	 */
	Integer add(Orders orders);

	/**
	 * 修改订单
	 * @param orders 订单对象
	 * @return 受影响行数
	 */
	Integer update(Orders orders);

	/**
	 * 根据主键查询订单表对象
	 * @param id 主键值
	 * @return 订单表对象
	 */
	Orders selectById(Long id);

	/**
	 * 根据条件查询订单列表
	 * @param orders 查询条件
	 * @return 订单列表
	 */
	List<Orders> select(Long memberId);
	
	/**
	 * 根据查询条件分页查询订单列表
     *  手机号、订单号两个个过滤条件为模糊查询
     *  并且这两个过滤条件之间为或者的关系，用 OR 连接
     *  这两个过滤条件与其他过滤条件依然是并且关系，用 AND 连接 
	 * @param orders 查询条件
	 * @return 订单列表
	 */
	List<Orders>searchByPage(Orders orders);
}
