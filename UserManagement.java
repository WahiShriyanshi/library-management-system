import java.util.*;
import java.util.stream.Collectors;

public class UserManagement {
	private HashMap<Integer, User> users;

	public UserManagement() {
		users = new HashMap<>();
	}

	// CRUD
	public String addUser(User u) {
		users.put(u.getId(), u);
		return "User has been added";
	}

	public String removeUserById(int id) {
		users.remove(id);
		return "User has been deleted";
	}

	public List<Member> showAllMembers() {
		List<Member> list = users.values().stream()
				.filter(u -> u instanceof Member)
				.map(u -> (Member) u)
				.collect(Collectors.toList());
		return list;
	}

	public List<Librarian> showAllLibrarians() {
		List<Librarian> list = users.values().stream()
				.filter(u -> u instanceof Librarian)
				.map(u -> (Librarian) u)
				.collect(Collectors.toList());
		return list;
	}

	public List<User> showAllUsers() {
		ArrayList<User> list = new ArrayList<>();
		users.values().stream()
				.forEach(u -> list.add(u));
		return list;
	}

	public Member showMemberById(int id) {
		// Member m = users.values().stream()
		// .filter(u -> u.getId() == id && u instanceof Member)
		// .map(u -> (Member) u)
		// .findFirst()
		// .orElse(null);
		// return m;
		User u = users.get(id);
		if (u instanceof Member) {
			return (Member) u;
		}
		return null;
	}

	public Librarian showLibrarianById(int id) {
		// Librarian l = users.values().stream()
		// .filter(u -> u.getId() == id && u instanceof Librarian)
		// .map(u -> (Librarian) u)
		// .findFirst()
		// .orElse(null);
		// return l;
		User u = users.get(id);
		if (u instanceof Librarian) {
			return (Librarian) u;
		}
		return null;
	}

	public User showUserById(int id) {
		return users.get(id);
	}
}