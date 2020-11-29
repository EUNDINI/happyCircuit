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
				request.getParameter("password"),
				request.getParameter("nickname"),
				request.getParameter("profile"),
				request.getParameter("image") );
		try {
			ArtistManager manager = ArtistManager.getInstance();
			manager.create(artist);
			return "redirect:/home"; // ���� �� ����� ����Ʈ ȭ������ redirect
			
		} catch (SQLException e) { // ���� �߻� �� ȸ������ form���� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("artist", artist);
			return "/article/login_register.jsp";
		}
	}

}
