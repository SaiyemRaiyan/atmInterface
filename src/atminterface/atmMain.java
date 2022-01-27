
package atminterface;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class atmMain 
{
           public static void main(String[] args) throws IOException, ClassNotFoundException
         {
                        
		Option opt = new Option();	
		int userSelection;
		
		Scanner input = new Scanner(System.in);
		Boolean verify = false;
		ATM atm = new ATM();	
		
		System.out.println("Disclaimer: Please Registration if you are not registrated.");
		
                                        boolean b = true;
                
                   do
                        {                 
	             try
                              {
			opt.showOption();	// Showing menu
			userSelection = input.nextInt();
			
                                             switch (userSelection) 
                                       {
                                                 
                                                    case 1:
                                                                      atm.registerCustomer();	// Registration of customer
                                                                      System.out.println("Your registration process has been completed!");
                                                                      break;
                            
                                                   case 2:
                                                                                 verify = atm.verifyCustomer();	// customer 
                                                                           if(verify == false)
                                                                      {
                                                                               System.out.println("Please verify customer first.");
                                                                      }
                                                                                  break;
                            
                                                             // No Double value is allowed for deposit cause ATM machine only take integer value. So no double can be deposited
                                                            // Also string is not allowed for ATM machine
                                                   case 3:
                                                                             if(verify == false)
                                                                     {
                                                                            System.out.println("Please verify customer first.");
                                                                     }
                                                                         else
                                                                     {
                                                                          atm.mainBalance();	// Balance Inquiry
                                                                     }
                                                                           break;
                            
                                                             // No Double value is allowed for withdraw cause ATM machine only take integer value. So no double can be withdrawn
                                                           // Also string is not allowed for ATM machine
                                                  case 4:
                                                                      
                                                                try
                                                            {
                                                                            if(verify == false)
                                                                      {
                                                                        System.out.println("Please verify customer first.");
                                                                      }
                                                                            else
                                                                      {
                                    
                                                                               atm.deposit();	// Depositing Money
                                    
                                                                     }
                                                            }
                                                                
                                                                   catch(InputMismatchException e){
                                                                       
                                                                       System.out.println("Exception");
                                                                    
                                                                   }
                                                         
                                                                           break;
                                                                                  
                            
                                                  case 5:
                                                      
                                                                   try
                                                            {
                                                                            if(verify == false)
                                                                      {
                                                                        System.out.println("Please verify customer first.");
                                                                      }
                                                                            else
                                                                      {
                                    
                                                                               atm.withdraw();	// withdraw Money
                                    
                                                                     }
                                                            }
                                                                           catch(InputMismatchException e)
                                                           {
                                                                       System.out.println("Sorry! Wrong Input.");
                                                                   
                                                            }
                                                                   
                                                                           break; 
                            
                                                   case 6:
                                                                            if(verify == false)
                                                                     {
                                                                               System.out.println("Please verify customer first.");
                                                                     }
                                                                           else
                                                                     {
                                                                                    atm.transactionInfo(); 	// Showing transaction information
                                                                      }
                                                                                   break;
                            
                                                   case 7:
                                                                          if(verify == false)
                                                                     {
                                                                                       System.out.println("Please verify customer first.");
                                                                     }
                                                                             else
                                                                     {
                                                                                  atm.showAccountInfo();	// Showing customer information
                                                                     }
                                                                                break;
                            
                                                  case 8:
                                                                           atm.exit();	// Exit Program
                                                                            break;
                            
                                                  default:
                                                                    opt.errorMessage();
                                                                      break;
                                       
			
                                       }
                              }
        
                                             catch(InputMismatchException e)
                                       {
                                           System.out.println("Exception: "+e);
                                               System.out.println("Wrong input....\nPlease Try again");
                                                  input.next();
                        
                                        }
                               
                   }
                          while(b = true);
                         input.close();
                         
                     
      
          }
}


