package org.mai.bean;

import java.util.ArrayList;
import java.util.List;

import org.mai.dto.CommentDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CommentList {
	private Boolean hasMore;
	private List<CommentDto> data;
	
	public CommentList() {
	    this.data = new ArrayList<>();
	}
	
	public Boolean getHasMore() {
		return hasMore;
	}
	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}
	public List<CommentDto> getData() {
		return data;
	}
	public void setData(List<CommentDto> data) {
		this.data = data;
	}
	
}
