package org.mai.service;

import java.util.List;

import org.mai.bean.Business;
import org.mai.bean.BusinessList;
import org.mai.dto.AdDto;
import org.mai.dto.BusinessDto;

public interface BusinessService {
	
	boolean add(BusinessDto businessDto);
    /**
     * 根据主键获取商户dto
     * @param id 主键
     * @return 商户dto
     */
	
	boolean remove(Long id);
	
	boolean modify(BusinessDto businessDto);
	
    BusinessDto getById(Long id);
	
    /**
     * 分页搜索商户列表
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表
     */
	List<BusinessDto> searchByPage(BusinessDto businessDto);
	
	/**
     * 分页搜索商户列表(接口专用)
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表Dto对象
     */
    BusinessList searchByPageForApi(BusinessDto businessDto);
}
