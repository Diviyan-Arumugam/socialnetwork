package fr.soat.socialnetwork.service.discussion;

import java.util.List;

import fr.soat.socialnetwork.bo.IDiscussion;
import fr.soat.socialnetwork.bo.IGroup;

public interface IDiscussionService {

	List<IDiscussion> getAllDiscussionsByGroups(List<IGroup> groups);
	IDiscussion getDiscussionById(int discussionId);
}
