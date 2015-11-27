package example;

/**
 * This is a simple demonstration class simulating an ATM
 */
public class ATM {

	private int balance = 1000;
	
	private boolean authenticated = false;
	
	/**
	 * The pin code is hard coded and not dependent on an account
	 * 
	 * @param pin
	 */
	public void insertCard(String pin) {
		if(pin.equals("1234")) {
			authenticated = true;
		}
	}
	
	/**
	 * Eject card from ATM
	 */
	public void ejectCard() {
		authenticated = false;
	}
	
	/**
	 * Withdraw the given amount of money from the account
	 * 
	 * @param amount
	 */
	public void withdraw(int amount) {
		checkAuthenticated();

		if(amount > balance) {
			// No overdraft, we just cancel the transaction
			authenticated = false;
			return;
		}
		// Bug: This should happen:
		// balance -= amount;

		balance -= 10;
	}
	
	/**
	 * Retrieve the balance, but only if we are authenticated.
	 * 
	 * For simplicity, there is only one balance value.
	 * 
	 * @return
	 */
	public int getBalance() {
		checkAuthenticated();
		return balance;
	}
	
	/**
	 * Check if we are authenticated
	 * 
	 * @return
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}
	
	/**
	 * Check if we are already authenticated
	 */
	private void checkAuthenticated() {
		if(!authenticated) 
			throw new RuntimeException("Not authenticated!");
	}
}
