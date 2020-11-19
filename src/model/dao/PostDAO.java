package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;

public class PostDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PostDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Post(���� �Խñ�) ����
	public int create(Post post) throws SQLException {
		String sql = "INSERT INTO POST VALUES (postId_seq.nextval, ?, SYSDATE, 0, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getPostTitle(), 
						post.getPostContent(),
						post.getPostAttachment(), 
						post.getPostCategoryId(), 
						post.getArtistId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	
	// Post(���� �Խñ�) ����
	public int update(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postTitle=?, postContent=?, postAttachment=?, postCategory=? "
					+ "WHERE postId=? ";
		Object[] param = new Object[] { post.getPostTitle(), 
				post.getPostContent(),
				post.getPostAttachment(), 
				post.getPostCategoryId(),
				post.getPostId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	// Post(���� �Խñ�) ����
	public int remove(int postId) throws SQLException {
		String sql = "DELETE FROM Post WHERE postId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	// Post�� �������� �˻��� ���� �����ͺ��̽����� ã�� List<Post>�� �����Ͽ� ��ȯ
	public List<Post> searchPostTitle(String PostTitle) throws SQLException {
        String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, a.artistId "
        			+ "FROM Post p, PostCategory pc, Artist a "
        			+ "WHERE p.postCategoryId = pc.postCategoryId "
        			+ "AND p.artistId = a.artistId "
        			+ "AND p.postTitle=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {PostTitle});	// JDBCUtil�� query���� �Ű� ���� ����
		List<Post> postList = new ArrayList<Post>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						//  ���� �߰�
				Post post = new Post(		// Post ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
						rs.getInt("postId"),
						rs.getString("postTitle"),
						rs.getDate("postDate"),
						rs.getInt("postView"),
						rs.getString("postContent"),
						rs.getInt("postAttachment"),
						rs.getInt("postCategoryId"),
						rs.getInt("artistId"));
				postList.add(post);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return postList;
	}
	
	// ��ü Post�� List�� ���� �� ��ȯ
	public List<Post> findPostList() throws SQLException {
		String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, a.artistId "
    			+ "FROM Post p, PostCategory pc, Artist a "
    			+ "WHERE p.postCategoryId = pc.postCategoryId "
    			+ "AND p.artistId = a.artistId "
    			+ "AND p.postTitle=? ";    
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Post> postList = new ArrayList<Post>();	
			while (rs.next()) {
				Post post = new Post(		
						rs.getInt("postId"),
						rs.getString("postTitle"),
						rs.getDate("postDate"),
						rs.getInt("postView"),
						rs.getString("postContent"),
						rs.getInt("postAttachment"),
						rs.getInt("postCategoryId"),
						rs.getInt("artistId"));
				postList.add(post);
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	// post�� view(��ȸ��) ����
	public int updateView(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postView=? "
					+ "WHERE postId=? ";
		Object[] param = new Object[] {post.getPostView() + 1, post.getPostId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
}
