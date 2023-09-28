$(document).ready(function() {
	$(".btn-yes").click(function() {
		const { origin, pathname } = window.location;
		window.location.replace(origin + pathname + "login");
	})
})