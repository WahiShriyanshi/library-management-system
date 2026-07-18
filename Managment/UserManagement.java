package Managment;

import java.util.*;
import java.util.stream.Collectors;
import Entity.Librarian;
import Entity.Member;
import Entity.User;
import Exception.UserDoesNotExistsException;

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

	public String removeUserById(int id) throws UserDoesNotExistsException {
		User u = users.get(id);
		if (u != null) {
			users.remove(id); // fix - actually remove karo
			return "User has been deleted";
		}
		throw new UserDoesNotExistsException("No User exists");
	}

	public List<Member> showAllMembers() throws UserDoesNotExistsException {
		List<Member> list = users.values().stream()
				.filter(u -> u instanceof Member)
				.map(u -> (Member) u)
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new UserDoesNotExistsException("No Member exists");
		}
		return list;
	}

	public List<Librarian> showAllLibrarians() throws UserDoesNotExistsException {
		List<Librarian> list = users.values().stream()
				.filter(u -> u instanceof Librarian)
				.map(u -> (Librarian) u)
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new UserDoesNotExistsException("No librarian exists");
		}
		return list;
	}

	public List<User> showAllUsers() throws UserDoesNotExistsException {
		ArrayList<User> list = new ArrayList<>(users.values());
		if (list.isEmpty()) {
			throw new UserDoesNotExistsException("There are no users");
		}
		return list;
	}

	public Member showMemberById(int id) throws UserDoesNotExistsException {
		User u = users.get(id);
		if (u instanceof Member) {
			return (Member) u;
		}
		throw new UserDoesNotExistsException("Member does not exist, please check the Member Id");
	}

	public Librarian showLibrarianById(int id) throws UserDoesNotExistsException {
		User u = users.get(id);
		if (u instanceof Librarian) {
			return (Librarian) u;
		}
		throw new UserDoesNotExistsException("Librarian does not exist, please check the Id");
	}

	public User showUserById(int id) {
		return users.get(id);
	}

	public double getPendingFine(int memberId) throws UserDoesNotExistsException {
		Member member = showMemberById(memberId); // already exception handle ho rahi hai
		return member.getPendingFine();
	}
}