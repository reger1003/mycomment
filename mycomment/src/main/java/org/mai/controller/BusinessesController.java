package org.mai.controller;

import org.mai.constant.DicTypeConst;
import org.mai.constant.PageCodeEnum;
import org.mai.dto.BusinessDto;
import org.mai.service.BusinessService;
import org.mai.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	DicService dicService;
	
	/**
	 * 商户列表
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String search(BusinessDto businessDto,Model model) {
		model.addAttribute("list",businessService.searchByPage(businessDto));
		model.addAttribute("searchParam", businessDto);
		return "/content/businessList";
	}
	
	/**
	 * 删除商户
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String remove(@PathVariable("id")Long id,Model model) {
		if(businessService.remove(id)) {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_SUCCESS);
			System.out.println(id);
			return "redirect:/businesses";
		}else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
			return "redirect:/businesses";
		}
	}
	
	/**
	 * 商户新增页初始化
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addInit(Model model) {
		
		 model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		 model.addAttribute("categoryList",dicService.getListByType(DicTypeConst.CATEGORY));
		 
		return "/content/businessAdd";
	}

	/**
	 * 商户新增
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String add(BusinessDto dto,RedirectAttributes attr) {
		if(businessService.add(dto)) {
			attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
			return "redirect:/businesses";
		} else {
			attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
			return "redirect:/businesses/addPage";
		}
	}

	
	/**
	 * 商户修改页初始化
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String modifyInit(@PathVariable("id")Long id,Model model) {
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		model.addAttribute("modifyObj", businessService.getById(id));
		return "/content/businessModify";
	}
	
	/**
	 * 商户修改
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String modify(BusinessDto businessDto,Model model) {
		model.addAttribute("modifyObj",businessDto);
		if(businessService.modify(businessDto)) {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
		}else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.REMOVE_FAIL);
		}
		return "/content/businessModify";
	}
}
