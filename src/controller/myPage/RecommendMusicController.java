package controller.myPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Artist;
import model.Music;
import model.dao.ArtistDAO;
import model.dao.MusicDAO;
import model.dao.RecommendMusicDAO;

public class RecommendMusicController implements Controller {

	private ArtistDAO artistDAO = new ArtistDAO();
	private MusicDAO musicDAO = new MusicDAO();
	private RecommendMusicDAO recommendDAO = new RecommendMusicDAO();
	private String artistId;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		artistId = request.getParameter("artistId");
		
		List<Music> musicList = new ArrayList<Music>();
		List<Music> likeMusicList = recommendDAO.findMusicListByArtistId(artistId); //artist�� ���ƿ並 ���� ���� list
		Collections.shuffle(likeMusicList);		//�����ϰ� ����
		Music likeMusic = likeMusicList.get(0);		//���� �ϳ� ����
		for (int i = 0; i < 10; i++) { 		
			Music music = null;
			
			int n = (int)(Math.random() * 3);
			System.out.println(n);
			
			switch (n) {
			case 0:
				music = pickMusic1(likeMusic);
				break;
			case 1:
				music = pickMusic2(likeMusic);
				break;
			case 2:
				music = pickMusic3();
				break;
			}
			
			if (music != null) {
				musicList.add(music);	//���� ���� list�� �߰�
			}
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

	public Music pickMusic1(Music likeMusic) {
		Music music = null;
		try {			
//			List<Artist> likeArtistList = recommendDAO.findArtistListByMusicId(likeMusic.getMusicId()); //���ǿ� ���ƿ並 ���� artist list
//			Collections.shuffle(likeArtistList);	//�����ϰ� ����
//			Artist artist = likeArtistList.get(0);	//artist �ϳ� ����
//
//			List<Music> likeMusicList2 = recommendDAO.findMusicListByArtistId(artist.getArtistId()); //�� artist�� ���ƿ並 ���� ���� list
//			Collections.shuffle(likeMusicList2);	//�����ϰ� ����
//			music = likeMusicList2.get(0);			//���� �ϳ� ����
			
			List<String> likeArtistIdList = recommendDAO.findArtistIdListByMusicId(likeMusic.getMusicId());
			Collections.shuffle(likeArtistIdList);	//�����ϰ� ����
			String artistId = likeArtistIdList.get(0);	//artist �ϳ� ����
			
			List<Integer> likeMusicIdList = recommendDAO.findMusicIdListByArtistId(artistId);
			Collections.shuffle(likeMusicIdList);	//�����ϰ� ����
			int musicId = likeMusicIdList.get(0);			//���� �ϳ� ����
			
			music = musicDAO.findMusic(musicId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return music;
	}
	
	public Music pickMusic2(Music likeMusic) {
		Music music = null;
		try {
//			List<Music> likeMusicList2 = recommendDAO.findCreateMusicListByArtistId(likeMusic.getArtistId()); //music�� ���� artist�� ���� ���� list
//			Collections.shuffle(likeMusicList2);	//�����ϰ� ����
//			music = likeMusicList2.get(0);			//���� �ϳ� ����
			
			List<Integer> likeMusicIdList = recommendDAO.findCreateMusicIdListByArtistId(likeMusic.getArtistId());
			Collections.shuffle(likeMusicIdList);	//�����ϰ� ����
			int musicId = likeMusicIdList.get(0);	//���� �ϳ� ����
			
			music = musicDAO.findMusic(musicId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return music;
	}
	
	public Music pickMusic3() {
		Music music = null;
		try {
			Music likeMusic = recommendDAO.findTopLikeMusicByArtistId(artistId);
			Artist likeArtist = recommendDAO.findTopLikeArtistByMusicId(likeMusic.getMusicId());
			music = recommendDAO.findTopLikeMusicByArtistId(likeArtist.getArtistId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return music;
	}
}
