package org.example.team;

import org.example.service.*;

import java.util.List;

public class Team {

	private org.example.customer.Customer Customer;
	private int teamID;
	private List<Team> teamList;
	public PolicyRenewalDate m_PolicyRenewalDate;
	public CancellationInsurance m_CancellationInsurance;
	public InsuranceProduct m_InsuranceProduct;
	public LossInvestigation m_LossInvestigation;
	public ReportAccident m_ReportAccident;
	public Payment m_Payment;
	public Compensation m_Compensation;
	public Validation m_Validation;

	public Team(){

	}

	public boolean apply(){
		return false;
	}

	public boolean cancellation(){
		return false;
	}

	public boolean lossInvest(){
		return false;
	}

	public boolean payment(){
		return false;
	}

	public boolean product(){
		return false;
	}

	public boolean renewalDate(){
		return false;
	}

	public boolean report(){
		return false;
	}

	public boolean validate(){
		return false;
	}

}