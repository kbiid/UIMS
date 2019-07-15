package kr.co.torpedo.uims.paging;

/**
 * 게시판 페이징 전용 클래스
 * 
 * @author user
 *
 */
public class Criteria {
	// 보여줄 페이지 번호
	private int page;
	// 페이지당 보여줄 게시글 갯수
	private int perPageNum;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	/**
	 * limit 구문에서 시작 위치를 지정할 때 사용되는 메소드.
	 * 
	 * @return
	 */
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
}
