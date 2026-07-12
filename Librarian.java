import java.time.LocalDate;

public class Librarian extends User {
	private Shift shift;

	public Librarian(int id, String name, LocalDate joiningDate, String email, String password, Shift shift) {
		super(id, name, joiningDate, email, password);
		this.shift = shift;
	}

	// getter
	public Shift getShift() {
		return shift;
	}

	// setter
	public void setShift(Shift shift) {
		this.shift = shift;
	}

}
