package fr.soat.socialnetwork.service.discussion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.Discussion;
import fr.soat.socialnetwork.bo.IDiscussion;
import fr.soat.socialnetwork.bo.IGroup;
import fr.soat.socialnetwork.bo.IPost;
import fr.soat.socialnetwork.bo.Post;
import fr.soat.socialnetwork.bo.User;

@Named("DiscussionService")
@ApplicationScoped
@ Default
public class DiscussionService implements IDiscussionService {

	public List<IDiscussion> getAllDiscussionsByGroups(List<IGroup> groups) {
		List<IDiscussion> discussions = new ArrayList<IDiscussion>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2012);
		cal.set(Calendar.MONTH,11);
		cal.set(Calendar.DATE,11);
		User userPost1 = new User();
		userPost1.setLogin("userPost1");
		userPost1.setFirstName("Didier");
		userPost1.setLastName("MAURER");

		IDiscussion discussion1 = new Discussion();
		discussion1.setId(1);
		discussion1.setSubject("Nouveau sujet de discussion");
		discussion1.setCreatedBy(userPost1);
		discussion1.setCreationTime(cal.getTime());
	
		cal.set(Calendar.DATE,14);
		
		discussion1.setUpdatedBy(userPost1);
		discussion1.setUpdateTime(cal.getTime());
		
		discussions.add(discussion1);
		
		
		cal.set(Calendar.DATE,13);
		User userPost2 = new User();
		userPost2.setLogin("userPost2");
		userPost2.setFirstName("Guillaume");
		userPost2.setLastName("PREHU");

		IDiscussion discussion2 = new Discussion();
		discussion2.setId(2);
		discussion2.setSubject("Autre nouveau sujet de discussion");
		discussion2.setCreatedBy(userPost2);
		discussion2.setCreationTime(cal.getTime());
	
		cal.set(Calendar.DATE,18);
		
		discussion2.setUpdatedBy(userPost1);
		discussion2.setUpdateTime(cal.getTime());

		discussions.add(discussion2);

		return discussions;
	}

	public IDiscussion getDiscussionById(int discussionId) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2012);
		cal.set(Calendar.MONTH,11);
		cal.set(Calendar.DATE,11);
		User userPost1 = new User();
		userPost1.setLogin("userPost1");
		userPost1.setFirstName("Didier");
		userPost1.setLastName("MAURER");

		IDiscussion discussion = new Discussion();
		if(discussionId == 1){
			discussion.setId(1);
			discussion.setSubject("Nouveau sujet de discussion");
			discussion.setCreatedBy(userPost1);
			discussion.setCreationTime(cal.getTime());
	
			List<IPost> posts = new ArrayList<IPost>();
			
			IPost post1 = new Post();
			post1.setDetail("Voici ce que j'ai à dire !");
			post1.setPostedBy(userPost1);
			post1.setPostTime(cal.getTime());
			posts.add(post1);
			
			IPost post2 = new Post();
			post2.setDetail("Je ne suis pas d'accord !!!");
			User userPost2 = new User();
			userPost2.setLogin("userPost2");
			userPost2.setFirstName("Guillaume");
			userPost2.setLastName("PREHU");
			post2.setPostedBy(userPost2);
			cal.set(Calendar.DATE,12);
			post2.setPostTime(cal.getTime());
			posts.add(post2);
	
			IPost post3 = new Post();
			post3.setDetail("Moi non plus !!!");
			User userPost3 = new User();
			userPost3.setLogin("userPost3");
			userPost3.setFirstName("Christophe");
			userPost3.setLastName("CHABANOIS");
			post3.setPostedBy(userPost3);
			cal.set(Calendar.DATE,13);
			post3.setPostTime(cal.getTime());
			posts.add(post3);
		
			IPost post4 = new Post();
			post4.setDetail("C'est moi qui décide !");
			post4.setPostedBy(userPost1);
			cal.set(Calendar.DATE,14);
			post4.setPostTime(cal.getTime());
			posts.add(post4);
			
			discussion.setPosts(posts);
			discussion.setUpdatedBy(userPost1);
			discussion.setUpdateTime(cal.getTime());
		}else if(discussionId == 2){
			cal.set(Calendar.DATE,13);
			User userPost2 = new User();
			userPost2.setLogin("userPost2");
			userPost2.setFirstName("Guillaume");
			userPost2.setLastName("PREHU");
			
			discussion.setId(2);
			discussion.setSubject("Autre nouveau sujet de discussion");
			discussion.setCreatedBy(userPost2);
			discussion.setCreationTime(cal.getTime());
	
			List<IPost> posts = new ArrayList<IPost>();

			IPost post1 = new Post();
			post1.setDetail("Je n'ai rien a dire !");
			post1.setPostedBy(userPost2);
			post1.setPostTime(cal.getTime());
			posts.add(post1);
			
			IPost post2 = new Post();
			post2.setDetail("Pas mieux !!!");
			post2.setPostedBy(userPost1);
			cal.set(Calendar.DATE,18);
			post2.setPostTime(cal.getTime());
			posts.add(post2);

			discussion.setPosts(posts);
			discussion.setUpdatedBy(userPost1);
			discussion.setUpdateTime(cal.getTime());
		}
		return discussion;
	}
}
