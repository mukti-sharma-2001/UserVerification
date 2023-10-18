package com.ass.mini2.entity;

public class PageInfo {
	private boolean hasNextPage;
    private boolean hasPreviousPage;
    private int total;
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "PageInfo [hasNextPage=" + hasNextPage + ", hasPreviousPage=" + hasPreviousPage + ", total=" + total
				+ "]";
	}
    
    
}
