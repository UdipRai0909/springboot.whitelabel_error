package springboot.whitelabel_error.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/welcome")
	public String hello() {
		return "Hello World";
	}
}
