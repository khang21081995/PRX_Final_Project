/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import db.DataAccessObject;
import entities.Customer;
import java.sql.ResultSet;

/**
 *
 * @author phamquangkhang
 */
public class CustomerManagement implements ManageAction {

    private DataAccessObject dao;
    private final String table_name = "";

    public CustomerManagement() throws Exception {
        this.dao = new DataAccessObject();
    }

    @Override
    public boolean create(Object o) {
        String sqlInsert = "INSERT INTO " + table_name
                + " (name,passport,dob,phoneNumber,email, score,accountType,gender,cardID) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";
        Customer c = o instanceof Customer ? (Customer) o : null;
        if (c == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Object o) {
        return true;

    }

    @Override
    public ResultSet select(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
