package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ListPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ�� �߰��ؾ� ��
		
		// ���߿� ����¡�� �߰��� ���� ����
		
		// List<Post>�� request�� �����Ͽ� ����

		return "findArtist/listPost.jsp";
	}

}
