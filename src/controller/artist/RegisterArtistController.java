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
		Artist artist = new Artist(); // getParamer로 가져와서 채우기
		try {
			ArtistManager manager = ArtistManager.getInstance();
			manager.create(artist);
			return "redirect:/board/home.jsp"; // 성공 시 사용자 리스트 화면으로 redirect

		} catch (ExistingArtistException e) { // 예외 발생 시 회원가입 form으로 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("artist", artist);
			return "/article/login_register.jsp";
		}
	}

}
