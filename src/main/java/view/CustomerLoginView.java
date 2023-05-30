package view;

import controller.CustomerController;
import domain.insurance.Accident;
import domain.insurance.AccidentReport;
import domain.insurance.Injury;
import domain.insurance.InjuryReport;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomerLoginView {
    private String userName;
    private CustomerController customerController;
    public CustomerLoginView(String userName) {
        this.userName = userName;
        this.customerController = new CustomerController();
    }
    public void customerSelect(BufferedReader bufferedReader) {
        try {
            OUT:
            while (true) {
                printCustomerMenu();
                String select = bufferedReader.readLine().trim();
                System.out.println(select);
                switch (select) {
                    case "1":
                        One:
                        while(true) {
                            printInsuranceProduct();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    String product = this.customerController.getInsuranceProduct(this.userName, "1");
                                    if (product == null) {System.out.println("해당 보험의 세부 내역을 불러오는데 실패하였습니다.");}
                                    else System.out.println(product);
                                    OneOut:
                                    while(true) {
                                        printregisterInsuranceProduct();
                                        select = bufferedReader.readLine().trim();
                                        switch (select) {
                                            case "1":
                                                if(this.customerController.registerInsuranceProduct(this.userName, "1")){
                                                    System.out.println("자동차 보험 가입 신청이 완료되었습니다. 검증 후 3일 뒤 가입 신청 결과를 확인할 수 있습니다.");
                                                }else{
                                                    System.out.println("이미 등록된 보험거나, 보험 가입이 거절 되었습니다.");
                                                }
                                                break OneOut;
                                            case "r": break OneOut;
                                            default : System.out.println("입력값이 올바르지 않습니다.");
                                        }
                                    }
                                    break;
                                case "2":
                                    String driverProduct = this.customerController.getInsuranceProduct(this.userName, "2");
                                    if(driverProduct == null) {System.out.println("해당 보험의 세부 내역을 불러오는데 실패하였습니다.");}
                                    else System.out.println(driverProduct);
                                    TwoOut:
                                    while(true) {
                                        printregisterInsuranceProduct();
                                        select = bufferedReader.readLine().trim();
                                        switch (select) {
                                            case "1":
                                                if(this.customerController.registerInsuranceProduct(this.userName, "2")){
                                                    System.out.println("운전자 보험 가입 신청이 완료되었습니다. 검증 후 3일 뒤 가입 신청 결과를 확인할 수 있습니다.");
                                                }else{
                                                    System.out.println("이미 등록된 보험거나, 보험 가입이 거절 되었습니다.");
                                                }
                                                break TwoOut;
                                            case "r":
                                                break TwoOut;
                                            default : System.out.println("입력값이 올바르지 않습니다.");
                                        }
                                    }
                                    break;
                                case "r": break One;
                                default : System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "2":
                        printRegisterInsurance();
                        break;
                    case "3":
                        String printInsurance = printRegisterInsurance();
                        if(printInsurance == null) {
                            System.out.println("가입된 보험 내역이 존재하지 않습니다.");
                            break;
                        }
                        ThreeOut:
                        while(true) {
                            printInsuranceList();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    if(printInsurance.contains("Car")){
                                        while(true) {
                                            System.out.print("발생하는 위약금 : ");
                                            long penaltyFee = new CustomerController().getPenaltyFeeInsuranceCar(userName);
                                            System.out.println(penaltyFee + "원");
                                            if(penaltyFee == 0) {
                                                System.out.println("해당 보험은 청약철회가 가능합니다.");
                                                System.out.println("1. 청약 철회 한다.");
                                                System.out.println("2. 청약 철회 하지않는다.");
                                            }else {
                                                System.out.println("1. 혜지한다.");
                                                System.out.println("2. 혜지하지않는다.");
                                            }
                                            select = bufferedReader.readLine().trim();
                                            if(select.equals("1")){
                                                if(new CustomerController().cancellationCarInsurance(userName)) {
                                                    System.out.println("보험이 혜지 되었습니다.");
                                                }else{
                                                    System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                                }
                                                break;
                                            }else if(select.equals("2")){
                                                System.out.println("보험 혜지를 취소하였습니다.");
                                                break;
                                            }else {
                                                System.out.println("입력값이 올바르지 않습니다.");
                                            }
                                        }
                                    }else{
                                        System.out.println("해당 보험에 가입된 내역이 없습니다.");
                                    }
                                    break;
                                case "2":
                                    if(printInsurance.contains("Driver")){
                                        while(true) {
                                            System.out.print("발생하는 위약금 : ");
                                            long penaltyFee = new CustomerController().getPenaltyFeeInsuranceDriver(userName);
                                            System.out.println(penaltyFee + "원");
                                            if(penaltyFee == 0) {
                                                System.out.println("해당 보험은 청약철회가 가능합니다.");
                                                System.out.println("1. 청약 철회 한다.");
                                                System.out.println("2. 청약 철회 하지않는다.");
                                            }else {
                                                System.out.println("1. 혜지한다.");
                                                System.out.println("2. 혜지하지않는다.");
                                            }
                                            select = bufferedReader.readLine().trim();
                                            if(select.equals("1")){
                                                if(new CustomerController().cancellationDriverInsurance(userName)) {
                                                    System.out.println("보험이 혜지 되었습니다.");
                                                }else{
                                                    System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                                }
                                                break;
                                            }else if(select.equals("2")){
                                                System.out.println("보험 혜지를 취소하였습니다.");
                                                break;
                                            }else {
                                                System.out.println("입력값이 올바르지 않습니다.");
                                            }
                                        }
                                    }else{
                                        System.out.println("해당 보험에 가입된 내역이 없습니다.");
                                    }
                                    break;
                                case "r": break ThreeOut;
                                default : System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "4":
                        printInsurance = printRegisterInsurance();
                        if(printInsurance == null) {
                            System.out.println("가입된 보험 내역이 존재하지 않습니다.");
                            break;
                        }
                        FourOut:
                        while(true) {
                            printRenewExpirationInsuranceList();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    if(printInsurance.contains("Car")){
                                        String[] insuranceInformations = new CustomerController().getCarInsuranceInformations(userName);
                                        printRenewExpirationCarInsurance(insuranceInformations);
                                        boolean expiredInsurance = new CustomerController().checkForExpiredCarInsuran(userName);
                                        if(expiredInsurance) {
                                            System.out.println("보험 계약이 끝나 만기 갱신을 하셔야 합니다.");
                                            while(true) {
                                                System.out.println("1. 갱신하기");
                                                System.out.println("2. 갱신하지않기");
                                                select = bufferedReader.readLine().trim();
                                                if(select.equals("1")){
                                                    if(new CustomerController().renewExpirationCarInsurance(userName)) {
                                                        System.out.println("보험 갱신이 완료되었습니다.");
                                                    }else{
                                                        System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                                    }
                                                    break;
                                                }else if(select.equals("2")){
                                                    System.out.println("보험 갱신을 취소하였습니다.");
                                                    break;
                                                }else {
                                                    System.out.println("입력값이 올바르지 않습니다.");
                                                }
                                            }
                                        }else{
                                            while(true) {
                                                System.out.println("1. 갱신하기");
                                                System.out.println("2. 갱신하지않기");
                                                select = bufferedReader.readLine().trim();
                                                if(select.equals("1")){
                                                    if(new CustomerController().renewExpirationCarInsurance(userName)) {
                                                        System.out.println("보험 갱신이 완료되었습니다.");
                                                    }else{
                                                        System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                                    }
                                                    break;
                                                }else if(select.equals("2")){
                                                    System.out.println("보험 갱신을 취소하였습니다.");
                                                    break;
                                                }else {
                                                    System.out.println("입력값이 올바르지 않습니다.");
                                                }
                                            }
                                        }
                                    }else{
                                        System.out.println("해당 보험에 가입된 내역이 없습니다.");
                                    }
                                    break;
                                case "2":
                                    if(printInsurance.contains("Driver")){
                                        String[] insuranceInformations = new CustomerController().getDriverInsuranceInformations(userName);
                                        printRenewExpirationDriverInsurance(insuranceInformations);
                                        boolean expiredInsurance = new CustomerController().checkForExpiredDriverInsuran(userName);
                                        if(expiredInsurance) {
                                            System.out.println("보험 계약이 끝나 만기 갱신을 하셔야 합니다.");
                                            while(true) {
                                                System.out.println("1. 갱신하기");
                                                System.out.println("2. 갱신하지않기");
                                                select = bufferedReader.readLine().trim();
                                                if(select.equals("1")){
                                                    if(new CustomerController().renewExpirationDriverInsurance(userName)) {
                                                        System.out.println("보험 갱신이 완료되었습니다.");
                                                    }else{
                                                        System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                                    }
                                                    break;
                                                }else if(select.equals("2")){
                                                    System.out.println("보험 갱신을 취소하였습니다.");
                                                    break;
                                                }else {
                                                    System.out.println("입력값이 올바르지 않습니다.");
                                                }
                                            }
                                        }else{
                                            while(true) {
                                                System.out.println("1. 갱신하기");
                                                System.out.println("2. 갱신하지않기");
                                                select = bufferedReader.readLine().trim();
                                                if(select.equals("1")){
                                                    if(new CustomerController().renewExpirationDriverInsurance(userName)) {
                                                        System.out.println("보험 갱신이 완료되었습니다.");
                                                    }else{
                                                        System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                                    }
                                                    break;
                                                }else if(select.equals("2")){
                                                    System.out.println("보험 갱신을 취소하였습니다.");
                                                    break;
                                                }else {
                                                    System.out.println("입력값이 올바르지 않습니다.");
                                                }
                                            }
                                        }
                                    }else{
                                        System.out.println("해당 보험에 가입된 내역이 없습니다.");
                                    }
                                    break;
                                case "r": break FourOut;
                                default : System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "5":
                        printInsurance = printRegisterInsurance();
                        if(printInsurance == null) {
                            System.out.println("가입된 보험 내역이 존재하지 않습니다.");
                            break;
                        }
                        FiveOut:
                        while(true) {
                            printAccidentChoice();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    if(printInsurance.contains("Car")) {
                                        if(!new CustomerController().isAccidentAdd(userName)){
                                            Accident accident = printCarAccidentReportMenu(bufferedReader);
                                            if(new CustomerController().AccidentAdd(accident)){
                                                System.out.println("사고 접수가 완료되었습니다. 사고 처리 담당자가 유선으로 안내를 드릴 예정 입니다. 감사합니다.");
                                            } else {
                                                System.out.println("사고 접수 정보 저장을 실패했습니다. 시스템에 직접 등록하시기 바랍니다.");
                                            }
                                        } else {
                                            System.out.println("이미 사고 접수가 완료되었습니다.");
                                        }
                                    } else {
                                        System.out.println("해당 보험에 가입된 내역이 없습니다.");
                                        break;
                                    }
                                    break;
                                case "2":
                                    if(printInsurance.contains("Driver")) {
                                        if(!new CustomerController().isInjuryAdd(userName)){
                                            Injury injury = printInjuryReportMenu(bufferedReader);
                                            if(new CustomerController().injuryAdd(injury)){
                                                System.out.println("상해 접수가 완료되었습니다. 사고 처리 담당자가 유선으로 안내를 드릴 예정 입니다. 감사합니다.");
                                            }else {
                                                System.out.println("상해 접수 정보 저장을 실패했습니다. 시스템에 직접 등록하시기 바랍니다.");
                                            }
                                        } else {
                                            System.out.println("이미 상해 접수가 완료되었습니다.");
                                        }
                                    } else {
                                        System.out.println("해당 보험에 가입된 내역이 없습니다.");
                                        break;
                                    }
                                    break;
                                case "r": break FiveOut;
                                default: System.out.println("입력값이 올바르지 않습니다.");
                            }
                        }
                        break;
                    case "6":
                        printInsurance = printRegisterInsurance();
                        if(printInsurance == null) {
                            System.out.println("가입된 보험 내역이 존재하지 않습니다.");
                            break;
                        }
                        SixOut:
                        while(true) {
                            printRequestInsurancePayoutMenu();
                            select = bufferedReader.readLine().trim();
                            switch (select) {
                                case "1":
                                    if(!new CustomerController().isAccidentAdd(userName)) {
                                        if(new CustomerController().isAccidentAddProcess(userName)){
                                            AccidentReport accidentReport = printRequestCarInsurancePayout(bufferedReader);
                                            accidentReport.setCustomerID(userName);
                                            if(new CustomerController().requestCarInsurancePayout(accidentReport)) {
                                                System.out.println("자동차 보험금 신청이 완료되었습니다.");
                                            } else {
                                                System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
                                            }
                                        } else {
                                            System.out.println("사고 접수를 한 내역이 존재하지 않습니다.");
                                        }
                                    } else {
                                        System.out.println("현재 사고 처리 승인을 기다리는 중입니다. 승인까지 최대 3일 소요됩니다.");
                                    }
                                    break;
                                case "2":
                                    if(!new CustomerController().isInjuryAdd(userName)) {
                                        if(new CustomerController().isInjuryAddProcess(userName)){
                                            InjuryReport injuryReports = printRequestDriverInsurancePayout(bufferedReader);
//                                            injuryReports.setCustomerID(userName);
//                                            if(new CustomerController().requestCarInsurancePayout(injuryReports)) {
//                                                System.out.println("상해 보험금 신청이 완료되었습니다.");
//                                            } else {
//                                                System.out.println("시스템에 일시적인 장애가 발생하였습니다. 잠시 후 다시 시도해주세요.");
//                                            }
                                        } else {
                                            System.out.println("사고 접수를 한 내역이 존재하지 않습니다.");
                                        }
                                    } else {
                                        System.out.println("현재 사고 처리 승인을 기다리는 중입니다. 승인까지 최대 3일 소요됩니다.");
                                    }
                                    break;
                                case "r":
                                    break SixOut;
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

    private InjuryReport printRequestDriverInsurancePayout(BufferedReader bufferedReader) {
        return null;
    }

    private AccidentReport printRequestCarInsurancePayout(BufferedReader bufferedReader) throws IOException {
        AccidentReport accidentReport = new AccidentReport();
        System.out.println("***************** 청구서류 작성 *****************");
        System.out.println("타이어 파손 개수 여부(1, 2, 3, 4) : ");
        accidentReport.setTire(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("앞 범퍼 파손 여부 (0. 파손X, 1. 파손O) : ");
        accidentReport.setFrontBumper(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("뒷 범퍼 파손 여부 (0. 파손X, 1. 파손O) : ");
        accidentReport.setBackBumper(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("앞 라이트 파손 개수 여부 (1, 2) :");
        accidentReport.setFrontLight(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("뒷 라이트 파손 개수 여부 (1, 2) :");
        accidentReport.setBackLight(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("문 파손 개수 여부 (1, 2, 3, 4) :");
        accidentReport.setDoor(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("0. 경미한 파손 1. 반정도 파손 2. 완전 파손 : ");
        accidentReport.setDamageCondition(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("상대 차량 여부 (0. 없음 1. 있음) :");
        accidentReport.setOtherCar(Integer.parseInt(bufferedReader.readLine().trim()));
        System.out.println("청구 서류 작성이 완료 되었습니다.");
        return accidentReport;
    }

    private void printRequestInsurancePayoutMenu() {
        System.out.println("***************** RequestInsurancePayout MENU *****************");
        System.out.println("1. 자동차 사고 보험금 접수");
        System.out.println("2. 상해 사고 보험금 접수");
        System.out.println("r. 뒤로가기");
    }

    private Injury printInjuryReportMenu(BufferedReader bufferedReader) throws IOException {
        Injury injury = new Injury();
        System.out.println("***************** InjuryReport MENU *****************");
        System.out.print("아이디 : ");
        String customerID = bufferedReader.readLine().trim();
        injury.setCustomerID(customerID);
        System.out.print("이름 : ");
        String customerName = bufferedReader.readLine().trim();
        injury.setCustomerName(customerName);
        System.out.print("주민등록번호 : ");
        String regitrationNumber = bufferedReader.readLine().trim();
        injury.setRegistrationNumber(regitrationNumber);
        System.out.print("전화번호 : ");
        String phoneNum = bufferedReader.readLine().trim();
        injury.setPhoneNum(phoneNum);
        System.out.print("사고 장소 : ");
        String location = bufferedReader.readLine().trim();
        injury.setLocation(location);
        System.out.print("사고 일시(YYYY-MM-DD) : ");
        String accidentDate = bufferedReader.readLine().trim();
        injury.setInjuryDate(accidentDate);
        System.out.print("병명 : ");
        String disease = bufferedReader.readLine().trim();
        injury.setDisease(disease);
        return injury;
    }

    private Accident printCarAccidentReportMenu(BufferedReader bufferedReader) throws IOException {
        Accident accident = new Accident();
        System.out.println("***************** AccidentReport MENU *****************");
        System.out.print("아이디 : ");
        String customerID = bufferedReader.readLine().trim();
        accident.setCustomerID(customerID);
        System.out.print("이름 :");
        String customerName = bufferedReader.readLine().trim();
        accident.setCustomerName(customerName);
        System.out.print("주민등록번호 : ");
        String regitrationNumber = bufferedReader.readLine().trim();
        accident.setRegistrationNumber(regitrationNumber);
        System.out.print("전화번호 : ");
        String phoneNum = bufferedReader.readLine().trim();
        accident.setPhoneNum(phoneNum);
        System.out.print("사고 장소 : ");
        String location = bufferedReader.readLine().trim();
        accident.setLocation(location);
        System.out.print("사고 일시(YYYY-MM-DD) : ");
        String accidentDate = bufferedReader.readLine().trim();
        accident.setAccidentDate(accidentDate);
        System.out.print("차량 번호 : ");
        String carNum = bufferedReader.readLine().trim();
        accident.setCarNum(carNum);
        System.out.print("서비스 종류 (1. 현장 출동, 2. 긴급 출동) : ");
        int service = Integer.parseInt(bufferedReader.readLine().trim());
        accident.setService(service);
        return accident;
    }

    private void printAccidentChoice() {
        System.out.println("***************** Accident MENU *****************");
        System.out.println("1. 자동차 사고 접수");
        System.out.println("2. 상해 사고 접수");
        System.out.println("r. 뒤로가기");
    }

    private void printRenewExpirationCarInsurance(String[] insuranceInformations) {
        System.out.println("보험 목록 : 자동차 보험\n"
                + "만기 일자 : " + insuranceInformations[1] + "\n"
                + "월 납입료 : " + insuranceInformations[0] + "\n");
    }
    private void printRenewExpirationDriverInsurance(String[] insuranceInformations) {
        System.out.println("보험 목록 : 운전자 보험\n"
                + "만기 일자 : " + insuranceInformations[1] + "\n"
                + "월 납입료 : " + insuranceInformations[0] + "\n");
    }
    private void printRenewExpirationInsuranceList() {
        System.out.println("***************** Insurance MENU *****************");
        System.out.println("1. 자동차 보험 갱신");
        System.out.println("2. 운전자 보험 갱신");
        System.out.println("r. 뒤로가기");
    }
    private void printInsuranceList() {
        System.out.println("***************** Insurance MENU *****************");
        System.out.println("1. 자동차 보험 혜지");
        System.out.println("2. 운전자 보험 혜지");
        System.out.println("r. 뒤로가기");
    }

    private String printRegisterInsurance() {
        String allInsurance = new CustomerController().getAllInsuranceProduct(userName);
        if(!allInsurance.equals("")) {
            System.out.println("가입된 보험 : " + allInsurance);
        } else {
            System.out.println("가입된 보험이 존재하지 않습니다.");
            return null;
        }
        return allInsurance;
    }

    private void printregisterInsuranceProduct() {
        System.out.println("1. 가입하기");
        System.out.println("r. 뒤로가기");
    }

    private static void printInsuranceProduct() {
        System.out.println("***************** PRODUCT MENU *****************");
        System.out.println("1. 자동차 보험 조회");
        System.out.println("2. 운전자 보험 조회");
        System.out.println("r. 뒤로가기");
    }

    private static void printCustomerMenu() {
        System.out.println("***************** CUSTOMER MENU *****************");
        System.out.println("1. 보험 상품 조회");
        System.out.println("2. 가입한 보험 상품 조회");
        System.out.println("3. 보험 혜지");
        System.out.println("4. 보험 갱신");
        System.out.println("5. 사고 접수");
        System.out.println("6. 보험금 신청");
        System.out.println("l. 로그아웃");
    }
}
