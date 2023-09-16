$(document).ready(function() {
	$(".btn-xoa").click(function() {
		let id = $(this).attr('id-task');
		let This = $(this);
		
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crm_project/api/task/delete?id=${id}`,
		})
		  .done(function( result ) {
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