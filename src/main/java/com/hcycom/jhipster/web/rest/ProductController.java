package com.hcycom.jhipster.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Product;
import com.hcycom.jhipster.service.mapper.ProductMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(tags = { "产品管理" }, description = "产品管理接口")
public class ProductController {

	@Autowired
	private ProductMapper productMapper;

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/product--POST')")
	@ApiOperation(value = "创建产品", notes = "传入产品表参数，创建产品", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> product(@RequestBody Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		product.setUuid(uuid);
		int i = productMapper.addProduct(product);
		if (i > 0) {
			map.put("data", product);
			map.put("msg", "产品添加成功！");
			map.put("error_code", 0);
		} else if (i == 0) {
			map.put("msg", "产品添加失败！");
			map.put("error_code", 1);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/product_delete/{uuid}", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/product_delete/{uuid}--DELETE')")
	@ApiOperation(value = "删除产品", notes = "传入产品的id，删除此产品", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> product_delete(@PathVariable String uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Product product = new Product();
		product.setUuid(uuid);
		int i = productMapper.deleteProduct(product);
		if (i > 0) {
			map.put("msg", "产品删除成功！");
			map.put("error_code", 0);
		} else if (i == 0) {
			map.put("msg", "产品删除失败！");
			map.put("error_code", 1);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@DeleteMapping("/product_deleteByMore")
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/product_deleteByMore--DELETE')")
	@ApiOperation(value = "删除多个产品", httpMethod = "DELETE", notes = "删除多个产品根据uuid数组")
	@ApiParam(name = "ids", value = "参数类型为String[],为产品uuid的数组", required = true)
	public ResponseEntity<Map<String, Object>> product_deleteByMore(@RequestBody String[] uuids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String uuid : uuids) {
			Product product = new Product();
			product.setUuid(uuid);
			productMapper.deleteProduct(product);
		}
		map.put("msg", "产品批量删除成功！");
		map.put("error_code", 0);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/product_put", method = RequestMethod.PUT)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/product_put--PUT')")
	@ApiOperation(value = "修改产品", notes = "传入产品表参数，根据id修改产品", httpMethod = "PUT")
	public ResponseEntity<Map<String, Object>> product_put(@RequestBody Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = productMapper.updateProduct(product);
		product = productMapper.getProductByID(product);
		if (i > 0) {
			map.put("data", product);
			map.put("msg", "产品修改成功！");
			map.put("error_code", 0);
		} else if (i == 0) {
			map.put("msg", "产品修改失败！");
			map.put("error_code", 1);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/product_get/{uuid}", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/product_get/{uuid}--GET')")
	@ApiOperation(value = "获取产品详情", notes = "根据id获取产品详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> product_get(@PathVariable String uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Product product = new Product();
		product.setUuid(uuid);
		product = productMapper.getProductByID(product);
		if (product.getProduct_name() != null && !product.getProduct_name().equals("")) {
			map.put("data", product);
			map.put("msg", "产品详情获取成功！");
			map.put("error_code", 0);
		} else if (product.getProduct_name() == null && product.getProduct_name().equals("")) {
			map.put("msg", "产品详情获取失败或产品不存在！");
			map.put("error_code", 1);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/product_getAll", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/product_getAll--GET')")
	@ApiOperation(value = "获取所有产品", notes = "获取所有产品详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> product_getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Product> list = productMapper.getAllProduct();
		if (list.size() > 0) {
			map.put("data", list);
			map.put("msg", "所有产品详情获取成功！");
			map.put("error_code", 0);
		} else if (list.size() == 0) {
			map.put("msg", "所有产品详情获取失败或产品为空！");
			map.put("error_code", 1);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
