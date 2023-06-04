package view;

import dao.AccidentDAOImpl;
import dao.EmployeeDAOImpl;
import dao.InjuryDAOImpl;
import insurance.*;
import team.*;

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
                        if(!new UnderWriting().retrieveEmployeeUnderWriting(employeeName)) {
                            System.out.println("언더라이팅 팀만 검증을 수행할 수 있습니다.");
                            break;
                        }
                        OneOut:
                        while(true) {
                            printUnderWritingMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1" :
                                    List<Car> carCustomers = new UnderWriting().retrieveCarUnderWritingList();
                                    if(carCustomers.size() == 0) {System.out.println("검증할 대상이 없습니다."); break;}
                                    int carIndex = -1;
                                    while(true) {
                                        carIndex = printTmpCarCustomer(carCustomers, bufferedReader);
                                        if(carIndex != -1) break;
                                    }
                                    if(!new UnderWriting().deleteCarUnderWriting(carCustomers.get(carIndex))){
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if(!new UnderWriting().updateCarUnderWriting(carCustomers.get(carIndex))){
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("검증을 완료 했습니다.");
                                    break;
                                case "2" :
                                    List<Driver> driverCustomers = new UnderWriting().retrieveDriverUnderWritingList();
                                    if(driverCustomers.size() == 0) {System.out.println("검증할 대상이 없습니다."); break;}
                                    int driverIndex = -1;
                                    while(true) {
                                        driverIndex = printTmpDriverCustomer(driverCustomers, bufferedReader);
                                        if(driverIndex != -1) break;
                                    }
                                    if(!new UnderWriting().deleteDriverUnderWriting(driverCustomers.get(driverIndex))){
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if(!new UnderWriting().updateDriverUnderWriting(driverCustomers.get(driverIndex))){
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
                        if(!new AccidentHandling().retrieveAccidentTeam(employeeName)) {
                            System.out.println("사고처리 팀만 사고처리를 수행할 수 있습니다.");
                            break;
                        }
                        TwoOut:
                        while(true) {
                            printAccidentMenu();
                            select = bufferedReader.readLine().trim();

                            switch (select) {
                                case "1":
                                    List<Accident> accidents = new AccidentHandling().retrieveAccidentList();
                                    if (accidents.size() == 0) {
                                        System.out.println("처리할 대상이 없습니다.");
                                        break;
                                    }
                                    int accidentIndex = -1;
                                    while (true) {
                                        accidentIndex = printTmpAccidentCustomer(accidents, bufferedReader);
                                        if (accidentIndex != -1) break;
                                    }
                                    if (!new AccidentHandling().deleteAccidentInsurance(accidents.get(accidentIndex))) {
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if (!new AccidentHandling().updateAccidentInsurance(accidents.get(accidentIndex))) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println(accidents.get(accidentIndex).getCustomerName() + " 사고처리를 완료 했습니다.");
                                    break;
                                case "2":
                                    List<Injury> injuries = new AccidentHandling().retrieveInjuryList();
                                    if (injuries.size() == 0) {
                                        System.out.println("처리할 대상이 없습니다.");
                                        break;
                                    }
                                    int injuryIndex = -1;
                                    while (true) {
                                        injuryIndex = printTmpInjuryCustomer(injuries, bufferedReader);
                                        if (injuryIndex != -1) break;
                                    }
                                    if (!new AccidentHandling().deleteInjuryInsurance(injuries.get(injuryIndex))) {
                                        System.out.println("DB 삭제에 오류가 발생했습니다.");
                                        break;
                                    }
                                    if (!new AccidentHandling().updateInjuryInsurance(injuries.get(injuryIndex))) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println(injuries.get(injuryIndex).getCustomerName() + "님의 상해 처리를 완료 했습니다.");
                                    break;
                                case "r": break TwoOut;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "3":
                        if(!new LossInvestigation().retrieveLossEvaluationTeam(employeeName)) {
                            System.out.println("손해사정 팀만 손해조사를 수행할 수 있습니다.");
                            break;
                        }
                        ThreeOut:
                        while(true) {
                            printLossEvaluation();
                            select = bufferedReader.readLine().trim();
                            switch(select) {
                                case "1":
                                    List<AccidentReport> accidentReports = new LossInvestigation().retrieveAccidentList(0);
                                    if (accidentReports.size() == 0) {
                                        System.out.println("손해 조사할 대상이 없습니다.");
                                        break;
                                    }
                                    int accidentReportIndex = -1;
                                    while (true) {
                                        accidentReportIndex = printAccidentReportCustomer(accidentReports, bufferedReader);
                                        if (accidentReportIndex != -1) break;
                                    }
                                    if (!new LossInvestigation().updateAccidentReport(accidentReports.get(accidentReportIndex), employeeName, 0)) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("손해 조사 처리를 완료 했습니다.");
                                    break;
                                case "2":
                                    List<InjuryReport> injuryReports = new LossInvestigation().retrieveInjuryList(0);
                                    if (injuryReports.size() == 0) {
                                        System.out.println("손해 조사할 대상이 없습니다.");
                                        break;
                                    }
                                    int injuryReportIndex = -1;
                                    while (true) {
                                        injuryReportIndex = printInjuryReportCustomer(injuryReports, bufferedReader);
                                        if (injuryReportIndex != -1) break;
                                    }
                                    if (!new LossInvestigation().updateInjuryReport(injuryReports.get(injuryReportIndex), employeeName, 0)) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("손해 조사 처리를 완료 했습니다.");
                                case "r": break ThreeOut;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "4":
                        if(!new LossAssessment().retrieveLossEvaluationTeam(employeeName)) {
                            System.out.println("손해사정 팀만 손해조사를 수행할 수 있습니다.");
                            break;
                        }
                        FourOut:
                        while(true) {
                            printLossInvestigationMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    List<AccidentReport> accidentList = new LossAssessment().retrieveAccidentList(1);
                                    if (accidentList.size() == 0) {
                                        System.out.println("손해 사정할 대상이 없습니다.");
                                        break;
                                    }
                                    int accidentListIndex = -1;
                                    while (true) {
                                        accidentListIndex = printAccidentReportLossEvaluationCustomer(accidentList, bufferedReader);
                                        if (accidentListIndex != -1) break;
                                    }
                                    if (!new LossAssessment().updateAccidentReport(accidentList.get(accidentListIndex), employeeName, 1)) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println(" 손해 사정 처리를 완료 했습니다.");
                                    break;
                                case "2":
                                    List<InjuryReport> injuryReports = new LossAssessment().retrieveInjuryList(1);
                                    if (injuryReports.size() == 0) {
                                        System.out.println("손해 사정할 대상이 없습니다.");
                                        break;
                                    }
                                    int injuryReportIndex = -1;
                                    while (true) {
                                        injuryReportIndex = printInjuryReportLossEvaluationCustomer(injuryReports, bufferedReader);
                                        if (injuryReportIndex != -1) break;
                                    }
                                    if (!new LossAssessment().updateInjuryReport(injuryReports.get(injuryReportIndex), employeeName, 1)) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println("손해 사정 처리를 완료 했습니다.");
                                    break;
                                case "r": break FourOut;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "5":
                        if(!new Compensation().retrieveClaimProcessingTeam(employeeName)) {
                            System.out.println("보상 처리 팀만 보험금 지급을 수행할 수 있습니다.");
                            break;
                        }
                        FiveOut:
                        while(true) {
                            printCompensationMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    List<AccidentReport> list = new Compensation().retrieveAccidentList(2);
                                    if (list.size() == 0) {
                                        System.out.println("보험금을 지급할 대상이 없습니다.");
                                        break;
                                    }
                                    int accidentIndex = -1;
                                    while (true) {
                                        accidentIndex = printCarCompensationCustomer(list, bufferedReader);
                                        if (accidentIndex != -1) break;
                                    }
                                    if (!new Compensation().deleteAccidentReport(list.get(accidentIndex))) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println(list.get(accidentIndex).getCompensation() + "원 보험금 지급을 완료 했습니다.");
                                    break;
                                case "2":
                                    List<InjuryReport> injuryReports = new Compensation().retrieveInjuryList(2);
                                    if (injuryReports.size() == 0) {
                                        System.out.println("보험금을 지급할 대상이 없습니다.");
                                        break;
                                    }
                                    int injuryIndex = -1;
                                    while (true) {
                                        injuryIndex = printInjuryCompensationCustomer(injuryReports, bufferedReader);
                                        if (injuryIndex != -1) break;
                                    }
                                    if (!new Compensation().deleteInjuryReport(injuryReports.get(injuryIndex))) {
                                        System.out.println("DB 업데이트에 오류가 발생했습니다.");
                                        break;
                                    }
                                    System.out.println(injuryReports.get(injuryIndex).getCompensation() + "원 보험금 지급을 완료 했습니다.");
                                    break;
                                case "r": break FiveOut;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
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

    private void printLossInvestigationMenu() {
        System.out.println("***************** Compensation Menu *****************");
        System.out.println("1. 자동차 보험금 손해 사정");
        System.out.println("2. 운전자 보험금 손해 사정");
        System.out.println("r. 뒤로가기");
    }

    private void printCompensationMenu() {
        System.out.println("***************** Compensation Menu *****************");
        System.out.println("1. 자동차 보험금 지급");
        System.out.println("2. 운전자 보험금 지급");
        System.out.println("r. 뒤로가기");
    }

    private int printInjuryCompensationCustomer(List<InjuryReport> injuryReports, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** Compensation Customers *****************");
        int count = 0;
        for(InjuryReport list : injuryReports){
            System.out.println(count++ + " | 아이디 : " + list.getCustomerID() + "최종 보상금 : " + list.getCompensation());
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine().trim());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private int printInjuryReportLossEvaluationCustomer(List<InjuryReport> injuryReports, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** AccidentReport Customers *****************");
        int count = 0;
        for(InjuryReport list : injuryReports){
            System.out.println(count++ + " | 아이디 : " + list.getCustomerID() + " | 염좌 여부 (0. 없음 1. 있음) : "
                    + list.getSprain() + " | 단순 골절 (0. 없음 1. 있음) : " + list.getSimpleFracture()
                    + " | 개방성 골절 (0. 없음 1. 있음) : " + list.getOpenFracture() + " | 절단 여부 (0. 없음 1. 있음) : " + list.getCut()
                    + "자동 계산 보상금 : " + calculateInjury(list));
            list.setCompensation(list.getCompensation() - 100000);
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        // 손해 사정 처리
        System.out.println("과진료 보상금 차감");
        return select;
    }

    private int printInjuryReportCustomer(List<InjuryReport> injuryReports, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** InjuryReport Customers *****************");
        int count = 0;
        for(InjuryReport list : injuryReports){
            System.out.println(count++ + " | 아이디 : " + list.getCustomerID() + " | 염좌 여부 (0. 없음 1. 있음) : "
                    + list.getSprain() + " | 단순 골절 (0. 없음 1. 있음) : " + list.getSimpleFracture()
                    + " | 개방성 골절 (0. 없음 1. 있음) : " + list.getOpenFracture() + " | 절단 여부 (0. 없음 1. 있음) : " + list.getCut()
                    + "자동 계산 보상금 : " + calculateInjury(list));
            list.setCompensation(calculateInjury(list));
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private int calculateInjury(InjuryReport list) {
        int sum = 100000;
        sum += (list.getSprain() + 1) * sum;
        sum += (list.getSimpleFracture() + 1) * sum;
        sum += (list.getOpenFracture() + 1) * sum;
        sum += (list.getCut()) * sum;
        return sum;
    }

    private void printLossEvaluation() {
        System.out.println("***************** LossEvaluation Handling *****************");
        System.out.println("1. 사고 손해 조사");
        System.out.println("2. 상해 손해 조사");
        System.out.println("r. 뒤로가기");
    }

    private int printAccidentReportLossEvaluationCustomer(List<AccidentReport> accidentReports, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** AccidentReport Customers *****************");
        int count = 0;
        for(AccidentReport list : accidentReports){
            System.out.println(count++ + " | 아이디 : " + list.getCustomerID() + " | 타이어 파손 개수 : "
                    + list.getTire() + " | 앞 범퍼 파손 여부 : " + list.getFrontBumper()
                    + " | 뒷 범퍼 파손 여부 : " + list.getBackBumper() + " | 앞 라이트 파손 개수 : " + list.getFrontLight()
                    + " | 뒷 라이트 파손 개수 : " + list.getBackLight() + " | 문 파손 개수 : " + list.getDoor()
                    + " | 차량 파손 여부 : " + list.getDamageCondition() + " | 상대차 유/무 : " + list.getOtherCar() + "자동 계산 보상금 : " + calculateCar(list));
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        // 손해 사정 처리
        if(accidentReports.get(select).getOtherCar() == 1) {
            System.out.println("상대 보험사와 합의 해 5 : 5로 손해 사정을 완료했습니다.");
            accidentReports.get(select).setCompensation(accidentReports.get(select).getCompensation() / 2);
        }
        return select;
    }

    private int printCarCompensationCustomer(List<AccidentReport> accidents, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** Compensation Customers *****************");
        int count = 0;
        for(AccidentReport list : accidents){
            System.out.println(count++ + " | 아이디 : " + list.getCustomerID() + "최종 보상금 : " + list.getCompensation());
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private int printAccidentReportCustomer(List<AccidentReport> accidentReports, BufferedReader bufferedReader) throws IOException {
        System.out.println("***************** AccidentReport Customers *****************");
        int count = 0;
        for(AccidentReport list : accidentReports){
            System.out.println(count++ + " | 아이디 : " + list.getCustomerID() + " | 타이어 파손 개수 : "
                    + list.getTire() + " | 앞 범퍼 파손 여부 : " + list.getFrontBumper()
                    + " | 뒷 범퍼 파손 여부 : " + list.getBackBumper() + " | 앞 라이트 파손 개수 : " + list.getFrontLight()
                    + " | 뒷 라이트 파손 개수 : " + list.getBackLight() + " | 문 파손 개수 : " + list.getDoor()
                    + " | 차량 파손 여부 : " + list.getDamageCondition() + " | 상대차 유/무 : " + list.getOtherCar() + "자동 계산 보상금 : " + calculateCar(list));
            list.setCompensation(calculateCar(list));
        }
        System.out.println("0 부터 " + (count - 1) + "까지 선택 해주세요.");
        int select = Integer.parseInt(bufferedReader.readLine());
        if(select < 0 || select > count - 1) return -1;
        return select;
    }

    private long calculateCar(AccidentReport list) {
        long sum = 0;
        sum += (long)list.getTire() * 300000;
        sum += (long)list.getFrontBumper() * 150000;
        sum += (long)list.getBackBumper() * 150000;
        sum += (long)list.getFrontLight() * 150000;
        sum += (long)list.getBackLight() * 150000;
        sum += (long)list.getDamageCondition() * 200000;
        sum += (long)list.getDamageCondition() * 5000000;
        sum += list.getOtherCar() * sum;
        return (10 * sum) / 2;
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
        int select = Integer.parseInt(bufferedReader.readLine().trim());
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
        System.out.println("3. 보험금 청구 내역 손해 조사");
        System.out.println("4. 보험금 청구 내역 손해 사정");
        System.out.println("5. 보험금 지급");
        System.out.println("l. 로그아웃");
    }
}
