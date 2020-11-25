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
		
		//GET request: form 요청
		if (request.getMethod().equals("GET")) {
			String artistId = request.getParameter("artistId");
			Artist artist = artistDAO.findArtistById(artistId);
			request.setAttribute("artist", artist);
			
			HttpSession session = request.getSession();
			if (ArtistSessionUtils.isLoginArtist(artistId, session) ||
				ArtistSessionUtils.isLoginArtist("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
								
				return "/myPage/update.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
				new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
			return "/myPage/myPage.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
		}
		
		//POST request (회원정보가 parameter로 전송됨)
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
