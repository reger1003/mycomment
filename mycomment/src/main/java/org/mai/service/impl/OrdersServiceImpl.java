package org.mai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mai.bean.Orders;
import org.mai.constant.CommentStateConst;
import org.mai.dao.OrdersDao;
import org.mai.dto.OrdersDto;
import org.mai.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	OrdersDao ordersDao;
	
	@Value("${businessImage.url}")
    private String businessImageUrl;
	
	/**
	 * 买单
	 */
	@Override
	public boolean add(OrdersDto ordersDto) {
		Orders orders = new Orders();
		BeanUtils.copyProperties(ordersDto, orders);
		orders.setCommentState(CommentStateConst.NOT_COMMENT);
		ordersDao.add(orders);
		return true;
	}
	
	
	@Override
	public OrdersDto getById(Long id) {
		OrdersDto result = new OrdersDto();
		Orders orders=ordersDao.selectById(id);
		BeanUtils.copyProperties(orders, result);
		return result;
	}
	
	/**
	 * 用户个人订单列表
	 */
	@Override
	public List<OrdersDto> getListByMemberId(@Param("memberId")Long memberId) {
		List<OrdersDto>result = new ArrayList<OrdersDto>();
		List<Orders> ordersLis=ordersDao.select(memberId);
		for (Orders orders : ordersLis) {
			OrdersDto ordersDto = new OrdersDto();
			result.add(ordersDto);
			BeanUtils.copyProperties(orders, ordersDto);
			ordersDto.setImg(businessImageUrl + orders.getBusiness().getImgFileName());
			ordersDto.setTitle(orders.getBusiness().getTitle());
			ordersDto.setCount(orders.getNum());
		}
		return result;
	}

	@Override
	public List<OrdersDto> searchByPage(OrdersDto ordersDto) {
		List<OrdersDto> result = new ArrayList<OrdersDto>();
		Orders condition = new Orders();
		BeanUtils.copyProperties(ordersDto, condition);
		List<Orders> list=ordersDao.searchByPage(condition);
		for (Orders orders : list) {
			OrdersDto ordersDtoTemp = new OrdersDto();
			BeanUtils.copyProperties(orders, ordersDtoTemp);
			result.add(ordersDtoTemp);
		}
		return result;
	}

	
	
}
