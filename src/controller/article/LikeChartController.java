package controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.LikeChart;
import model.dao.MusicDAO;

public class LikeChartController implements Controller {
	private MusicDAO likeChartDAO = new MusicDAO();
	
	//�帣���� ��Ʈ�ѷ��� ������ �ϳ�?
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String condition = request.getParameter("tabs");
		List<LikeChart> likeChart = likeChartDAO.getLikeChart(condition); //������������ �������� ǥ���� ���� ��
		request.setAttribute("likeChart", likeChart);
		
		return "/article/home.jsp";
	}


}
