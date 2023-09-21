package entity;

// entity: là nơi khai báo ra các class đặt tên và thuộc tính giống với lại tên bảng trong BD
// Nếu cột là khóa ngoại thì k khai báo biến mà sẽ chuyển thành đối tượng của bảng được tham chiếu tới
public class User {
	private int id;
	private String email;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Role role;
	private String fullName;
	private String image;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
