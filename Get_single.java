package callablestatementPrograms;


	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Types;
	import java.util.Scanner;

	import com.mysql.cj.xdevapi.Type;

	import callable.JdbcUtility;
	import callable.JdbcUtility;
	public class Get_single {
		private static final String sqlquery="{CALL get_Product_Details_Single(?)}";
		public static void main(String[] args) {
			Connection connection=null;
			CallableStatement callablestatement=null;
			Scanner scanner=null;
			String productname1=null;
			ResultSet resultset=null;
			boolean flag=false;
			try {
				connection=JdbcUtility.getConnection();
				if(connection!=null) {
					callablestatement=connection.prepareCall(sqlquery);
					scanner=new Scanner(System.in);
					if(scanner!=null) {
						System.out.println("Enter product name::");
						productname1=scanner.next();
					}
					if(callablestatement!=null) {
						callablestatement.setString(1, productname1);
					
					}
					if(callablestatement!=null) {
						callablestatement.execute();
					}
					if(callablestatement!=null) {
						resultset=callablestatement.getResultSet();
					}
					if(resultset!=null) {
						while(resultset.next()) {
							Integer productid=resultset.getInt(1);
							String productname=resultset.getString(2);
							Integer productprice=resultset.getInt(3);
							Integer quantity=resultset.getInt(4);
							System.out.println("productid\tproductname\tproductprice\tquantity");
							System.out.println(productid+"\t"+productname+"\t"+productprice+"\t"+quantity);
							flag=true;
						}
					}
					if(flag) {
						System.out.println("records are available");
					}else {
						System.out.println("records are not available");
					}
				}
			}catch(FileNotFoundException fe) {
				fe.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
				scanner.close();
				JdbcUtility.close(connection, callablestatement,resultset);
			}
		}


}
