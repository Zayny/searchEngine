package searchengine.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("sys/managers")
public class SysManagerController {
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	private String userList() {
		System.out.println("SysManagerController.userList()");
		return "sys/users";
	}
	@RequestMapping(value="/web",method=RequestMethod.GET)
	private String toWebScoketHtml() {
		System.out.println("SysManagerController.userList()");
		return "sys/web";
	}
}
