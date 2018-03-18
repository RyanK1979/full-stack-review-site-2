package org.wecancodeit.reviewsitefullstack;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagRestController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	TagRepository tagRepo;

	@RequestMapping("/tags/{id}")
	public Tag findOneTagName(@PathVariable long id) {

		return tagRepo.findOne(id);
	}

	@RequestMapping("/tags")
	public Iterable<Tag> findAllTags() {
		return tagRepo.findAll();

	}
}
