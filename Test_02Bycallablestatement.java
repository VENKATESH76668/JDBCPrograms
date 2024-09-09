package Batchupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connectionestablishment.JdbcUtility;

public class Test_02Bycallablestatement {
	private static final String sqlquery="{CALL insert_Details_cricketers(?,?,?)}";
	public static void main(String[] args) {
		Connection connection=null;
		CallableStatement callablestatement=null;
		Scanner scanner=null;
		try {
			connection=JdbcUtility.getConnection();
			if(connection!=null) {
				callablestatement=connection.prepareCall(sqlquery);
				if(callablestatement!=null) {
					scanner=new Scanner(System.in);
					while(true) {
						System.out.println("Enter cid::");
						Integer cid=scanner.nextInt();
						System.out.println("Enter cname::");
						String cname=scanner.next();
						System.out.println("Enter cage::");
						Integer cage=scanner.nextInt();
						callablestatement.setInt(1, cid);
						callablestatement.setString(2, cname);
						callablestatement.setInt(3, cage);
						callablestatement.addBatch();
						System.out.println("Do you want to add more rows [YES/NO]::");
						String option=scanner.next();
						if(option.equalsIgnoreCase("NO")) {
							break;
						}else {
							continue;
						}
					}
					callablestatement.executeBatch();
					int row=callablestatement.getUpdateCount();
					System.out.println("Number of records inserted::"+row);
				}
			}
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		finally {
			scanner.close();
			JdbcUtility.close(connection, callablestatement);
		}
		*/
	}

}
