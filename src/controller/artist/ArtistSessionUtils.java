package controller.artist;

import javax.servlet.http.HttpSession;

public class ArtistSessionUtils {
	public static final String ARTIST_SESSION_KEY = "artistId";
	public static final String ARTIST_NICKNAME = "nickname";
	
    /* ���� �α����� ������� ID�� ���� */
    public static String getLoginArtistId(HttpSession session) {
        String artistId = (String)session.getAttribute(ARTIST_SESSION_KEY);
        return artistId;
    }

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginArtistId(session) != null) {
            return true;
        }
        return false;
    }

    /* ���� �α����� ������� ID�� artistId���� �˻� */
    public static boolean isLoginArtist(String artistId, HttpSession session) {
        String loginArtist = getLoginArtistId(session);
        if (loginArtist == null) {
            return false;
        }
        return loginArtist.equals(artistId);
    }
}
