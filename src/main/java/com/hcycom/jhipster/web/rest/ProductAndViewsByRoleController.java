package com.hcycom.jhipster.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Product;
import com.hcycom.jhipster.domain.Views;
import com.hcycom.jhipster.security.SecurityUtils;
import com.hcycom.jhipster.service.UserService;
import com.hcycom.jhipster.service.mapper.ProductMapper;
import com.hcycom.jhipster.service.mapper.ViewsMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = { "产品视图权限管理" }, description = "产品视图权限管理接口")
public class ProductAndViewsByRoleController {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ViewsMapper viewsMapper;

	@Autowired
	private UserService service;

	@RequestMapping(value = "/getuser_ProductAndViews", method = RequestMethod.GET)
	@Timed
	@ApiOperation(value = "获取登录用户产品、视图", notes = "根据当前登录用户获取该用户拥有的产品和视图,无权限控制", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getuser_ProductAndViews() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Product> products = new ArrayList<Product>();
		List<Views> views = new ArrayList<Views>();

		String username = SecurityUtils.getCurrentUserLogin();
		Map<String, Object> user = service.getUser(username);
		if (user.get("username").equals("admin")) {
			products = productMapper.getAllProduct();
			views = viewsMapper.getAllViews();
		} else {
			String[] roles = ((String)user.get("roles")).split(",");
			for (String roleid : roles) {
				List<Product> p = productMapper.getAllProductByRole(Integer.parseInt(roleid));
				List<Views> v = viewsMapper.getAllViewsByRole(Integer.parseInt(roleid));
				products.removeAll(p);
				products.addAll(p);
				views.removeAll(v);
				views.addAll(v);
			}
		}
		map.put("productsDate", products);
		map.put("viewsDate", views);
		map.put("error_code", 1);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
