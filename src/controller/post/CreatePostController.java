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
		if (!ArtistSessionUtils.hasLogined(session)) { // �α��� �ȵǾ��ִ� �ִ� ���
			System.out.println("(CreatePostController) session: " + session);
			return "redirect:/post/list";	
        }
		
		// ���� ó��
		String uploadPath = request.getServletContext().getRealPath("/") + "\\resources\\findArtist";
		File Folder = new File(uploadPath);
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
	    String postAttachmentPath;
	    if (fileName == null) {
	    	postAttachmentPath = "÷�����Ͼ���";
	    }
	    else {
	    	postAttachmentPath = "../resources/findArtist/" + fileName;
	    }
	    // ���� ó�� ��
		
		// ������ ����Ͽ� ������� id �� nickname ���
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		Artist artist = artistDAO.findArtistById(artistId);
		
		// postCategoryId�� ����ؼ� postCategoryName�� �޾ƿ���
		int postCategoryId = Integer.parseInt((String) multi.getParameter("postCategoryId"));
		String postCategoryName = postDAO.findPostCategoryName(postCategoryId);
		String nickname = artist.getNickname(); 		
		String postTitle = multi.getParameter("postTitle");
		String postContent = multi.getParameter("postContent");

		Post post = new Post(0, postTitle,
				null, 0,
				postContent, postAttachmentPath, 
				postCategoryId, postCategoryName,
				artistId, nickname); // �α��� �κ� �����Ǹ� �̺κ� ���� �ؾ���
		
		int postId = postDAO.create(post);
		request.setAttribute("postId", postId);
		request.setAttribute("post", postDAO.findPost(postId));

		return "redirect:/post/view?postId=" + postId;		
	}

}