package springboot.whitelabel_error.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import springboot.whitelabel_error.model.ErrorJson;

@RestController
public class CustomErrorController implements ErrorController {

	private static final String PATH = "/error";

	@Value("${debug}")
	private boolean debug;
	
	@Autowired
	private ErrorAttributes errorAttributes;
	
	@RequestMapping(value=PATH)
	ErrorJson error(HttpServletRequest request, HttpServletResponse response) {
		return new ErrorJson(response.getStatus(), getErrorAttributes(request, debug));
	}
	
//	@GetMapping(PATH)
//	public String defaultErrorMessage() {
//		return "Requested Resource is not found!!";
//	}
	
	@Override
	public String getErrorPath() {
		return PATH;
	}
	
	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttr = new ServletRequestAttributes(request);
		return errorAttributes.getErrorAttributes(requestAttr, includeStackTrace);
	}
	
}
