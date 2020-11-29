package controller.artist;

import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Controller;
import model.Artist;
import model.dao.ArtistDAO;
import model.service.ExistingArtistException;
import model.service.ArtistManager;

public class RegisterArtistController implements Controller {
	
	private ArtistDAO artistDAO = new ArtistDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String realFolder = ""; 
		String filename = ""; 
		int maxSize = 1024*1024*5; 
		String encType = "UTF-8"; 
		String savefile = "sample"; 
		ServletContext scontext = request.getServletContext(); 
		realFolder = scontext.getRealPath(savefile); 
		MultipartRequest multi = null;
		 
		try{ 
			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy()); 
			Enumeration<?> files = multi.getFileNames(); 
			String file1 = (String)files.nextElement(); 
			filename = multi.getFilesystemName(file1); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 
		
		Artist artist = new Artist(
				multi.getParameter("artistId"),
				multi.getParameter("pw"),
				multi.getParameter("nickname"),
				multi.getParameter("profile"),
				filename );
		try {
			artistDAO.create(artist);
			return "redirect:/home"; // 성공 시 사용자 리스트 화면으로 redirect
			
		} catch (SQLException e) { // 예외 발생 시 회원가입 form으로 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("artist", artist);
			return "/article/login_register.jsp";
		}
	}

}
