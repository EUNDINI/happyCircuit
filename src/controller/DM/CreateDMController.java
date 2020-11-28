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
		artistList.add(artistDAO.findArtistById(artistId)); //���� �α��ε� artist
		
		artistId = request.getParameter("artistId");
		artistList.add(artistDAO.findArtistById(artistId)); //��� artist
		
		//�� ���̿� �̹� DM�� �ִ��� ������...
		//������ ���� ����� ������ �� dmId ��������
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
