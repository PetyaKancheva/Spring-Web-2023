package org.softuni.mobilele.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component("currentUser")
@SessionScope
public class CurrentUser {

    private String firstName;
    private String lastName;
    private boolean isLogged;

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser(){

    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public CurrentUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }
    public String getFullName(){
        return getFirstName() +" " + getLastName();
    }

    public void logout() {
        setLogged(false);
        setFirstName(null);
        setLastName(null);
    }
}