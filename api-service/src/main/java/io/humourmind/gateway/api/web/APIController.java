package io.humourmind.gateway.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@GetMapping(value = {"/hello", "/p/hello", "/springfans/pp/hello", "/pnh/hello", "/rh/hello"})
	public String hello() {
		return "Hi Spring fans!";
	}

	@GetMapping(value = {"/hello2"})
	public String hello2() {
		return "Hi Spring APAC fans!";
	}

}
