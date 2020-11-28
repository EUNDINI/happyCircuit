package controller.myPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.Music;
import model.dao.RecommendMusicDAO;

public class RecommendMusicController implements Controller {

	RecommendMusicDAO recommendDAO = new RecommendMusicDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String artistId = request.getParameter("artistId");
		
		List<Music> musicList = new ArrayList<Music>();
		for (int i = 0; i < 10; i++) {
			List<Music> likeMusicList = recommendDAO.findMusicListByArtistId(artistId);
			Collections.shuffle(likeMusicList);
			Music music = likeMusicList.get(0);
			
			List<Artist> likeArtistList = recommendDAO.findArtistListByMusicId(music.getMusicId());
			Collections.shuffle(likeArtistList);
			Artist artist = likeArtistList.get(0);

			List<Music> likeMusicList2 = recommendDAO.findMusicListByArtistId(artist.getArtistId());
			Collections.shuffle(likeMusicList2);
			music = likeMusicList2.get(0);
			
			musicList.add(music);
		}
		
		request.setAttribute("musicList", musicList);
		return "/myPage/recommendMusic.jsp";
	}

}
