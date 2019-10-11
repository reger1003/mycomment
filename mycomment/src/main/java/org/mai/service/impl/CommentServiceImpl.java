package org.mai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mai.bean.Business;
import org.mai.bean.Comment;
import org.mai.bean.CommentList;
import org.mai.bean.Orders;
import org.mai.bean.Page;
import org.mai.constant.CommentStateConst;
import org.mai.dao.CommentDao;
import org.mai.dao.OrdersDao;
import org.mai.dto.CommentDto;
import org.mai.dto.CommentForSubmitDto;
import org.mai.service.CommentService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;
	@Autowired
	OrdersDao ordersDao;

	@Override
	@Transactional
	public boolean saveComment(CommentForSubmitDto commentForSubmitDto) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForSubmitDto, comment);
		comment.setOrdersId(commentForSubmitDto.getId());
		comment.setCreateTime(new Date());
		commentDao.submit(comment);
		Orders orders = new Orders();
		orders.setId(commentForSubmitDto.getId());
		orders.setCommentState(CommentStateConst.HAS_COMMENT);
		ordersDao.update(orders);
		return true;
	}

	@Override
	public CommentList getListByBusinessId(Long businessId, Page page) {
		CommentList result = new CommentList();
		// 组织查询条件
		Comment comment = new Comment();
		Orders orders = new Orders();
		Business business = new Business();
		// 评论里包含了订单对象
		comment.setOrders(orders);
		// 订单对象里包含了商户对象
		orders.setBusiness(business);
		// 设置商户主键
		business.setId(businessId);
		// 前端app页码从0开始计算，这里需要+1
		page.setCurrentPage(page.getCurrentPage() + 1);
		// 设置分页条件
		comment.setPage(page);
		List<Comment>commentList=commentDao.selectByPage(comment);
		List<CommentDto>data = new ArrayList<CommentDto>();
		for (Comment commentTemp : commentList) {
			CommentDto commentDto = new CommentDto();
			BeanUtils.copyProperties(commentTemp, commentDto);
			data.add(commentDto);
			/*
			 * StringBuffer phoneBuffer = new
			 * StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
			 * commentDto.setUsername(phoneBuffer.replace(3, 7, "****").toString());
			 */
		}
		result.setData(data);
		result.setHasMore(page.getCurrentPage()<page.getTotalPage());
		return result;
	}


}
