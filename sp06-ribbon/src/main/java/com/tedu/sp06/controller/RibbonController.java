package com.tedu.sp06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;

@RestController
public class RibbonController {
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/item-service/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId){
		return restTemplate.getForObject("http://item-service/{1}", JsonResult.class, orderId);
	} 
	@PostMapping("/item-service/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		return restTemplate.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
	}
	
	@GetMapping("/user-service/{userId}")
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		return restTemplate.getForObject("http://user-serivce/{1}", JsonResult.class, userId);
	}

	@GetMapping("/user-service/{userId}/score")
	public JsonResult addScore(
			@PathVariable Integer userId, Integer score) {
		return restTemplate.getForObject("http://user-serivce/{1}/score?score={2}", JsonResult.class, userId, score);
	}
	
	@GetMapping("/order-service/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		return restTemplate.getForObject("http://order-service/{1}", JsonResult.class, orderId);
	}
	
	@GetMapping("/order-service/")
	public JsonResult addOrder() {
		return restTemplate.getForObject("http://order-service/", JsonResult.class);
	}
}
