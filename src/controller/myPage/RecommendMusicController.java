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
			List<Music> likeMusicList = recommendDAO.findMusicListByArtistId(artistId); //artist�� ���ƿ並 ���� ���� list
			Collections.shuffle(likeMusicList);		//�����ϰ� ����
			Music music = likeMusicList.get(0);		//���� �ϳ� ����
			
			List<Artist> likeArtistList = recommendDAO.findArtistListByMusicId(music.getMusicId()); //���ǿ� ���ƿ並 ���� artist list
			Collections.shuffle(likeArtistList);	//�����ϰ� ����
			Artist artist = likeArtistList.get(0);	//artist �ϳ� ����

			List<Music> likeMusicList2 = recommendDAO.findMusicListByArtistId(artist.getArtistId()); //�� artist�� ���ƿ並 ���� ���� list
			Collections.shuffle(likeMusicList2);	//�����ϰ� ����
			music = likeMusicList2.get(0);			//���� �ϳ� ����
			
			musicList.add(music);	//���� ���� list�� �߰�
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
