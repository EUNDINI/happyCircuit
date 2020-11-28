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

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		
//		if (ArtistSessionUtils.getLoginArtistId(session).equals(artistId)) {
//			request.setAttribute("artistId", artistId);
//			request.setAttribute("updateFailed", true);
//			request.setAttribute("exception", 
//					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));    
//		}
		
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

		request.setAttribute("dmList", dmList);
		request.setAttribute("artistList", artistList);
		request.setAttribute("lastMsgList", lastMsgList);
		return "/DM/list.jsp";
	}

}
