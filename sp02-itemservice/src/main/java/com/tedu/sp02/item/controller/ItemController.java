package com.tedu.sp02.item.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;
import com.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ItemController {
	@Autowired
	private ItemService itemService;

	@Value("${server.port}")
	private Integer port;

	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId) throws Exception {
		log.info("server.port=" + port + ", orderId=" + orderId);

//		if (Math.random() < 0.6) {
//			int t = new Random().nextInt(5000);
//			log.info("延迟 - " + t);
//			Thread.sleep(t);
//		}

		List<Item> items = itemService.getItems(orderId);
		return JsonResult.ok(items).msg("port = " + port);
	}

	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items) {
		itemService.decreaseNumber(items);
		return JsonResult.ok();
	}
}
