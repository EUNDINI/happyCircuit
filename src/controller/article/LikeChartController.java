package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.LikeChart;
import model.dao.MusicDAO;

public class LikeChartController implements Controller {
	private MusicDAO likeChartDAO = new MusicDAO();
	
	//장르별로 컨트롤러를 만들어야 하나?
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<LikeChart> likeChartAll = likeChartDAO.getLikeChart("all");
		List<LikeChart> likeChartRock = likeChartDAO.getLikeChart("rock");
		List<LikeChart> likeChartRNb = likeChartDAO.getLikeChart("rNb");
		List<LikeChart> likeChartPop = likeChartDAO.getLikeChart("pop");
		List<LikeChart> likeChartEdm = likeChartDAO.getLikeChart("edm");
		List<LikeChart> likeChartHiphop = likeChartDAO.getLikeChart("hiphop");
		List<LikeChart> likeChartEtc = likeChartDAO.getLikeChart("etc");
		
		request.setAttribute("likeChartAll", likeChartAll);
		request.setAttribute("likeChartRock", likeChartRock);
		request.setAttribute("likeChartRNb", likeChartRNb);
		request.setAttribute("likeChartPop", likeChartPop);
		request.setAttribute("likeChartEdm", likeChartEdm);
		request.setAttribute("likeChartHiphop", likeChartHiphop);
		request.setAttribute("likeChartEtc", likeChartEtc);
		
		return "/article/home.jsp";
	}


}
