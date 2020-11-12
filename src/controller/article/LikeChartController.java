package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.LikeChart;
import model.dao.LikeChartDAO;

public class LikeChartController implements Controller {
	private LikeChartDAO likeChartDAO = new LikeChartDAO();
	
	//�帣���� ��Ʈ�ѷ��� ������ �ϳ�?
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String condition = request.getParameter("tabs");
		List<LikeChart> likeChart = likeChartDAO.getLikeChart(condition);
		request.setAttribute("likeChart", likeChart);
		
		return "/article/home.jsp";
	}


}
