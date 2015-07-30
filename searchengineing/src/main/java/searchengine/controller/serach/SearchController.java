package searchengine.controller.serach;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import searchengine.Vo.ResponseVo;
import searchengine.manager.SearchManager;

@Controller
@RequestMapping("search")
public class SearchController {
	private static final Logger log = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchManager searchManager;

	@RequestMapping(method = RequestMethod.GET)
	public String toSearcher(HttpServletRequest request) {
		log.debug(request.getRequestURI());
		return "search/index";
	}

	@RequestMapping(value = "/getmsg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo serarch(String keyWord) throws Exception {
		keyWord = new String(keyWord.getBytes("iso8859-1"), "utf8").trim();
		log.debug("keyWord : " + keyWord);
		return new ResponseVo(searchManager.searchOnKey(keyWord));
	}
}
