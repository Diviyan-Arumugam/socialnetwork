package fr.soat.socialnetwork.ui;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import fr.soat.socialnetwork.bo.IDiscussion;
import fr.soat.socialnetwork.bo.IPost;
import fr.soat.socialnetwork.bo.Post;
import fr.soat.socialnetwork.service.discussion.IDiscussionService;

@Named("discussionBean")
@RequestScoped
public class DiscussionBean {

	private IDiscussionService discussionService;

	private List<IDiscussion> allDiscussions;
	private IDiscussion currentDiscussion;
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

	public IDiscussion getCurrentDiscussion() {
		return currentDiscussion;
	}

	public void setCurrentDiscussion(IDiscussion currentDiscussion) {
		this.currentDiscussion = currentDiscussion;
	}

	public void setCurrentAnswer(String currentAnswer) {
		this.currentAnswer = currentAnswer;
	}

	// We always reload discussion (perhaps, it has been updated
	public void onRowSelect(SelectEvent event) {  
		Integer discussionId = ((IDiscussion) event.getObject()).getId();
		this.currentDiscussion = discussionService.getDiscussionById(discussionId);
    }

	public void addAnswer() {
    	if(this.currentAnswer != null && !this.currentAnswer.isEmpty()){
    		IPost post = new Post();
    		post.setDetail(this.currentAnswer);
    		post.setPostedBy(this.sessionBean.getUser());
    		post.setPostTime(new Date());
    		this.currentDiscussion.getPosts().add(post);
    		this.currentAnswer = null;
    	}
	}
}
