package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Collaboration;
import model.Post;
import model.dao.CollaborationDAO;
import model.dao.PostDAO;

public class FindArtistManager {
	private static FindArtistManager findArtistMan = new FindArtistManager();
	private PostDAO postDAO;
	private CollaborationDAO collaborationDAO;
	
	private FindArtistManager() {
		try {
			postDAO = new PostDAO();
			collaborationDAO = new CollaborationDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FindArtistManager getInstance() {
		return findArtistMan;
	}
	
	public int createPost(Post post) throws SQLException {
		return postDAO.create(post);		
	}
	
	public int updatePost(Post post) throws SQLException {
		return postDAO.update(post);		
	}
	
	public int removePost(int postId) throws SQLException {
		return postDAO.remove(postId);		
	}
	
	public List<Post> searchPostTitle(String PostTitle) throws SQLException {
		return postDAO.searchPostTitle(PostTitle);
	}
	
	public List<Post> findPostList() throws SQLException {
		return postDAO.findPostList();
	}
	
	public int updateView(Post post) throws SQLException {
		return postDAO.updateView(post);
	}
	
	public int createCollaboration(Collaboration collaboration) throws SQLException {
		return collaborationDAO.create(collaboration);		
	}
	
	public int updateCollaboration(Collaboration collaboration) throws SQLException {
		return collaborationDAO.update(collaboration);		
	}
	
	public int removeCollaboration(int collaborationId) throws SQLException {
		return collaborationDAO.remove(collaborationId);		
	}
	
}
