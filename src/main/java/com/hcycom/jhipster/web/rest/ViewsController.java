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
import com.hcycom.jhipster.domain.Views;
import com.hcycom.jhipster.service.mapper.ViewsMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(tags = { "视图管理" }, description = "视图管理接口")
public class ViewsController {

	@Autowired
	private ViewsMapper viewsMapper;
	
	@RequestMapping(value = "/views", method = RequestMethod.POST)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/views')")
	@ApiOperation(value = "创建视图", notes = "传入视图表参数，创建视图", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> views(@RequestBody Views views) {
		Map<String, Object> map=new HashMap<String, Object>();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		views.setUuid(uuid);
		int i=viewsMapper.addViews(views);
		if(i>0){
			map.put("data", views);
			map.put("msg", "视图添加成功！");
			map.put("error_code", 0);
		}else if(i==0){
			map.put("msg", "视图添加失败！");
			map.put("error_code", 1);
		}else{
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/views_delete/{uuid}", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/views_delete/{uuid}')")
	@ApiOperation(value = "删除视图", notes = "传入视图的id，删除此视图", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> views_delete(@PathVariable String uuid) {
		Map<String, Object> map=new HashMap<String, Object>();
		Views views=new Views();
		views.setUuid(uuid);
		int i=viewsMapper.deleteViews(views);
		if(i>0){
			map.put("msg", "视图删除成功！");
			map.put("error_code", 0);
		}else if(i==0){
			map.put("msg", "视图删除失败！");
			map.put("error_code", 1);
		}else{
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	} 
	
	@DeleteMapping("/views_deleteByMore")
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/views_deleteByMore')")
	@ApiOperation(value = "删除多个视图", httpMethod = "DELETE", notes = "删除多个视图根据uuid数组")
	@ApiParam(name = "ids", value = "参数类型为String[],为视图uuid的数组", required = true)
	public ResponseEntity<Map<String, Object>> views_deleteByMore(@RequestBody String[] uuids) {
		Map<String, Object> map=new HashMap<String, Object>();
		for (String uuid : uuids) {
			Views views=new Views();
			views.setUuid(uuid);
			viewsMapper.deleteViews(views);
		}
		map.put("msg", "视图批量删除成功！");
		map.put("error_code", 0);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/views_put", method = RequestMethod.PUT)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/views_put')")
	@ApiOperation(value = "修改视图", notes = "传入视图表参数，根据id修改视图", httpMethod = "PUT")
	public ResponseEntity<Map<String, Object>> views_put(@RequestBody Views views) {
		Map<String, Object> map=new HashMap<String, Object>();
		int i=viewsMapper.updateViews(views);
		views=viewsMapper.getViewsByID(views);
		if(i>0){
			map.put("data", views);
			map.put("msg", "视图修改成功！");
			map.put("error_code", 0);
		}else if(i==0){
			map.put("msg", "视图修改失败！");
			map.put("error_code", 1);
		}else{
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/views_get/{uuid}", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/views_get/{uuid}')")
	@ApiOperation(value = "获取视图详情", notes = "根据id获取视图详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> views_get(@PathVariable String uuid) {
		Map<String, Object> map=new HashMap<String, Object>();
		Views views=new Views();
		views.setUuid(uuid);
		views=viewsMapper.getViewsByID(views);
		if(views.getViews_name_cn()!=null&&!views.getViews_name_cn().equals("")){
			map.put("data", views);
			map.put("msg", "视图详情获取成功！");
			map.put("error_code", 0);
		}else if(views.getViews_name_cn()==null&&views.getViews_name_cn().equals("")){
			map.put("msg", "视图详情获取失败或产品不存在！");
			map.put("error_code", 1);
		}else{
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/views_getAll", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'productmodule/api/views_getAll')")
	@ApiOperation(value = "获取所有视图", notes = "获取所有视图详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> views_getAll() {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Views> list=viewsMapper.getAllViews();
		if(list.size()>0){
			map.put("data", list);
			map.put("msg", "所有视图详情获取成功！");
			map.put("error_code", 0);
		}else if(list.size()==0){
			map.put("msg", "所有视图详情获取失败或产品为空！");
			map.put("error_code", 1);
		}else{
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	
}
