package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.ArticlePaging;
import model.Music;
import model.MusicArticle;
import model.dao.MusicDAO;
import model.dao.RecommendMusicDAO;

public class ReadMusicController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	RecommendMusicDAO recommendDAO = new RecommendMusicDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ddd");
		int musicId = Integer.parseInt(request.getParameter("musicId"));

		if (request.getParameter("like") != null) {
			System.out.println("aaaa");
			musicDAO.increaseLikeCount(musicId);
			musicDAO.decreaseReadCount(musicId);
			
			HttpSession session = request.getSession();
			recommendDAO.create(ArtistSessionUtils.getLoginArtistId(session), musicId);
		}

		MusicArticle musicArticle = musicDAO.findMusicArticle(musicId);
		int original = musicArticle.getMusic().getOriginalMusicId();

		int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		ArticlePaging paging = new ArticlePaging();
		paging.makeBlock(currentPage);
		paging.makeLastPageNum(musicId);

		List<MusicArticle> nthCreationList = musicDAO.NthCreationMusicList(musicId, currentPage, 15);

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
}