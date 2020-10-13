package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.LikeChartDAO;
import model.dto.LikeChart;

public class LikeChartController implements Controller {
	private LikeChartDAO likeChartDAO = new LikeChartDAO();
	
	//장르별로 컨트롤러를 만들어야 하나?
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String condition = request.getParameter("condition");
		List<LikeChart> likeChart = likeChartDAO.getLikeChart(condition); //현재페이지와 페이지의 표시할 글의 수
		request.setAttribute("likeChart", likeChart);
		
		return "/board/home.jsp";
	}


}
