
package atminterface;
public class Option
{
	
	public void showOption()
	{
		
	    System.out.println("______________________________________________________________________________________________________\n");
	    System.out.println("     <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Welcome to ATM Program >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    System.out.println("______________________________________________________________________________________________________\n");
		System.out.println("Choose the job you want:");
		System.out.println("1. Customer registration");
		System.out.println("2. Customer authentication");
		System.out.println("3. Balance inquiry");
		System.out.println("4. Deposit");
		System.out.println("5. Withdraw");
		System.out.println("6. Transaction history inquiry");
		System.out.println("7. Customer information inquiry");
		System.out.println("8. Log Out\n");
		System.out.print("Select: ");
		
		
	}
	
	public void errorMessage()
	{
		System.out.println("Wrong input. \n Please try Again!");
	}
}