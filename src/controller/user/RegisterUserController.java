package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User(); // getParamer로 가져와서 채우기
		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			return "redirect:/board/home.jsp"; // 성공 시 사용자 리스트 화면으로 redirect

		} catch (ExistingUserException e) { // 예외 발생 시 회원가입 form으로 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/article/login_register.jsp";
		}
	}

}
