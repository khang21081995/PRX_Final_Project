/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.sql.ResultSet;

/**
 *
 * @author phamquangkhang
 */
public interface ManageAction {

    public boolean create(Object o);

    public boolean update(Object o);

    public ResultSet select(Object o);
}
