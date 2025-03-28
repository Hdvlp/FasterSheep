package com.fastersheep.fastersheep.model;

import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @Column(name = "roles")
    private BigInteger roles = RoleEnum.NOBODY.getValue();

    public Users(){
        this.roles = RoleEnum.NOBODY.getValue();
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(List<RoleEnum> roleList) {
        BigInteger tmpRoles = this.roles;
        for (RoleEnum roleEnum : roleList){
            if (tmpRoles.mod(roleEnum.getValue()) != BigInteger.valueOf(0) && 
                tmpRoles == RoleEnum.NOBODY.getValue()){
                tmpRoles = roleEnum.getValue();
            }
            if (tmpRoles.mod(roleEnum.getValue()) != BigInteger.valueOf(0) && 
                tmpRoles != RoleEnum.NOBODY.getValue()){
                tmpRoles = tmpRoles.multiply(roleEnum.getValue());
            }
        }
        this.roles = tmpRoles;
    }
    
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public BigInteger getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return this.id + '|' + this.username + '|' + this.password + '|' + this.roles ;
    }

}
