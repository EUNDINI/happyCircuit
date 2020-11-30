package controller.DM;

import java.util.ArrayList;
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

		// �α��� ���� Ȯ��
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form ��û���� redirect
        }
    	
		int dmId = Integer.parseInt(request.getParameter("dmId"));
		List<Message> msgList = dmDAO.findMessageList(dmId);
		
		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);

		//���� �α��� �� artist�� msg�� ���� artist�� ������ true
		List<Boolean> artistBooleanList = new ArrayList<Boolean>();
		for (Message msg : msgList) {
			if (msg.getArtist().getArtistId().equals(artistId)) {
				artistBooleanList.add(true);
			} else {
				artistBooleanList.add(false);
			}
		}
		
		request.setAttribute("artistBooleanList", artistBooleanList);
		request.setAttribute("msgList", msgList);
		request.setAttribute("artistId", artistId);
		request.setAttribute("dmId", dmId);
		return "/DM/detail.jsp";
	}

}
