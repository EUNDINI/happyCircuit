package controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.dao.ArtistDAO;

public class DeleteArtistController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String artistId = request.getParameter("artistId");
	
		HttpSession session = request.getSession();	
	
		if ((ArtistSessionUtils.isLoginArtist("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !artistId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!ArtistSessionUtils.isLoginArtist("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			ArtistSessionUtils.isLoginArtist(artistId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
				
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
