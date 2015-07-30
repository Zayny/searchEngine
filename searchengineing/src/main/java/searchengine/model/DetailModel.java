package searchengine.model;

import java.sql.Timestamp;

public class DetailModel extends BaseModel {
	
	
	private String title;
	private String shortDesc;
	private String url;
	private Timestamp time;
	private String jobDesc;
	private String linkman;
	
	public String getTitle() {
		return title;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public String getUrl() {
		return url;
	}
	public Timestamp getTime() {
		return time;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

}
