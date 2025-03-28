
# note: 27 March 2025
#
# Example: On Windows, using PowerShell,
#
# You need to assign a different username and change your password.
$env:fasterSheepDebugging="false"; $env:allowUserToRegisterAdmin="false"; $env:useRoleHierarchy="true"; $env:fasterSheepDataSourceDriverClassName="org.mariadb.jdbc.Driver"; $env:fasterSheepDataSourceUrl="jdbc:mysql://[::1]:3306/fastersheep";$env:fasterSheepDataSourceUsername="randomfastersheeper"; $env:fasterSheepDataSourcePassword="xFYvmtj2qK7AnG14Nb7Oft0Xd4ca05hHv9p55gUkArf520CeuHk3i24O9dR0uPS"; ./mvnw spring-boot:run;


