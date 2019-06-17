/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Junior
 */
public class User {
    protected int id;
    protected String lastname;
    protected String firstname;
    protected String email;
    protected String gender="male";
    protected String password="default";
    protected String tel;
    protected Date date_creat;
    public String status="inactif";
    public boolean is_admin; 
    protected String company;
    
    public User() {
    }
    
    public User(String lastname, String firstname, String email, String password, String tel, String company, Date date_creat, boolean is_admin, String status, String gender) {
        User.this.lastname = lastname;
        User.this.firstname = firstname;
        User.this.email = email;
        User.this.password = password;
        User.this.tel = tel;
        User.this.company = company;
        User.this.date_creat = date_creat;
        User.this.is_admin = is_admin;
        User.this.status = status;
        User.this.gender = gender;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDate_creat() {
        return date_creat;
    }

    public void setDate_creat(Date date_creat) {
        this.date_creat = date_creat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean is_admin() {
        return is_admin;
    }

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
       
    @Override
    public String toString() {
        return "User{" + "lastname=" + lastname + ", firstname=" + firstname + ", email=" + email +  ", gender=" + gender + ", password=" + password + ", tel=" + tel + ", status=" + status + ", date de création=" + date_creat + ", admin=" + is_admin + ", company=" + company + ')';
    }
}
