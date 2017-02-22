
package testproject10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculation {
    public static double totalExpenseCalculator(){
    double totalExpense=0;
    try {
            Connection con=ConnectionClass.createConnection();
            String query="select expense from expenses";
            PreparedStatement pst=con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             totalExpense+=Double.parseDouble(rs.getString(1));
            }
         pst.close();
         con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return totalExpense;
    }
    public static double totalMealCalculator() throws SQLException{
    double totalMeal=0;
    Connection con=ConnectionClass.createConnection();
    PreparedStatement pst=con.prepareStatement("select mealamount from memberinfo");
    ResultSet rs=pst.executeQuery();
    while(rs.next()){
    totalMeal+=Double.parseDouble(rs.getString("mealamount"));
    } 
    pst.close();
    con.close();
    return totalMeal; 
    }
    public static double getTotalIndivisualmeal(String name)throws SQLException{
    double mealNumber=0;
    Connection con=ConnectionClass.createConnection();
    PreparedStatement pst=con.prepareStatement("select mealamount from memberinfo where name=?");
    pst.setString(1, name);
    ResultSet rs=pst.executeQuery();
   if(rs.next()){
    mealNumber=Double.parseDouble(rs.getString("mealamount"));
   }
    pst.close();
   con.close();
    return mealNumber;
    }
    public static double getTotalIndivisualbal(String name)throws SQLException{
    double bal=0;
    Connection con=ConnectionClass.createConnection();
    PreparedStatement pst=con.prepareStatement("select balance from memberinfo where name=?");
    pst.setString(1, name);
    ResultSet rs=pst.executeQuery();
   if(rs.next()){
    bal=Double.parseDouble(rs.getString("balance"));
   }
    pst.close();
   con.close();
    return bal;
    }
    public static String getphone(String name)throws SQLException{
     String phonenumber="";
    Connection con=ConnectionClass.createConnection();
    PreparedStatement pst=con.prepareStatement("select phone from memberinfo where name=?");
    pst.setString(1, name);
    ResultSet rs=pst.executeQuery();
   if(rs.next()){
    phonenumber=(rs.getString("phone"));
   }
   pst.close();
   con.close();
    return phonenumber;
    }
    
}
