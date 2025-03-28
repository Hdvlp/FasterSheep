package com.fastersheep.fastersheep.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleConfig {


    @Value("${useRoleHierarchy}")
    private String useRoleHierarchy;

    // reads the environment property of useRoleHierarchy
    // havingValue = "true" means if use, this Bean is on.
    //                            if false, this Bean is off.
    // matchIfMissing = false means if not set, this Bean is off by default.
    //                        By default, RoleHierarchy is not used.
    //                        Permissions can be set independently,
    //                        i.e. ADMIN permissions are separately set.
    //                        ADMIN permissions do not automatically include USER permissions.
        
    @Bean
    @ConditionalOnProperty(name = "useRoleHierarchy", havingValue = "true", matchIfMissing = false)
    public RoleHierarchy roleHierarchy() {

      // FAQ:
      // 
      // Example:
      //
      // ROLE_USER > ROLE_GUEST means that if ROLE_GUEST is allowed to do A,
      // ROLE_USER is automatically allowed as well.
      // ROLE_STAFF > ROLE_USER means that if ROLE_USER is allowed to do A,
      // ROLE_STAFF is automatically allowed as well.

      // If the hierarchy of roles is not what you want,
      // for example, you want USER to be set independently.
      // You also want STAFF to be set independently.
      // Then, pass an enviroment variable to turn off
      // the hierarchy.
      // 
      // In Windows, using PowerShell
      // $env:useRoleHierarchy="false"; 
      // This line above turns off the hierarchy.
      //
      // See custom_power_shell.ps1.
      // 


      RoleHierarchyImpl roleHierarchyImpl = RoleHierarchyImpl.fromHierarchy("""
				  ROLE_DBADMIN > ROLE_CEO
				  ROLE_CEO > ROLE_CFO
				  ROLE_CFO > ROLE_CTO
				  ROLE_CTO > ROLE_ADMIN
				  ROLE_ADMIN > ROLE_STAFF
				  ROLE_STAFF > ROLE_DEPARTMENTA
				  ROLE_DEPARTMENTA > ROLE_DEPARTMENTB
				  ROLE_DEPARTMENTB > ROLE_DEPARTMENTC
				  ROLE_DEPARTMENTC > ROLE_DEPARTMENTD
				  ROLE_DEPARTMENTD > ROLE_DEPARTMENTE
				  ROLE_DEPARTMENTE > ROLE_DEPARTMENTF
				  ROLE_DEPARTMENTF > ROLE_DEPARTMENTG
				  ROLE_DEPARTMENTG > ROLE_DEPARTMENTH
				  ROLE_DEPARTMENTH > ROLE_DEPARTMENTI
				  ROLE_DEPARTMENTI > ROLE_DEPARTMENTJ
				  ROLE_DEPARTMENTJ > ROLE_DEPARTMENTK
				  ROLE_DEPARTMENTK > ROLE_DEPARTMENTL
				  ROLE_DEPARTMENTL > ROLE_DEPARTMENTM
				  ROLE_DEPARTMENTM > ROLE_DEPARTMENTN
				  ROLE_DEPARTMENTN > ROLE_DEPARTMENTO
				  ROLE_DEPARTMENTO > ROLE_USER
				  ROLE_USER > ROLE_GUEST
				  ROLE_GUEST > ROLE_NOBODY
				""");
      return roleHierarchyImpl;
    }
}
