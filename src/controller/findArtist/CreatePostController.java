package controller.findArtist;

import java.io.File;
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
			return "redirect:/findArtist/list";	
        }
		
		// 세션을 사용하여 사용자의 id 및 nickname 얻기
		String artistId = ArtistSessionUtils.getLoginArtistId(session);

		Artist artist = artistDAO.findArtistById(artistId);
		System.out.println("(CreatePostController) post(artistId): " + artistId);
		
		// postCategoryId를 사용해서 postCategoryName을 받아오기
		int postCategoryId = Integer.parseInt((String) request.getParameter("postCategoryId"));
		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
		
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		System.out.println("(CreatePostController) postTitle: " + postTitle);
		System.out.println("(CreatePostController) postContent: " + postContent);
		
		// postAttachment resources로 추가 필요, nickname 잘 돌아가는지 로그인 추가 후 확인 필요
//		String postAttachment = "첨부파일"; 
			
		String postAttachmentRoute = request.getParameter("postAttachment");
		String postAttachment;
		if (postAttachmentRoute.length() == 0) {
			postAttachment = "첨부파일없음";
		} 
		else {
			postAttachment = postAttachmentRoute.substring(postAttachmentRoute.lastIndexOf("\\") + 1);
		}
		System.out.println("(CreatePostController) postAttachmentRoute 크기: " + postAttachmentRoute.length() );
		System.out.println("(CreatePostController) postAttachmentRoute: 시작" + postAttachmentRoute + "끝");
		System.out.println("(CreatePostController) postAttachment: 시작" + postAttachment + "끝");
//		String nickname = "artist1"; // 현재는 로그인 추가 안해서 임의 지정하지만 나중에는 아래처럼 코드 짜줘야함
		String nickname = artist.getNickname(); 
		System.out.println("(CreatePostController) postAttachment: " + postAttachment);
		System.out.println("(CreatePostController) nickname: " + nickname);
		
		
		
//		String projectPath = "D:\\2020\\2학기\\데이터베이스프로그래밍(컴퓨터학과)\\프로젝트\\PROJECT";
//		String filePath = ".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\happy\\resources\\findArtist";
//		String imgPath = projectPath + "\\" + filePath;
//		System.out.println("(CreatePostController) imgPath:" + projectPath + "\\" + filePath);
//		
//		request.setCharacterEncoding("UTF-8");
//		String realFolder = ""; 
//		String filename = ""; 
//		int maxSize = 1024*1024*5; 
//		String encType = "UTF-8"; 
//		String savefile = "\\resources\\findArtist"; 
//		ServletContext scontext = request.getServletContext(); 
//		System.out.println("(CreatePostController) scontext:" + scontext);
//		realFolder = scontext.getRealPath(savefile); 
//		System.out.println("(CreatePostController) realFolder:" + realFolder);
//		MultipartRequest multi = null;
//		 
//		try{ 
//			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy()); 
//			Enumeration<?> files = multi.getFileNames(); 
//			String file1 = (String)files.nextElement(); 
//			filename = multi.getFilesystemName(file1); 
//			System.out.println("(CreatePostController) filename: " + filename);
//		} catch(Exception e) { 
//			e.printStackTrace(); 
//		} 
		
//		String uploadPath = request.getServletContext().getRealPath("/") + "\\resources\\findArtist";
//		File Folder = new File(uploadPath);
//		System.out.println(uploadPath);
//		if (!Folder.exists()) {
//			try{
//			    Folder.mkdir(); //폴더 생성
//			    System.out.println("폴더가 생성되었습니다.");
//		        } 
//		        catch(Exception e){
//			    e.getStackTrace();
//			}
//		}
//	
//		int maxSize =1024 *1024 *10 * 10;
//	   
//	    MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());	    
//	    
//	    String fileName = multi.getFilesystemName("postAttachment"); //파일명
////		String postAttachmentPath = "../postAttachment/" + fileName;
////	    String postAttachmentPath = uploadPath + "/" + fileName;
//	    String postAttachmentPath = fileName;
//	    System.out.println("postAttachmentPath: " + postAttachmentPath);
//		
//		// 세션을 사용하여 사용자의 id 및 nickname 얻기
//		String artistId = ArtistSessionUtils.getLoginArtistId(session);
//		Artist artist = artistDAO.findArtistById(artistId);
//		System.out.println("(CreatePostController) post(artistId): " + artistId);
//		
//		// postCategoryId를 사용해서 postCategoryName을 받아오기
//		int postCategoryId = Integer.parseInt((String) multi.getParameter("postCategoryId"));
//		System.out.println("(CreatePostController) postCategoryId: " + postCategoryId);
//		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
//		System.out.println("(CreatePostController) postCategoryName: " + postCategoryName);
//		
//		String nickname = artist.getNickname(); 
//		
//		String postTitle = multi.getParameter("postTitle");
//		String postContent = multi.getParameter("postContent");
//		System.out.println("(CreatePostController) postTitle: " + postTitle);
//		System.out.println("(CreatePostController) postContent: " + postContent);
		
		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachment, 
				postCategoryId, postCategoryName,
				artistId, nickname); // 로그인 부분 구현되면 이부분 수정 해야함

		
//		Post post = new Post(0, postTitle,
//				null, 0,
//				postContent, postAttachmentPath, 
//				postCategoryId, postCategoryName,
//				artistId, nickname); // 로그인 부분 구현되면 이부분 수정 해야함
		
		System.out.println("(CreatePostController) post: " + post);
		System.out.println("(CreatePostController) post(getPostTitle): " + post.getPostTitle());
		System.out.println("(CreatePostController) post(getPostContent): " + post.getPostContent());
		System.out.println("(CreatePostController) post(getPostCategoryId): " + post.getPostCategoryId());
		
		int postId = postDAO.create(post);
		System.out.println("(CreatePostController) postId: " + postId);
		request.setAttribute("postId", postId);
		request.setAttribute("post", postDAO.findPost(postId));

		return "redirect:/findArtist/view/post?postId=" + postId;
//		return "/findArtist/view/post?postId=" + postId;
		
	}

}
