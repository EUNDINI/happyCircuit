package controller.DM;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.DM;
import model.dao.DMDAO;

public class ListDMController implements Controller {
	
	private DMDAO dmDAO = new DMDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<DM> dmList = dmDAO.findDMListByArtistId(request.getParameter("artistId"));

		request.setAttribute("dmList", dmList);
		return "/DM/list.jsp";
	}

}
