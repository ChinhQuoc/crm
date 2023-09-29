$(document).ready(function() {
	$(".btn-yes").click(function() {
		let origin = window.location.origin;
		window.location.replace(origin + "/login");
	})
})