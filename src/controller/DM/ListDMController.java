package controller.DM;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.DM;
import model.Message;
import model.dao.DMDAO;

public class ListDMController implements Controller {

	private DMDAO dmDAO = new DMDAO();

	@SuppressWarnings("null")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 여부 확인
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form 요청으로 redirect
        }
    	
		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		
		List<DM> dmList = dmDAO.findDMListByArtistId(artistId);
		
		List<Artist> artistList = new ArrayList<Artist>();
		for (DM dm : dmList) {
			dm.setArtistList(dmDAO.findArtistListFromMembership(dm.getDmId()));
			for (Artist artist : dm.getArtistList()) {
				if (!artist.getArtistId().equals(artistId))
					artistList.add(artist);
			} 
		}
		
		List<Message> lastMsgList = new ArrayList<Message>();
		for (DM dm : dmList) {
			Message msg = dmDAO.findLastMessage(dm.getDmId());
			lastMsgList.add(msg);
		}

		request.setAttribute("artistId", artistId);
		request.setAttribute("dmList", dmList);
		request.setAttribute("lastMsgList", lastMsgList);
		return "/DM/list.jsp";
	}

}
