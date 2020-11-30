package controller.post;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
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
		// 로그인 여부
		HttpSession session = request.getSession();
		System.out.println("(CreatePostController) session: " + session);
		if (!ArtistSessionUtils.hasLogined(session)) { // 로그인 안되어있는 있는 경우
			System.out.println("(CreatePostController) session: " + session);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글 작성은 로그인이 필요합니다.'); history.go(-2);</script>");
			out.flush();

			return "redirect:/post/list";	
        }
		
		// 파일 처리
		String uploadPath = request.getServletContext().getRealPath("/") + "\\resources\\findArtist";
		File Folder = new File(uploadPath);
		System.out.println(uploadPath);
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}
		}
	
		int maxSize =1024 *1024 *10 * 10;
	   
	    MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());	    
	    System.out.println("(CreatePostController) In");
	    
	    String fileName = multi.getFilesystemName("postAttachment"); //파일명
	    System.out.println("(CreatePostController) fileName: " + fileName);
	    String postAttachmentPath;
	    if (fileName == null) {
	    	postAttachmentPath = "첨부파일없음";
	    }
	    else {
	    	postAttachmentPath = "../resources/findArtist/" + fileName;
	    }
	    System.out.println("postAttachmentPath: " + postAttachmentPath); 
	    // 파일 처리 끝
		
		// 세션을 사용하여 사용자의 id 및 nickname 얻기
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		Artist artist = artistDAO.findArtistById(artistId);
		System.out.println("(CreatePostController) post(artistId): " + artistId);
		
		// postCategoryId를 사용해서 postCategoryName을 받아오기
		int postCategoryId = Integer.parseInt((String) multi.getParameter("postCategoryId"));
		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
		
		String nickname = artist.getNickname(); 
		
		String postTitle = multi.getParameter("postTitle");
		String postContent = multi.getParameter("postContent");
		System.out.println("(CreatePostController) postTitle: " + postTitle);
		System.out.println("(CreatePostController) postContent: " + postContent);
		
		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachmentPath, 
				postCategoryId, postCategoryName,
				artistId, nickname); // 로그인 부분 구현되면 이부분 수정 해야함
		
		System.out.println("(CreatePostController) post: " + post);
		System.out.println("(CreatePostController) post(getPostTitle): " + post.getPostTitle());
		System.out.println("(CreatePostController) post(getPostContent): " + post.getPostContent());
		System.out.println("(CreatePostController) post(getPostCategoryId): " + post.getPostCategoryId());
		
		int postId = postDAO.create(post);
		System.out.println("(CreatePostController) postId: " + postId);
		request.setAttribute("postId", postId);
		request.setAttribute("post", postDAO.findPost(postId));

		return "redirect:/post/view?postId=" + postId;
		
	}

}