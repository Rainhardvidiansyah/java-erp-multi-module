package com.mini.erp.user.pojo;

public class AuthorityInfo {


    private String authority;


    public AuthorityInfo() {}

    public AuthorityInfo(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


    @Override
    public String toString() {
        return "RolesInfo{" +
                ", roleName='" + authority + '\'' +
                '}';
    }
}
