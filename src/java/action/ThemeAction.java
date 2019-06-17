/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.Theme;
import Model.ThemeDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class ThemeAction extends ActionSupport{
    public Theme theme = new Theme();
    public ArrayList<Theme> themes = new ArrayList<>();
    
    public String addTheme() throws SQLException {
        ThemeDAO themeDAO = new ThemeDAO();
        System.out.println("Theme : " + theme.getSubject() + "\n");
        themeDAO.create(getTheme());
        setThemes(themeDAO.getAll());
        return "success";
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }
    

}
