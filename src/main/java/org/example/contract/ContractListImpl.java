package org.example.contract;

import java.util.List;

public class ContractListImpl implements ContractList {

	private List contractIDList;
	public Contract m_Contract;

	public ContractListImpl(){

	}

	public boolean add(){
		return false;
	}

	public boolean delete(){
		return false;
	}

	public List<Contract> getContractList(){
		return null;
	}

	public Contract retrieve(){
		return null;
	}

	public boolean update(){
		return false;
	}

}