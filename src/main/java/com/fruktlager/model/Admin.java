package com.fruktlager.model;

public class Admin extends Member {
    public Admin(Integer number, Address address, MemberType memberType, String email, String password, Area area, String username) {
        super(number, address, memberType, email, password, area, username);

    }
}
