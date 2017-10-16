package is2103practicallab05client;

import ejb.session.stateless.CheckProcessingSessionBeanRemote;
import java.math.BigDecimal;
import java.util.Scanner;
import util.exception.AccountNotFoundException;
import util.exception.EncashCheckException;
import util.exception.InsufficientBalanceException;



public class MainApp
{
    private CheckProcessingSessionBeanRemote checkProcessingSessionBeanRemote;
    
    
    
    public MainApp()
    {
    }

    
    
    public MainApp(CheckProcessingSessionBeanRemote checkProcessingSessionBeanRemote) 
    {
        this();
        
        this.checkProcessingSessionBeanRemote = checkProcessingSessionBeanRemote;
    }
    
    
    
    public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response;
        
        while(true)
        {
            System.out.println("*** Welcome to IS2103 Practical Lab 05 ***\n");
            System.out.println("1: Demo Transactions - Required");
            System.out.println("2: Demo Transactions - Requires New");
            System.out.println("3: Exit\n");
            response = 0;
            
            while(response < 1 || response > 3)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    demo1();
                }
                else if (response == 2)
                {
                    demo2();
                }
                else if (response == 3)
                {
                    break;
                }
                else
                {
                    System.out.print("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 3)
            {
                break;
            }
        }
    }



    private void demo1()
    {
        try
        {
            System.out.println("*** IS2103 Practical Lab 05 :: 1 - Demo Transactions - Required ***\n");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Checking Account Id> ");
            Long checkingAccountId = scanner.nextLong();
            System.out.print("Enter Savings Account Id> ");
            Long savingsAccountId = scanner.nextLong();
            System.out.print("Enter Check amount> ");
            BigDecimal amount = scanner.nextBigDecimal();

            checkProcessingSessionBeanRemote.encashCheck(checkingAccountId, savingsAccountId, amount);
            
            System.out.println("Check encashed successfully!");
        }
        catch(EncashCheckException ex)
        {
            System.out.println("An error has occurred while encashing your check: " + ex.getMessage() + "!");
        }
    }
    
    
    
    private void demo2()
    {
        try
        {
            System.out.println("*** IS2103 Practical Lab 05 :: 2 - Demo Transactions - Requires New ***\n");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Checking Account Id> ");
            Long checkingAccountId = scanner.nextLong();
            System.out.print("Enter Savings Account Id> ");
            Long savingsAccountId = scanner.nextLong();
            System.out.print("Enter Check amount> ");
            BigDecimal amount = scanner.nextBigDecimal();

            checkProcessingSessionBeanRemote.encashCheckRequiresNewTransaction(checkingAccountId, savingsAccountId, amount);
            
            System.out.println("Check encashed successfully!");
        }
        catch(EncashCheckException ex)
        {
            System.out.println("An error has occurred while encashing your check: " + ex.getMessage() + "!");
        }
    }
}
