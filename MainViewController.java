package testproject10;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController implements Initializable {
//fields from main view fxml file     
@FXML Label nameText=new Label();
@FXML Label phoneText=new Label();
@FXML Label mealText=new Label();
@FXML Label balText=new Label();
@FXML Label rateText=new Label();
@FXML Label stText=new Label();

  String name="";
  String phone="";
  String meal="";
  String bal="";
  
  //stages used to show new pop up windows
static String selecteditem="";
public static Stage stage=new Stage();
public static Stage addstage=new Stage();
public static Stage updatestage=new Stage();

//Expense Button handler

public void expenseButtonhandler(ActionEvent e) throws IOException  {
 
        FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("expenseGUI.fxml"));
        Parent root=(Parent)fxmlloader.load();
        stage.setTitle("Add Expense Here");
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
}
//this method is handling delete button

public void deleteButtonhandler(ActionEvent ev) throws SQLException{
    Connection con=ConnectionClass.createConnection();
   PreparedStatement pst=con.prepareStatement("delete from memberinfo where name=?");
   pst.setString(1, selecteditem);
   pst.executeUpdate();
   items.remove(selecteditem);
    nameText.setText("No Data");
    phoneText.setText("No Data");
    balText.setText("0.0");
    mealText.setText("0.0");
    rateText.setText("0.0");
    stText.setText("0.0");
   pst.close();
   con.close();

}
//this method is handling update button

public void updateButtonhandler(ActionEvent eve) throws IOException{
        FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("updateGUI.fxml"));
        Parent root=(Parent)fxmlloader.load();     
        Scene scene=new Scene(root);
        updatestage.setTitle("Update Selected Member");
        updatestage.setScene(scene);
        updatestage.show();

}
//this method is handling reset Button
public void resetButtonhandler(ActionEvent even) throws SQLException{
    Connection con=ConnectionClass.createConnection();
    String rmeal="0";
    String rbalance="0";
    PreparedStatement pst=con.prepareStatement("update memberinfo set mealamount=?,balance=?");
    pst.setString(1, rmeal);
    pst.setString(2, rbalance);
    String rexpense="0";
    PreparedStatement pst1=con.prepareStatement("update expenses set expense=?");
    pst1.setString(1, rexpense);
    pst.executeUpdate();
    pst1.executeUpdate();
    nameText.setText("No Data");
    phoneText.setText("No Data");
    balText.setText("0.0");
    mealText.setText("0.0");
    rateText.setText("0.0");
    stText.setText("0.0");
    pst.close();
    pst1.close();

}

//this method is handling add button
public void addButtonhandler(ActionEvent event) throws IOException{
   FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("addGUI.fxml"));
        Parent root=(Parent)fxmlloader.load();
        Scene scene=new Scene(root);
        addstage.setTitle("Add New Member");
        addstage.setScene(scene);
        addstage.show();

}
  
//observable list is being used to keep data in it so that this data can be shown in list view
    public static  ObservableList<String> items =FXCollections.observableArrayList ();
   @FXML ListView<String> listView = new ListView<>(items);   
    //this is initializer
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // setting all items from list in list view
     listView.setItems(items);
     //adding click listener to the list view so that we can manipulate our list later
     listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // Your action her
        selecteditem=newValue;
        double meal=0;
        double taka=0;
        String phone="";
        double mealrate=0;
        double status=0;
        try {
            
             mealrate=(Calculation.totalExpenseCalculator()/Calculation.totalMealCalculator());
             meal=Calculation.getTotalIndivisualmeal(newValue);
             taka=Calculation.getTotalIndivisualbal(newValue);
             status=taka-(mealrate*meal);
             phone=Calculation.getphone(newValue);
             mealrate =Double.parseDouble(new DecimalFormat("##.###").format(mealrate));
             status =Double.parseDouble(new DecimalFormat("##.###").format(status));
           
        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         nameText.setText(newValue);
         mealText.setText(""+meal);
         balText.setText(""+taka);
         phoneText.setText(phone);
         rateText.setText(""+mealrate);
         stText.setText(""+status);
        
    }
       });
   
   
 //when app starts this method pic all the name from database and put it in list
    }
  public static void itemSetter() throws SQLException{
        Connection con=ConnectionClass.createConnection();
        String query="select name from memberinfo";
        PreparedStatement pst=con.prepareStatement(query);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
        items.add(rs.getString(1));
        }
        pst.close();
        con.close();
  
  } 

  
  
  
}
