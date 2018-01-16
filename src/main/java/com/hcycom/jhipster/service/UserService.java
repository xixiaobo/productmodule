package com.hcycom.jhipster.service;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcycom.jhipster.client.AuthorizedFeignClient;


@AuthorizedFeignClient(name = "jhipsteruaa")
public interface UserService {
	
	@GetMapping("/api/users/{login}")
	Map<String, Object> getUser(@PathVariable(value="login") String login);
	

}
