/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author krish
 */
public class CafeModel {
    
    private String fName;
    private String lName;
    private String uName;
    private String password;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JTable getTblOrder() {
        return tblOrder;
    }

    public void setTblOrder(JTable tblOrder) {
        this.tblOrder = tblOrder;
    }

    public JLabel getLblTot() {
        return lblTot;
    }

    public void setLblTot(JLabel lblTot) {
        this.lblTot = lblTot;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
            
    private String sqlQuery;
    private int count;
    
    private JTable tblOrder;
    private JLabel lblTot;        
    private String name;
    private int qty;
    private double price;
    private double amount;

    public int getFdId() {
        return fdId;
    }

    public void setFdId(int fdId) {
        this.fdId = fdId;
    }
    private int fdId;

    public JTable getTblFd() {
        return tblFd;
    }

    public void setTblFd(JTable tblFd) {
        this.tblFd = tblFd;
    }

    public JTable getTblBvrg() {
        return tblBvrg;
    }

    public void setTblBvrg(JTable tblBvrg) {
        this.tblBvrg = tblBvrg;
    }
    
    private JTable tblFd;
    private JTable tblBvrg;

    public int getBvId() {
        return bvId;
    }

    public void setBvId(int bvId) {
        this.bvId = bvId;
    }
    private int bvId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    private String date;
    private double total;

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }
    private int billNo;

    public JTable getTblHistory() {
        return tblHistory;
    }

    public void setTblHistory(JTable tblHistory) {
        this.tblHistory = tblHistory;
    }
    
    private JTable tblHistory;
}

