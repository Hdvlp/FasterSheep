package com.fastersheep.fastersheep.model;

public enum RoleEnum {
    // FAQ:
    //
    // This is a work-around for managing a few roles in RoleEnum file.
    // In the real world, you do not need to cater for 
    // more than 30 roles in a small organization.
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
    // the process is simpler and easier to maintain.
    // There is no need to use @ManyToMany / @JoinColumn annotations
    // unless you need to store many roles and
    // you need to work with "table joins."
    //
    //
    // Example:
    //
    // 2*3 = 6 Then, 6 % 2 == 0 means DBADMIN
    //               6 % 3 == 0 means ADMIN
    //
    // 7*11 = 77 Then, 77 % 7 == 0 means USER
    //                 77 % 11 == 0 means GUEST
    //
    // There is also the file called RoleConfig
    // where the hierarchy can be modified / switched off.
    //

    DBADMIN(2),
    CEO(3),
    CFO(5),
    CTO(7),
    ADMIN(11),
    STAFF(13),
    DEPARTMENTA(17),
    DEPARTMENTB(19),
    DEPARTMENTC(23),
    DEPARTMENTD(29),
    DEPARTMENTE(31),
    DEPARTMENTF(37),
    DEPARTMENTG(41),
    DEPARTMENTH(43),
    DEPARTMENTI(47),
    DEPARTMENTJ(53),
    DEPARTMENTK(59),
    DEPARTMENTL(61),
    DEPARTMENTM(67),
    DEPARTMENTN(71),
    DEPARTMENTO(73),
    USER(79),
    GUEST(83),
    NOBODY(89);

    public final long value;

    private RoleEnum(long value) {
        this.value = value;
    }
    public long getValue() {
        return value;
    }
}
