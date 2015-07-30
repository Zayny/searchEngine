package searchengine.model;

public class Menu extends BaseModel {

	private String name;
	private String code;
	private String parentCode;
	private String text;
	private String url;
	private String target;

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}

	public String getTarget() {
		return target;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
