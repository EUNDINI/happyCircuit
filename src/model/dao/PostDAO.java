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
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}
	
	// Post(���� �Խñ�) ����
	public int create(Post post) throws SQLException {
		String sql = "INSERT INTO POST VALUES (postId_seq.nextval, ?, to_date(SYSDATE, 'yyyy-mm-dd'), 0, ?, ?, ?, ?)";		
		Object[] param = new Object[] { post.getPostTitle(), 
						post.getPostContent(),
						post.getPostAttachment(), 
						post.getPostCategoryId(), 
						post.getArtistId() };	
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = { "postId" };
		try {
			jdbcUtil.executeUpdate(key); // insert �� ����
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
			jdbcUtil.close(); // resource ��ȯ
		}

		return 0;			
	}
	
	// Post(���� �Խñ�) ����
	public int update(Post post) throws SQLException {
		String sql = "UPDATE Post "
					+ "SET postTitle=?, postContent=?, postAttachment=?, postCategoryId=? "
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
	
	// �־��� postId�� �ش��ϴ� post�� �����ͺ��̽����� ã�� ������ Ŭ������ �����Ͽ� ��ȯ
	public Post findPost(int postId) throws SQLException {
		String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, pc.postCategoryName, a.artistId, a.nickname "
    			+ "FROM Post p, PostCategory pc, Artist a "
    			+ "WHERE p.postCategoryId = pc.postCategoryId "
    			+ "AND p.artistId = a.artistId " 
				+ "AND p.postId=? ";          
	jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�� query���� �Ű� ���� ����
	try {
		ResultSet rs = jdbcUtil.executeQuery();		// query ����
		Post post = null;
		if (rs.next()) {						//  ���� �߰�
			post = new Post(		// Post ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
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
		jdbcUtil.close();		// resource ��ȯ
	}
	return null;
		
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return postList;
	}
	
	// ��ü Post�� List�� ���� �� ��ȯ
	public List<Post> findPostList() throws SQLException {
		String sql = "SELECT p.postId, p.postTitle, p.postDate, p.postView, p.postContent, p.postAttachment, pc.postCategoryId, pc.postCategoryName, a.artistId, a.nickname "
    			+ "FROM Post p, PostCategory pc, Artist a "
    			+ "WHERE p.postCategoryId = pc.postCategoryId "
    			+ "AND p.artistId = a.artistId " 
				+ "ORDER BY p.postId DESC ";
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
						rs.getString("postAttachment"),
						rs.getInt("postCategoryId"),
						rs.getString("postCategoryName"),
						rs.getString("artistId"),
						rs.getString("nickname"));
				postList.add(post);
				System.out.println("�߰��ƽ��ϴ�!!!!!");
				System.out.println("(PostDAO) postList�� ����: " + postList.size());
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
	
	// postCategoryId�� �̿��Ͽ� postCategoryName ã��
	public String findPostCategoryName(int postCategoryId) throws SQLException {
		String sql = "SELECT pc.postCategoryName "
    			+ "FROM PostCategory pc "
				+ "WHERE postCategoryId=? ";          
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postCategoryId});	// JDBCUtil�� query���� �Ű� ���� ����
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			String postCategoryName = null;
			if (rs.next()) {						//  ���� �߰�
				postCategoryName = rs.getString("postCategoryName");
			}
			return postCategoryName;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
}
