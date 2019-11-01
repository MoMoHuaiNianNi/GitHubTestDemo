package com.tedu.sp02.item.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
	
	@Override
	public List<Item> getItems(String orderId) {
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(new Item(1, "商品 1", 1));
		list.add(new Item(2, "商品 2", 4));
		list.add(new Item(3, "商品 3", 1));
		list.add(new Item(4, "商品 4", 2));
		list.add(new Item(5, "商品 5", 3));
		return list;
	}

	@Override
	public void decreaseNumber(List<Item> items) {
		if (log.isInfoEnabled()) {
			for (Item item : items) {
				log.info("减少库存 -- "+item);
			}
		}
	}

}
