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
import model.dao.ArtistDAO;
import model.dao.DMDAO;

public class CreateDMController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private DMDAO dmDAO = new DMDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		
		List<Artist> artistList = new ArrayList<Artist>();
		artistList.add(artistDAO.findArtistById(artistId)); //현재 로그인된 artist
		
		artistId = request.getParameter("artistId");
		artistList.add(artistDAO.findArtistById(artistId)); //상대 artist
		
		//둘 사이에 이미 DM이 있는지 없는지...
		//없으면 새로 만들고 있으면 그 dmId 가져오고
		int dmId = dmDAO.findMembership(artistList);
		if (dmId == 0) {
			try {
				DM dm = new DM(0, artistList);
				dmDAO.createDMAndMembership(dm);
				dmId = dm.getDmId();
			} catch (Exception e) {
				return "redirect:/DM/list";
			}
		}

		return "redirect:/DM/room?dmId=" + String.valueOf(dmId); 
	}

}
