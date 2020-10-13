package controller.findArtist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ListPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인 추가해야 함
		
		// 나중에 페이징도 추가할 수도 있음
		
		// List<Post>를 request에 저장하여 전달

		return "findArtist/listPost.jsp";
	}

}
