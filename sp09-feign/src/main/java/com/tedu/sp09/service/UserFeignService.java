package com.tedu.sp09.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.UserService;
import com.tedu.web.util.JsonResult;

@FeignClient(name = "user-service", fallback = UserFeignServiceFB.class)
public interface UserFeignService {
	@GetMapping("/{userId}")
	JsonResult<User> getUser(@PathVariable Integer userId);

	// 拼接路径 /{userId}/score?score=新增积分
	// 如果请求参数和方法参数同名,@RequestParam可省略
	@GetMapping("/{userId}/score")
	JsonResult addScore(@PathVariable Integer userId, @RequestParam Integer score);
}
