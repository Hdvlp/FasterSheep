package com.fastersheep.fastersheep.model;

import java.math.BigInteger;

public enum RoleEnum {
    // FAQ:
    //
    // This is for managing an arbitrary number of roles in RoleEnum file.
    //
    //
    // Values here are unique prime numbers starting from 2.
    // These prime numbers should not repeat for each role.
    //
    //
    // These prime numbers are easy to use.
    // In an opinionated manner, using prime numbers
    // to map the permissions is better than 
    // database table joins because
    // the process is done via BigInteger in this application
    // and is easier to maintain.
    // There is no need to use @ManyToMany / @JoinColumn annotations
    // unless you need to store many roles and
    // you need to work with "table joins."
    //
    //
    // Example:
    //                 for (RoleEnum roleEnum : RoleEnum.values()) {
    //                      if (roles.mod(roleEnum.getValue())
    //                              .equals(BigInteger.ZERO) == true) {
    //                          rolesArr.add("ROLE_" + roleEnum.name());
    //                      }
    //                  }
    //
    //
    // There is also the file called RoleConfig
    // where the hierarchy can be modified / switched off.
    //

    DBADMIN(BigInteger.valueOf(2)),
    CEO(BigInteger.valueOf(3)),
    CFO(BigInteger.valueOf(5)),
    CTO(BigInteger.valueOf(7)),
    ADMIN(BigInteger.valueOf(11)),
    STAFF(BigInteger.valueOf(13)),
    DEPARTMENTA(BigInteger.valueOf(17)),
    DEPARTMENTB(BigInteger.valueOf(19)),
    DEPARTMENTC(BigInteger.valueOf(23)),
    DEPARTMENTD(BigInteger.valueOf(29)),
    DEPARTMENTE(BigInteger.valueOf(31)),
    DEPARTMENTF(BigInteger.valueOf(37)),
    DEPARTMENTG(BigInteger.valueOf(41)),
    DEPARTMENTH(BigInteger.valueOf(43)),
    DEPARTMENTI(BigInteger.valueOf(47)),
    DEPARTMENTJ(BigInteger.valueOf(53)),
    DEPARTMENTK(BigInteger.valueOf(59)),
    DEPARTMENTL(BigInteger.valueOf(61)),
    DEPARTMENTM(BigInteger.valueOf(67)),
    DEPARTMENTN(BigInteger.valueOf(71)),
    DEPARTMENTO(BigInteger.valueOf(73)),
    USER(BigInteger.valueOf(79)),
    GUEST(BigInteger.valueOf(83)),
    NOBODY(BigInteger.valueOf(89));

    public final BigInteger value;

    private RoleEnum(BigInteger value) {
        this.value = value;
    }
    public BigInteger getValue() {
        return value;
    }
}
