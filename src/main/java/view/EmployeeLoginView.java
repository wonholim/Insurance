package view;

import java.io.BufferedReader;

public class EmployeeLoginView {
    private String employeeName;
    public EmployeeLoginView(String employeeName) {
        this.employeeName = employeeName;
    }
    public void employeeSelect(BufferedReader br) {

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
        System.out.println("r. 뒤로가기");
    }
}
