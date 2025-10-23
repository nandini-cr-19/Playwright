package com.tests;
 
import com.pages.AdminPage;

import com.pages.DeleteUserPage;

import com.pages.EditUserPage;

import com.pages.LoginPage;

import com.pages.SearchUserPage;
import com.utilities.TestBase;

import org.testng.annotations.Test;
 
public class LoginTest extends TestBase {
 
    @Test

    public void testSuccessfulLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.loginCre(userName, password);
 
        AdminPage adminPage = new AdminPage(page);
 
        String employeeName = "Thomas Kutty Benny";

        String newUsername = "Qwerqwe";

        String newPassword = "Jellyfish@123";

        String confirmPassword = "Jellyfish@123";

        String userRole = "Admin";

        String status = "Enabled";
 
        adminPage.admininput(employeeName, newUsername, newPassword, confirmPassword, userRole, status);

        SearchUserPage searchPage = new SearchUserPage(page);

        searchPage.searchUser(newUsername, "Admin", "Enabled");

        EditUserPage editUserPage = new EditUserPage(page);

        editUserPage.editUserStatus("Disabled");
 
        DeleteUserPage deleteUserPage = new DeleteUserPage(page);

        deleteUserPage.deleteUser();
 
        
 
 
    }

}

 