package searchengine.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sys")
public class LoginController {
	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
	public String login(HttpServletRequest request, String name, String pwd) throws Exception {
		if (null != name && null != pwd) {
			if ("admin@163.com".equals(name) && "admin".equals(pwd)) {
				return "sys/index";
			}
		}
		return "sys/loginerror";
	}

	@RequestMapping(value = "/login")
	public String dologin(HttpServletRequest request) throws Exception {
		System.out.println("LoginController.login()");
		System.out.println(request.getHeader("Referer"));
		System.out.println(request.getParameter("name"));
		return "sys/index";
	}

}
