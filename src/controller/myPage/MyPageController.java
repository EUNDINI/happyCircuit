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
		// 로그인 여부 확인
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form 요청으로 redirect
        }
    	
		String artistId = request.getParameter("artistId");

    	Artist artist = null;
    	artist = artistDAO.findArtistById(artistId);	// 사용자 정보 검색	
		
    	request.setAttribute("artist", artist);		// 사용자 정보 저장		
		return "myPage/myPage.jsp";
	}

}
