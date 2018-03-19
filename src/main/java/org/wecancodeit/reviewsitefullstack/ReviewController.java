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
	public String addContentTag(String tagName, Long reviewId) {
		// Long longReviewId = Long.parseLong(reviewId);
		Review appendedReview = reviewRepo.findOne(reviewId);
		Tag newTag = new Tag(tagName, appendedReview);
		tagRepo.save(newTag);
		return "redirect:/review?id=" + reviewId;
	}

	@RequestMapping("/remove-tag")
	public String removeTag(Long tagId, Long reviewId) {
		tagRepo.delete(tagId);
		return "redirect:/review?id=" + reviewId;
	}

}