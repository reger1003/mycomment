package org.mai.controller;

import java.util.List;

import org.mai.dto.OrdersDto;
import org.mai.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	OrdersService ordersService;
	
	@RequestMapping
	public String init(OrdersDto ordersDto,Model model) {
		model.addAttribute("list",ordersService.searchByPage(ordersDto));
		model.addAttribute("searchParam",ordersDto);
		return "/content/orderList";
	}
	@RequestMapping("/search")
	public String search(OrdersDto ordersDto,Model model) {
		model.addAttribute("list",ordersService.searchByPage(ordersDto));
		model.addAttribute("searchParam",ordersDto);
		return "/content/orderList";
	}
}
