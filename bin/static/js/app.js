//const xhr = new XMLHttpRequest()
//xhr.onreadystatechange = function() {
//	if (xhr.readyState === 4 && xhr.status === 200) {
//		const res = JSON.parse(xhr.response);
//
//		if(res.length) {
//			res.forEach(function(tag) {
//				appendOneElementToBody(tag)
//			})
//		} else {
//			appendOneElementToBody(res)
//		}
//		
//		function appendOneElementToBody(res) {
//			const body = document.body
//
//			const tagContainer = document.createElement('div')
//			tagContainer.classList.add('tagContainer')
//
//			appendElement(tagContainer, createElement('h2', res.name))
//			res.instructor.tagsUrls.forEach(function(tagsUrl) {
//				const tagLink = createElement('a', tagsUrl)
//				tagLink.setAttribute('href', tagsUrl)
//				appendElement(tagContainer, tagLink)
//
//			})
//
//			appendElement(body, tagContainer)
//		}
//
//		function createElement(elem, textValue) {
//			const newElem = document.createElement(elem)
//			newElem.innerText = textValue
//			return newElem
//		}
//
//		function appendElement(parent, child) {
//			parent.appendChild(child)
//		}
//
//		function showAllPropsInObject(object) {
//			for (prop in res) {
//				console.log(`${prop}: ${res[prop]}`)
//				// prop + ': ' + res[prop]
//			}
//		}
//
//		console.log(res)
//	}
//}
//
//xhr.open('GET', 'http://localhost:8080/tags', true)
//xhr.send()