package Exception;

public class TransactionNotFound extends Exception {

	public TransactionNotFound() {
		super("This transaction doesn't exists");
	}

}
