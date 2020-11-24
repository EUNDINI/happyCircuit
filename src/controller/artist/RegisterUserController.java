package controller.artist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User(); // getParamer�� �����ͼ� ä���
		// request ó��
		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
			return "redirect:/article/home.jsp"; // ���� �� ����� ����Ʈ ȭ������ redirect

		} catch (ExistingUserException e) { // ���� �߻� �� ȸ������ form���� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/artist/login_register.jsp";
		}
	}

}
