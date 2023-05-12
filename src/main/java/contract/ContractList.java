package contract;

import java.util.List;

public interface ContractList {

	public boolean add();

	public boolean delete();

	public List<Contract> getContractList();

	public Contract retrieve();

	public boolean update();

}