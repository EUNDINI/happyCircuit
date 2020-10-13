package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private RequestMapping rm;

	public void init() throws ServletException {
		rm = new RequestMapping();
		rm.initMapping(); // request URI�� controller ���� mapping ����
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		// RequestURI �� servletPath�� �����Ǵ� controller�� ����
		Controller controller = rm.findController(servletPath);
		try {
			// controller�� �����Ͽ� request�� ó���� ��, �̵��� uri�� ��ȯ ����
			String uri = controller.execute(request, response);
			// ��ȯ�� uri�� ���� forwarding �Ǵ� redirection ���θ� �����ϰ� �̵�
			if (uri.startsWith("redirect:")) {
				String targetUri = contextPath + uri.substring("redirect:".length());
				response.sendRedirect(targetUri); // redirection ����
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response); // forwarding ����
			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}