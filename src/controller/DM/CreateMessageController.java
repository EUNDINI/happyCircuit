package controller.DM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.Message;
import model.dao.ArtistDAO;
import model.dao.DMDAO;

public class CreateMessageController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Artist artist = artistDAO.findArtistById(request.getParameter("artistId"));
		Message msg = new Message(
				(Integer) null, 
				request.getParameter("message"), 
				null,
				artist,
				Integer.parseInt(request.getParameter("dmId")));
		
		try {
			dmDAO.createMessage(msg);
			return "/DM/detail.jsp";
		} catch (Exception e) {
			return "redirect:/DM/list";
		}
	}

}
