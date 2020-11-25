package controller.myPage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Music;

public class RecommendMusicController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Music> musicList = new ArrayList<Music>();
		
		request.setAttribute("musicList", musicList);
		return "/myPage/recommendMusic.jsp";
	}

}
