package org.wecancodeit.reviewsitefullstack;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	CommentRepository commentRepo;

	@RequestMapping("/review")
	public String showReview(@PathVariable Long id, Model model) {
		model.addAttribute("currentReview", reviewRepo.findOne(id));
		return "singleReview";
	}

	@RequestMapping("/category")
	public String getCategory(@RequestParam long id, Model model) {
		Category category = categoryRepo.findOne(id);
		model.addAttribute("category", category);
		model.addAttribute("review", reviewRepo.findOne(id));
		model.addAttribute("reviews", reviewRepo.findByCategory(category));
		return "category";
	}

	@RequestMapping("/categories")
	public String getCategories(Model model) {
		Collection<Category> categories = (Collection<Category>) categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "categories";
	}

	@RequestMapping(value = "/index")
	public String getAllTables(Model model) {
		Collection<Review> reviews = (Collection<Review>) reviewRepo.findAll();
		model.addAttribute("reviews", reviews);
		Collection<Category> categories = (Collection<Category>) categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "index";

	}

	@RequestMapping("/review/{id}/addcomment")
	public String addCommentToReview(@PathVariable Long id, String userName, String commentText) {
		Review currentReview = reviewRepo.findOne(id);
		Comment comment = new Comment(currentReview, userName, commentText);
		commentRepo.save(comment);

		currentReview.addComment(comment);
		reviewRepo.save(currentReview);

		return "redirect:/review/{id}";
	}

	@RequestMapping("/review/{reviewId}/deletecomment/{commentId}")
	public String deleteComment(@PathVariable Long reviewId, @PathVariable Long commentId) {
		commentRepo.delete(commentId);
		return "redirect:/review/{reviewId}";
	}
}