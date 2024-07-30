package main;

import entities.client.email.Email;

public class Main {
    public static void main(String []args) {
        String a = "henrique_gustavo@gmail.com";
        System.out.println(Email.validate(a));
    }
}
