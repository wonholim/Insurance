package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class CustomerLoginView {
    private String userName;
    public CustomerLoginView(String userName) {
        this.userName = userName;
    }
    public void customerSelect(BufferedReader br) {
        try {
            OUT:
            while (true) {
                printCustomerMenu();
                String select = br.readLine().trim();
                System.out.println(select);
                switch (select) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                    case "l":
                        break OUT;
                    default:
                        System.out.println("입력값이 올바르지 않습니다.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void printCustomerMenu() {
        System.out.println("***************** CUSTOMER MENU *****************");
        System.out.println("1. 보험가입");
        System.out.println("2. 보험혜지");
        System.out.println("3. 보험갱신");
        System.out.println("4. 사고접수");
        System.out.println("5. 보험금신청");
        System.out.println("l. 로그아웃");
    }

}
