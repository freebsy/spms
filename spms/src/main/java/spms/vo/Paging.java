package spms.vo;

public class Paging {
	protected int totalRowCount;
    protected int currentPage;
    protected int pagePerRow;
    protected int pageSize;
    protected int startPage;
    protected int endPage;
    protected int lastPage;
    
    public int getTotalRowCount() {
        return totalRowCount;
    }
    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPagePerRow() {
        return pagePerRow;
    }
    public void setPagePerRow(int pagePerRow) {
        this.pagePerRow = pagePerRow;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage() {
        startPage = ((currentPage-1)/pageSize)*pageSize+1;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage() {
        endPage = startPage + pageSize -1;
         if(endPage > lastPage){
                endPage = lastPage;
            }
    }
    public int getLastPage() {
        return lastPage;
    }
    public void setLastPage() {
        lastPage = totalRowCount/pagePerRow;
        if(totalRowCount%pagePerRow!=0){
            lastPage++;
        }
    } 
}
