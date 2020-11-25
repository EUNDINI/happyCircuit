package controller.DM;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.DM;
import model.Message;
import model.dao.DMDAO;

public class ListDMController implements Controller {
	
	private DMDAO dmDAO = new DMDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String artistId = request.getParameter("artistId");
		List<DM> dmList = dmDAO.findDMListByArtistId(artistId);
		
		List<Artist> artistList = null;
		for (DM dm : dmList) {
			List<Artist> tmpArtistList = dmDAO.findArtistListFromMembership(dm.getDmId());
			for (Artist artist : tmpArtistList) {
				if (artist.getArtistId() != artistId)
					artistList.add(artist);
			}
		}
		
		List<Message> lastMsgList = null;
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
