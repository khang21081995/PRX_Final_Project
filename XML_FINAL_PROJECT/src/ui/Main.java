/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author khang
 */
public class Main {

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("db.db_config", Locale.getDefault());
        System.out.println(rb.getString("DB_USERNAME"));
    }
}
