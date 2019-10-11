package org.mai.service;

import org.mai.bean.CommentList;
import org.mai.bean.Page;
import org.mai.dto.CommentForSubmitDto;

public interface CommentService {
	/**
	 * 提交评论
	 * @param commentForSubmitDto
	 * @return
	 */
	boolean saveComment(CommentForSubmitDto commentForSubmitDto);

	/**
	 * 按分页条件，根据商户ID获取商户下的评论列表dto
	 * @param businessId 商户ID
	 * @param page 分页对象
	 * @return 评论列表dto
	 */
	CommentList getListByBusinessId(Long businessId,Page page);
}
