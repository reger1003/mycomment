package org.mai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mai.bean.Ad;
import org.mai.bean.Comment;



public interface CommentDao {

	/**
	 *  根据查询条件分页查询评论列表
	 * @param comment 查询条件
	 * @return 评论列表
	 */
	List<Comment> selectByPage(Comment comment);

	/**
	 * 提交评论
	 * @param comment 评论对象
	 * @return 影响行数
	 */
	Integer submit(Comment comment);
}
