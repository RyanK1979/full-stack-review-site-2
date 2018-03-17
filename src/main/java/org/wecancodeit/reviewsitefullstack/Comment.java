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
	private String userName;
	private String commentText;

	@SuppressWarnings("unused")
	private Comment() {
	}

	public Comment(Review review, String userName, String commentText) {
		this.review = review;
		this.userName = userName;
		this.commentText = commentText;
	}

	public long getId() {
		return id;
	}

	public Review getReview() {
		return review;
	}

	public String getUser() {
		return userName;
	}

	public String getCommentText() {
		return commentText;
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
