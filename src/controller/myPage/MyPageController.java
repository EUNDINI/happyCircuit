package controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.dao.ArtistDAO;
import model.service.ArtistNotFoundException;

public class MyPageController implements Controller {
	
	private ArtistDAO artistDAO = new ArtistDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ��
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form ��û���� redirect
        }
    	
		String artistId = request.getParameter("artistId");

    	Artist artist = null;
    	artist = artistDAO.findArtistById(artistId);	// ����� ���� �˻�	
		
    	request.setAttribute("artist", artist);		// ����� ���� ����		
		return "myPage/myPage.jsp";
	}

}
