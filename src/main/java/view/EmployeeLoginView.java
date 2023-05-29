package view;

import controller.EmployeeController;
import domain.insurance.Accident;
import domain.insurance.Car;
import domain.insurance.Driver;
import domain.insurance.Injury;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmployeeLoginView {
    private String employeeName;
    public EmployeeLoginView(String employeeName) {
        this.employeeName = employeeName;
    }
    public void employeeSelect(BufferedReader bufferedReader) {
        try {
            OUT:
            while (true) {
                printEmployeeMenu();
                String select = bufferedReader.readLine().trim();
                System.out.println(select);
                switch (select) {
                    case "1":
                        // 3. 언더라이팅팀
                        if(!new EmployeeController().checkUnderWriting(employeeName)) {
                            System.out.println("언더라이팅 팀만 검증을 수행할 수 있습니다.");
                            break;
                        }
                        OneOut:
                        while(true) {
                            printUnderWritingMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1" :
                                    List<Car> carCustomers = new EmployeeController().customersCarUnderWriting();
                                    if(carCustomers.size() == 0) {System.out.println("검증할 대상이 없습니다."); break;}
                                    int carIndex = -1;
                                    while(true) {
                                        carIndex = printTmpCarCustomer(carCustomers, bufferedReader);
                                        if(carIndex != -1) break;
                                    }
                                    if(!new EmployeeController().customersCarUnderWritingDelete(carCustomers.get(carIndex))){
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if(!new EmployeeController().customersCarUnderWritingUpdate(carCustomers.get(carIndex))){
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("검증을 완료 했습니다.");
                                    break;
                                case "2" :
                                    List<Driver> driverCustomers = new EmployeeController().customersDriverUnderWriting();
                                    if(driverCustomers.size() == 0) {System.out.println("검증할 대상이 없습니다."); break;}
                                    int driverIndex = -1;
                                    while(true) {
                                        driverIndex = printTmpDriverCustomer(driverCustomers, bufferedReader);
                                        if(driverIndex != -1) break;
                                    }
                                    if(!new EmployeeController().customersDriverUnderWritingDelete(driverCustomers.get(driverIndex))){
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if(!new EmployeeController().customersDriverUnderWritingUpdate(driverCustomers.get(driverIndex))){
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("검증을 완료 했습니다.");
                                    break;
                                case "r" :
                                    break OneOut;
                            }
                        }
                        break;
                    case "2":
                        // 1. 사고처리 팀
                        if(!new EmployeeController().checkAccidentTeam(employeeName)) {
                            System.out.println("사고처리 팀만 사고처리를 수행할 수 있습니다.");
                            break;
                        }
                        TwoOut:
                        while(true) {
                            printAccidentMenu();
                            select = bufferedReader.readLine().trim();

                            switch (select) {
                                case "1":
                                    List<Accident> accidents = new EmployeeController().getTmpAccidentList();
                                    if (accidents.size() == 0) {
                                        System.out.println("검증할 대상이 없습니다.");
                                        break;
                                    }
                                    int accidentIndex = -1;
                                    while (true) {
                                        accidentIndex = printTmpAccidentCustomer(accidents, bufferedReader);
                                        if (accidentIndex != -1) break;
                                    }
                                    if (!new EmployeeController().tmpAccidentInsuranceDelete(accidents.get(accidentIndex))) {
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if (!new EmployeeController().accidentInsuranceUpdate(accidents.get(accidentIndex))) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("사고처리를 완료 했습니다.");
                                    break;
                                case "2":
                                    List<Injury> injuries = new EmployeeController().getTmpInjuryList();
                                    if (injuries.size() == 0) {
                                        System.out.println("검증할 대상이 없습니다.");
                                        break;
                                    }
                                    int injuryIndex = -1;
                                    while (true) {
                                        injuryIndex = printTmpInjuryCustomer(injuries, bufferedReader);
                                        if (injuryIndex != -1) break;
                                    }
                                    if (!new EmployeeController().tmpInjuryInsuranceDelete(injuries.get(injuryIndex))) {
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if (!new EmployeeController().InjuryInsuranceUpdate(injuries.get(injuryIndex))) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("사고처리를 완료 했습니다.");
                                    break;
                                case "r": break TwoOut;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
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

    private int printTmpInjuryCustomer(List<Injury> injuries, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** Injury Customers *****************");
        int count = 0;
        for(Injury list : injuries){
            System.out.println(count++ + " | 이름 : " + list.getCustomerName() + " | 주민 등록 번호 : "
                    + list.getRegistrationNumber() + " | 전화 번호 : " + list.getPhoneNum()
                    + " | 사고 지역 : " + list.getLocation() + " | 병명 : " + list.getDisease());
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private int printTmpAccidentCustomer(List<Accident> accidents, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** Accident Customers *****************");
        int count = 0;
        for(Accident list : accidents){
            System.out.println(count++ + " | 이름 : " + list.getCustomerName() + " | 주민 등록 번호 : "
                    + list.getRegistrationNumber() + " | 전화 번호 : " + list.getPhoneNum() + " | 사고 날짜 : "
                    + list.getAccidentDate() + " | 출동 여부 (1. 현장 출동 2. 긴급 출동) " + list.getService()
                    + " | 사고 지역 : " + list.getLocation() + " | 차 번호 : " + list.getCarNum());
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private void printAccidentMenu() {
        System.out.println("***************** Accident Handling *****************");
        System.out.println("1. 사고 처리");
        System.out.println("2. 상해 처리");
        System.out.println("r. 뒤로가기");
    }

    private int printTmpDriverCustomer(List<Driver> list, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** Driver UnderWriting *****************");
        int count = 0;
        for(Driver driver : list){
            System.out.println(count++ + " | 이름 : " + driver.getCustomerName() + " | 운전 면허 : "
                    + driver.getDriverLicense() + " | 전화 번호 : " + driver.getPhoneNum() + " | 월 납입료 : "
                    + driver.getPrice());
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }
    private int printTmpCarCustomer(List<Car> list, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** Car UnderWriting *****************");
        int count = 0;
        for(Car c : list){
            System.out.println(count++ + " | 이름 : " + c.getCustomerName() + " | 차종 : " + c.getCarModel()
                    + " | 운전면허 : " + c.getDriverLicense() + " | 월 납부료 : " + c.getPrice());
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private void printUnderWritingMenu() {
        System.out.println("***************** EMPLOYEE MENU *****************");
        System.out.println("1. 자동차 보험 가입 신청자 검증");
        System.out.println("2. 운전자 보험 가입 신청자 검증");
        System.out.println("r. 뒤로가기");
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
