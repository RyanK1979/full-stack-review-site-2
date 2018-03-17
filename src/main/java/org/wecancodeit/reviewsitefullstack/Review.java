package org.wecancodeit.reviewsitefullstack;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String image;
	@Lob
	private String review;

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	@ManyToMany
	private Set<Tag> tags;

	public Review(long id, Category category) {
		this.id = id;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public String getReview() {
		return review;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public Category getCategory() {
		return category;
	}

	public Long getId() {
		return id;
	}

	public void addTag(Tag tag) {
		tags.add(tag);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
	}

	public Review(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	public void addComment(Comment comment) {

	}

	public Review(String review, String name, String image, Category category, Tag... tags) {
		this.review = review;
		this.name = name;
		this.image = image;
		this.category = category;
		this.tags = new HashSet<>(asList(tags));
	}

	@SuppressWarnings("unused")
	private Review() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return id == ((Review) obj).id;
	}

}