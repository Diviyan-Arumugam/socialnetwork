package fr.soat.socialnetwork.service.discussion;

import java.util.List;

import fr.soat.socialnetwork.bo.Group;
import fr.soat.socialnetwork.bo.IDiscussion;

public interface IDiscussionService {

	List<IDiscussion> getAllDiscussionsByGroups(List<Group> groups);
	IDiscussion getDiscussionById(int discussionId);
}
