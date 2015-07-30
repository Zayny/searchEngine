package searchengine.controller.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import searchengine.Vo.ResponseVo;
import searchengine.controller.BaseController.BaseController;
import searchengine.helper.DateHelper;
import searchengine.helper.HexHelper;
import searchengine.helper.MailHelper;
import searchengine.manager.AccountManager;
import searchengine.model.Account;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
	@Autowired
	private AccountManager accountManager;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo getById(@PathVariable Long id) throws Exception {
		return new ResponseVo(accountManager.getById(id));
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo add(Account account) throws Exception {
		account.setUpdateOn(new Date());
		String dateStr = DateHelper.fomertDate(new Date());
		String activCode = dateStr + Math.random();
		String md5code = HexHelper.getEncryptedPwd(activCode);
		account.setActivateCode(md5code);
		accountManager.add(account);
		String url = "localhost:8080/tail/accounts/activate/" + md5code;
		String ht = "<a href=" + url + ">" + url + "</a>";
		MailHelper.sendMailHtml(account.getEmail(), "TAIL用户激活", ht);
		return new ResponseVo();
	}

	@RequestMapping(value = "/activate/{code}", method = RequestMethod.GET)
	public String activate(@PathVariable String code, RedirectAttributes attributes) throws Exception {
		if (accountManager.getByCode(code) == 1) {
			accountManager.activiteByCode(code);
			attributes.addAttribute("code", 8421);
			return "redirect:/search";
		}
		return "fail";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVo login(String email, String password) throws Exception {
		Account ac = accountManager.login(email, password);
		if (null != ac) {
			return new ResponseVo(1);
		}
		return new ResponseVo(0);
	}
}
