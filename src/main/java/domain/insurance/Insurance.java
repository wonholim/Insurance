package domain.insurance;

import domain.contract.Contract;
import domain.team.Team;

public abstract class Insurance {

	private int account;
	private int beneficiary;
	private int duration;
	private String insuranceID;
	private int monthPayment;
	private String name;

	private enum type{

	}
	public Team m_Team;
	public Contract m_Contract;

	public Insurance() {

	}



	public double calculateRate(){
		return 1.0;
	}

	public double subscribe(){
		return 0;
	}

}