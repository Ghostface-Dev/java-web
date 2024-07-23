package ghostface.dev.main;

import ghostface.dev.core.Account;
import ghostface.dev.entities.Bank;
import ghostface.dev.entities.Customer;
import ghostface.dev.entities.SavingsAccount;
import ghostface.dev.entities.TransactionAccount;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("1", "henrique", "sousa", "@gmail", "123");
        Account account = new TransactionAccount("1", customer);
        Account account1 = new SavingsAccount("2", customer);


    }

}
