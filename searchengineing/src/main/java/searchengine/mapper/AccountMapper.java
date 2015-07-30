package searchengine.mapper;

import org.apache.ibatis.annotations.Param;

import searchengine.model.Account;

public interface AccountMapper extends BaseMapper<Account> {
	/**
	 * 更具code查看用户是否存在并未激活
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public Long getByCode(String code) throws Exception;
	public void activiteByCode(String code) throws Exception;
	public Account login(@Param("email")String email,@Param("password")String password) throws Exception;

}
