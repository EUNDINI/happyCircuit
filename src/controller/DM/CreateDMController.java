package controller.DM;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.DM;
import model.dao.ArtistDAO;
import model.dao.DMDAO;

public class CreateDMController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String[] artistIds = request.getParameterValues("artistIds");
			List<Artist> list = new ArrayList<Artist>();
			for (String artistId: artistIds) {
				Artist artist = artistDAO.findArtistById(artistId);
				list.add(artist);
			}
			
			DM dm = new DM(Integer.parseInt(request.getParameter("dmId")), list);
			dmDAO.createDMAndMembership(dm);
			
			request.setAttribute("dmId", dm.getDmId());
			return "/DM/detail.jsp"; 
		} catch (Exception e) {
			return "redirect:/DM/list";
		}

	}

}
