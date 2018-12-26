
package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.model.Users;
import com.demo.service.UsersService;

/**
 * Users Controller
 * 
 * @author
 * @date 2018-12-12
 */
@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 * hello 测试
	 * 
	 * @return
	 */
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello New friend !";
	}

	/**
	 * 获取数据并放回对象到页面
	 * 
	 * @param model
	 * @return Model对象
	 */
	@RequestMapping(value = "/findAll")
	public String admin(Model model) {
		List<Users> list = usersService.findAll();

		model.addAttribute("hello", list);
		return "admin/index";
	}

	/**
	 * 通过参数获取数据并返回JSON字符串到页面
	 * 
	 * @param id
	 * @return JSON对象
	 */
	@RequestMapping(value = "/findById", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findById(int id) {
		Users user = usersService.findById(id);

		return JSONObject.toJSONString(user, SerializerFeature.WriteMapNullValue);

	}

}
