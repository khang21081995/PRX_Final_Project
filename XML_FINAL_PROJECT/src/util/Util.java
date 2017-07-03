/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author phamquangkhang
 */
public class Util {

    public static String getOsPath() {
        String windowsPath = "\\";
        String nonWindowsPath = "/";
        return System.getProperty("os.name").startsWith("Windows") ? windowsPath : nonWindowsPath;
    }

}
