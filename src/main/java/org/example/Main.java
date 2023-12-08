package org.example;

import org.example.Repository.AccountRepository;

public class Main {
    public static void main(String[] args) {
        AccountRepository c = new AccountRepository();
        System.out.println(c.selectAllAccountsPagination());
        System.out.println("Hello world!");
    }
}