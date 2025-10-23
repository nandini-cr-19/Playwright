package com.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    // Selectors
    private final String usernameSelector = "input[placeholder='Username']";
    private final String passwordSelector = "input[placeholder='Password']";
    private final String loginButtonSelector = "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void setName(String user) {
        page.locator(usernameSelector).fill(user);
    }

    public void setPassword(String passw) {
        page.locator(passwordSelector).fill(passw);
    }

    public void loginButton() {
        page.locator(loginButtonSelector).click();
    }

    public void loginCre(String username, String password) {
        setName(username);
        setPassword(password);
        loginButton();
    }
}
