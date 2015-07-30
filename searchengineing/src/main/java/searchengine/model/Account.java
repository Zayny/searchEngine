package searchengine.model;

public class Account extends BaseModel {

	private String name;
	private String email;
	private String mobile;
	private String industry;
	private String password;
	private Integer age;
	private String activateCode;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getIndustry() {
		return industry;
	}

	public String getPassword() {
		return password;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getActivateCode() {
		return activateCode;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}

}
