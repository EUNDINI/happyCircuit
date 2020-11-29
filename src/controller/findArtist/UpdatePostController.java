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
		// 로그인 추가
		
		int oldPostId = Integer.parseInt(request.getParameter("postId"));
		Post oldPost = postDAO.findPost(oldPostId);
		
		int updatePostCategoryId = Integer.parseInt(request.getParameter("postCategoryId"));
		String updatePostCategoryName = postDAO.findPostCategoryName(updatePostCategoryId);
		String updatePostTitle = request.getParameter("postTitle");
		String updatePostContent = request.getParameter("postContent");
		// 나중에 resources 하면서 수정 필요
		String newPostAttachment = "첨부파일";

		System.out.println("(UpdatePostController) IN 4");
		Post post = new Post(
				oldPostId, updatePostTitle,
				oldPost.getPostDate(), oldPost.getPostView(),
				updatePostContent, newPostAttachment,
				updatePostCategoryId, updatePostCategoryName,
				oldPost.getArtistId(), "artist1");  // "artist1" 부분에 artistDAO.findNickname 추가해서 수정해야
		postDAO.update(post);
		
//		return "/findArtist/view/post?postId=" + oldPostId;
		return "redirect:/findArtist/view/post?postId=" + oldPostId;
	}

}
