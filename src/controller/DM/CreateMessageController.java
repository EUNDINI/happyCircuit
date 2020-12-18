package controller.DM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.Message;
import model.dao.ArtistDAO;
import model.dao.DMDAO;

public class CreateMessageController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		int dmId = Integer.parseInt(request.getParameter("dmId"));
		
		Artist artist = artistDAO.findArtistById(artistId);
		Message msg = new Message(
				0, request.getParameter("message"), 
				null, artist, dmId);
		
		try {
			dmDAO.createMessage(msg);
			request.setAttribute("dmId", dmId);
			return "redirect:/DM/room?dmId=" + String.valueOf(dmId);
		} catch (Exception e) {
			return "redirect:/DM/list";
		}
	}

}
