package fr.soat.socialnetwork.dao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="discussion")
public class DiscussionDTO extends AbstractEntity {

	private static final long serialVersionUID = 2536884411241773477L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Integer id;
	private String subject;
	@OneToOne
	//@Column(name="USER_CREATEDBY_ID")
    private UserDTO createdBy;
	private Date creationTime;
	@OneToOne
    private UserDTO updatedBy;
	private Date updateTime;
	@OneToOne
    private GroupDTO group;
	
	//@OneToMany(cascade = CascadeType.ALL)
	private List<PostDTO> posts;

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
	public UserDTO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public GroupDTO getGroup() {
		return group;
	}
	public void setGroup(GroupDTO group) {
		this.group = group;
	}
	public List<PostDTO> getPosts() {
		return posts;
	}
	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}
	public UserDTO getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserDTO updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

