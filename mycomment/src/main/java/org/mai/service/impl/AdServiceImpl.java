package org.mai.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mai.bean.Ad;
import org.mai.dao.AdDao;
import org.mai.dto.AdDto;
import org.mai.service.AdService;
import org.mai.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.org.apache.regexp.internal.recompile;


@Service
public class AdServiceImpl implements AdService{
	@Autowired
	private AdDao adDao;

	@Value("${adImage.savePath}")
	private String adImageSavePath;

	@Value("${adImage.url}")
	private String adImageUrl;

	@Override
	//TODO 可以改成获取失败详细原因
	public boolean add(AdDto adDto) {
		Ad ad = new Ad();
		ad.setLink(adDto.getLink());
		ad.setTitle(adDto.getTitle());
		ad.setWeight(adDto.getWeight());
		if(adDto.getImgFile()!=null&&adDto.getImgFile().getSize()>0) {
			String fileName = System.currentTimeMillis()+"_"+adDto.getImgFile().getOriginalFilename();
			File file = new File(adImageSavePath+fileName);
			File fileFolder = new File(adImageSavePath);
			if(!fileFolder.exists()) {
				fileFolder.mkdirs();
			}
			try {
				adDto.getImgFile().transferTo(file);
				ad.setImgFileName(fileName);
				Integer row = adDao.insert(ad);
				System.out.println(row);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				//TODO 
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public boolean remove(Long id) {
		Ad ad=adDao.selectById(id);
		if(ad==null) {
			return false;
		}
		Integer row=adDao.delete(id);
		FileUtil.delete(adImageSavePath+ad.getImgFileName());
		return row==1;
	}

	@Override
	public boolean modify(AdDto adDto) {
		Ad ad = new Ad();
		BeanUtils.copyProperties(adDto, ad);
		String fileName = null;
		if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
			try {
				fileName = FileUtil.save(adDto.getImgFile(), adImageSavePath);
				ad.setImgFileName(fileName);
			} catch (Exception e) {
				// TODO 需要添加日志
				e.printStackTrace();
				return false;
			}
		}
		int updateCount = adDao.update(ad);
		if (updateCount != 1) {
			return false;
		}
		if (fileName != null) {
			return FileUtil.delete(adImageSavePath + adDto.getImgFileName());
		}
		return true;
	}
	
	/**
     * 分页搜索广告列表
     * @param adDto 查询条件(包含分页对象)
     * @return 广告列表
     */
	@Override
	public List<AdDto> searchByPage(AdDto adDto) {
		List<AdDto> result = new ArrayList<AdDto>();
		Ad condition = new Ad();
		BeanUtils.copyProperties(adDto, condition);
		List<Ad>adList = adDao.selectByPage(condition);
		for(Ad ad:adList) {
			AdDto adDtoTemp = new AdDto();
			result.add(adDtoTemp);
			adDtoTemp.setImg(adImageUrl+ad.getImgFileName());
			BeanUtils.copyProperties(ad, adDtoTemp);
		}
		return result;
	}

	@Override
	public AdDto getById(Long id) {
		AdDto adDto = new AdDto();
		Ad ad=adDao.selectById(id);
		BeanUtils.copyProperties(ad, adDto);
		adDto.setImg(adImageUrl+ad.getImgFileName());
		return adDto;
	}



}
