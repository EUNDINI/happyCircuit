package controller.artist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Artist;
import model.dao.ArtistDAO;

public class UpdateArtistController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
		Artist artist = artistDAO.findArtistById(artistId);
		
		System.out.println(request.getParameter("profile"));
		System.out.println(request.getMethod());
		
		//GET request: form ��û
		if (request.getMethod().equals("GET")) {
			request.setAttribute("artist", artist);
			
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
		Artist updateArtist = new Artist(
				artistId,
				artist.getPw(),
				artist.getNickname(),
				request.getParameter("profile"),
//				request.getParameter("image") );
				null);
		artistDAO.update(updateArtist);

		request.setAttribute("artistId", artistId);
		return "redirect:/myPage";
	}

}
