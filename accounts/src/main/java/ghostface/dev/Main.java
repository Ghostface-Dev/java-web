package ghostface.dev;


import ghostface.dev.core.Account;
import ghostface.dev.client.Customer;
import ghostface.dev.movement.Deposit;
import ghostface.dev.system.DatabaseImpl;

public class Main {

    public static void main(String[] args) {
        DatabaseImpl bancoSantander = new DatabaseImpl();

        Customer clientJarlan = new Customer(1,"jarlan","clovis", "@gmail.com", "555");
        Customer clientRobson = new Customer(2,"robson","airton", "@gmail.com", "555");

        bancoSantander.recordCustomer(clientJarlan, "jarlan clovis", "555");
        bancoSantander.recordCustomer(clientRobson, "robson airton", "555");

        Account contaJarlan = new Account(1,"javan",clientJarlan);
        Account contaRobson = new Account(2,"mendy", clientRobson);

        bancoSantander.recordAccount(contaJarlan, clientJarlan);
        bancoSantander.recordAccount(contaRobson, clientRobson);

        Deposit deposit = new Deposit(1, contaRobson, 500);

        System.out.println(deposit.getValue());
        System.out.println(contaRobson.getBalance());
        System.out.println(bancoSantander.getCustomer(2).getCompleteName());

    }

}

