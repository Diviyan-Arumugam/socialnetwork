package fr.soat.socialnetwork.bo;

import java.util.Date;

public interface IPost {

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getDetail();

	public abstract void setDetail(String detail);

	public abstract User getPostedBy();

	public abstract void setPostedBy(User postedBy);

	public abstract Date getPostTime();

	public abstract void setPostTime(Date postTime);

}