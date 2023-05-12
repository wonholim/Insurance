package customer;

import java.util.List;

public interface CustomerList {

	public boolean add();

	public boolean delete();

	public List<Customer> getCustomerList();

	public Customer retrieve();

	public boolean update();

}