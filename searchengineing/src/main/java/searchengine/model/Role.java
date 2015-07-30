package searchengine.model;

public class Role extends BaseModel {

	private String name;
	private String describe;
	private Integer mark;
	private Integer middleId;

	public String getName() {
		return name;
	}

	public String getDescribe() {
		return describe;
	}

	public Integer getMark() {
		return mark;
	}

	public Integer getMiddleId() {
		return middleId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public void setMiddleId(Integer middleId) {
		this.middleId = middleId;
	}

}
