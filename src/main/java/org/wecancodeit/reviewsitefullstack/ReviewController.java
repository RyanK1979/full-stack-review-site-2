package org.wecancodeit.reviewsitefullstack;

import java.util.Set;

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
	TagRepository tagRepo;

	@Resource
	CategoryRepository categoryRepo;

	@RequestMapping("/review")
	public String getAReview(@RequestParam Long id, Model model) {
		model.addAttribute("review", reviewRepo.findOne(id));
		model.addAttribute("tag", tagRepo.findOne(id));
		return "review";
	}

	@RequestMapping("/reviews")
	public String showAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/tag")
	public String showOneTag(@RequestParam(value = "id", required = true) long id, Model model) {
		model.addAttribute("tag", tagRepo.findOne(id));
		return "tag";
	}

	@RequestMapping("/tags")
	public String showAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
	}

	@RequestMapping("/categories")
	public String getCategories(Model model) {
		model.addAttribute("category", categoryRepo.findAll());
		return "categories";
	}

	@RequestMapping("/category")
	public String getCategory(@RequestParam long id, Model model) {
		model.addAttribute("category", categoryRepo.findOne(id));
		return "category";
	}

	@RequestMapping(value = "/index")
	public String showAllClasses(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("tags", tagRepo.findAll());
		model.addAttribute("reviews", reviewRepo.findAll());
		return "index";

	}

	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value = "id") Long id, String tag) {
		if (!tag.equals("")) {
			Tag tagCreation = tagRepo.findByTag(tag);
			if (tagCreation == null) {
				tagCreation = new Tag(tag);
				tagRepo.save(tagCreation);
			}
			Review review = reviewRepo.findOne(id);
			Set<Tag> reviewTags = review.getTags();
			if (!reviewTags.contains(tagCreation)) {
				review.addTag(tagCreation);
				reviewRepo.save(review);
			}
		}
		return "redirect:/review?id=" + id;
	}

	@RequestMapping("/remove-tag")
	public String removeTag(@RequestParam Long tagId, @RequestParam Long reviewId) {
		Tag deleteTag = tagRepo.findOne(tagId);
		Review review = reviewRepo.findOne(reviewId);
		review.removeTag(deleteTag);
		reviewRepo.save(review);
		return "redirect:/review?id=" + reviewId;
	}

}
