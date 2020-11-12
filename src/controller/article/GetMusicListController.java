package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.MusicArticle;
import model.dao.MusicDAO;

public class GetMusicListController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MusicArticle> musicArticleList = musicDAO.findMusicArticleList(); //������������ �������� ǥ���� ���� ��
		request.setAttribute("musiArticleList", musicArticleList);
		return "/artice/articeMain.jsp";
	}

}
