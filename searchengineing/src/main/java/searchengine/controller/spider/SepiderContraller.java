package searchengine.controller.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import searchengine.manager.SpiderManager;

@Controller
@RequestMapping("/climbs")
public class SepiderContraller {
	
	@Autowired
	SpiderManager spiderManager;
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView tospider(){
		return new ModelAndView("spider/index");
	}
	@RequestMapping("/def")
	public String defaultClimb(String url){
		return "";
	}
	@RequestMapping(value="/jobs",method=RequestMethod.GET)
	@ResponseBody
	public boolean lagouClimb(String url){
		return spiderManager.lagouClimb(url);
	}
}
