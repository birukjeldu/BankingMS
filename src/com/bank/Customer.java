package com.bank;

import java.util.Date;
import java.util.Scanner;

public class Customer {
    //static ArrayList listOfCustomer = Manager.listOfCustomer;
    private String name;
    private int accountNumber;
    private String passowrd;
    private double balance = 0;
    private String phoneNumber;
     static int logIndex = -1;  //To track which customer is logged in

    public Customer(String name, int accountNumber, String passowrd, double balance, String phoneNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.passowrd = passowrd;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //The Customers login page
    public static void customerLoginPage(){
        Scanner in = new Scanner(System.in);
        String numPatt = "^[0-9]+$";
        System.out.println("--------------- Customer Log In ---------------");
        System.out.print("\t Account Number: ");
        String accNumber = in.nextLine();
        while (!accNumber.matches(numPatt)) {
            System.out.println("\t!! only integer allowed !!");
            System.out.print("\t Account Number: ");
            accNumber = in.nextLine();
        }
        System.out.print("\t Password: ");
        String passowrd = in.next();

        if(!Manager.listOfCustomer.isEmpty()){
            if(isUserFound(accNumber) != -1){
                int index = isUserFound(accNumber);
                if(passowrd.equals(Manager.listOfCustomer.get(index).getPassowrd())){
                    System.out.println("Login is Successful");
                    logIndex = index;
                    UserInterface.customerPage();
                }else{
                    System.out.println("!!! Incorrect Password !!!");
                    logIndex = -1;
                    customerLoginPage();
                }
            }else{
                System.out.println("!!! NO Account found with that Account Number !!!");
                logIndex = -1;
                customerLoginPage();
            }

        }else{
            System.out.println("!!! You Dont have any Customer. Please add some customer !!!");
            UserInterface.loginPage();
        }
    }

    //To search if the username exists
    public static int isUserFound(String str) {
        int index =-1;
        for (int i = 0; i < Manager.listOfCustomer.size(); ) {
            if (str.equals(String.valueOf(Manager.listOfCustomer.get(i).getAccountNumber()))) {
                index = i;
                break;
            } else {
                i++;
            }
        }
        return index;
    }

    //To display all the customers account info
    public static void displayCustomerInfo(){
        if (logIndex != -1){
            System.out.println("--------------- Information About Your Account ---------------");
            System.out.println("\t Name: "+Manager.listOfCustomer.get(logIndex).getName());
            System.out.println("\t Account Number: "+Manager.listOfCustomer.get(logIndex).getAccountNumber());
            System.out.println("\t Password: "+Manager.listOfCustomer.get(logIndex).getPassowrd());
            System.out.println("\t Total Balance: "+Manager.listOfCustomer.get(logIndex).getBalance()+" Birr");
            System.out.println("\t Phone Number: "+Manager.listOfCustomer.get(logIndex).getPhoneNumber());
            Scanner in = new Scanner(System.in);
            System.out.print("Write anything and Press Enter to Continue ....");
            in.next();
            UserInterface.customerPage();
        }
    }
    //To withdraw cash
    public static void withdrawCash(){
        Scanner in = new Scanner(System.in);
        String numPatt = "^[0-9]+$";
        if(logIndex != -1){
            System.out.println("--------------- Withdraw ---------------");
            System.out.print("\t Enter Amount to Withdraw: ");
            String cashAmount = in.next();
            //To evaluate the input to be Double only
            while (!cashAmount.matches(numPatt)) {
                System.out.println("\t!! only Number allowed !!");
                System.out.print("\t Enter Amount to Withdraw: ");
                cashAmount = in.nextLine();
            }
            //converting the user input to double
            Double amount = Double.parseDouble(cashAmount);
            if(Manager.listOfCustomer.get(logIndex).getBalance() >= amount){ //checking if the user has enough money to withdraw
                //To store the transaction
                Transaction tran = new Transaction();
                Date date = new Date();
                tran.setDate(date);
                tran.setSign('-');
                tran.setAccNumber(Manager.listOfCustomer.get(logIndex).getAccountNumber());
                double bal = Manager.listOfCustomer.get(logIndex).getBalance() - amount;  //Subtracting the money from the account
                Manager.listOfCustomer.get(logIndex).setBalance(bal);
                tran.setAmount(amount);
                Manager.transactionList.add(tran);
                System.out.println("You have Successfully Withdrew "+amount+" Birr from your account");
                UserInterface.customerPage();

            } else if (Manager.listOfCustomer.get(logIndex).getBalance() == 0) {
                System.out.println("!!! You Account Balance is 0 Birr. Please deposit some money !!!");
                UserInterface.customerPage();
            } else{
                System.out.println("!!! You don't have enough money to withdraw. Your balance is "+ Manager.listOfCustomer.get(logIndex).getBalance() +" !!!");
                withdrawCash();
            }
        }
    }

    //To check the balance in the account
    public static void checkBalance(){
        if (logIndex != -1){
            System.out.println("--------------- Balance ---------------");
            System.out.println("\t Your Balance is "+ Manager.listOfCustomer.get(logIndex).getBalance() +" Birr");
            Scanner in = new Scanner(System.in);
            System.out.print("Write anything and Press Enter to Continue ....");
            in.next();
            UserInterface.customerPage();
        }
    }

    //Log out
    public static void logOut(){
        logIndex = -1;
        UserInterface.loginPage();
    }

    //To See the recent transaction the user made
    public static void transactionList() {
        System.out.println("--------------- List of Recent Transactions ---------------");
        int account = Manager.listOfCustomer.get(logIndex).getAccountNumber();
        for (int i = 0; i < Manager.transactionList.size(); i++) {
            if (account == Manager.transactionList.get(i).getAccNumber()){
                System.out.println("\t "+Manager.transactionList.get(i).getSign()+Manager.transactionList.get(i).getAmount()+" on "+Manager.transactionList.get(i).getDate()+" "+Manager.transactionList.get(i).getMessage());
            }
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Write anything and Press Enter to Continue ....");
        in.next();
        UserInterface.customerPage();
    }

    //Transfer money to another account
    public static void transferHandler(){
        String patt = "^[a-zA-Z ]+$";
        String numPatt = "^[0-9]+$";
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Transfer to another User ---------------");
        System.out.print("\t Enter Account Number: ");
        String accNum = in.next();
        //To evaluate the input to be integer only
        while (!accNum.matches(numPatt)) {
            System.out.println("\t!! only integer allowed !!");
            System.out.print("\t Enter Account Number: ");
            accNum = in.nextLine();
        }
        //To check if the account number exist
        if (Manager.listOfCustomer != null) {
            int flag = Manager.isAccountFound(accNum);
            while( flag == -1){
                System.out.println("\t !! Account doesn't exist !!");
                System.out.print("\t Enter Account Number: ");
                accNum = in.next();
                //To evaluate the input to be integer only
                while (!accNum.matches(numPatt)) {
                    System.out.println("\t!! only integer allowed !!");
                    System.out.print("\t Enter Account Number: ");
                    accNum = in.nextLine();
                }
                flag = Manager.isAccountFound(accNum);
            }
            if(Manager.isAccountFound(accNum) != -1){
                int i = Manager.isAccountFound(accNum);
                System.out.println("\t Account found. Account owner is "+Manager.listOfCustomer.get(i).getName());
                System.out.print("\t Enter Amount of Cash to Transfer: ");
                String cashAmount = in.next();
                //To evaluate the input to be integer only
                while (!cashAmount.matches(numPatt)) {
                    System.out.println("\t!! only Number allowed !!");
                    System.out.print("\t Enter Amount of Cash: ");
                    cashAmount = in.nextLine();
                }

                int index = Manager.isAccountFound(accNum);
                //converting the user input to double
                Double amount = Double.parseDouble(cashAmount);
                if(Manager.listOfCustomer.get(logIndex).getBalance() >= amount){ //checking if the user has enough money to transfer
                    //To store the transaction
                    Transaction tran = new Transaction();
                    Transaction tranRe = new Transaction();
                    Date date = new Date();
                    tran.setDate(date);
                    tran.setSign('-');
                    tran.setAccNumber(Manager.listOfCustomer.get(logIndex).getAccountNumber());
                    tran.setAmount(amount);
                    tran.setMessage("Sent to "+Manager.listOfCustomer.get(index).getName());
                    //recievers transaction
                    tranRe.setDate(date);
                    tranRe.setSign('+');
                    tranRe.setAmount(amount);
                    tranRe.setAccNumber(Manager.listOfCustomer.get(index).getAccountNumber());
                    tranRe.setMessage("Received From "+Manager.listOfCustomer.get(logIndex).getName());

                    double bal = Manager.listOfCustomer.get(logIndex).getBalance() - amount;  //Subtracting the money from the account
                    Manager.listOfCustomer.get(logIndex).setBalance(bal);
                    double balAcceptor = Manager.listOfCustomer.get(index).getBalance();
                    balAcceptor += amount;
                    Manager.listOfCustomer.get(index).setBalance(balAcceptor);  //Adding the money to the receivers account
                    System.out.println("You have Successfully Transferred "+amount+" Birr from your account to "+Manager.listOfCustomer.get(index).getName());
                    Manager.transactionList.add(tran);
                    Manager.transactionList.add(tranRe);
                    UserInterface.customerPage();

                } else if (Manager.listOfCustomer.get(logIndex).getBalance() == 0) {
                    System.out.println("!!! You Account Balance is 0 Birr. Please deposit some money !!!");
                    UserInterface.customerPage();
                } else{
                    System.out.println("!!! You don't have enough money to Transfer. Your balance is "+ Manager.listOfCustomer.get(logIndex).getBalance() +" !!!");
                    transferHandler();
                }
            }

        }
    }
}
