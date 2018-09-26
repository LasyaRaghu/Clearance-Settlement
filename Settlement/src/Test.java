import com.beans.Company;
import com.operations.CompanyOperations;
import com.operations.impl.CompanyOperationsImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      CompanyOperations cop = new CompanyOperationsImpl();
      Company c = cop.findByCompanyId("C01");
      System.out.println(c.getAvailableFunds());
	}

}
