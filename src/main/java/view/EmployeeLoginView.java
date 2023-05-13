package view;

import java.io.BufferedReader;
import java.io.IOException;

public class EmployeeLoginView {
    private String employeeName;
    public EmployeeLoginView(String employeeName) {
        this.employeeName = employeeName;
    }
    public void employeeSelect(BufferedReader br) {
        try {
            OUT:
            while (true) {
                printEmployeeMenu();
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
                    case "6":
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
    private static void printEmployeeMenu() {
        System.out.println("***************** EMPLOYEE MENU *****************");
        System.out.println("1. 보험가입 신청정보 검증");
        System.out.println("2. 사고처리");
        System.out.println("3. 보험금 청구 내역 조사");
        System.out.println("4. 보험금 청구 내역 심사");
        System.out.println("5. 보험금 지급");
        System.out.println("6. 상품개발 등록");
        System.out.println("l. 로그아웃");
    }
}
