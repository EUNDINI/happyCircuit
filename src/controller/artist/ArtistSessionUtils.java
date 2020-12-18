package controller.artist;

import javax.servlet.http.HttpSession;

public class ArtistSessionUtils {
	public static final String ARTIST_SESSION_KEY = "artistId";
	public static final String ARTIST_NICKNAME = "nickname";
	
    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginArtistId(HttpSession session) {
        String artistId = (String)session.getAttribute(ARTIST_SESSION_KEY);
        return artistId;
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginArtistId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 artistId인지 검사 */
    public static boolean isLoginArtist(String artistId, HttpSession session) {
        String loginArtist = getLoginArtistId(session);
        if (loginArtist == null) {
            return false;
        }
        return loginArtist.equals(artistId);
    }
}
