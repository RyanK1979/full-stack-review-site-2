package org.wecancodeit.reviewsitefullstack;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;
	private String category;

	@OneToMany(mappedBy = "category")
	private Set<Review> reviews;

	public Category(String category) {
		this.category = category;
	}

	public String getCategoryName() {
		return category;
	}

	@SuppressWarnings("unused")
	private Category() {
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return id == ((Category) obj).id;
	}

}