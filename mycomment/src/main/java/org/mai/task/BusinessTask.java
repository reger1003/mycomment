package org.mai.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mai.bean.Business;
import org.mai.dao.BusinessDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商户相关的定时任务
 */
@Component("BusinessTask")
public class BusinessTask {

	private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);
	@Autowired
	BusinessDao businessDao;
	
	
	public BusinessTask() {
	
	}

	/**
	 * 同步已售数量
	 */
	public void synNumber() {
		businessDao.updateNumber();
		logger.info("已同已售数量！");
	}
	
	/**
	 * 同步星级
	 */
	@Transactional
	public void synStar() {
		logger.info("同步星");
	}
}