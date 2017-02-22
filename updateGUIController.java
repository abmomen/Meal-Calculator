
package testproject10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class updateGUIController {
    @FXML TextField addmealField;
     @FXML TextField addbalField;
     
    public void okupdateHandler() throws SQLException{
        String name=MainViewController.selecteditem;
         String meal=addmealField.getText();
         String bal=addbalField.getText();
         double mealint=Double.parseDouble(meal);
         double balint=Double.parseDouble(bal);
         
         String mealamount=null;
         String balance=null;
         Connection con=ConnectionClass.createConnection();
         PreparedStatement pst=con.prepareStatement("select mealamount,balance from memberinfo where name=?");
         pst.setString(1, name);
         ResultSet rs=pst.executeQuery();
         if(rs.next()){
         mealamount=rs.getString("mealamount");
         balance=rs.getString("balance");
         double mealamountint=Double.parseDouble(mealamount);
         double balanceint=Double.parseDouble(balance);
         mealamountint+=mealint;
         balanceint+=balint;
         String mealstr=""+mealamountint;
         String balstr=""+balanceint;
         PreparedStatement pst1=con.prepareStatement("update memberinfo set mealamount=?,balance=? where name=?");
                 pst1.setString(1, mealstr);
                 pst1.setString(2, balstr);
                 pst1.setString(3, name);
                 pst1.executeUpdate();
                 pst1.close();
         }
         
     pst.close();
    con.close();
         
         MainViewController.updatestage.close();
    }
    
}
