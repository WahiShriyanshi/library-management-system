package Entity;

import java.time.LocalDate;

public class Transaction {
	private int transactionId;
	private int bookId;
	private int memberId;
	private int librarianId;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private double totalFine;
	private double paidFine;
	private TransactionStatus status;

	public Transaction(int transactionId, int bookId, int memberId, int librarianId, LocalDate issuDate) {
		this.transactionId = transactionId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.librarianId = librarianId;
		this.issueDate = issuDate;
		this.dueDate = issueDate.plusDays(14);
		this.returnDate = null;
		this.totalFine = 0.0;
		this.paidFine = 0.0;
		this.status = TransactionStatus.ACTIVE;
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

	public double getTotalFine() {
		return totalFine;
	}

	public double getPaidFine() {
		return paidFine;
	}

	public TransactionStatus getStatus() {
		return status;
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

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public void setTotalFine(double totalFine) {
		this.totalFine = totalFine;
	}

	public void setPaidFine(double paidFine) {
		this.paidFine = paidFine;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public String isFullyPaid() {
		if (paidFine >= totalFine) {
			return "Fine has been paid completely";
		}
		return "Amount" + paidFine + "has been paid" + (totalFine - paidFine) + "is pending";
	}
}
