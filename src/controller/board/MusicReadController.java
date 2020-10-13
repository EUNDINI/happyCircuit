package controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.MusicDAO;
import model.dto.MusicBoard;

public class MusicReadController implements Controller {
	private MusicDAO musicDAO = new MusicDAO();
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	MusicBoard musicBoard = null;
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		musicBoard = musicDAO.findMusicBoard(musicId);			
		
		List<MusicBoard> nthCreationList = musicDAO.NthCreationMusicList(musicId);
		
		request.setAttribute("musicBoard", musicBoard);	
		request.setAttribute("NthCreationList", nthCreationList);
		return "/board/boardRead.jsp";
    }
}