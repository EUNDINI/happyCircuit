package controller.DM;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.DM;
import model.Message;
import model.dao.DMDAO;

public class ViewDMController implements Controller {

	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int dmId = Integer.parseInt(request.getParameter("dmId"));
		List<Message> msgList = dmDAO.findMessageList(dmId);
		
		//현재 로그인 된 artist와 msg를 보낸 artist가 같으면 true
		List<Boolean> artistBooleanList = null;
		HttpSession session = request.getSession();
		for (Message msg : msgList) {
			if (msg.getArtist().equals(ArtistSessionUtils.getLoginArtistId(session))) {
				artistBooleanList.add(true);
			} else {
				artistBooleanList.add(false);
			}
		}
		
		request.setAttribute("artistBooleanList", artistBooleanList);
		request.setAttribute("msgList", msgList);
		request.setAttribute("dmId", dmId);
		return "/DM/detail.jsp";
	}

}
