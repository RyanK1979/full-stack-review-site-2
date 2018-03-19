const xhr = new XMLHttpRequest()

xhr.onreadystatechange = function() {
	if (xhr.readyState === 4 && xhr.status === 200) {

		const res = xhr.responseText;

		const tagList = document.getElementById('AJAXcontainer');

		tagList.innerHTML = res;

	}
}

const form = document.getElementById('addtag-form');

form.addEventListener('submit', function(event) {
	
	event.preventDefault();
	
	const tagInputBox = document.getElementById('add-tag-name-input').value;
	
	if (tagInputBox == "") {
		alert("Empty tag name, try again!")
		return false;
	}

	const reviewId = document.getElementById('add-tag-reviewId').value;
	const tagName = document.getElementById('add-tag-name-input').value;
	
	xhr.open('POST', 'http://localhost:8080/add-tag', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("name=" + tagName + "&reviewId=" + reviewId);
})

document.querySelectorAll('.tag-remove-button').forEach(function(button){
	button.addEventListener("click", function(event){
		event.preventDefault();
		const tagToBeRemovedId = button.parentElement.querySelector('.tag-remove-tag-id').value;
		// console.log(tagToBeRemovedId);
		const reviewId = button.parentElement.querySelector('.tag-remove-review-id').value;
		// console.log(reviewId);
		xhr.open('POST', 'http://localhost:8080/remove-tag', true);	
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.send("tagId=" + tagToBeRemovedId + "&reviewId="+ reviewId);
	});
});