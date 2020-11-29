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
		// 로그인 여부 확인 추가
		
		// 세션을 사용하여 사용자의 id 및 nickname 얻기(추후 추가 예정)
		HttpSession session = request.getSession();
		String artistId = (String)session.getAttribute("artistId");
		Artist artist = artistDAO.findArtistById(artistId);
		System.out.println("(CreatePostController) post(artistId): " + artistId);
		
		// postCategoryId를 사용해서 postCategoryName을 받아오기
		int postCategoryId = Integer.parseInt((String) request.getParameter("postCategoryId"));
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
		
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		System.out.println("(CreatePostController) postTitle: " + postTitle);
		System.out.println("(CreatePostController) postContent: " + postContent);
		
		// postAttachment resources로 추가 필요, nickname 잘 돌아가는지 로그인 추가 후 확인 필요
		String postAttachment = "첨부파일"; 
		String nickname = "artist1"; // 현재는 로그인 추가 안해서 임의 지정하지만 나중에는 아래처럼 코드 짜줘야함
//		String nickname = artist.getNickname(); 
		System.out.println("(CreatePostController) postAttachment: " + postAttachment);
		System.out.println("(CreatePostController) nickname: " + nickname);
		
		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachment, 
				postCategoryId, postCategoryName,
				"artist1", "artist1"); // 로그인 부분 구현되면 이부분 수정 해야함
		
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
