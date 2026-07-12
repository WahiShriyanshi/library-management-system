import java.time.LocalDate;

public class Transaction {
	private int transactionId;
	private int bookId;
	private int memberId;
	private int librarianId;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private Integer totalFine;
	private Boolean paidFine;

	public Transaction(int transactionId, int bookId, int memberId, int librarianId, LocalDate issuDate,
			LocalDate dueDate, Integer totalFine) {
		this.transactionId = transactionId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.librarianId = librarianId;
		this.issueDate = issuDate;
		this.dueDate = dueDate;
		this.returnDate = null;
		this.totalFine = null;
		this.paidFine = null;
	}

	// getters
	public int getTransId() {
		return transactionId;
	}

	public int getBookId() {
		return bookId;
	}

	public int getMemberId() {
		return memberId;
	}

	public int getLibrarianId() {
		return librarianId;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public int getTotalFine() {
		return totalFine;
	}

	public Boolean ispaidFine() {
		return paidFine;
	}

	// setters
	public void setTransId(int transactionId) {
		this.transactionId = transactionId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}

	public void setIssueDate(int IssueDate) {
		this.issueDate = issueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public void setTotalFine(int totalFine) {
		this.totalFine = totalFine;
	}

	public void setpaidFine() {
		this.paidFine = paidFine;
	}

}
