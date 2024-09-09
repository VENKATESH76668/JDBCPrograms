package Batchupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import connectionestablishment.JdbcUtility;

public class Test_01_Batchupdate {
	private static final String sqlquery="insert into cricketer(cid,cname,cage)values(?,?,?)";
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement preparedstatement=null;
		Scanner scanner=new Scanner(System.in);
		try {
			   connection=JdbcUtility.getConnection();
			   if(connection!=null) {
				   preparedstatement=connection.prepareStatement(sqlquery);
				   if(preparedstatement!=null) {
					   scanner=new Scanner(System.in);
					   while(true) {
						   System.out.println("Enter cricker id::");
						   Integer cricketerid=scanner.nextInt();
						   System.out.println("Enter cricketer name::");
						   String name=scanner.next();
						   System.out.println("Enter cricketer age::");
						   Integer age=scanner.nextInt();
						   preparedstatement.setInt(1, cricketerid);
						   preparedstatement.setString(2, name);
						   preparedstatement.setInt(3, age);
						   preparedstatement.addBatch();
						   System.out.println("Do you want to add more [YES/NO]::");
						   String option=scanner.next();
						   if(option.equalsIgnoreCase("No")) {
							   break;
						   }else {
							   continue;
						   }
					   }
					   preparedstatement.executeBatch();
					   int row=preparedstatement.getUpdateCount();
		               System.out.println("Records inserted successfully"+row);			  
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
