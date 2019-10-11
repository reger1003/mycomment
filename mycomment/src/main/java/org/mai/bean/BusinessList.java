package org.mai.bean;

import java.util.ArrayList;
import java.util.List;

import org.mai.dto.BusinessDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BusinessList {
	private Boolean hasMore;
	private List<BusinessDto> data;
	
	public BusinessList() {
	    this.data = new ArrayList<>();
	}
	
	public Boolean getHasMore() {
		return hasMore;
	}
	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}
	public List<BusinessDto> getData() {
		return data;
	}
	public void setData(List<BusinessDto> data) {
		this.data = data;
	}
	
}
