/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import controller.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CafeModel;

import view.MainForm;

public class CafeController {
    
    private Connection connObj;
    
    public void cal(CafeModel tableCalObj){ 
    
       int numOfRow = tableCalObj.getTblOrder().getRowCount();
       double tot = 0.0;
       
       for(int i = 0; i< numOfRow; i++){
           double value = Double.parseDouble(tableCalObj.getTblOrder().getValueAt(i, 3).toString());
           tot = tot+value;
       }
       DecimalFormat df = new DecimalFormat("00.00");
       tableCalObj.getLblTot().setText(df.format(tot));
    }
    
    public void billToDatabas(CafeModel AddObj) 
    {
        String date;
        double amount=0;
        
        DefaultTableModel model = (DefaultTableModel) AddObj.getTblOrder().getModel();
        
        if (model.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Table is Empty");   
        }else{
            
            
            int column = 0; 
            StringBuilder dataBuilder = new StringBuilder();

            for (int row = 0; row < model.getRowCount(); row++) {
                String item = model.getValueAt(row, 0).toString(); 
                String quantity = model.getValueAt(row, 1).toString(); 
                dataBuilder.append(item).append(" : ").append(quantity).append(", ");
            }

            String concatenatedData = dataBuilder.toString().trim();
            if (concatenatedData.endsWith(", ")) {
                concatenatedData = concatenatedData.substring(0, concatenatedData.length() - 2);
            }

            try 
            {
              connObj = DatabaseConnection.getConnection();
              String sql = "INSERT INTO custbills (date,items,amount) VALUES (?,?,?)";
              PreparedStatement st = connObj.prepareStatement(sql);
              st.setString(1, AddObj.getDate());
              st.setString(2, concatenatedData);
              st.setDouble(3, AddObj.getTotal()) ;

              int status = st.executeUpdate();   
                if (status == 1){
                JOptionPane.showMessageDialog(null, "Order Placed!!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                } 
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
        }
    }
    
    
    
    public void addTable(CafeModel addTblObj){ //add items to table
        DefaultTableModel dt = (DefaultTableModel)addTblObj.getTblOrder().getModel();
        DecimalFormat df = new DecimalFormat("00.00");
        double totprice = addTblObj.getAmount() * Double.valueOf(addTblObj.getQty());
        String TotalPrice = df.format(totprice);
        
        
        for (int row = 0; row < addTblObj.getTblOrder().getRowCount(); row++){
            if(addTblObj.getName() == addTblObj.getTblOrder().getValueAt(row, 0)){
                dt.removeRow(addTblObj.getTblOrder().convertColumnIndexToModel(row));
            }
        }
       
        Vector v = new Vector();
        v.add(addTblObj.getName());
        v.add(addTblObj.getQty());
        v.add(addTblObj.getPrice());
        v.add(TotalPrice);
        
        dt.addRow(v);
    }
    
    
    public void inserToDB(CafeModel obj) //insert to user account to DB
    {
        connObj = DatabaseConnection.getConnection();
        try
        { 
            if("".equals(obj.getfName())){
                JOptionPane.showMessageDialog(new JFrame(), "Please enter your First Name", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if("".equals(obj.getlName())){
                JOptionPane.showMessageDialog(new JFrame(), "Please enter your Last Name", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if("".equals(obj.getuName())){
                JOptionPane.showMessageDialog(new JFrame(), "Please enter Username", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if("".equals(obj.getPassword())){
                JOptionPane.showMessageDialog(new JFrame(), "Please enter Password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                PreparedStatement ps=connObj.prepareStatement("INSERT INTO user values (?,?,?,?)");
                ps.setString(1, obj.getuName());
                ps.setString(2, obj.getfName());
                ps.setString(3, obj.getlName());
                ps.setString(4, obj.getPassword());
                
                int status = ps.executeUpdate();            
                if (status == 1){
                    JOptionPane.showMessageDialog(null, "New account has been created successfully!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                } 
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
    }
    
    public void getfromDB(CafeModel Loginobj) //Get user info from DB
    {
        connObj = DatabaseConnection.getConnection();
        try
        {
            Statement st = connObj.createStatement();
            if("".equals(Loginobj.getuName())){
                JOptionPane.showMessageDialog(new JFrame(), "Username is require", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if("".equals(Loginobj.getPassword())){
                JOptionPane.showMessageDialog(new JFrame(), "Password is require", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                
                PreparedStatement ps=connObj.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
               
                ps.setString(1, Loginobj.getuName());
                ps.setString(2, Loginobj.getPassword());
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    MainForm Main = new MainForm();
                    Main.setVisible(true);
                    
                    
                } else {
                    // Invalid credentials
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            }
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void fdItemAdd(CafeModel itemsobj){
         
         connObj = DatabaseConnection.getConnection();
         try
         {
          Statement st = connObj.createStatement();
          ResultSet rs = st.executeQuery(itemsobj.getSqlQuery());
          
          double fdprice = 0.00;
          
          if(rs.next()){
              fdprice = rs.getDouble("fdPrice");
          }
          itemsobj.setPrice(fdprice);
          
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
     
    public void bvItemAdd(CafeModel bvItemsObj){
         
         connObj = DatabaseConnection.getConnection();
         try
         {
          Statement st = connObj.createStatement();
          ResultSet rs = st.executeQuery(bvItemsObj.getSqlQuery());
          
          double bvprice = 0.00;
          
          if(rs.next()){
              bvprice = rs.getDouble("bvPrice");
          }
          bvItemsObj.setPrice(bvprice);
          
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    
    public void getfromDBtoFdTable(CafeModel UpdateObj) 
    {
        connObj = DatabaseConnection.getConnection();
        try
        {
            Statement st = connObj.createStatement();
            
            DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name", "Price"}, 0);

            UpdateObj.getTblFd().setModel(model);
            String sql = "SELECT * FROM foods";

            ResultSet rs = st.executeQuery(sql);
            String i, f, l;
            while(rs.next()){
              i = rs.getString("fdID");
              f = rs.getString("fdName");
              l = rs.getString("fdPrice");

              model.addRow(new Object[]{i, f, l});
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void getfromDBtoBvTable(CafeModel UpdateObj) 
    {
        connObj = DatabaseConnection.getConnection();
        try
        {
            Statement st = connObj.createStatement();
            
            DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name", "Price"}, 0);

            UpdateObj.getTblBvrg().setModel(model);
            String sql = "SELECT * FROM beverage";

            ResultSet rs = st.executeQuery(sql);
            String i, b, p;
            while(rs.next()){
              i = rs.getString("bvID");
              b = rs.getString("bvName");
              p = rs.getString("bvPrice");

              model.addRow(new Object[]{i, b, p});
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void getfromDBtoHistoryTable(CafeModel histryObj) 
    {
        connObj = DatabaseConnection.getConnection();
        try
        {
            Statement st = connObj.createStatement();
            
            DefaultTableModel model = new DefaultTableModel(new String[]{"Bill No.", "Date", "Items", "Bill Amount"}, 0);

            histryObj.getTblHistory().setModel(model);
            String sql = "SELECT * FROM custbills";

            ResultSet rs = st.executeQuery(sql);
            String b, d, i, a;
            while(rs.next()){
              b = rs.getString("billNo");
              d = rs.getString("date");
              i = rs.getString("items");
              a = rs.getString("amount");

              model.addRow(new Object[]{b, d, i, a});
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
   
    
    public void UpdateFdPrice(CafeModel UpdateObj){
        
        int ID;
        double p;
        
        connObj = DatabaseConnection.getConnection();
        try {
            Statement st = connObj.createStatement();
            
               ID = UpdateObj.getFdId();
            
               String sql = "SELECT * FROM foods WHERE fdID="+ID;
               ResultSet rs = st.executeQuery(sql);
               while(rs.next()){
                 p = UpdateObj.getPrice();
                
                 String sql2 = "UPDATE foods SET fdPrice='"+p+"'  WHERE fdID="+ID;
                 st.executeUpdate(sql2); 
               }
        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
            
        } 
        
    }
    
    public void UpdateBvPrice(CafeModel UpdateObj){
        
        int ID;
        double p;
        
        connObj = DatabaseConnection.getConnection();
        try {
            Statement st = connObj.createStatement();
            
               ID = UpdateObj.getBvId();
            
               String sql = "SELECT * FROM beverage WHERE bvID="+ID;
               ResultSet rs = st.executeQuery(sql);
               while(rs.next()){
                 p = UpdateObj.getPrice();
                
                 String sql2 = "UPDATE beverage SET bvPrice='"+p+"'  WHERE bvID="+ID;
                 st.executeUpdate(sql2); 
               }
        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
            
        } 
        
    }
    
    public void setBillNo(CafeModel billNoObj){
        
        
        connObj = DatabaseConnection.getConnection();
        try {
            Statement st = connObj.createStatement();
            
               String sql = "select max(billNo) from custbills";
               ResultSet rs = st.executeQuery(sql);
               int billNo;
               while(rs.next()){
                 billNo = rs.getInt("max(billNo)");
                 billNoObj.setBillNo(billNo);
               }
        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
            
        } 
    }
 
}
