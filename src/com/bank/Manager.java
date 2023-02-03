package com.bank;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Manager {
    private String name;
    private String password;
    static ArrayList<Customer> listOfCustomer = new ArrayList<>();
    static ArrayList<Transaction> transactionList = new ArrayList<>();

    public Manager() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    static Manager manager = new Manager();




//To add new user account
    public static void addCustomer(){
        Customer cust = new Customer();
        String patt = "^[a-zA-Z ]+$";
        String numPatt = "^[0-9]+$";
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Add Customer ---------------");
        System.out.print("\t Enter Customer Name: ");
        String custName = in.nextLine();
        //Evaluating the input to only accept Strings only
        while (!custName.matches(patt)) {
            System.out.println("\t!! only string allowed !!");
            System.out.print("\t Enter Customer Name: ");
            custName = in.nextLine();
        }
        cust.setName(custName);
        //Evaluating the input to only accept Strings
        System.out.print("\t Enter Account Number: ");
        String accNumber = in.next();
        while (!accNumber.matches(numPatt)) {
            System.out.println("\t!! only integer allowed !!");
            System.out.print("\t Enter Account Number: ");
            accNumber = in.nextLine();
        }
        //To check if the account number is taken
        if (listOfCustomer != null) {
            int i =0;
                for (; i < listOfCustomer.size(); ) {
                    if (accNumber.equals(String.valueOf(listOfCustomer.get(i).getAccountNumber()))) {
                        System.out.println("\t!! Account Number is taken. Enter another !!");
                        System.out.print("\t Enter Account Number: ");
                        accNumber = in.next();
                        i=0;
                    }else {
                        i++;
                    }

                }
        }
        int finalAccNum = Integer.parseInt(accNumber);
        cust.setAccountNumber(finalAccNum);

        System.out.print("\t Enter Password: ");
        String pass = in.next();
        cust.setPassowrd(pass);

        System.out.print("\t Enter Phone Number: ");
        String phone = in.next();
        //To evaluate the input to be integer only
        while (!phone.matches(numPatt)) {
            System.out.println("\t!! only integer allowed !!");
            System.out.print("\t Enter Phone Number: ");
            phone = in.nextLine();
        }
        cust.setPhoneNumber(phone);
        listOfCustomer.add(cust);
        for (int i = 0; i < listOfCustomer.size(); i++) {
            System.out.println(listOfCustomer.get(i).getAccountNumber());
        }
        UserInterface.managerPage();
      /*  String url = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String passowrd = "12345";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,passowrd);
            Statement statement =  connection.createStatement();
            int id=8;
            int age = 55;
            String name = "ronaldo";
            String sql = "INSERT INTO `student` (`id`, `name`, `age`) VALUES ('"+ id +"', '"+name+"', '"+age+"');";
            statement.execute(sql);
            System.out.println("completted");
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }*/


    }

    public static void depositCash(){
        String patt = "^[a-zA-Z ]+$";
        String numPatt = "^[0-9]+$";
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Deposit Cash ---------------");
        System.out.print("\t Enter Account Number: ");
        String accNum = in.next();
        //To evaluate the input to be integer only
        while (!accNum.matches(numPatt)) {
            System.out.println("\t!! only integer allowed !!");
            System.out.print("\t Enter Account Number: ");
            accNum = in.nextLine();
        }
        //To check if the account number is available
        if (listOfCustomer != null) {
            int flag = isAccountFound(accNum);
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
                flag = isAccountFound(accNum);
            }
            if(isAccountFound(accNum) != -1){
                System.out.print("\t Enter Amount of Cash: ");
                String cashAmount = in.next();
                //To evaluate the input to be integer only
                while (!cashAmount.matches(numPatt)) {
                    System.out.println("\t!! only Number allowed !!");
                    System.out.print("\t Enter Amount of Cash: ");
                    cashAmount = in.nextLine();
                }
                //converting the user input to double
                Double finalCashAmount = Double.parseDouble(cashAmount);
                Transaction tran = new Transaction();
                Date date = new Date();
                tran.setAmount(finalCashAmount);
                tran.setSign('+');
                tran.setDate(date);
                tran.setAccNumber(Integer.parseInt(accNum));
                tran.setMessage("Deposit");
                int index = isAccountFound(accNum);
                double oldBalance = listOfCustomer.get(index).getBalance();
                double newBalance = oldBalance + finalCashAmount;
                //Updating the balance of the user
                listOfCustomer.get(index).setBalance(newBalance);
                transactionList.add(tran);
                System.out.println("Balance updated successfully. New balance = "+listOfCustomer.get(index).getBalance()+" Birr");
            }

        }
        UserInterface.managerPage();

    }

    //To search if the account number exists
    public static int isAccountFound(String str) {
        int index =-1;
        for (int i = 0; i < listOfCustomer.size(); ) {
            if (str.equals(String.valueOf(listOfCustomer.get(i).getAccountNumber()))) {
                index = i;
                break;
            } else {
                i++;
            }
        }
        return index;
    }

    //To delete user account
    public static void deleteAccount(){
        String numPatt = "^[0-9]+$";
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Delete Account ---------------");
        System.out.print("\t Enter Account Number: ");
        String accNum = in.next();
        //To evaluate the input to be integer only
        while (!accNum.matches(numPatt)) {
            System.out.println("\t!! only integer allowed !!");
            System.out.print("\t Enter Account Number: ");
            accNum = in.nextLine();
        }

        if (listOfCustomer != null) {
            int flag = isAccountFound(accNum);
            while( flag== -1){
                System.out.println("\t !! Account doesn't exist !!");
                System.out.print("\t Enter Account Number: ");
                accNum = in.next();
                //To evaluate the input to be integer only
                while (!accNum.matches(numPatt)) {
                    System.out.println("\t!! only integer allowed !!");
                    System.out.print("\t Enter Account Number: ");
                    accNum = in.nextLine();
                }
                flag = isAccountFound(accNum);
            }
            if(isAccountFound(accNum) != -1){

                int index = isAccountFound(accNum);

                //Deleting the user account
                listOfCustomer.remove(index);
                System.out.println("Account has been Deleted successfully");
            }

        }
        UserInterface.managerPage();
    }

    public static void logOut(){
        UserInterface.loginPage();
    }

    public static void managerLogin(){
        manager.setName("admin");
        manager.setPassword("123");
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Manager Log In ---------------");
        System.out.print("\t Manager Name: ");
        String userName = in.next();
        System.out.print("\t Password: ");
        String passowrd = in.next();
        if (userName.equals(manager.getName()) && passowrd.equals(manager.getPassword()) ){
            UserInterface.managerPage();
        }else{
            System.out.println("\t !!! Incorrect Username and Password !!! ");
            managerLogin();
        }
    }

}
