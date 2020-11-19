package controller.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class UpdateArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//GET request
		if (request.getMethod().equals("GET")) {
			return "/myPage/update.jsp";
		}
		
		//POST request
		return "redirect:/mypage";
	}

}
