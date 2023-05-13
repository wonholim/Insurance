package domain.insurance;

import java.util.List;

public interface InsuranceList {

	public boolean add();

	public List<Insurance> getInsuranceList();

	public boolean retrieve();

	public boolean update();


}