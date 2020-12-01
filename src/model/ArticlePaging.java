package model;

import model.dao.MusicDAO;

public class ArticlePaging {
	    private final static int pageCount = 15;
	    private int blockStartNum = 0;
	    private int blockLastNum = 0;
	    private int lastPageNum = 0;
	    private int total = 0;

	    public int getBlockStartNum() {
	        return blockStartNum;
	    }
	    public void setBlockStartNum(int blockStartNum) {
	        this.blockStartNum = blockStartNum;
	    }
	    public int getBlockLastNum() {
	        return blockLastNum;
	    }
	    public void setBlockLastNum(int blockLastNum) {
	        this.blockLastNum = blockLastNum;
	    }
	    public int getLastPageNum() {
	        return lastPageNum;
	    }
	    public void setLastPageNum(int lastPageNum) {
	        this.lastPageNum = lastPageNum;
	    }
	    
	    public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		
		// block을 생성
	    // 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
	    public void makeBlock(int curPage){
	        int blockNum = 0;

	        blockNum = (int)Math.floor((curPage-1)/ pageCount);
	        blockStartNum = (pageCount * blockNum) + 1;
	        blockLastNum = blockStartNum + (pageCount-1);
	    }

	    // 총 페이지의 마지막 번호
	    public void makeLastPageNum() {
	        MusicDAO dao = new MusicDAO();
	        total = dao.countMusicArticle();

	        if( total % pageCount == 0 ) {
	            lastPageNum = (int)Math.floor(total/pageCount);
	        }
	        else {
	            lastPageNum = (int)Math.floor(total/pageCount) + 1;
	        }
	    }

	    // 검색을 했을 때 총 페이지의 마지막 번호
	    public void makeLastPageNum(String condition, String kwd) {
	    	 MusicDAO dao = new  MusicDAO();
	        total = dao.countSearchMusicArticle(condition, kwd);

	        if( total % pageCount == 0 ) {
	            lastPageNum = (int)Math.floor(total/pageCount);
	        }
	        else {
	            lastPageNum = (int)Math.floor(total/pageCount) + 1;
	        }
	    }
	    
	    // nthlist
	    public void makeLastPageNum(int total) {
	    	MusicDAO dao = new  MusicDAO();
	        this.total = total;

	        if( total % 5 == 0 ) {
	            lastPageNum = (int)Math.floor(total/5);
	        }
	        else {
	            lastPageNum = (int)Math.floor(total/5) + 1;
	        }
	    }
	}