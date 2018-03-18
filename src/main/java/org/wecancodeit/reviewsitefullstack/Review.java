package org.wecancodeit.reviewsitefullstack;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	@Lob
	private String description;
	private String image;
	private String title;

	@JsonIgnore
	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	@Lob
	@ElementCollection
	private Collection<String> content;

	@JsonIgnore
	@ManyToMany
	private Set<Tag> tags;

	public String getTitle() {
		return title;
	}

	public String getImage() {
		return image;
	}

	public String getReview() {
		return description;
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

	public Collection<Comment> getComments() {
		return comments;
	}

	public Collection<String> getContent() {
		return content;
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

	public Review(String description, String name, String image, Category category, Tag... tags) {
		this.description = description;
		this.title = name;
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