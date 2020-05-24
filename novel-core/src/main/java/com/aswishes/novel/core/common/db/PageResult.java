package com.aswishes.novel.core.common.db;

import java.util.List;

/**
 * 分页
 */
public abstract class PageResult<T> {
	/**
	 * 分页的两种模式: 总数式，计数式<br>
	 * 总数式: 需要查询符合条件的记录的总数，可以直接跳转到某页．适合记录总数不多的情况(可能是十万,百万级别)．因为数量统计会很慢<br>
	 * 计数式: 不需要查询记录总数，只有上一页和下一页．适合对记录数量不敏感的情况<br>
	 */
	public enum Mode {
		TOTAL,
		COUNT
	}
	
    /** 页码 */
    private long pageNo = 1;
    /** 每页数量 */
    private int pageSize = 20;
    /** 总页数，应当在查询完成后赋值 */
    private long pageCount = 1;
    /** 总记录数 */
    private long totalCount = 0;
    /** 下一页 */
    private long nextNo;
    /** 上一页 */
    private long prevNo;
    /** 分页数据 */
    private List<T> result;
    /** 分页数据的起始索引位置 */
    private long startIndex = 0;
    
    private Mode mode = Mode.TOTAL;
    /** 是否有下一页 */
    private boolean hasNext = false;
    /** 是否有上一页 */
    private boolean hasPrev = false;

    public PageResult() {}

    public PageResult(int pageSize) {
    	if (pageSize > 0) {
    		this.pageSize = pageSize;
    	}
    }

    public PageResult(long pageNo, int pageSize) {
    	this(pageSize);
    	if (pageNo < 1) {
    		this.pageNo = 1;
    	} else {
    		this.pageNo = pageNo;
    	}
    }

    public List<T> paging() throws Exception {
        if (mode == Mode.TOTAL) {
        	return pagingForTotal();
        } else {
        	return pagingForCount();
        }
    }
    
    private List<T> pagingForTotal() throws Exception {
   		this.totalCount = queryCount();
        calPageCount();
        this.startIndex = (pageNo - 1) * pageSize;
        if (this.totalCount < 1) {
        	return null;
        }
        result = query(startIndex, pageNo, pageSize);
        return result;
    }
    
    /**
     * 总页数。如果总记录数为 0，页数为1.
     */
    private void calPageCount() {
        if (totalCount == 0) {
            pageCount = 1;
        } else if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
        	pageCount = totalCount / pageSize + 1;
        }
        if (pageNo > pageCount) {
        	pageNo = pageCount;
        }
        nextNo = pageNo + 1;
        hasNext = true;
        if (nextNo > pageCount) {
        	nextNo = pageCount;
        	hasNext = false;
        }
        prevNo = pageNo - 1;
        hasPrev = true;
        if (prevNo < 1) {
        	prevNo = 1;
        	hasPrev = false;
        }
    }
    
    private List<T> pagingForCount() throws Exception {
        this.startIndex = (pageNo - 1) * pageSize;
        // 多查一条，能够满载说明有下一页，否则就是最后一页.
    	result = query(startIndex, pageNo, pageSize + 1);
    	int resultSize = result.size();
    	if (result != null && resultSize == pageSize + 1) {
    		result.remove(resultSize - 1);
    		nextNo = pageNo + 1;
    		hasNext = true;
    	} else {
    		nextNo = pageNo;
    		hasNext = false;
    	}
    	if (pageNo == 1) {
    		prevNo = 1;
    		hasPrev = false;
    	} else {
    		prevNo = pageNo - 1;
    		hasPrev = true;
    	}
        return result;
    }

    public abstract long queryCount() throws Exception;

    public abstract List<T> query(long startIndex, long pageNo, int pageSize) throws Exception;

    /**
     * 查询的起始索引位置
     * @return 起始索引位置
     */
    public long getStartIndex() {
    	return this.startIndex;
    }

    public long getPageCount() {
		return pageCount;
	}

    public List<T> getResult() {
    	return this.result;
    }

    public void setResult(List<T> list) {
    	this.result = list;
    }

    /**
     * @return 总记录数
     */
    public long getTotalCount() {
        return this.totalCount;
    }

    /**
     * @return 每页数量
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * @return 当前页码
     */
    public long getPageNo() {
        return this.pageNo;
    }

    public long getPrevNo() {
    	return this.prevNo;
    }

    public long getNextNo() {
    	return this.nextNo;
    }
    
    public boolean isHasNext() {
		return hasNext;
	}
    
    public boolean isHasPrev() {
		return hasPrev;
	}
    
    public PageResult<T> setMode(Mode mode) {
    	this.mode = mode;
    	return this;
    }
}
