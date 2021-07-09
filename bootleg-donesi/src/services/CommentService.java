package services;

import java.util.ArrayList;


import model.Comment;
import model.CommentStatus;


public class CommentService {
	public static ArrayList<Comment> commentList = new ArrayList<Comment>();
	
	private static void save() {
		
	}
	
	public static void load() {
		commentList.add(new Comment(1, 1, 2, 4, "Nije lose",CommentStatus.APPROVED,"Mihajlo"));
		commentList.add(new Comment(2, 1, 2, 3, "Nije lose",CommentStatus.APPROVED,"Mihajlo"));
		commentList.add(new Comment(3, 1, 2, 4, "Sranje",CommentStatus.WAITING,"Mihajlo"));
		
	}
	
	private static Integer generateID() 
	{
		int id = 0;
		for (Comment comment : commentList) {
			if (comment.getEntityID() > id) {
				id = comment.getEntityID();
			}
		}
			
		return id + 1;
	}
	

	
	public static ArrayList<Comment> getAll() {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for (Comment comment: commentList) {
			if (!comment.isDeleted()) {
				comments.add(comment);	
			}
		}		
		return comments;
	}
	
	public static ArrayList<Comment> getApproved() {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for (Comment comment: commentList) {
			if (!comment.isDeleted() && comment.getAproved() == CommentStatus.APPROVED) {
				comments.add(comment);
			}
		}		
		return comments;
	}
	
	public static ArrayList<Comment> getAllForRestaurant(int id) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for (Comment comment: commentList) {
			if (!comment.isDeleted() && comment.getRestaurant() == id) {
				comments.add(comment);
			}
		}		
		return comments;
	}
	
	public static ArrayList<Comment> getApprovedForRestaurant(int id) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		for (Comment comment: commentList) {
			if (!comment.isDeleted() && comment.getRestaurant() == id && comment.getAproved() == CommentStatus.APPROVED) {
				comments.add(comment);
			}
		}		
		return comments;
	}
	

	public static void delete(int entityID) {
		for (Comment comment : commentList) {
			if (comment.getEntityID() == entityID) {
				comment.setDeleted(true);
				break;
			}
		}
		save();
		
	}

	public static void denyComment(int entityID) {
		for (Comment comment : getAll()) {
			if (comment.getEntityID() == entityID) {
				comment.setApproved(CommentStatus.DENIED);
				break;
			}
		}
		save();
		
	}
	
	public static void approveComment(int entityID) {
		for (Comment comment : getAll()) {
			if (comment.getEntityID() == entityID) {
				comment.setApproved(CommentStatus.APPROVED);
				break;
			}
		}
		save();
		
	}
	
	public static int calculateRestaurantRating(int entityID) {
		double average = 0;
		double count = 0;
		double sum = 0;
		
		for (Comment comment : getAll()) {
			if (comment.getRestaurant() == entityID) {
				count++;
				sum = sum + comment.getRating();
			}
		}
		
		if (count == 0) {
			return 0;
		}
		else {
			average = sum/count;
			int adjustedAverage = (int) Math.round(average);	
			return adjustedAverage;
		}
		
	}
	
	public static boolean checkCommentable(int delivered, int restauratn, int customer) {
		int comments = 0;
		for (Comment comment : getAll()) {
			if(comment.getCustomer() == customer && comment.getRestaurant() == restauratn) {
				comments++;
			}
		}
		
		if(comments < delivered) {
			return true;
		}
		else {
			return false;
		}

	}

	public static void addComment(Comment comment) {
		comment.setApproved(CommentStatus.WAITING);
		comment.setEntityID(generateID());
		comment.setDeleted(false);
		commentList.add(comment);
		save();
	}
	
}
