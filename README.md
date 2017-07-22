# 2017-C2D
Assignment for the 2017 edition of the "Web Development and the Semantic Web" course, by Allan Araujo Silva and Gabriel Correa De Macena

### How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [Eclipse Neon (version 4.6.x)](http://www.eclipse.org/);

2. Install [WildFly 10.x](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `c2d` and a user called `c2d` with password `c2d` and full access to the homonymous database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

```XML
 <datasource jta="true" jndi-name="java:/jboss/datasources/C2D" pool-name="C2DPool" enabled="true" use-java-context="true">
	<connection-url>jdbc:mysql://localhost:3306/c2d</connection-url>
	<driver>mysql</driver>
	<security>
	    <user-name>c2d</user-name>
	    <password>c2d</password>
	</security>
 </datasource>
```

6. Configure the security domain in WildFly's `standalone.xml` file:
```XML
<security-domain name="c2d">
    <authentication>
          <login-module code="Database" flag="required">
              <module-option name="dsJndiName" value="java:jboss/datasources/C2D"/>
              <module-option name="principalsQuery" value="select password from Academic where email=?"/>
              <module-option name="rolesQuery" value="select r.name, 'Roles' from Role r inner join Academic_Role ar on r.id = ar.roles_id inner join Academic a on ar.Academic_id = a.id where email=?"/>
              <module-option name="hashAlgorithm" value="MD5"/>
              <module-option name="hashEncoding" value="base64"/>
              <module-option name="hashUserPassword" value="true"/>
          </login-module>
    </authentication>
</security-domain>
```
7. In Eclipse, use _File_ > _Import_ > _Git_ > _Projects from Git_ to import the Eclipse project existing in this repository;

8. You might have to adjust the server settings in the imported project: right-click the _C2D_ project and select _Properties_. In the _Server_ section, select the _WildFly 10.x_ server. In the _Targeted Runtimes_ section, select the _WildFly 10.x Runtime_;

9. At the end, it is possible that C2D is not able to retrieve PrimeFaces dependencies because of an exception like: `sun.security.validator.ValidatorException: PKIX path building failed`. If that happens, read [this](https://github.com/dwws-ufes/Marvin#maven-primefaces-and-ssl-certificates).

## Authors

* **Allan Araujo Silva**
* **Gabriel Correa de Macena**
