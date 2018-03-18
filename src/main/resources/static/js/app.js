const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {
		const res = JSON.parse(xhr.response);

const reviewIdElement = document.querySelector('#review-id')
const currentReviewId = reviewIdElement.value

const tagList = document.querySelectorAll('.tag-delete-x')
tagList.forEach(function(x) {
	x.addEventListener('click', function() {
		const currentTagId = x.nextElementSibling.value
		const requestUrl = 'http://localhost:8080/review/' + currentReviewId + '/tag/' + currentTagId + '/deletetag'

		xhr.open('DELETE', requestUrl, true)
		xhr.send()

		x.parentNode.remove();
	})
})