
package testproject10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class addGUIController {
    @FXML TextField nameField;
    @FXML TextField phoneFeild;
    @FXML TextField mealField;
    @FXML TextField balField;
  
    public void okButtonHandler() throws SQLException{
        String name=nameField.getText();
        String phone=phoneFeild.getText();
        String meal=mealField.getText();
        String bal=balField.getText();
        
    Connection con=ConnectionClass.createConnection();
    PreparedStatement pst=con.prepareStatement("insert into memberinfo(name,phone,mealamount,balance) values(?,?,?,?)");
    pst.setString(1, name);
    pst.setString(2, phone);
    pst.setString(3, meal);
    pst.setString(4, bal);
    pst.executeUpdate();
    MainViewController.items.add(name);
    MainViewController.addstage.close();
    pst.close();
    con.close();
    MainViewController.addstage.close();
    }
}
