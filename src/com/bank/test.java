package com.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
        String[] list = {"123","456","789"};
        Scanner in = new Scanner(System.in);
        System.out.println("enter n: ");
        String x = in.next();
        for (int i = 0; i < list.length; ) {
            boolean flag = true;
            if(x.equals(list[i])){
                i=0;
                System.out.println("it exists");
                System.out.println("enter n: ");
                x = in.next();
                flag=false;
            }else {
                i++;
            }
            if(flag){
                System.out.println("Succwss");
            }
        }

    }
}
