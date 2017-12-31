package com.fruktlager.model;

public class Member {

    private Integer number;
    private Address address;
    private MemberType memberType;
    private String email;
    private String password;
    private Area area;
    private String username;

    public Member(Address address, MemberType memberType, Area area) {
        this.address = address;
        this.memberType = memberType;
        this.area = area;
    }

    public Member(Integer number, Address address, MemberType memberType, String email, String password, Area area, String username) {
        this.number = number;
        this.address = address;
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.area = area;
        this.username = username;
    }

    public Member(Address address, MemberType memberType, String email, String password, Area area, String username) {
        this.address = address;
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.area = area;
        this.username = username;
    }

    public void editMemberData() {

    }

    public void getMemeberData() {

    }

    public MemberType getMemberType() {
        return memberType;
    }

    public Integer getNumber() {
        return number;
    }

    public Address getAddress() {
        return address;
    }

    public Area getArea() {
        return area;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "number=" + number +
                ", address=" + address +
                ", memberType=" + memberType +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", area=" + area +
                ", username='" + username + '\'' +
                '}';
    }
}
