package org.mai.dto;

import org.mai.bean.Comment;

public class CommentDto extends Comment{
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
