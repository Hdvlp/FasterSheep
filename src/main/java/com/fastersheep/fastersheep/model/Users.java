package com.fastersheep.fastersheep.model;

import java.math.BigInteger;
import java.util.Set;

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

    @Column(name = "roles", columnDefinition = "text")
    private String roles = RoleEnum.NOBODY.getValue().toString(10);

    public Users(){
        this.roles = RoleEnum.NOBODY.getValue().toString(10);
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
    public void setRoles(Set<RoleEnum> roleList) {
        BigInteger tmpRoles = new BigInteger(this.roles);
        for (RoleEnum roleEnum : roleList){
            if (tmpRoles.mod(roleEnum.getValue())
                    .equals(BigInteger.ZERO) == false && 
                tmpRoles.equals(RoleEnum.NOBODY.getValue()) == true){
                tmpRoles = roleEnum.getValue();
            }
            if (tmpRoles.mod(roleEnum.getValue())
                    .equals(BigInteger.ZERO) == false && 
                tmpRoles.equals(RoleEnum.NOBODY.getValue()) == false){
                tmpRoles = tmpRoles.multiply(roleEnum.getValue());
            }
        }
        this.roles = tmpRoles.toString(10);
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

    public String getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return this.id + '|' + this.username + '|' + this.password + '|' + this.roles ;
    }

}
