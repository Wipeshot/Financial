package com.financial.controller;

import com.financial.object.User;

public class UserController {

        private static User user;

        public static User getUser() {
                return user;
        }

        public static void setUser(User user) {
                UserController.user = user;
        }



}
