package controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.dao.ArtistDAO;

public class UpdateArtistController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//GET request: form ��û
		if (request.getMethod().equals("GET")) {
			String artistId = request.getParameter("artistId");
			Artist artist = artistDAO.findArtistById(artistId);
			request.setAttribute("artist", artist);
			
			HttpSession session = request.getSession();
			if (ArtistSessionUtils.isLoginArtist(artistId, session) ||
				ArtistSessionUtils.isLoginArtist("admin", session)) {
				// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
								
				return "/myPage/update.jsp";   // �˻��� ����� ������ update form���� ����     
			}    
			
			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
				new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
			return "/myPage/myPage.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
		}
		
		//POST request (ȸ�������� parameter�� ���۵�)
		Artist artist = new Artist(
				request.getParameter("artistId"),
				request.getParameter("pw"),
				request.getParameter("nickname"),
				request.getParameter("profile"),
				request.getParameter("image") );
		artistDAO.update(artist);
		
		return "redirect:/myPage";
	}

}
