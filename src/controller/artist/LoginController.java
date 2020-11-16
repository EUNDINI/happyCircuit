package controller.artist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.ArtistManager;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String artistId = request.getParameter("artistId");
		String passwd = request.getParameter("passwd");
		
		try {
			ArtistManager manager = ArtistManager.getInstance();
			manager.login(artistId, passwd);

			HttpSession session = request.getSession();
			session.setAttribute(ArtistSessionUtils.ARTIST_SESSION_KEY, artistId);

			return "redirect:/article/articleMain.jsp";
		} catch (Exception e) {
			request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
		}
		
		 return "/user/login.jsp";	
	}

}
