/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.TestDAO;
import Model.User;
import Model.UserDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Junior
 */
public class LoginUser extends ActionSupport implements SessionAware{
    private int test_id;
    private String userEmail;
    private User user;
    Map<String, Object> map;

    @Override
    public String execute() throws Exception {
        Map<String, Object> currentSession = ActionContext.getContext().getSession();
        System.out.println("session : " + currentSession.get("userMail") + "\n");
        setUserEmail(currentSession.get("userMail").toString());
        /*if (test_id != 0) {
            UserDAO userdao = new UserDAO();
            TestDAO testdao = new TestDAO();
            setUserEmail(testdao.find(test_id).get().getUser_id());
            setUser(userdao.find(userEmail).get());
            return "success";
        } else {
            UserDAO userdao = new UserDAO();
            setUser(userdao.find(userEmail).get());
            return "success";
        }*/
        UserDAO userdao = new UserDAO();
        setUser(userdao.find(userEmail).get());
        return "success";

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.map = map;
    }
    
    
    
}
