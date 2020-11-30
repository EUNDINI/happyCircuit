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
		// �α��� ����
		HttpSession session = request.getSession();
		System.out.println("(CreatePostController) session: " + session);
		if (!ArtistSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			System.out.println("(CreatePostController) session: " + session);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�Խñ� �ۼ��� �α����� �ʿ��մϴ�.'); history.go(-2);</script>");
			out.flush();

			return "redirect:/post/list";	
        }
		
		// ���� ó��
		String uploadPath = request.getServletContext().getRealPath("/") + "\\resources\\findArtist";
		File Folder = new File(uploadPath);
		System.out.println(uploadPath);
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //���� ����
			    System.out.println("������ �����Ǿ����ϴ�.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}
		}
	
		int maxSize =1024 *1024 *10 * 10;
	   
	    MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());	    
	    System.out.println("(CreatePostController) In");
	    
	    String fileName = multi.getFilesystemName("postAttachment"); //���ϸ�
	    System.out.println("(CreatePostController) fileName: " + fileName);
	    String postAttachmentPath;
	    if (fileName == null) {
	    	postAttachmentPath = "÷�����Ͼ���";
	    }
	    else {
	    	postAttachmentPath = "../resources/findArtist/" + fileName;
	    }
	    System.out.println("postAttachmentPath: " + postAttachmentPath); 
	    // ���� ó�� ��
		
		// ������ ����Ͽ� ������� id �� nickname ���
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		Artist artist = artistDAO.findArtistById(artistId);
		System.out.println("(CreatePostController) post(artistId): " + artistId);
		
		// postCategoryId�� ����ؼ� postCategoryName�� �޾ƿ���
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
				artistId, nickname); // �α��� �κ� �����Ǹ� �̺κ� ���� �ؾ���
		
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