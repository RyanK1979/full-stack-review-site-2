package org.wecancodeit.reviewsitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Review review;
	private String commentText;
	private String userName;

	@SuppressWarnings("unused")
	private Comment() {
	}

	public Comment(String userName, Review review, String commentText) {
		this.userName = userName;
		this.review = review;
		this.commentText = commentText;
	}

	public long getCommentId() {
		return id;
	}

	public Review getReview() {
		return review;
	}

	public String getCommentText() {
		return commentText;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Comment) obj).id;
	}

}
