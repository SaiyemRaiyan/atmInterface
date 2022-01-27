
package atminterface;

        import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.Serializable;
	import java.util.Calendar;
                    import java.util.InputMismatchException;
	import java.util.Scanner; 
                    

	public class ATM extends InfoShow implements Serializable
{
		
		private Scanner input;                           //Variable of Scanner Class
		private Customer[] customers;           //Variable of Customer class
		private String userID; 
		private Customer user;	  //Variable of Customer class
		private AccountInfo accountLog;	//Variable of AccountLog class
		private FileWriter fw;
		private ObjectOutputStream writeInfo;
		
                
		ATM() throws IOException, ClassNotFoundException
	{
			
			input = new Scanner(System.in);  
			customers = new Customer[1000];  //There are 1000 customers. 
			for(int i = 0; i < 1000; i++)
			{
				customers[i] = new Customer();
			}
			userID = new String();
			user = new Customer();
			
			CustomerInfo();	// Call loadCustomerInfo method
			accountLog = new AccountInfo();		
			writeInfo = new ObjectOutputStream (new FileOutputStream ("CustomerInfo.data")); // Data file created
			
	}
		
                
		private void CustomerInfo() throws IOException, ClassNotFoundException //For read customer information
		{
	   
			try 
			{
				ObjectInputStream readInfo = new ObjectInputStream(new FileInputStream("CustomerInfo.data")); //readInfo variable declared
				for(int i = 0; i < 1000; i++)
				{
					customers[i] = (Customer) readInfo.readObject();	// read Customer Information
				}
				readInfo.close();
		                    } 
                        
			catch (FileNotFoundException e)
			{
					
				System.out.println("No customer data file.");
					
			} 
			         catch (IOException | ClassNotFoundException eOFException)
		                   {
						
	                                        }
	                   }
		
		
		public void registerCustomer() throws ClassNotFoundException, IOException //Registration of Customer
                   { 
			Customer newCustomer = new Customer();	// Variable of Customer class
			System.out.print("Please enter your name : ");
			newCustomer.name = input.nextLine();	// Name input

			System.out.print("Please enter your phone number : ");
			newCustomer.telephone = input.nextLine(); // Phone Number input

			System.out.print("Please enter an address : ");
			newCustomer.address = input.nextLine();	// Address input

			System.out.print("Please enter your account number : ");
			newCustomer.accountNumber = input.nextLine();	// Account number input

		     	for(int i = 0; i < 1000; i++)
	                                       {
				  if(newCustomer.accountNumber.equals(customers[i].accountNumber))
			                   {
			    	   	System.out.println("Redundant account is the value.");	// 
				    	return;
			                    }
			}
			
		     	
		       	System.out.print("Please enter your PIN : ");
		    	newCustomer.PIN = input.nextLine(); // PIN input
			
		       	File file = new File("C:/Users/Abul Kalam Liton/Documents/NetBeansProjects/atmInterface/src/atminterface/Information.txt");
			try 
			{
				FileWriter write = new FileWriter(file,true);
				write.write("Name: " + newCustomer.name + "\nPhone Number: " + newCustomer.telephone +"\nAddress: " + 
						 newCustomer.address + "\nAccount Number: " + newCustomer.accountNumber + "\nPIN: " + newCustomer.PIN);
				write.flush();
				write.close();
			} 
                        
			   catch (IOException e) 
			{
				
				System.out.println("Withdrawal information cannot be recorded.\n\n");
			}
			
			try 
			{
				writeInfo.writeObject(newCustomer);	// write information in data file
				writeInfo.flush();
				//writeInfo.close();
	                                        } 
			        catch (FileNotFoundException e) 
			{
			       	System.out.println("No customer data file");
			} 
	                                            catch (IOException e)
			{
	        	
	                                        }
		       	           CustomerInfo();
			
	}
                
		
		public Boolean verifyCustomer() throws ClassNotFoundException, IOException // Certify the Customer
	{
			
			CustomerInfo();
			System.out.print("Please enter your account number: ");
			String customerAccount = input.nextLine();
			
			System.out.print("Please enter a PIN : ");
			String pin = input.nextLine();
			
			try 
			{
				
				    for(int i = 0; i < 1000; i++)
				{
					
					if(customers[i].accountNumber.equals(customerAccount))
					{	//Checking customer Account Number
						
						if(customers[i].PIN.equals(pin))
						{	//Checking customer pin
							
						          userID = customerAccount;
					                              user = customers[i];
						          System.out.println("\nCustomer authentication was successful.\n\n");
							return true;
						}
						   break;
					}
				}
	                        
				System.out.println("Customer authentication failed\n\n");
				return false;
			} 
			       catch (Exception nullpointerException) 
		  	        {
			     	return false;
			         }
                    }
                
		
	   	public void mainBalance() throws ClassNotFoundException, IOException // Balance Inquiry
                    {
			CustomerInfo();
			System.out.println("\nBalance is " + user.remain +" taka.\n"); // Checking Balance
	}
                
		
		public void deposit() throws ClassNotFoundException, IOException // Deposit Money
                   {
                    
			CustomerInfo();
                      
			System.out.print("Enter the amount to be deposited : ");
			int deposit = input.nextInt();// Amount of depositing money
                       
                           	
			if(deposit < 0) 
			{
				System.out.println("Please deposit right amount.");	//Checking for negative number
			}
			
		               else 
		         {
		              	user.remain += deposit;	//Addition to total amount
		            	System.out.println("Deposit has been completed.\n");
			
		                      	AccountInfo newAccountLog = new AccountInfo();
		                      	newAccountLog = getTime();	// time of depositing money
		                    	newAccountLog.type = "deposited";
		                     	newAccountLog.amount = deposit;
                                        
		                	File file = new File("C:/Users/Abul Kalam Liton/Documents/NetBeansProjects/atmInterface/src/atminterface/transaction.txt");
		                   	file.createNewFile(); // create new file
                         try{	 
				     FileWriter write = new FileWriter(file,true);
			       	write.write(Integer.toString(newAccountLog.day) + "/" + Integer.toString(newAccountLog.month+1) + "/" + Integer.toString(newAccountLog.year) +
			    	 " ------> " + newAccountLog.type + " " + Integer.toString(newAccountLog.amount) + " taka\n"); // write in the file
				
			         	write.flush();
			          	write.close();	//close writer
				
			} 
                    
			   catch (InputMismatchException e)  
			{
				System.out.println("Deposit can't be recorded\n\n");
			}
                                                 
	                            }     
                }
		
                
		public void withdraw() throws ClassNotFoundException, IOException // Withdraw Money
	{
			System.out.print("Enter the amount to withdraw : ");
			int withdraw = input.nextInt();	// Input withdrawn money
			
			if(withdraw < 0)     //Checking for negative input
			{ 
				System.out.println("Please withdraw right amount."); 
			}
			
			else 
			{
			            if(user.remain - withdraw > 0)
			         {
				user.remain -= withdraw;	//Subtraction of withdrawn money
				System.out.println("You have completed the withdrawal.\n");
				
				AccountInfo newAccountLog = new AccountInfo();
				newAccountLog = getTime();	// time of withdrawing money
				newAccountLog.type = "withdrawn";
				newAccountLog.amount = withdraw;
				
				File file = new File("C:/Users/Abul Kalam Liton/Documents/NetBeansProjects/atmInterface/src/atminterface/Transaction.txt");
				try 
				{
					
				    	FileWriter write = new FileWriter(file,true);
					write.write(Integer.toString(newAccountLog.day) + "/" + Integer.toString(newAccountLog.month+1) + "/" + Integer.toString(newAccountLog.year) +
							" ------> " + newAccountLog.type + " " + Integer.toString(newAccountLog.amount) + " taka\n");
									// Write the time of withdrawn money
					write.flush();
					write.close();
				} 
				  catch (IOException e) 
				{
					
					System.out.println("Withdrawal information cannot be recorded.\n\n");
				}
				
			         }
			            else
			         {	//If withdrawn more money than balance
				         System.out.println("Not enough balance.\n\n");
                                                                     }
		   }
	}
                
                
		
