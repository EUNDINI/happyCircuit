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

		// 로그인 여부
		if (!ArtistSessionUtils.hasLogined(request.getSession())) { // 로그인 안되어있는 있는 경우
			return "redirect:/artist/login/form";	
        }

		HttpSession session = request.getSession();	
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		int dmId = Integer.parseInt(request.getParameter("dmId"));
		
		if (ArtistSessionUtils.getLoginArtistId(session).equals(artistId)) { //로그인한 artist가 해당 dm에 있으면
			dmDAO.deleteMembership(artistId, dmId);
		}
		
		return "redirect:/DM/list";
	}

}
