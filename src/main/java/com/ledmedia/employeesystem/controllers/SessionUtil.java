package com.ledmedia.employeesystem.controllers;


import com.ledmedia.employeesystem.models.Staff;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static boolean hasSession(HttpSession session) {
        return ((Staff) session.getAttribute("userLoggedIn")) != null;
    }
}