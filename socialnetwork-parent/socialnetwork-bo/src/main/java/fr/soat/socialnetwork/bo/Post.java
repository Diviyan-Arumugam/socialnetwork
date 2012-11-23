package fr.soat.socialnetwork.bo;

import java.util.Date;

public class Post implements IPost {

	private Integer id;
	private String detail;
	private IUser postedBy;
	private Date postTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public IUser getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(IUser postedBy) {
		this.postedBy = postedBy;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

}
