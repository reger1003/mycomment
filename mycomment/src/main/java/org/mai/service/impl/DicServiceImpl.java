package org.mai.service.impl;

import java.util.List;

import org.mai.bean.Dic;
import org.mai.dao.DicDao;
import org.mai.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicServiceImpl implements DicService{

	@Autowired
	DicDao dicDao;
	
	@Override
	public List<Dic> getListByType(String type) {
		Dic dic = new Dic();
		dic.setType(type);
		return dicDao.getListByType(type);
	}

}
