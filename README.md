# Traning mybatis - build to weblogic

hiện tại chỉ làm hoàn chỉnh mybatis with mapper Anotation
- `mybatis-dynamic-sql` chỉ viết 1 ví dụ cách dùng MovieSQLDynamicMapper  (tuy nhiên làm thằng dynamic này khá rắc rối , nên có lẽ KH ko dùng đén)

# Note Weblogic

- để có thể deploy 1 app trên weblogi : cần nhất thiết phải có `weblogic.xml`
example :
```
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-web-app
	xmlns:wls="http://xmlns.oracle.com/weblogic/weblogic-web-app"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd
http://xmlns.oracle.com/weblogic/weblogic-web-app http://xmlns.oracle.com/weblogic/weblogic-web-app/1.4/weblogic-web-app.xsd">

	<description>training</description>
	<weblogic-version>12.2.1.3</weblogic-version>
<!-- 	<container-descriptor>
		<prefer-web-inf-classes>false</prefer-web-inf-classes>
	</container-descriptor> -->
	<context-root>training</context-root>
</weblogic-web-app>
```
- weblogic version 12.2.xx trở lên mới bắt đầu support nhận thư viện JSF 2.2 

## cách khai báo 1 địa chỉ ngoài web

sử dụng `virtual-directory-mapping`
```
	<virtual-directory-mapping>
	     <local-path>C:/trainingExtensionSource/views/</local-path>
	     <url-pattern>*.xhtml</url-pattern>
	</virtual-directory-mapping>
```
tesssst