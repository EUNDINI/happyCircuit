package controller.DM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.dao.DMDAO;

public class DeleteMessageController implements Controller {

	private DMDAO dmDAO = new DMDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		
		int dmId = Integer.parseInt(request.getParameter("dmId"));
		int msgId = Integer.parseInt(request.getParameter("msgId"));
		
		//로그인된 유저 검사해서 삭제해도 되는지 체크
		if ((ArtistSessionUtils.isLoginArtist("admin", session) && 	// 로그인한 사용자가 관리자이고 	
				!artistId.equals("admin"))							// 삭제 대상이 일반 사용자인 경우, 
				   || 												// 또는 
			(!ArtistSessionUtils.isLoginArtist("admin", session) && // 로그인한 사용자가 관리자가 아니고 
			ArtistSessionUtils.isLoginArtist(artistId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
					 
			dmDAO.deleteMessage(msgId);
				
			return "redirect:/DM/room?dmId=" + String.valueOf(dmId);	
		}
		
		return "redirect:/DM/list";
	}
}
