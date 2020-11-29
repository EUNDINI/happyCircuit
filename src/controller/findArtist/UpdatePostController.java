package controller.findArtist;

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
		// 로그인 여부
		HttpSession session = request.getSession();
		if (!ArtistSessionUtils.hasLogined(session)) { // 로그인 안되어있는 있는 경우
			return "redirect:/findArtist/list";	
        }
		
		// 로그인 된 사람과 post를 작성한 사람이 같은지 검사하는 부분 추가
		int oldPostId = Integer.parseInt(request.getParameter("postId"));
		Post oldPost = postDAO.findPost(oldPostId);
		String artistId = oldPost.getArtistId();
		if (!ArtistSessionUtils.isLoginArtist(artistId, session)) { // 로그인 된 사람과 post를 작성한 사람이 다르면
			return "redirect:/findArtist/list";	
		}

		// GET method: 초기값 전송 및 form 화면 출력
		if (request.getMethod().equals("GET")) {
			System.out.println("(UpdatePostController) oldPostId: " + oldPostId);
			Post post = postDAO.findPost(oldPostId);
			request.setAttribute("post", post);	
			return "/findArtist/updatePost.jsp";
		}

		// POST method: 업데이트 된 데이터를 받아와 DB 갱신
		int updatePostCategoryId = Integer.parseInt(request.getParameter("postCategoryId"));
		String updatePostCategoryName = postDAO.findPostCategoryName(updatePostCategoryId);
		String updatePostTitle = request.getParameter("postTitle");
		String updatePostContent = request.getParameter("postContent");
		// 나중에 resources 하면서 수정 필요
//		String newPostAttachment = "첨부파일";
		String newPostAttachmentRoute = request.getParameter("postAttachment");
		String newPostAttachment;
		if (newPostAttachmentRoute.length() == 0) {
			newPostAttachment = "첨부파일없음";
		} 
		else {
			newPostAttachment = newPostAttachmentRoute.substring(newPostAttachmentRoute.lastIndexOf("\\") + 1);
		}
		String nickname = artistDAO.findArtistById(artistId).getNickname();

		System.out.println("(UpdatePostController) IN 4");
		Post post = new Post(
				oldPostId, updatePostTitle,
				oldPost.getPostDate(), oldPost.getPostView(),
				updatePostContent, newPostAttachment,
				updatePostCategoryId, updatePostCategoryName,
				oldPost.getArtistId(), nickname);  // "artist1" 부분에 artistDAO.findNickname 추가해서 수정해야
		postDAO.update(post);
		
		return "redirect:/findArtist/view/post?postId=" + oldPostId;
	}

}
