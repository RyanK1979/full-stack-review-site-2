package org.wecancodeit.reviewsitefullstack;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	TagRepository tagRepo;

	@Resource
	CommentRepository commentRepo;

	@RequestMapping(value = "reviews")
	public String getAllReviews(@RequestParam Long id, Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("review")
	public String getAReview(@RequestParam Long id, Model model) {
		model.addAttribute("review", reviewRepo.findOne(id));
		return "review";
	}

	@RequestMapping("/add-tag")
	public String addTag(String name, Long reviewId) {
		Review appendedReview = reviewRepo.findOne(reviewId);

		Iterable<Tag> tags = tagRepo.findAll();
		Long existingTagId = 0L;
		for (Tag tag : tags) {
			if (tag.getTagName().equalsIgnoreCase(name)) {
				existingTagId = tag.getId();
				break;
			}
		}
		if (existingTagId != 0L) {
			Tag existingTag = tagRepo.findOne(existingTagId);
			if (appendedReview.getTags().contains(existingTag)) {
				return "redirect:/review?id=" + reviewId;
			} else {
				appendedReview.getTags().add(existingTag);
				existingTag.getReview().add(appendedReview);
				reviewRepo.save(appendedReview);
				tagRepo.save(existingTag);
				return "redirect:/review?id=" + reviewId;
			}
		} else {
			Tag newTag = new Tag(name, appendedReview);
			tagRepo.save(newTag);
			return "redirect:/review?id=" + reviewId;
		}
	}

	@RequestMapping("/remove-tag")
	public String removeTag(Long tagId, Long reviewId) {
		Review parentReview = reviewRepo.findOne(reviewId);
		Tag tagToRemove = tagRepo.findOne(tagId);
		parentReview.getTags().remove(tagToRemove);
		tagToRemove.getReview().remove(parentReview);
		reviewRepo.save(parentReview);
		if (tagToRemove.getReview().size() == 0) {
			tagRepo.delete(tagId);
		} else {
			tagRepo.save(tagToRemove);
		}
		return "redirect:/review?id=" + reviewId;
	}

	@RequestMapping("tag")
	public String getATag(@RequestParam Long id, Model model) {
		model.addAttribute("tag", tagRepo.findOne(id));
		return "tag";
	}

}