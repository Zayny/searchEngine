package searchengine.controller.game;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/games")
public class GameController {	
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String toTypedGame(HttpServletRequest request){
		System.out.println("GameController.toTypedGame()");
		return "games/games";
	}
	
	@RequestMapping(value = "/typed",method = RequestMethod.GET)
	public String toGame(HttpServletRequest request){
		return "games/games";
	}
}
