package org.mai.dao;

import java.util.List;

import org.mai.bean.Dic;

public interface DicDao {
	List<Dic>getListByType(String type);
}
