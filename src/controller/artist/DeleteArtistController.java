package controller.artist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Artist;
import model.DM;
import model.dao.ArtistDAO;
import model.dao.DMDAO;
import model.dao.RecommendMusicDAO;

public class DeleteArtistController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private DMDAO dmDAO = new DMDAO();
	private RecommendMusicDAO recommendMusicDAO = new RecommendMusicDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// �α��� ���� Ȯ��
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form ��û���� redirect
        }
    	
		HttpSession session = request.getSession();	
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
	
		if ((ArtistSessionUtils.isLoginArtist("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !artistId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!ArtistSessionUtils.isLoginArtist("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			ArtistSessionUtils.isLoginArtist(artistId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
				
//			List<DM> dmList = dmDAO.findDMListByArtistId(artistId);
//			for (DM dm : dmList) {
//				dmDAO.deleteMembership(artistId, dm.getDmId());
//			}
//			dmDAO.deleteMessage(artistId);
			recommendMusicDAO.remove(artistId);
			artistDAO.remove(artistId);				// ����� ���� ����
			
			if (ArtistSessionUtils.isLoginArtist("admin", session))	// �α����� ����ڰ� ������ 	
				return "redirect:/home";		// ����� ����Ʈ�� �̵�
			else 									// �α����� ����ڴ� �̹� ������
				return "redirect:/artist/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		Artist artist = artistDAO.findArtistById(artistId);	// ����� ���� �˻�
		request.setAttribute("artist", artist);						
		request.setAttribute("deleteFailed", true);
		String msg = (ArtistSessionUtils.isLoginArtist("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "myPage/myPage.jsp";
	}

}
