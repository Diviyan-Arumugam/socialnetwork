package fr.soat.socialnetwork.bo;

import java.util.Date;
import java.util.List;

public class Discussion implements IDiscussion {

	private Integer id;
	private String subject;
	private User createdBy;
	private Date creationTime;
	private User updatedBy;
	private Date updateTime;
	private IGroup group;
	
	private List<IPost> posts;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public IGroup getGroup() {
		return group;
	}
	public void setGroup(IGroup group) {
		this.group = group;
	}
	public List<IPost> getPosts() {
		return posts;
	}
	public void setPosts(List<IPost> posts) {
		this.posts = posts;
	}
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
