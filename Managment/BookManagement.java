package Managment;

import java.util.*;

import Entity.Book;
import Exception.InvalidRequestException;

public class BookManagement {
	private HashMap<Integer, Book> books;

	public BookManagement() {
		// System.out.println("BookManagment is working");
		books = new HashMap<>();
	}

	// CRUD
	// Addition of books
	public String addBook(int bookId, String bookName, String author, String description, int price, boolean available)
			throws InvalidRequestException {
		if (bookId != 0 && bookName != null && author != null && description != null && price > 0) {
			Book b = new Book(bookId, bookName, author, description, price, available);
			books.put(bookId, b);
			return "Book added successfully";
		}
		throw new InvalidRequestException("Please Enter Valid Data");
	}

	// Saari books show karna
	public ArrayList<Book> showAllBooks() throws InvalidRequestException {
		ArrayList<Book> b = new ArrayList<>(books.values());
		if (b.isEmpty()) {
			throw new InvalidRequestException("No books exist");
		}
		return b;
	}

	// Books by id show karna
	public Book showBookbyId(int id) throws InvalidRequestException {
		Book b = books.get(id);
		if (b == null) {
			throw new InvalidRequestException("Book with ID" + id + "does not exist in the system");
		}
		return b;
	}

	// Books remove karna saari
	public String removeBookbyId(int id) throws InvalidRequestException {
		Book b = books.get(id);
		if (b == null) {
			throw new InvalidRequestException("Book with ID" + id + "already does not exist in the system");
		}
		books.remove(id);
		return "Book deleted successfully";
	}

	// By name of book search karna
	public List<Book> searchBookByName(String name) throws InvalidRequestException {
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getBookName().toLowerCase().contains(name.toLowerCase())) {
				list.add(book);
			}
		}
		if (list.isEmpty()) {
			throw new InvalidRequestException("No books with name" + name + "exits");
		}
		return list;
	}

	// By name of Author change karna
	public List<Book> searchBookByAuthor(String name) throws InvalidRequestException {
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
				list.add(book);
			}
		}
		if (list.isEmpty()) {
			throw new InvalidRequestException("No books with name" + name + "exits");
		}
		return list;

	}
	// Updation ke liye alag method ki zarurt ni hai directly library class mai
	// karenge setters use kar ke
}
