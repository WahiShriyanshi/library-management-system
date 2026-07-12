import java.time.LocalDate;

public class User {
	private int id;
	private String name;
	private LocalDate joiningDate;
	private String email;
	private String password;

	public User(int id, String name, LocalDate joiningDate, String email, String password) {
		this.id = id;
		this.name = name;
		this.joiningDate = joiningDate;
		this.email = email;
		this.password = password;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public String getEmail() {
		return email;
	}

	public boolean verifyPassword(String password) {
		return this.password.equals(password);
	}
}