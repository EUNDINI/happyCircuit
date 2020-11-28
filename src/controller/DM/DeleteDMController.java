package controller.DM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.dao.DMDAO;

public class DeleteDMController implements Controller {
	
	private DMDAO dmDAO = new DMDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();	
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		int dmId = Integer.parseInt(request.getParameter("dmId"));
		
		if (ArtistSessionUtils.getLoginArtistId(session).equals(artistId)) { //�α����� artist�� �ش� dm�� ������
			dmDAO.deleteMembership(artistId, dmId);
		}
		
		return "redirect:/DM/list";
	}

}
