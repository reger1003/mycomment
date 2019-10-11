package org.mai.service;

import java.util.List;

import org.mai.dto.AdDto;

public interface AdService {
	/**
	 * 新增广告
	 * @param adDao
	 * @return 是否成功添加
	 */
	boolean add(AdDto adDto);
	
	/**
	 * 删除广告
	 * @param adDto
	 * @return 是否成功生成
	 */
	boolean remove(Long id);
	
   /**
     * 修改广告
     * @param adDto
     * @return 是否修改成功：true-成功;fale-失败
     */
	boolean modify(AdDto adDto);
	
	/**
     * 分页搜索广告列表
     * @param adDto 查询条件(包含分页对象)
     * @return 广告列表
     */
	List<AdDto> searchByPage(AdDto adDto);
	
   /**
     * 根据主键获取广告的Dto对象
     * @param id 广告表主键值
     * @return adDto对象
     */
    AdDto getById(Long id);
}
