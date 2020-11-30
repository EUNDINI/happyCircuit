package controller.myPage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.Music;
import model.dao.ArtistDAO;
import model.dao.RecommendMusicDAO;
import model.service.ArtistNotFoundException;

public class MyPageController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private RecommendMusicDAO recommendMusicDAO = new RecommendMusicDAO();

	@SuppressWarnings("null")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form 요청으로 redirect
        }
    	
		HttpSession session = request.getSession();
		String artistId = request.getParameter("artistId");

		if (ArtistSessionUtils.getLoginArtistId(session).equals(artistId)) {
			request.setAttribute("isSameArtist", true);
		} else {
			request.setAttribute("isSameArtist", false);
		}

    	Artist artist = artistDAO.findArtistById(artistId);	// 사용자 정보 검색	
  
    	List<Music> musicList = recommendMusicDAO.findMusicListByArtistId(artistId);
    	
    	List<Artist> artistList = new ArrayList<Artist>();
    	for (Music music : musicList) {
    		Artist musicArtist = artistDAO.findArtistById(music.getArtistId());
    		artistList.add(musicArtist);
    	}
    	
    	request.setAttribute("artist", artist);		// 사용자 정보 저장	
    	request.setAttribute("musicList", musicList);
    	request.setAttribute("artistList", artistList);
		return "/myPage/myPage.jsp";
	}

}
