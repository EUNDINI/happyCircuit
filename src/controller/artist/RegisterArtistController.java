package controller.artist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.service.ExistingArtistException;
import model.service.ArtistManager;

public class RegisterArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Artist artist = new Artist(); // getParamer�� �����ͼ� ä���
		try {
			ArtistManager manager = ArtistManager.getInstance();
			manager.create(artist);
			return "redirect:/board/home.jsp"; // ���� �� ����� ����Ʈ ȭ������ redirect

		} catch (ExistingArtistException e) { // ���� �߻� �� ȸ������ form���� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("artist", artist);
			return "/article/login_register.jsp";
		}
	}

}
