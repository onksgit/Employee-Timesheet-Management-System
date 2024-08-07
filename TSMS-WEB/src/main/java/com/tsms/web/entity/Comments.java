package com.tsms.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {
	@Id
	@Column(name = "pk_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentid;

	@Column(name = "comment_title")
	private String commentTitle;

	@Column(name = "comment_content")
	private String comment;

	@Column(name = "poster_name")
	private String posterName;

	@Column(name = "web_page_name")
	private String webPageName;

	@Column(name = "comment_date1")
	private String commentDate1;

	public Long getCommentid() {
		return commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}

	public String getCommentTitle() {
		return commentTitle;
	}

	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getWebPageName() {
		return webPageName;
	}

	public void setWebPageName(String webPageName) {
		this.webPageName = webPageName;
	}

	public String getCommentDate1() {
		return commentDate1;
	}

	public void setCommentDate1(String commentDate) {
		this.commentDate1 = commentDate;
	}
	
	
	
}
