package callablestatementPrograms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import callable.JdbcUtility;

public class Insertcallablestatement {
	private static final String sqlquery="{call insert_into_prodcut_Details(?,?,?,?)}";
	public static void main(String[] args) {
		Connection connection=null;
		CallableStatement callablestatement=null;
		Scanner scanner=null;
		Integer productid=null;
		String productname=null;
		Integer productprice=null;
		Integer quantity=null;
		try {
		       connection=JdbcUtility.getConnection();
		       if(connection!=null) {
		    	   callablestatement=connection.prepareCall(sqlquery);
		    	   scanner=new Scanner(System.in);
		    	   if(scanner!=null) {
		    		   System.out.println("Enter productid::");
		    		   productid=scanner.nextInt();
		    		   System.out.println("Enter productname::");
		    		   productname=scanner.next();
		    		   System.out.println("Enter price::");
		    		   productprice=scanner.nextInt();
		    		   System.out.println("Enter quantity::");
		    		   quantity=scanner.nextInt();
		    	   }
		    	   if(callablestatement!=null) {
		    		   callablestatement.setInt(1, productid);
		    		   callablestatement.setString(2, productname);
		    		   callablestatement.setInt(3, productprice);
		    		   callablestatement.setInt(4, quantity);
		    	   }
		    	   if(callablestatement!=null) {
		    		   callablestatement.execute();
		    	   }
		    	   if(callablestatement!=null) {
		    		   int count=callablestatement.getUpdateCount();
		    		   System.out.println("Number of rows inserted::"+count);
		    	   }
		       }
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
