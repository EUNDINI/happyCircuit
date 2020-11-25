package controller.artist;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.dao.ArtistDAO;
import model.service.ExistingArtistException;
import model.service.ArtistManager;

public class RegisterArtistController implements Controller {
	
	private ArtistDAO artistDAO = new ArtistDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Artist artist = new Artist(
				request.getParameter("artistId"),
				request.getParameter("pw"),
				request.getParameter("nickname"),
				request.getParameter("profile"),
				request.getParameter("image") );
		try {
			artistDAO.create(artist);
			return "redirect:/home"; 

		} catch (SQLException e) { // 예외 발생 시 회원가입 form으로 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("artist", artist);
			return "/article/login_register.jsp";
		}
	}

}
