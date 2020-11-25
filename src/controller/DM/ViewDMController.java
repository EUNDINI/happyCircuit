package controller.DM;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.DM;
import model.Message;
import model.dao.DMDAO;

public class ViewDMController implements Controller {

	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int dmId = Integer.parseInt(request.getParameter("dmId"));
		List<Message> msgList = dmDAO.findMessageList(dmId);
		
		request.setAttribute("msgList", msgList);
		return "/DM/detail.jsp";
	}

}
