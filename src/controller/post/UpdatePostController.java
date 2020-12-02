package controller.post;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.PostDAO;

public class UpdatePostController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ����
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			return "redirect:/post/list";	
        }
		
		// �α��� �� ����� post�� �ۼ��� ����� ������ �˻��ϴ� �κ� �߰�
		int oldPostId = Integer.parseInt(request.getParameter("postId"));
		Post oldPost = postDAO.findPost(oldPostId);
		String artistId = oldPost.getArtistId();
		if (!ArtistSessionUtils.isLoginArtist(artistId, session)) { // �α��� �� ����� post�� �ۼ��� ����� �ٸ���
			return "redirect:/post/list";	
		}

		// GET method: �ʱⰪ ���� �� form ȭ�� ���
		if (request.getMethod().equals("GET")) {
			Post post = postDAO.findPost(oldPostId);
			request.setAttribute("post", post);	
			return "/post/updatePost.jsp";
		}

		// POST method: ������Ʈ �� �����͸� �޾ƿ� DB ����
		int updatePostCategoryId = Integer.parseInt(request.getParameter("postCategoryId"));
		String updatePostCategoryName = postDAO.findPostCategoryName(updatePostCategoryId);
		String updatePostTitle = request.getParameter("postTitle");
		String updatePostContent = request.getParameter("postContent");
		String postAttachment = oldPost.getPostAttachment();
		String nickname = artistDAO.findArtistById(artistId).getNickname();

		Post post = new Post(
				oldPostId, updatePostTitle,
				oldPost.getPostDate(), oldPost.getPostView(),
				updatePostContent, postAttachment,
				updatePostCategoryId, updatePostCategoryName,
				oldPost.getArtistId(), nickname);  // "artist1" �κп� artistDAO.findNickname �߰��ؼ� �����ؾ�
		postDAO.update(post);
		
		return "redirect:/post/view?postId=" + oldPostId;
	}

}
