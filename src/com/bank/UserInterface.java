package com.bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    //First page
    public static void loginPage(){
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Welcome to My Bank System ---------------");
        System.out.println("\t A) Login as a Manager");
        System.out.println("\t B) Login as a Customer");
        try{
            String option;
            System.out.print("\t choose: ");
            option = in.next();
            option = option.toUpperCase();
            switch(option){
                case "A":
                    Manager.managerLogin();
                    break;
                case "B":
                    Customer.customerLoginPage();
                    break;
                default:
                    System.out.println("\tInvalid Option");
                    loginPage();
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
        }

    }

// Main page of manager after login
    public static void managerPage(){
        Scanner in = new Scanner(System.in);
        System.out.println("--------------- Welcome Back Manager ---------------");
        System.out.println("\t A) Create Customer Account");
        System.out.println("\t B) Deposit");
        System.out.println("\t C) Delete User Account");
        System.out.println("\t D) Log Out");
        try{
            String option;
            System.out.print("\t choose: ");
            option = in.next();
            option = option.toUpperCase();
            switch(option){
                case "A":
                    Manager.addCustomer();
                    break;
                case "B":
                    Manager.depositCash();
                    break;
                case "C":
                    Manager.deleteAccount();
                    break;
                case "D":
                    Manager.logOut();
                    break;
                default:
                    System.out.println("\tInvalid Option");
                    managerPage();
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
        }
    }

    //Main page of Customer after login
    public static void customerPage(){
        int userIndex =  Customer.logIndex;
        Scanner in = new Scanner(System.in);
        if (userIndex != -1){
            System.out.println("--------------- Welcome Back "+Manager.listOfCustomer.get(userIndex).getName() +" ---------------");
        }else {
            System.out.println("--------------- Welcome Back Customer ---------------");
        }

        System.out.println("\t A) Display Account Information");
        System.out.println("\t B) Withdraw Cash");
        System.out.println("\t C) Check Balance");
        System.out.println("\t D) Transfer to other Account");
        System.out.println("\t E) See Transaction");
        System.out.println("\t F) Log Out");
        try{
            String option;
            System.out.print("\t choose: ");
            option = in.next();
            option = option.toUpperCase();
            switch(option){
                case "A":
                    Customer.displayCustomerInfo();
                    break;
                case "B":
                    Customer.withdrawCash();
                    break;
                case "C":
                    Customer.checkBalance();
                    break;
                case "D":
                    Customer.transferHandler();
                    break;
                case "E":
                    Customer.transactionList();
                    break;
                case "F":
                    Customer.logOut();
                    break;
                default:
                    System.out.println("\tInvalid Option");
                    customerPage();
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Option");
        }
    }

}