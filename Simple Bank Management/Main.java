import java.util.*;
class Account{
    private String firstName;
    private String lastname;
    private int id;
    private double balance;
    private int password;

    public Account(){}

    public Account(String firstName, String lastname, int id, double balance, int password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.id = id;
        this.balance = balance;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

}

class Deposit{
    public Deposit(Scanner sc, Account acc){
        System.out.println("Enter Amount:");
        double amount = sc.nextDouble();
        acc.setBalance(acc.getBalance() + amount);
        System.out.println("Deposited Successfully!"); 
    }
}

class Withdraw{
    public Withdraw(Scanner sc, Account acc){
        System.out.println("Enter Amount:");
        double amount = sc.nextDouble();
        acc.setBalance(acc.getBalance() - amount);
        System.out.println("Withdrawed Successfully!"); 
    }
}

class Menu{
    public Menu(Scanner sc, Account acc){
        int i = 0;
        do{
            System.out.println("\nWelcome " + acc.getFirstName()+ " " + acc.getLastname()+"\n");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance");
            System.out.println("4. Exit");
            i = sc.nextInt();
            switch(i){
                case 1:
                    new Deposit(sc,acc);
                    break;
                case 2:
                    new Withdraw(sc,acc);
                    break;
                case 3:
                    System.out.println("Your balance is: " + acc.getBalance());
                    break;
            }
        }while(i!=4);
    }
}

class Login{
    public Login(Scanner sc, ArrayList<Account> accounts){
        System.out.println("Enter your Account id:");
        int id = sc.nextInt();
        System.out.println("Enter Passcode:");
        int passcode = sc.nextInt();
        Account account = new Account();
        boolean exist = false;
        for(Account acc : accounts){
            if(acc.getId()==id && acc.getPassword()==passcode){
                exist = true;
                account = acc;
            }
        }
        if(exist){
            new Menu(sc,account);
        }else{
            System.out.println("Account doesn't exist");
        }
    }
}

class createnewAcc{
    public createnewAcc(Scanner sc, int Lastaccid, ArrayList<Account> accounts){
        System.out.println("Enter first Name:");
        String firstName = sc.next();
        System.out.println("Enter last Name:");
        String lastName = sc.next();
        System.out.println("Enter Balance:");
        double balance = sc.nextDouble();
        System.out.println("Enter Passcode: (digits only)");
        int passcode = sc.nextInt();
        System.out.println("Confirm Passcode:");
        int confpasscode = sc.nextInt();
        if(passcode != confpasscode){
            System.out.println("Not Maching\n");
            return;
        }
        int id = 1000000 + Lastaccid;
        Lastaccid += 1;
        Account acc = new Account(firstName, lastName, id, balance, passcode);
        accounts.add(acc);
        System.out.println("Your account id: " + id);
        new Menu(sc, acc);
    }
}

public class Main{
    static Scanner sc;
    static int Lastaccid = 0;
    private static ArrayList<Account> accounts;

    public static void main(String[] args){
        accounts = new ArrayList<>();
        sc = new Scanner(System.in);
        System.out.println("Welcome To our Bank");
        System.out.println("1. Create new Account");
        System.out.println("2. Login");
        int i = sc.nextInt();
        switch(i){
            case 1: new createnewAcc(sc, Lastaccid, accounts);
                    break;
            case 2: new Login(sc, accounts);
                    break;
        }
    }
}