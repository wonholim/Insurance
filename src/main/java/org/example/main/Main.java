package org.example.main;

import org.example.contract.Contract;
import org.example.contract.ContractListImpl;
import org.example.customer.Customer;
import org.example.customer.CustomerListImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.*;
public class Main {
    public static void main(String[] args) throws NotBoundException, IOException {
        BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                printMenu();
                String sChoice = objReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        registerCustomer();
                        break;
                    case "2":
                        insuranceContract();
                        break;
                    case "3":
                        accidentReceipt();
                        break;
                    case "4":
                        compensation();
                        break;
                    case "5":
                        policyRenewalDate();
                        break;
                    case "6":
                        cancellationInsurance();
                        break;
                    case "7":
                        payment();
                        break;
                    case "x":
                        exitProgram();
                        return;
                    default:
                        System.out.println("Invalid Choice");
                }
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void payment() {
    }

    private static void cancellationInsurance() {
    }

    private static void policyRenewalDate() {
    }

    private static void compensation() {
    }

    private static void accidentReceipt() {
    }

    private static void insuranceContract() {
        ContractListImpl contractListImpl = new ContractListImpl();
        Contract contract = new Contract();
    }

    private static void registerCustomer() {
        CustomerListImpl customerListImpl = new CustomerListImpl();
        Customer customer = new Customer();
        System.out.println("Input Customer Name");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String name = br.readLine();
            customer.setName(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void printMenu() {
        System.out.println("***************** MENU *****************");
        System.out.println("1. Register Customer");
        System.out.println("2. Insurance Contract");
        System.out.println("3. Accident Receipt");
        System.out.println("4. Compensation");
        System.out.println("5. PolicyRenewalDate");
        System.out.println("6. CancellationInsurance");
        System.out.println("7. Payment");
        System.out.println("x. Exit");
    }

    private static void exitProgram() {
        System.out.println("Exit Program");
        System.exit(0);
    }
}