package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Artist;
import model.Post;
import model.dao.ArtistDAO;
import model.dao.PostDAO;
import model.service.FindArtistManager;

public class CreatePostController implements Controller {

	PostDAO postDAO = new PostDAO();
	ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ�� �߰�
		
		// ������ ����Ͽ� ������� id �� nickname ���(���� �߰� ����)
		HttpSession session = request.getSession();
		String artistId = (String)session.getAttribute("artistId");
		Artist artist = artistDAO.findArtistById(artistId);
		System.out.println("(CreatePostController) post(artistId): " + artistId);
		
		// postCategoryId�� ����ؼ� postCategoryName�� �޾ƿ���
		int postCategoryId = Integer.parseInt((String) request.getParameter("postCategoryId"));
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
		
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		System.out.println("(CreatePostController) postTitle: " + postTitle);
		System.out.println("(CreatePostController) postContent: " + postContent);
		
		// postAttachment resources�� �߰� �ʿ�, nickname �� ���ư����� �α��� �߰� �� Ȯ�� �ʿ�
		String postAttachment = "÷������"; 
		String nickname = "artist1"; // ����� �α��� �߰� ���ؼ� ���� ���������� ���߿��� �Ʒ�ó�� �ڵ� ¥�����
//		String nickname = artist.getNickname(); 
		System.out.println("(CreatePostController) postAttachment: " + postAttachment);
		System.out.println("(CreatePostController) nickname: " + nickname);
		
		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachment, 
				postCategoryId, postCategoryName,
				"artist1", "artist1"); // �α��� �κ� �����Ǹ� �̺κ� ���� �ؾ���
		
		System.out.println("(CreatePostController) post: " + post);
		System.out.println("(CreatePostController) post(getPostTitle): " + post.getPostTitle());
		System.out.println("(CreatePostController) post(getPostContent): " + post.getPostContent());
		System.out.println("(CreatePostController) post(getPostCategoryId): " + post.getPostCategoryId());
		
		int postId = postDAO.create(post);
		request.setAttribute("postId", postId);
		request.setAttribute("post", postDAO.findPost(postId));

//		return "/findArtist/view/post?postId=" + postId;
		return "redirect:/findArtist/view/post?postId=" + postId;
		
	}

}
