$(document).ready(function() {
	$(".btn-xoa").click(function() {
		let id = $(this).attr('id-task');
		let idStatus = $(this).attr('id-status');
		let idUser = $(this).attr('id-user');
		let This = $(this);

		$.ajax({
			method: "GET",
			url: `http://localhost:8080/crm_project/api/task/delete?id=${id}&idStatus=${idStatus}&idUser=${idUser}`,
		})
			.done(function(result) {
				if (result.data == true) {
					window.location.reload();
				} else {
					alert("Task đang được sử dụng");
				}
			})
			.fail(function(jqXHR, textStatus, errorThrown) {
				alert("Lỗi xóa task " + errorThrown);
			});
	})
})