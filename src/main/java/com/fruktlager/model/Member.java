package com.fruktlager.members;

public class Member {

    private int number;
    private String firstName;
    private String lastName;
    private Address address;
    private String phone;
    private MemberType memberType;
    private String email;
    private String password;
    private Area area;
    private String username;

    public Member(String firstName, String lastName, Address address, String phone, MemberType memberType, Area area) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.memberType = memberType;
        this.area = area;
    }

    public Member(int number, String firstName, String lastName, Address address, String phone, MemberType memberType, String email, String password, Area area, String username) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.area = area;
        this.username = username;
    }


}
