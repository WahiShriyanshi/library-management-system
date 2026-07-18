package Managment;

import Entity.Member;
import Entity.Transaction;
import Entity.TransactionStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.crypto.dsig.TransformException;

import Exception.NewTransactionCannotBeCreatedException;
import Exception.TransactionNotFound;
import Exception.UserDoesNotExistsException;

//Transaction(int transactionId, int bookId, int memberId, int librarianId, LocalDate issuDate,	LocalDate dueDate, int totalFine)
public class TransactionManagement {
	HashMap<Integer, Transaction> transaction = new HashMap<>();
	private static final int FINE_PER_DAY = 50;
	private static final int MAX_FINE_ALLOWED = 1000;
	private UserManagement userManagemnt;

	public TransactionManagement(UserManagement userManagment) {
		this.userManagemnt = userManagment;
		transaction = new HashMap<>();
	}

	public void updateStatusIfOverdue(Transaction t) {
		if (t.getStatus() == TransactionStatus.ACTIVE) {
			if (t.getDueDate().isBefore(LocalDate.now())) {
				t.setStatus(TransactionStatus.OVERDUE);
			}
		}
	}

	public double calculateFine(Transaction t) {
		LocalDate today = LocalDate.now();
		if (t.getDueDate().isBefore(today)) {
			long days = ChronoUnit.DAYS.between(t.getDueDate(), LocalDate.now());
			return (double) days * FINE_PER_DAY;
		}
		return 0;

	}

	public String newTransaction(int memberId, int bookId, int librarianId)
			throws NewTransactionCannotBeCreatedException, UserDoesNotExistsException {
		Member member = userManagemnt.showMemberById(memberId);
		if (member.getPendingFine() >= MAX_FINE_ALLOWED) {
			throw new NewTransactionCannotBeCreatedException("Fine has exceeded the limit please pay your fine first");
		}
		int transactionId = transaction.size() + 1;
		// int transactionId, int bookId, int memberId, int librarianId, LocalDate
		// issuDate,Integer totalFine, TransactionStatus status
		Transaction t = new Transaction(transactionId, bookId, memberId, librarianId, LocalDate.now());
		transaction.put(t.getTransId(), t);
		return "New book" + bookId + "has been issued to" + memberId;
	}

	public String completeReturn(Transaction t) throws TransactionNotFound, UserDoesNotExistsException {
		Member member = userManagemnt.showMemberById(t.getMemberId());
		if (transaction.containsKey(t.getTransId())) {
			updateStatusIfOverdue(t);
			TransactionStatus s = t.getStatus();
			if (s == TransactionStatus.OVERDUE) {
				double fine = calculateFine(t);
				member.setPendingFine(member.getPendingFine() + fine);
				t.setTotalFine(fine);
				t.setReturnDate(LocalDate.now());
				t.setStatus(TransactionStatus.RETURN);
				return "Book has been returned with fine Rs." + fine;
			} else {
				t.setReturnDate(LocalDate.now());
				t.setStatus(TransactionStatus.RETURN);
				return "Book has been returned successfully. No fine.";
			}
		} else {
			throw new TransactionNotFound();
		}
	}
}