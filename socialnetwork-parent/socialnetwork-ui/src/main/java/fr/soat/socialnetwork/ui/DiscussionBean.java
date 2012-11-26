package fr.soat.socialnetwork.ui;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import fr.soat.socialnetwork.bo.Discussion;
import fr.soat.socialnetwork.bo.Post;
import fr.soat.socialnetwork.service.discussion.IDiscussionService;

@Named("discussionBean")
@RequestScoped
public class DiscussionBean {

	private IDiscussionService discussionService;

	private List<Discussion> allDiscussions;
	private Discussion currentDiscussion;
	private String currentAnswer;
	
	@Inject
	private SessionBean sessionBean;
	
	protected DiscussionBean()
	{
	}

	@Inject
	public DiscussionBean(
			IDiscussionService discussionService)
	{
		this.discussionService = discussionService;
		this.currentDiscussion = null;
	}

	@PostConstruct
	public void init()
	{
	}

	public Discussion getCurrentDiscussion() {
		return currentDiscussion;
	}

	public void setCurrentDiscussion(Discussion currentDiscussion) {
		this.currentDiscussion = currentDiscussion;
	}

	public List<Discussion> getAllDiscussions() {
		return discussionService.getAllDiscussionsByGroups(this.sessionBean.getUser().getGroups());
	}

    public String getCurrentAnswer() {
		return currentAnswer;
	}

	public void setCurrentAnswer(String currentAnswer) {
		this.currentAnswer = currentAnswer;
	}

	// We always reload discussion (perhaps, it has been updated
	public void onRowSelect(SelectEvent event) {  
		Integer discussionId = ((Discussion) event.getObject()).getId();
		this.currentDiscussion = discussionService.getDiscussionById(discussionId);
    }

	public void addAnswer() {
    	if(this.currentAnswer != null && !this.currentAnswer.isEmpty()){
    		Post post = new Post();
    		post.setDetail(this.currentAnswer);
    		post.setPostedBy(this.sessionBean.getUser());
    		post.setPostTime(new Date());
    		this.currentDiscussion.getPosts().add(post);
    		this.currentAnswer = null;
    	}
    }
}
