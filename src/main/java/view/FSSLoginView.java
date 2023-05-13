package view;

import java.io.BufferedReader;
import java.io.IOException;

public class FSSLoginView {
    private String fssName;
    public FSSLoginView(String fssName) {
        this.fssName = fssName;
    }

    public void fssSelect(BufferedReader br) {
        try {
            OUT:
            while (true) {
                printFSSMenu();
                String select = br.readLine().trim();
                System.out.println(select);
                switch (select) {
                    case "1":
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

    private void printFSSMenu() {
        System.out.println("***************** EMPLOYEE LOGIN MENU *****************");
        System.out.println("1. 상품판매허가");
        System.out.println("l. 로그아웃");
    }

}
