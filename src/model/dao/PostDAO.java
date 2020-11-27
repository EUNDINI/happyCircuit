package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;
import model.PostCategory;

public class PostDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PostDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}
	
	// Post(구인 게시글) 생성
	public int create(Post post) throws SQLException {
		String sql = "INSERT INTO POST VALUES (postId_seq.nextval, ?, to_date(SYSDATE, 'yyyy-mm-dd'), 0, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getPostTitle(), 
						post.getPostContent(),
						post.getPostAttachment(), 
						post.getPostCategoryId(), 
						post.getArtistId() };	
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = { "postId" };
		try {
			jdbcUtil.executeUpdate(key); // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();

			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				return generatedKey;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}

		return 0;			
	}
	
	// Post(구인 게시글) 수정
	public int update(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postTitle=?, postContent=?, postAttachment=?, postCategoryId=? "
					+ "WHERE postId=? ";
		Object[] param = new Object[] { post.getPostTitle(), 
				post.getPostContent(),
				post.getPostAttachment(), 
				post.getPostCategoryId(),
				post.getPostId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	// Post(구인 게시글) 삭제
	public int remove(int postId) throws SQLException {
		String sql = "DELETE FROM Post WHERE postId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	// 주어진 postId에 해당하는 post를 데이터베이스에서 찾아 도메인 클래스에 저장하여 반환
	public Post findPost(int postId) throws SQLException {
		String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, pc.postCategoryName, a.artistId, a.nickname "
    			+ "FROM Post p, PostCategory pc, Artist a "
    			+ "WHERE p.postCategoryId = pc.postCategoryId "
    			+ "AND p.artistId = a.artistId " 
				+ "AND p.postId=? ";          
	jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil에 query문과 매개 변수 설정
	try {
		ResultSet rs = jdbcUtil.executeQuery();		// query 실행
		Post post = null;
		if (rs.next()) {						//  정보 발견
			post = new Post(		// Post 객체를 생성하여 커뮤니티 정보를 저장
					rs.getInt("postId"),
					rs.getString("postTitle"),
					rs.getDate("postDate"),
					rs.getInt("postView"),
					rs.getString("postContent"),
					rs.getString("postAttachment"),
					rs.getInt("postCategoryId"),
					rs.getString("postCategoryName"),
					rs.getString("artistId"),
					rs.getString("nickname"));
		}
		return post;
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();		// resource 반환
	}
	return null;
		
	}
	
	// Post의 제목으로 검색한 것을 데이터베이스에서 찾아 List<Post>에 저장하여 반환
	public List<Post> searchPostTitle(String PostTitle) throws SQLException {
        String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, a.artistId "
        			+ "FROM Post p, PostCategory pc, Artist a "
        			+ "WHERE p.postCategoryId = pc.postCategoryId "
        			+ "AND p.artistId = a.artistId "
        			+ "AND p.postTitle=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {PostTitle});	// JDBCUtil에 query문과 매개 변수 설정
		List<Post> postList = new ArrayList<Post>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						//  정보 발견
				Post post = new Post(		// Post 객체를 생성하여 커뮤니티 정보를 저장
						rs.getInt("postId"),
						rs.getString("postTitle"),
						rs.getDate("postDate"),
						rs.getInt("postView"),
						rs.getString("postContent"),
						rs.getString("postAttachment"),
						rs.getInt("postCategoryId"),
						rs.getString("postCategoryName"),
						rs.getString("artistId"),
						rs.getString("nickname"));
				postList.add(post);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return postList;
	}
	
	// 전체 Post를 List에 저장 및 반환
	public List<Post> findPostList() throws SQLException {
		String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, pc.postCategoryName, a.artistId, a.nickname "
    			+ "FROM Post p, PostCategory pc, Artist a "
    			+ "WHERE p.postCategoryId = pc.postCategoryId "
    			+ "AND p.artistId = a.artistId " 
				+ "ORDER BY p.postId DESC ";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Post> postList = new ArrayList<Post>();	
			while (rs.next()) {
				Post post = new Post(		
						rs.getInt("postId"),
						rs.getString("postTitle"),
						rs.getDate("postDate"),
						rs.getInt("postView"),
						rs.getString("postContent"),
						rs.getString("postAttachment"),
						rs.getInt("postCategoryId"),
						rs.getString("postCategoryName"),
						rs.getString("artistId"),
						rs.getString("nickname"));
				postList.add(post);
				System.out.println("추가됐습니다!!!!!");
				System.out.println("(PostDAO) postList의 길이: " + postList.size());
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	// post의 view(조회수) 증가
	public int updateView(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postView=? "
					+ "WHERE postId=? ";
		Object[] param = new Object[] {post.getPostView() + 1, post.getPostId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	// postCategoryId를 이용하여 postCategoryName 찾기
	public String findPostCategoryName(int postCategoryId) throws SQLException {
		String sql = "SELECT pc.postCategoryName "
    			+ "FROM PostCategory pc "
				+ "WHERE postCategoryId=? ";          
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postCategoryId});	// JDBCUtil에 query문과 매개 변수 설정
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			String postCategoryName = null;
			if (rs.next()) {						//  정보 발견
				postCategoryName = rs.getString("postCategoryName");
			}
			return postCategoryName;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
}