		public void transactionInfo()	// Read transaction information
	{
			String readLine = new String();
			File file = new File("C:/Users/Abul Kalam Liton/Documents/NetBeansProjects/atmInterface/src/atminterface/Transaction.txt");
			try 
			{
				Scanner read = new Scanner(file);
				do
				{
					readLine = read.nextLine(); //Read customer information from file
					System.out.println(readLine);
				}
				  while(read.hasNext());
				
			} 
			  catch (FileNotFoundException e)
			{
				
			}
			
	}
		
		
		@Override
		public void showAccountInfo()	// Showing customer information
	{
			System.out.println("\n" + "Your name is " + user.name );
			System.out.println("Your phone number is " + user.telephone );
			System.out.println("Your address is " + user.address );
			System.out.println("Your account number is " + user.accountNumber );
			System.out.println("Your PIN is " + user.PIN + "\n");
                    }
                
		
		public void exit()	// Exit 
                   {
			System.out.println("Thanks for using ATM!!!");
			System.exit(0);	// Exiting the program
 	}
		
                
	  	private AccountInfo getTime()	// Getting time
                   {
			Calendar cal = Calendar.getInstance();
			AccountInfo time = new AccountInfo();
			time.year = cal.get(Calendar.YEAR);	// Getting year
			time.month = cal.get(Calendar.MONTH);	// Getting month
			time.day = cal.get(Calendar.DAY_OF_MONTH);	// Getting day
			return time;	// Returning time
	}
			      
}
