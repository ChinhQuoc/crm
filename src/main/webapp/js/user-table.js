// Khi nào file html được load thì thực hiện gì đó
$(document).ready(function() {
	
	
	// đăng ký sự kiện click: $("tên thẻ || tên class || id").click()
	// class => .
	// id => #
	// đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	$(".btn-xoa").click(function() {
		// lấy gtr của thuộc tính (attr) bên thẻ có class là btn-xoa
		// $(this): đại diện cho function đang thực thi
		let id = $(this).attr('id-user');
		let This = $(this);
		
		// RestTemplate, HttpClient
		$.ajax({
		  method: "GET",
		  url: `http://localhost:8080/crm_project/api/user/delete?id=${id}`, // string literals
		  //data: { name: "John", location: "Boston" } // tham số data chỉ dành cho phương thức POST
		})
		  .done(function( result ) {
			  // result đại diện cho kq từ link url trả về
			  if (result.data == true) {
				  // .parent => đi ra 1 thẻ cha
				  //.closest => đi ra thằng cha được chỉ định
				  //.remove => Xóa thẻ
				  //This.parent().parent().remove();
				  This.closest('tr').remove();
			  }
	  	});
	})
})