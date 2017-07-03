/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import db.DataAccessObject;
import entities.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamquangkhang
 */
public class CustomerManagement implements ManageAction {

    private DataAccessObject dao;
    private final String table_name = "Customer";

    public CustomerManagement() throws Exception {
        this.dao = new DataAccessObject();
    }

    @Override
    public boolean create(Object o) throws SQLException {
        String sqlInsert = "INSERT INTO " + table_name
                + " (name,passport,dob,phoneNumber,email, score,accountType,gender,cardID) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";

        Customer c = o instanceof Customer ? (Customer) o : null;
        if (c == null) {
            return false;
        }
        return dao.executeSQLwithParams(sqlInsert, DataAccessObject.MODE_UPDATE.SMALL_UPDATE
        ,c.getName(),c.getPassport(),c.getDob(),c.getPhoneNumber(),c.getEmail(),c.getScore()+""
                ,c.getAccountType(),c.getGender(),c.getCardID());
    }

    @Override
    public boolean update(Object o) {
        return true;

    }

    @Override
    public ResultSet select(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main(String[] args) {
        try {
            Customer c = new Customer();
            c.setAccountType("Premium");
            c.setCardID("0000000001");
            c.setDob("21/08/1995");
            c.setEmail("KhangPQ.vn@gmail.com");
            c.setGender("Nam");
            c.setName("KhangPQ");
            c.setPassport("001095002587");
            c.setPhoneNumber("0981604050");
            c.setScore(0);
            new CustomerManagement().create(c);
            System.out.println("OK");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
