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
		if (!ArtistSessionUtils.hasLogined(session)) { // 로그인 안되어있는 있는 경우
			System.out.println("(CreatePostController) session: " + session);
			return "redirect:/post/list";	
        }
		
		// 파일 처리
		String uploadPath = request.getServletContext().getRealPath("/") + "\\resources\\findArtist";
		File Folder = new File(uploadPath);
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
	    String postAttachmentPath;
	    if (fileName == null) {
	    	postAttachmentPath = "첨부파일없음";
	    }
	    else {
	    	postAttachmentPath = "../resources/findArtist/" + fileName;
	    }
	    // 파일 처리 끝
		
		// 세션을 사용하여 사용자의 id 및 nickname 얻기
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		Artist artist = artistDAO.findArtistById(artistId);
		
		// postCategoryId를 사용해서 postCategoryName을 받아오기
		int postCategoryId = Integer.parseInt((String) multi.getParameter("postCategoryId"));
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		String nickname = artist.getNickname(); 		
		String postTitle = multi.getParameter("postTitle");
		String postContent = multi.getParameter("postContent");

		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachmentPath, 
				postCategoryId, postCategoryName,
				artistId, nickname); // 로그인 부분 구현되면 이부분 수정 해야함
		
		int postId = postDAO.create(post);
		request.setAttribute("postId", postId);
		request.setAttribute("post", postDAO.findPost(postId));

		return "redirect:/post/view?postId=" + postId;		
	}

}