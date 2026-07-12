import java.time.LocalDate;

public class Member extends User {
	private int issuedBooks;
	private double pendingFine;

	public Member(int id, String name, LocalDate joiningDate, String email, String password, int issuedBooks) {
		super(id, name, joiningDate, email, password);
		this.issuedBooks = issuedBooks;
	}

	// getter
	public int getIssuedBooks() {
		return issuedBooks;
	}

	public double getPendingFine() {
		return pendingFine;
	}

	// setter
	public void setIssuedBooks(int issuedBooks) {
		this.issuedBooks = issuedBooks;
	}

	public void setPendingFine(double pendingFine) {
		this.pendingFine = pendingFine;
	}
}
