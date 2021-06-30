package services;

import java.util.ArrayList;


import model.Comment;
import model.CommentStatus;


public class CommentService {
	public static ArrayList<Comment> commentList = new ArrayList<Comment>();
	
	private static void save() {
		
	}
	
	public static void load() {
		commentList.add(new Comment(1, 1, 1, 1, 3, "Nije lose",CommentStatus.APPROVED,"Mihajlo"));
		commentList.add(new Comment(2, 1, 2, 1, 3, "Nije lose",CommentStatus.APPROVED,"Mihajlo"));
		commentList.add(new Comment(3, 1, 3, 1, 3, "Sranje",CommentStatus.WAITING,"Mihajlo"));
		
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
	
}
