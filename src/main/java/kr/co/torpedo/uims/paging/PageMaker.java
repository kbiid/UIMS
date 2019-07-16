package kr.co.torpedo.uims.paging;

/**
 * 페이징 버튼 부분 담당하는 클래스
 * 
 * @author user
 *
 */
public class PageMaker {
	/**
	 * 게시판 전체 데이터
	 */
	private int totalCount;
	// 한번에 보여질 페이징 번호 갯수
	private int displayPageNum = 10;

	// 현재화면에서 보일 startPage 번호
	private int startPage;
	// 현재화면에서 보일 endPage 번호
	private int endPage;
	// 이전 버튼 활성화 여부
	private boolean prev;
	// 다음 버튼 활성화 여부
	private boolean next;

	private Criteria cri;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcDate();
	}

	private void calcDate() {
		calcEndPage();
		startPage = (endPage - displayPageNum) + 1;
		int tempEndPage = calcTempPage();
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	private int calcTempPage() {
		int tempEndPage;
		if ((totalCount % cri.getPerPageNum()) == 0) {
			tempEndPage = (totalCount / cri.getPerPageNum());
		} else {
			tempEndPage = (totalCount / cri.getPerPageNum()) + 1;
		}
		return tempEndPage;
	}

	private void calcEndPage() {
		if ((cri.getPage() % displayPageNum) == 0) {
			endPage = (cri.getPage() / displayPageNum) * displayPageNum;
		} else {
			endPage = ((cri.getPage() / displayPageNum) + 1) * displayPageNum;
		}
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
}
