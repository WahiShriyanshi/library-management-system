import java.util.*;

public class BookManagement {
	private HashMap<Integer, Book> books;

	public BookManagement() {
		//System.out.println("BookManagment is working");
		books = new HashMap<>();
	}

	// CRUD
	// Addition of books
	public String addBook(int bookId, String bookName, String author, String description, int price,
			boolean available) {
		Book b = new Book(bookId, bookName, author, description, price, available);
		books.put(bookId, b);

		return "Book added successfully";
	}

	// Saari books show karna
	public ArrayList<Book> showAllBooks() {
		return new ArrayList<>(books.values());
	}

	// Books by id show karna
	public Book showBookbyId(int id) {
		return books.get(id);
	}

	// Books remove karna saari
	public String removeBookbyId(int id) {
		books.remove(id);
		return "Book deleted successfully";
	}

	// By name of book search karna
	public List<Book> searchBookByName(String name) {
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getBookName().toLowerCase().contains(name.toLowerCase())) {
				list.add(book);
			}
		}
		return list;
	}

	// By name of Author change karna
	public List<Book> searchBookByAuthor(String name) {
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
				list.add(book);
			}
		}
		return list;
	}
	// Updation ke liye alag method ki zarurt ni hai directly library class mai
	// karenge setters use kar ke
}
