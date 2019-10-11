package org.mai.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mai.bean.Ad;
import org.mai.bean.Business;
import org.mai.bean.BusinessList;
import org.mai.bean.Page;
import org.mai.constant.CategoryConst;
import org.mai.dao.AdDao;
import org.mai.dao.BusinessDao;
import org.mai.dto.AdDto;
import org.mai.dto.BusinessDto;
import org.mai.service.AdService;
import org.mai.service.BusinessService;
import org.mai.util.CommonUtil;
import org.mai.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.org.apache.regexp.internal.recompile;


@Service
public class BusinessServiceImpl implements BusinessService{
	
	@Autowired
	BusinessDao businessDao;
	
	@Value("${businessImage.url}")
	private String businessImageUrl;
	
	@Value("${businessImage.savePath}")
	private String savePath;
	
	@Value("${businessImage.url}")
	private String url;
	
	@Override
	public BusinessDto getById(Long id) {
		BusinessDto result = new BusinessDto();
		Business business=businessDao.getById(id);
		BeanUtils.copyProperties(business, result);
		result.setImg(businessImageUrl+business.getImgFileName());
		return result;
	}

	@Override
	public boolean add(BusinessDto businessDto) {
		Business business = new Business();
		BeanUtils.copyProperties(businessDto, business);
		if(businessDto.getImgFile()!=null&&businessDto.getImgFile().getSize()>0) {
			try {
				String fileName = FileUtil.save(businessDto.getImgFile(), savePath);
				business.setImgFileName(fileName);
				business.setStar(0L);
				business.setNumber(0);
				businessDao.insert(business);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
				
			}
		}else {
			return false;
		}
	}

	@Override
	public boolean remove(Long id) {
		Business business=businessDao.getById(id);
		if(business==null) {
			return false;
		}
		Integer row=businessDao.remove(id);
		FileUtil.delete(savePath+business.getImgFileName());
		return row==1;
	}
	
	@Override
	public boolean modify(BusinessDto businessDto) {
		Business business = new Business();
		BeanUtils.copyProperties(businessDto, business);
		String fileName=null;
		if(businessDto.getImgFile()!=null&&businessDto.getImgFile().getSize()>0) {
			try {
				fileName=FileUtil.save(businessDto.getImgFile(), savePath);
				business.setImgFileName(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		Integer row=businessDao.update(business);
		System.out.println(row);
		if(row!=1) {
			return false;
		}
		if(fileName!=null) {
			return FileUtil.delete(savePath+businessDto.getImgFileName());
		}
		return true;
	}
	
	@Override
	public List<BusinessDto> searchByPage(BusinessDto businessDto) {
		List<BusinessDto> result = new ArrayList<BusinessDto>();
		Business condition = new Business();
		BeanUtils.copyProperties(businessDto, condition);
		List<Business> list = businessDao.searchByPage(condition);
		for (Business business : list) {
			BusinessDto businessDtoTemp = new BusinessDto();
			result.add(businessDtoTemp);
			businessDtoTemp.setImg(businessImageUrl+business.getImgFileName());
			BeanUtils.copyProperties(business, businessDtoTemp);
		}
		return result;
	}

	@Override
	public BusinessList searchByPageForApi(BusinessDto businessDto) {
		BusinessList result = new BusinessList();
		
		//组织查询条件
		Business businessForSelect = new Business();
		BeanUtils.copyProperties(businessDto, businessForSelect);
		// 当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
				// TODO 改进做法：全文检索
				if (!CommonUtil.isEmpty(businessDto.getKeyword())) {
					businessForSelect.setTitle(businessDto.getKeyword());
					businessForSelect.setSubtitle(businessDto.getKeyword());
					businessForSelect.setDescsort(businessDto.getKeyword());
				}
				// 当类别为全部(all)时，需要将类别清空，不作为过滤条件
				if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
					businessForSelect.setCategory(null);
				}
				// 前端app页码从0开始计算，这里需要+1
				int currentPage = businessForSelect.getPage().getCurrentPage();
				businessForSelect.getPage().setCurrentPage(currentPage + 1);

				List<Business> list = businessDao.selectLikeByPage(businessForSelect);

				// 经过查询后根据page对象设置hasMore
				Page page = businessForSelect.getPage();
				result.setHasMore(page.getCurrentPage() < page.getTotalPage());

				// 对查询出的结果进行格式化
				for (Business business : list) {
					BusinessDto businessDtoTemp = new BusinessDto();
					result.getData().add(businessDtoTemp);
					BeanUtils.copyProperties(business, businessDtoTemp);
					businessDtoTemp.setImg(url + business.getImgFileName());
					// 为兼容前端mumber这个属性
					businessDtoTemp.setMumber(business.getNumber());
					//businessDtoTemp.setStar(this.getStar(business));
				}

				return result;
	}



	

	
	
}
