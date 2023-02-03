package com.bank;

import java.util.Date;

public class Transaction {
    private int accNumber;
    private Date date;
    private char sign;
    private String message ="";
    private double amount;

    public Transaction(int accNumber, Date date, String message) {
        this.accNumber = accNumber;
        this.date = date;
        this.message = message;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public Transaction(){

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
