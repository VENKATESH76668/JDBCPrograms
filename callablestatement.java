package callablestatementPrograms;
import callable.JdbcUtility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import callable.JdbcUtility;
public class callablestatement {
	private static final String sqlquery="{CALL get_product_details(?,?,?,?)}";
	public static void main(String[] args) {
		java.sql.Connection connection=null;
		CallableStatement callablestatement=null;
		Scanner scanner=null;
		Integer productid=null;
		try {
			connection=JdbcUtility.getConnection();
			if(connection!=null) {
				callablestatement=connection.prepareCall(sqlquery);
				scanner=new Scanner(System.in);
				if(scanner!=null) {
					System.out.println("Enter productid::");
					productid=scanner.nextInt();
				}
				//setting the input values
				if(callablestatement!=null) {
					callablestatement.setInt(1, productid);
				}
				//registering or   setting the datatype of output values
				if(callablestatement!=null) {
					callablestatement.registerOutParameter(2,Types.VARCHAR);
					callablestatement.registerOutParameter(3,Types.INTEGER );
					callablestatement.registerOutParameter(4,Types.INTEGER);
				}
				//execute the stored procedures
				if(callablestatement!=null) {
					callablestatement.execute();
				}
				//get the result
				if(callablestatement!=null) {
					System.out.println("Name of the product::"+callablestatement.getString(2));
					System.out.println("Product price::"+callablestatement.getInt(3));
					System.out.println("quantity of the product::"+callablestatement.getInt(4));
				}
			}
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtility.close(connection, callablestatement);
		}
		
	}

}
