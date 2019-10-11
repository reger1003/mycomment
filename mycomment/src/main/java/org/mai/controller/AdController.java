package org.mai.controller;

import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.mai.constant.PageCodeEnum;
import org.mai.dto.AdDto;
import org.mai.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

@Controller
@RequestMapping("/ad")
public class AdController {

	@Autowired
	AdService adService;
	
	@RequestMapping
	public String init(AdDto adDto,Model model) {
		model.addAttribute("list", adService.searchByPage(adDto));
		model.addAttribute("searchParam", adDto);
		return "/content/adList";
	}
	
	@RequestMapping("/search")
	public String search(AdDto adDto,Model model) {
		model.addAttribute("list",adService.searchByPage(adDto));
		model.addAttribute("searchParam",adDto);
		return "/content/adList";
	}
	
	@RequestMapping("/remove")
	public String Remove(@RequestParam("id")Long id,Model model) {
		if(adService.remove(id)) {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
		}else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
		}
		return "forward:/ad";
	}
	
	@RequestMapping("/addInit")
	public String addInit() {
		return "/content/adAdd";
	}
	
	@RequestMapping("add")
	public String add(AdDto adDto,Model model) {
		if(adService.add(adDto)) {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
		}else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
		}
		return "/content/adAdd";
	}
	
	/**
	 * 修改页初始化
	 */
	@RequestMapping("/modifyInit")
	public String modifyInit(Model model, @RequestParam("id") Long id) {
		model.addAttribute("modifyObj", adService.getById(id));
		return "/content/adModify";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model,AdDto adDto) {
		model.addAttribute("modifyObj",adDto);
		if(adService.modify(adDto)) {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
		}else {
			model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
		}
		return "/content/adModify";
	}
}
