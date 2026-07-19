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
import Exception.NewTransactionCannotBeCreatedException;
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

	public String addMember(int memberid, String name, String email, String password)
			throws InvalidRequestException {
		if (memberid != 0 && name != null && email != null && password != null) {
			Member member = new Member(memberid, name, LocalDate.now(), email, password);
			userManagement.addUser(member);
			return "New Member has been added with Id: " + memberid;
		}
		throw new InvalidRequestException("Check all the fields correctly");
	}

	public String addLibrarian(int userid, String name, String email, String password, Shift shift)
			throws InvalidRequestException {
		if (userid != 0 && name != null && email != null && password != null) {
			Librarian librarian = new Librarian(userid, name, LocalDate.now(), email, password, shift);
			userManagement.addUser(librarian);
			return "New Librarian has been added with Id: " + userid;
		}
		throw new InvalidRequestException("Check all the fields correctly");
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

	public Librarian showLibrarianById(int librarianid) throws UserDoesNotExistsException {
		return userManagement.showLibrarianById(librarianid);
	}

	public double getPendingFine(int memberId) throws UserDoesNotExistsException {
		return userManagement.getPendingFine(memberId);
	}

	public String removeMemberById(int id) throws UserDoesNotExistsException {
		Member member = showMemberById(id);
		return userManagement.removeUserById(id);
	}

	public String removeLibrarianById(int id) throws UserDoesNotExistsException {
		Librarian librarian = showLibrarianById(id);
		return userManagement.removeUserById(id);
	}

	public String updateMemberNameById(int id, String newname)
			throws UserDoesNotExistsException {
		Member member = userManagement.showMemberById(id);
		member.setName(newname);
		return "Name has been updated for id: " + id + " New Name: " + newname;
	}

	public String updateMemberEmailById(int id, String newemail)
			throws UserDoesNotExistsException {
		Member member = userManagement.showMemberById(id);
		member.setEmail(newemail);
		return "Email has been updated for id: " + id + " New Email: " + newemail;
	}

	public String updateMemberPasswordById(int id, String newpassword)
			throws UserDoesNotExistsException {
		Member member = userManagement.showMemberById(id);
		member.setPassword(newpassword);
		return "Password has been updated for id: " + id;
	}

	public String updateLibrarianNameById(int id, String newname)
			throws UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setName(newname);
		return "Name has been updated for id: " + id + " New Name: " + newname;
	}

	public String updateLibrarianEmailById(int id, String newemail)
			throws UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setEmail(newemail);
		return "Email has been updated for id: " + id + " New Email: " + newemail;
	}

	public String updateLibrarianPasswordById(int id, String newpassword)
			throws UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setPassword(newpassword);
		return "Password has been updated for id: " + id;
	}

	public String updateLibrarianShiftById(int id, Shift shift) throws UserDoesNotExistsException {
		Librarian librarian = userManagement.showLibrarianById(id);
		librarian.setShift(shift);
		return "Shift has been updated for Librarian Id: " + id;
	}

	public String issueBook(int memberId, int bookId, int librarianId)
			throws InvalidRequestException, UserDoesNotExistsException, NewTransactionCannotBeCreatedException {
		Member member = userManagement.showMemberById(memberId);
		member.setIssuedBooks(member.getIssuedBooks() + 1);
		return transactionManagement.newTransaction(memberId, bookId, librarianId);
	}

	public String returnBook(Transaction t)
			throws TransactionNotFound, UserDoesNotExistsException, InvalidRequestException {
		Member member = userManagement.showMemberById(t.getMemberId());
		if (member.getIssuedBooks() <= 0) {
			throw new InvalidRequestException("Member has no issued books to return");
		}
		member.setIssuedBooks(member.getIssuedBooks() - 1);
		return transactionManagement.completeReturn(t);
	}
}