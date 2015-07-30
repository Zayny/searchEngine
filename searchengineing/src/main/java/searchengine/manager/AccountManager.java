package searchengine.manager;

import searchengine.model.Account;

public interface AccountManager extends BaseManager<Account>{
	/**
	 * 更具code查看用户是否存在并未激活
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Long getByCode(String code) throws Exception;
	/**
	 * 激活
	 * @param code
	 * @throws Exception
	 */
	public void activiteByCode(String code) throws Exception;
	/**
	 * 登陆
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Account login(String email,String password) throws Exception;

}
