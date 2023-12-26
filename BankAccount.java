/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankaccount;

/**
 *
 * @author Administrator
 */
import java.util.*;

class Bank {
   
    private String accNum;
    private int mpin;
    private double balance;
    
    public Bank(String accNum,int mpin){
        if(!valid(accNum) && !validmpin(mpin)){
             System.out.println("Invalid Account Number or mpin, Please Try Again");
        }
          this.accNum =accNum;
        this.mpin = mpin;
        this.balance = 0.0;
        
    }
    

    public String getAccnum(){
        return accNum;
    }
    
    public double getBalance(){
        return balance;
    }
     public int getMpin(){
        return mpin;
    }
    

    public void setAccnum(String accNum){
        this.accNum = accNum;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    public void setMpin(int mpin){
        this.mpin = mpin;
    }
    
    public void deposit(double amount){
        if(amount >0){
            balance += amount;
            System.out.println("Deposited $ "+amount);
        }
        else{
            System.out.println("Invalid amount!");
        }
    }
    public void withdraw(double amount){
        if(amount <=balance && amount % 100 == 0){
            balance -= (amount +18);
            System.out.println("Withdrawal $"+amount);
        }
        else if(amount >balance) {
            System.out.println("Insufficient amount!");
        }
        else if(amount < 100 || amount % 100 != 0 ){
            System.out.println("Amount not valid, Input hundreds only");
        }

    }
    
     public void receipt(int ch,double amount){
      if(ch == 1){
           if (accNum != null) {
            // Convert only the 4-digit portion to asterisks
            String asteriskAccNum = convertToAsterisk(accNum, 4);
          System.out.println("\nRECEIPT: ");
          System.out.println("ACCOUNT NUMBER: "+asteriskAccNum);
          System.out.println("TRANSACTION: DEPOSIT");
          System.out.println("AMOUNT: "+amount);
          System.out.println("BALANCE "+balance);
           }
      }
      else if(ch == 2){
          if (accNum != null) {
            // Convert only the 4-digit portion to asterisks
            String asteriskAccNum = convertToAsterisk(accNum, 4);
          System.out.println("\nRECEIPT: ");
          System.out.println("ACCOUNT NUMBER: "+asteriskAccNum);
          System.out.println("TRANSACTION: WITHDRAW");
          System.out.println("AMOUNT: "+amount);
          System.out.println("BANK FEE: "+" $ 18.00");
          System.out.println("BALANCE "+balance);
      }
      }
    }
    
     private boolean valid(String accNum){
            return accNum.matches("\\d{9}");
        } 
      private boolean validmpin(int mpin){
            return String.valueOf(mpin).matches("\\d{4}");
        } 
      
    public static String convertToAsterisk(String accNum, int numDigits) {
        // Check if the account number has at least numDigits digits
        if (accNum.length() >= numDigits) {
            // Retrieve the substring containing the last numDigits digits
            String firstDigits = accNum.substring(0,numDigits);

            // Replace the lastDigits with asterisks
            String asterisks = "*".repeat(numDigits);
            accNum = accNum.replace(firstDigits, asterisks);
        }

        return accNum;
    }
}

public class BankAccount {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        HashMap<String, Bank> details = new HashMap<>();
        
        while(true){
        System.out.println("\nWelcome to our trusted bank");
        System.out.println("1.Create Account: ");
        System.out.println("2.Open existing account: ");
        System.out.println("0.Exit ");
        System.out.print("\nChoose from option: ");
        int opt = sc.nextInt();
    
        switch(opt){
            
            case 1: 
               
                System.out.print("Set your Account Number(9 digits): ");
                String accNum = sc.next();
                if(!accNum.matches("\\d{9}") ){
                    System.out.println("Enter 9 digits number");
                    continue; 
                }
                if(details.containsKey(accNum)){ 
                    System.out.println("Account already exist!,Please try again ");
                    continue; 
                
                }
                

                System.out.print("Set your MPIN (4 digits): ");
                int mpin = sc.nextInt();
                if(!String.valueOf(mpin).matches("\\d{4}") ){
                    System.out.println("Enter 4 digits number");
                    continue;
                }
                
                Bank newAcc = new Bank(accNum, mpin);
                details.put(accNum, newAcc);
                System.out.println("\nAccount Created! ");
        break;
            case 2:
                int attempt = 3;
                
         while(attempt>0){
            
        System.out.print("Enter your Account Number: ");
         accNum = sc.next();
          if(accNum.equals("0")){
           break;
        }
        System.out.print("Enter you mpin: ");
         mpin = sc.nextInt();
         
        Bank acc = details.get(accNum);
        
       if(!accNum.matches("\\d{9}")){
            System.out.println("\nInvalid AccountNumber. Please try again. Type 0 to exit");
                continue;
        }else if(!String.valueOf(mpin).matches("\\d{4}")){
                System.out.println("\nInvalid MPIN. Please try again. Type 0 to exit");
            }
       
        if (acc == null) {
                System.out.println("\nAccount not found. Please try again. Type 0 to exit");
                continue; // Restart the loop if account number not found
            }
         if ( acc.getMpin() != mpin) {
                 attempt--;
                System.out.println("\nInvalid Mpin. Please try again.("+attempt+" attempts) remaining. Type 0 to exit");
                // Restart the loop if account number not found
               
            continue;
            }
         if(attempt <=1 && acc.getMpin() != mpin){
            attempt --;
             System.out.println("\nMaximum attempts reached! ");
            continue;
         }
          


       boolean valids = false;
        while(!valids){
        System.out.println("\nWelcome to Our Online Banking");
        System.out.println("1.Deposit ");
        System.out.println("2.Withdraw ");
        System.out.println("3.Show Balance");
        System.out.println("4.Cancel Transaction");
        System.out.println("0.Exit");
        int ch = sc.nextInt();
        
        switch(ch){
            
            case 1:
                System.out.println("Enter amount to deposit: ");
                double amount = sc.nextDouble();
                System.out.println(amount+" will deposited in "+accNum+" Press 1 to confirm Press any num to cancel");
                int con = sc.nextInt();
                if(con == 1){
                acc.deposit(amount);
                } else{
                    System.out.println("Deposit Cancelled ");
                    break;
                }
                acc.receipt(ch,amount);
                break;
                
            case 2:
                System.out.println("Enter amount to withdraw not less than 100: ");
                amount = sc.nextDouble();
                System.out.println(amount+" will deduct in "+accNum+" Press 1 to confirm Press any num to cancel");
                 con = sc.nextInt();
                 
                  if(con == 1){
                acc.withdraw(amount);
                } else{
                    System.out.println("Withdrawal Cancelled ");
                    break;
                }
                acc.receipt(ch,amount);
                break;

            case 3:
                System.out.println("Balance: "+acc.getBalance());
                
                break;
            case 4:
                System.out.println("Transaction Cancelled");
                valids = true;
                break;
            case 0:
                System.out.println("Thank you for trusting our bank! ");
                sc.close();
                System.exit(0);
                break;
                
            default:
                System.out.println("Invalid Input!");
                break;
        } 
        } break;
        
       
       } break;
          case 0: 
        System.out.println("Thank you for using our bank!");
        sc.close();
        System.exit(0);
        break;
        
        default:
        
        System.out.println("Invalid Input! Please try again");
        break;
    }
        }
    }
    
}
