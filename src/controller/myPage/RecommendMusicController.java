package controller.myPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.Music;
import model.dao.ArtistDAO;
import model.dao.RecommendMusicDAO;

public class RecommendMusicController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private RecommendMusicDAO recommendDAO = new RecommendMusicDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String artistId = request.getParameter("artistId");
		
		List<Music> musicList = new ArrayList<Music>();
		for (int i = 0; i < 8; i++) {
			List<Music> likeMusicList = recommendDAO.findMusicListByArtistId(artistId); //artist가 좋아요를 누른 음악 list
			Collections.shuffle(likeMusicList);		//랜덤하게 섞음
			Music music = likeMusicList.get(0);		//음악 하나 선택
			
			List<Artist> likeArtistList = recommendDAO.findArtistListByMusicId(music.getMusicId()); //음악에 좋아요를 누른 artist list
			Collections.shuffle(likeArtistList);	//랜덤하게 섞음
			Artist artist = likeArtistList.get(0);	//artist 하나 선택

			List<Music> likeMusicList2 = recommendDAO.findMusicListByArtistId(artist.getArtistId()); //그 artist가 좋아요를 누름 음악 list
			Collections.shuffle(likeMusicList2);	//랜덤하게 섞음
			music = likeMusicList2.get(0);			//음악 하나 선택
			
			musicList.add(music);	//최종 음악 list에 추가
		}

    	List<Artist> artistList = new ArrayList<Artist>();
    	for (Music music : musicList) {
    		Artist musicArtist = artistDAO.findArtistById(music.getArtistId());
    		artistList.add(musicArtist);
    	}
    	
		request.setAttribute("musicList", musicList);
    	request.setAttribute("artistList", artistList);
		return "/myPage/recommendMusic.jsp";
	}

}
