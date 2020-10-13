package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.LikeChartDAO;
import model.dto.LikeChart;

public class LikeChartController implements Controller {
	private LikeChartDAO likeChartDAO = new LikeChartDAO();
	
	//�帣���� ��Ʈ�ѷ��� ������ �ϳ�?
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String condition = request.getParameter("condition");
		List<LikeChart> likeChart = likeChartDAO.getLikeChart(condition); //������������ �������� ǥ���� ���� ��
		request.setAttribute("likeChart", likeChart);
		
		return "/board/home.jsp";
	}


}
