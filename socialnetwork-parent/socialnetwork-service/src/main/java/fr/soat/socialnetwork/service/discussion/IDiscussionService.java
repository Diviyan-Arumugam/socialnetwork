package fr.soat.socialnetwork.service.discussion;

import java.util.List;

import fr.soat.socialnetwork.bo.Discussion;
import fr.soat.socialnetwork.bo.Group;

public interface IDiscussionService {

	List<Discussion> getAllDiscussionsByGroups(List<Group> groups);
	Discussion getDiscussionById(int discussionId);
}
