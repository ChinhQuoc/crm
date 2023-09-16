$(document).ready(function() {
	$(".btn-xoa").click(function() {
		let id = $(this).attr('id-role');
		let This = $(this);
		
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crm_project/api/groupwork/delete?id=${id}`,
		})
		  .done(function( result ) {
			  if (result.data == true) {
				  window.location.reload();
			  } else {
				  alert("Groupwork đang được sử dụng");
			  }
		  })
		  .fail(function(jqXHR, textStatus, errorThrown) {
			  alert("Lỗi xóa groupwork " + errorThrown);
		  });
	})
})