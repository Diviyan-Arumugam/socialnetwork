package fr.soat.socialnetwork.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class PostDTO extends AbstractEntity {

	private static final long serialVersionUID = -1267861120220841336L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Integer id;
	private String detail;
	@OneToOne
	private UserDTO postedBy;
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
	public UserDTO getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(UserDTO postedBy) {
		this.postedBy = postedBy;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

}
