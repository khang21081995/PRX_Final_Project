package entities;

import util.ValidationAndNormalizingText;

/**
 * Created by khang on 7/1/2017.
 */
public class Staff {

    private String username;
    private String password;
    private String name;
    private String dob;
    private String email;
    private String address;
    private String department;
    private String phoneNumber;
    private boolean isManager;

    public Staff() {
        this.username = "";
        this.password = "";
        this.name = "";
        this.dob = "";
        this.email = "";
        this.address = "";
        this.department = "";
        this.phoneNumber = "";
        this.isManager = false;
    }

    public Staff(String username, String password, String name, String dob, String email, String address, String department, String phoneNumber, boolean isManager) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.isManager = isManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) throws Exception {
        String format = "dd/MM/yyyy";
        String temp;
        if ((temp = ValidationAndNormalizingText.validationDate(dob, format)) != format) {
            this.dob = temp;
        } else {
            this.dob = temp;
            throw new Exception("Ngày sinh phải ở định dạng " + temp);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email != "") {
            if ((this.email = ValidationAndNormalizingText.validationEmail(email)) == "") {
                throw new Exception("Email sai định dạng!!!");
            }
        } else {
            this.email = "";
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        this.phoneNumber = ValidationAndNormalizingText.validAndNomalPhoneNumber(phoneNumber, ".");
    }
}
