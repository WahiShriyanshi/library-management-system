import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entity.Book;
import Entity.Librarian;
import Entity.Member;
import Entity.Shift;
import Entity.Transaction;
import Entity.User;
import Exception.InvalidRequestException;
import Exception.TransactionNotFound;
import Exception.UserDoesNotExistsException;
import Managment.BookManagement;
import Managment.TransactionManagement;
import Managment.UserManagement;

public class Library {
	private BookManagement bookManagement;
	private TransactionManagement transactionManagement;
	private UserManagement userManagement;

	public Library() {
		this.userManagement = new UserManagement();
		this.bookManagement = new BookManagement();
		this.transactionManagement = new TransactionManagement(userManagement);
	}

	// Books Managment Functions
	public String addBook(int bookId, String bookName, String author, String description, int price, boolean available)
			throws InvalidRequestException {
		return bookManagement.addBook(bookId, bookName, author, description, price, available);
	}

	public ArrayList<Book> showAllBooks() throws InvalidRequestException {
		return bookManagement.showAllBooks();
	}

	public Book showBookById(int id) throws InvalidRequestException {
		return bookManagement.showBookbyId(id);
	}

	public String removeBookById(int id) throws InvalidRequestException {
		return bookManagement.removeBookbyId(id);
	}

	public List<Book> searchBookByName(String name) throws InvalidRequestException {
		return bookManagement.searchBookByName(name);
	}

	public List<Book> searchBookByAuthor(String name) throws InvalidRequestException {
		return bookManagement.searchBookByAuthor(name);
	}

	public String updateBookByPrice(int bookId, int price) throws InvalidRequestException {
		Book b = bookManagement.showBookbyId(bookId);
		b.setPrice(price);
		return "Book price has been updated";
	}

	public String updateBookByDescription(int bookId, String description) throws InvalidRequestException {
		Book b = bookManagement.showBookbyId(bookId);
		b.setDescription(description);
		return "Book description has been updated";
	}

	public String updateBookByAvailability(int bookId, boolean available) throws InvalidRequestException {
		Book b = bookManagement.showBookbyId(bookId);
		b.setAvailable(available);
		return "Book availability has been updated";
	}

	// UserManagment
	// int id, String name, LocalDate joiningDate, String email, String password
	public String addMember(int memberid, String name, String email, String password) throws InvalidRequestException {
		if (memberid != 0 && name != null && email != null && password != null) {
			Member member = new Member(memberid, name, LocalDate.now(), email, password);
			userManagement.addUser(member);
			return "New Member has been added with Id" + memberid;
		}
		throw new InvalidRequestException("Check all the feilds correctly");
	}

	public String addLibrarian(int userid, String name, String email, String password, Shift shift)
			throws InvalidRequestException {
		if (userid != 0 && name != null && email != null && password != null) {
			Librarian librarian = new Librarian(userid, name, LocalDate.now(), email, password, shift);
			userManagement.addUser(librarian);
			return "New Member has been added with Id" + userid;
		}
		throw new InvalidRequestException("Check all the feilds correctly");
	}

	public List<Librarian> showAllLibrarian() throws UserDoesNotExistsException {
		return userManagement.showAllLibrarians();
	}

	public List<Member> showAllMembers() throws UserDoesNotExistsException {
		return userManagement.showAllMembers();
	}

	public List<User> showAllUsers() throws UserDoesNotExistsException {
		return userManagement.showAllUsers();
	}

	public Member showMemberById(int memberid) throws UserDoesNotExistsException {
		return userManagement.showMemberById(memberid);
	}

	public Librarian showLibrarianById(int Librarianid) throws UserDoesNotExistsException {
		return userManagement.showLibrarianById(Librarianid);
	}

	public double getPendingFine(int memberId) throws UserDoesNotExistsException {
		return userManagement.getPendingFine(memberId);
	}

	public String removeMemberbyId(int id) throws UserDoesNotExistsException {
		Member member = showMemberById(id);
		return userManagement.removeUserById(id);
	}

	public String removeLibrarianbyId(int id) throws UserDoesNotExistsException {
		Librarian librarian = showLibrarianById(id);
		return userManagement.removeUserById(id);
	}

	public String updateMemberNameById(int id, String newname)
			throws InvalidRequestException, UserDoesNotExistsException {
		Member member = userManagement.showMemberById(id);
		member.setName(newname);
		return "Name has been updated for id" + id + "NewNam:" + newname;
	}

	public String updateMemberEmailByid(int id, String newemail)
			throws InvalidRequestException, UserDoesNotExistsException {
		Member member = userManagement.showMemberById(id);
		member.setEmail(newemail);
		return "Name has been updated for id" + id + "NewNam:" + newemail;
	}

	public String updateMemberPasswordById(int id, String newpassword)
			throws InvalidRequestException, UserDoesNotExistsException {
		Member member = userManagement.showMemberById(id);
		member.setPassword(newpassword);
		return "Password has been updated for id" + id;
	}

	public String updateLibrarianPasswordById(int id, String newpassword)
			throws InvalidRequestException, UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setPassword(newpassword);
		return "Password has been updated for id" + id;
	}

	public String updateLibrarianNameById(int id, String newname)
			throws InvalidRequestException, UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setName(newname);
		return "Name has been updated for id" + id + "NewNam:" + newname;
	}

	public String updateLibrarianEmailByid(int id, String newemail)
			throws InvalidRequestException, UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setEmail(newemail);
		return "Name has been updated for id" + id + "NewNam:" + newemail;
	}

	public String updateLibrarianShiftByid(int id, Shift shift) throws UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setShift(shift);
		return "Shift has been changed for LibrarianId:" + id;
	}

	public String updateStatusIfOverdue(int transactionId) throws TransactionNotFound {
		Transaction t = transactionManagement.getTransactionById(transactionId);
		transactionManagement.updateStatusIfOverdue(t);
		return "Status has been updated";
	}

	public double calculateFine(int transactionId) throws TransactionNotFound {
		Transaction t = transactionManagement.getTransactionById(transactionId);
		return transactionManagement.calculateFine(t);
	}

}
