package controller.myPage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.artist.ArtistSessionUtils;
import model.Artist;
import model.Music;
import model.dao.ArtistDAO;
import model.service.ArtistNotFoundException;

public class MyPageController implements Controller {
	
	private ArtistDAO artistDAO = new ArtistDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ��
    	if (!ArtistSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/artist/login/form";		// login form ��û���� redirect
        }
    	
		HttpSession session = request.getSession();
		String artistId = request.getParameter("artistId");

		if (ArtistSessionUtils.getLoginArtistId(session).equals(artistId)) {
			request.setAttribute("isSameArtist", true);
		} else {
			request.setAttribute("isSameArtist", false);
		}

    	Artist artist = artistDAO.findArtistById(artistId);	// ����� ���� �˻�	
  
    	List<Music> musicList = new ArrayList<Music>();
    	
		String projectPath = "E:\\hw\\6\\databaseproject\\newclone";
		String filePath = ".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\happy\\sample";
		String imgPath = projectPath + "\\" + filePath;
		
    	request.setAttribute("artist", artist);		// ����� ���� ����	
    	request.setAttribute("musicList", musicList);
		request.setAttribute("imgPath", imgPath + "\\");
		return "/myPage/myPage.jsp";
	}

}
