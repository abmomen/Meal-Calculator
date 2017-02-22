
package testproject10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class expenseGUIController {

@FXML TextField expenseField;

public void okButtonhandler() throws SQLException{
   String expense=expenseField.getText();
   Connection con=ConnectionClass.createConnection();
   PreparedStatement pst=con.prepareStatement("insert into expenses(expense) values(?)");
   pst.setString(1, expense);
   pst.executeUpdate();
   pst.close();
   con.close();
   MainViewController.stage.close();

}


}
