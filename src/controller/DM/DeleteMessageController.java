package controller.DM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.dao.DMDAO;

public class DeleteMessageController implements Controller {

	private DMDAO dmDAO = new DMDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		
		int dmId = Integer.parseInt(request.getParameter("dmId"));
		int msgId = Integer.parseInt(request.getParameter("msgId"));
		
		//�α��ε� ���� �˻��ؼ� �����ص� �Ǵ��� üũ
		if ((ArtistSessionUtils.isLoginArtist("admin", session) && 	// �α����� ����ڰ� �������̰� 	
				!artistId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
				   || 												// �Ǵ� 
			(!ArtistSessionUtils.isLoginArtist("admin", session) && // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			ArtistSessionUtils.isLoginArtist(artistId, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
					 
			dmDAO.deleteMessage(msgId);
				
			return "redirect:/DM/room?dmId=" + String.valueOf(dmId);	
		}
		
		return "redirect:/DM/list";
	}
}
