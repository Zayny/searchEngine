package searchengine.model;

import java.util.Date;

public class BaseModel {

	private Long id;// 主键
	private String uid; // uuid主键
	private Date createOn;// 创建时间
	private Date updateOn;// 更新时间

	public Long getId() {
		return id;
	}

	public String getUid() {
		return uid;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

}
