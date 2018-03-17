// package org.wecancodeit.reviewsitefullstack;
//
// import javax.annotation.Resource;
//
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// @RequestMapping("/tags")
// public class ReviewRestController {
//
// @Resource
// private TagRepository repo;
//
// @RequestMapping("")
// public Iterable<Tag> findAllCourses(@RequestParam(defaultValue = "") String
// search,
// @RequestParam(defaultValue = "") String advanced) throws InterruptedException
// {
// if (search.isEmpty()) {
// if (advanced.isEmpty()) {
// return repo.findAll();
// }
//
// @RequestMapping("/{id}")
// public Tag findOneCourse(@PathVariable long id) {
// return repo.findOne(id);
// }
// }
