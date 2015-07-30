package searchengine.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import searchengine.manager.AccountManager;
import searchengine.mapper.AccountMapper;
import searchengine.model.Account;

@Service
public class AccountManagerImpl extends BaseManagerImpl<Account> implements AccountManager {
	@Autowired
	private AccountMapper accountMapper;

	@Override
	protected void initBaseMapper() {
		super.setBaseMapper(accountMapper);
	}

	@Override
	public Long getByCode(String code) throws Exception {
		if (null == code || "".equals(code)) {
			return -1l;
		}
		return accountMapper.getByCode(code);
	}

	@Override
	public void activiteByCode(String code) throws Exception {
		accountMapper.activiteByCode(code);
	}

	@Override
	public Account login(String email, String password) throws Exception {
		return accountMapper.login(email, password);
	}
}
