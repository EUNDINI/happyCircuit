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
		String condition = request.getParameter("tabs");
		List<LikeChart> likeChart = likeChartDAO.getLikeChart(condition); //현재페이지와 페이지의 표시할 글의 수
		request.setAttribute("likeChart", likeChart);
		
		return "/article/home.jsp";
	}


}
