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

		// 로그인 여부 확인
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form 요청으로 redirect
        }
    	
		HttpSession session = request.getSession();	
		String artistId = ArtistSessionUtils.getLoginArtistId(session);
	
		if ((ArtistSessionUtils.isLoginArtist("admin", session) && 	// 로그인한 사용자가 관리자이고 	
			 !artistId.equals("admin"))							// 삭제 대상이 일반 사용자인 경우, 
			   || 												// 또는 
			(!ArtistSessionUtils.isLoginArtist("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
			ArtistSessionUtils.isLoginArtist(artistId, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
				
//			List<DM> dmList = dmDAO.findDMListByArtistId(artistId);
//			for (DM dm : dmList) {
//				dmDAO.deleteMembership(artistId, dm.getDmId());
//			}
//			dmDAO.deleteMessage(artistId);
			recommendMusicDAO.remove(artistId);
			artistDAO.remove(artistId);				// 사용자 정보 삭제
			
			if (ArtistSessionUtils.isLoginArtist("admin", session))	// 로그인한 사용자가 관리자 	
				return "redirect:/home";		// 사용자 리스트로 이동
			else 									// 로그인한 사용자는 이미 삭제됨
				return "redirect:/artist/logout";		// logout 처리
		}
		
		/* 삭제가 불가능한 경우 */
		Artist artist = artistDAO.findArtistById(artistId);	// 사용자 정보 검색
		request.setAttribute("artist", artist);						
		request.setAttribute("deleteFailed", true);
		String msg = (ArtistSessionUtils.isLoginArtist("admin", session)) 
				   ? "시스템 관리자 정보는 삭제할 수 없습니다."		
				   : "타인의 정보는 삭제할 수 없습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "myPage/myPage.jsp";
	}

}
