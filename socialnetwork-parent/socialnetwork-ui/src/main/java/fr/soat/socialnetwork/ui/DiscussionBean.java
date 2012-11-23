package fr.soat.socialnetwork.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabCloseEvent;

import fr.soat.socialnetwork.bo.IDiscussion;
import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.discussion.IDiscussionService;

@Named("discussionBean")
@ViewScoped
public class DiscussionBean implements Serializable {

	private static final long serialVersionUID = 910121394151244927L;

	private IDiscussionService discussionService;

	private List<IDiscussion> allDiscussions;
	private List<IDiscussion> openedDiscussions;
	private IDiscussion currentDiscussion;
	private int tabIndex;

	protected DiscussionBean()
	{
	}

	@Inject
	public DiscussionBean(
			IDiscussionService discussionService)
	{
		this.discussionService = discussionService;
	}

	@PostConstruct
	public void init()
	{
		IUser user = (IUser) FacesContext.getCurrentInstance().getExternalContext()
		        .getSessionMap().get(LoginBean.AUTH_KEY);
		if(user != null){
			this.allDiscussions = discussionService.getAllDiscussionsByGroups(user.getGroups());
			this.tabIndex = 0;
		}
	}

	public IDiscussion getCurrentDiscussion() {
		return currentDiscussion;
	}

	public void setCurrentDiscussion(IDiscussion currentDiscussion) {
		this.currentDiscussion = currentDiscussion;
	}

	public List<IDiscussion> getAllDiscussions() {
		return allDiscussions;
	}

	public void setAllDiscussions(List<IDiscussion> allDiscussions) {
		this.allDiscussions = allDiscussions;
	}

	public void onRowSelect(SelectEvent event) {  
		int discussionId = ((IDiscussion) event.getObject()).getId();
		int currentTabIndex = 1;
		if(openedDiscussions == null){
			openedDiscussions = new ArrayList<IDiscussion>();
		}
		for(IDiscussion discussion : openedDiscussions){
			if(discussion.getId() == discussionId){
				tabIndex = currentTabIndex;
				return;
			}
			currentTabIndex++;
		}
		this.currentDiscussion = discussionService.getDiscussionById(discussionId);
		openedDiscussions.add(this.currentDiscussion);
		tabIndex = currentTabIndex;
    }

	public List<IDiscussion> getOpenedDiscussions() {
		return openedDiscussions;
	}

	public void setOpenedDiscussions(List<IDiscussion> openedDiscussions) {
		this.openedDiscussions = openedDiscussions;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

    public void closeDiscussion(TabCloseEvent event) {
    	for(IDiscussion discussion : this.openedDiscussions){
    		if(event.getTab().getTitle().equals(discussion.getSubject())){
    			this.openedDiscussions.remove(discussion);
    			tabIndex = 0;
    			break;
    		}
    	}
    } 
}
