package controller.article;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.ArticlePaging;
import model.Music;
import model.MusicArticle;
import model.dao.MusicDAO;
import oracle.net.aso.n;

public class ReadMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	private RecommendMusicDAO recommendMusicDAO = new RecommendMusicDAO();
	private List<MusicArticle> nthCreationList;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int musicId = Integer.parseInt(request.getParameter("musicId"));

		if (request.getParameter("like") != null) {
			musicDAO.increaseLikeCount(musicId);
			musicDAO.decreaseReadCount(musicId);
			recommendMusicDAO.create(artistId, musicId);
		}

		MusicArticle musicArticle = musicDAO.findMusicArticle(musicId);
		int original = musicArticle.getMusic().getOriginalMusicId();
		nthCreationList = musicDAO.NthCreationMusicList(musicId);
		
		int i = 0;
		while (i < nthCreationList.size()) {
			List<MusicArticle> list = musicDAO.NthCreationMusicList(nthCreationList.get(i).getMusicId());

			for (MusicArticle a : list) {
				if(isExist(a.getMusicId()))
					continue;
				
				nthCreationList.add(a);
			}
			
			i++;
		}
		
		NthComparator comp = new NthComparator();
		Collections.sort(nthCreationList, comp);
		
		int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		ArticlePaging paging = new ArticlePaging();
		paging.makeBlock(currentPage);
		paging.makeLastPageNum(i);
		
		request.setAttribute("musicArticle", musicArticle);
		request.setAttribute("nthCreationList", nthCreationList);
		request.setAttribute("blockStartNum", paging.getBlockStartNum());
		request.setAttribute("blockLastNum", paging.getBlockLastNum());
		request.setAttribute("lastPageNum", paging.getLastPageNum());
		request.setAttribute("page", currentPage);
		request.setAttribute("total", paging.getTotal());

		if (original != 0) {
			Music originalMusic = musicDAO.findMusic(original);
			int prior = musicArticle.getMusic().getPriorMusicId();
			Music priorMusic = musicDAO.findMusic(prior);

			request.setAttribute("originalMusicName", originalMusic.getMusicName());
			request.setAttribute("priorMusicName", priorMusic.getMusicName());
		}

		return "/article/articleRead.jsp";
	}
	
	public Boolean isExist(int m) {
		for (MusicArticle a : nthCreationList) {
			if(a.getMusicId() == m)
				return true;
		}
		
		return false;
	}
	
	class NthComparator implements Comparator<MusicArticle> {

		@Override
		public int compare(MusicArticle mA1, MusicArticle mA2) {
			int mId1 = mA1.getMusicId();
			int mId2 = mA2.getMusicId();
			
			if(mId1 > mId2)
				return -1;
			else if(mId1 < mId2)
				return 1;
			else
				return 0;
		}
		
	}
}
