package controller.findArtist;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.PostDAO;

public class UpdatePostController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� �߰�
		
		int oldPostId = Integer.parseInt(request.getParameter("postId"));
		Post oldPost = postDAO.findPost(oldPostId);
		
		int updatePostCategoryId = Integer.parseInt(request.getParameter("postCategoryId"));
		String updatePostCategoryName = postDAO.findPostCategoryName(updatePostCategoryId);
		String updatePostTitle = request.getParameter("postTitle");
		String updatePostContent = request.getParameter("postContent");
		// ���߿� resources �ϸ鼭 ���� �ʿ�
		String newPostAttachment = "÷������";

		System.out.println("(UpdatePostController) IN 4");
		Post post = new Post(
				oldPostId, updatePostTitle,
				oldPost.getPostDate(), oldPost.getPostView(),
				updatePostContent, newPostAttachment,
				updatePostCategoryId, updatePostCategoryName,
				oldPost.getArtistId(), "artist1");  // "artist1" �κп� artistDAO.findNickname �߰��ؼ� �����ؾ�
		postDAO.update(post);
		
//		return "/findArtist/view/post?postId=" + oldPostId;
		return "redirect:/findArtist/view/post?postId=" + oldPostId;
	}

}
