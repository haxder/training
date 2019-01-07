# traning
trainingJSF

# JSF là tập hợp các trang JSP
    - trang chưa khởi tạo thì sẽ được tạo , nếu đã được tạo thì sẽ lưu tại máy khách 

## Life cycale JSF có 6 phase :
 - 1.`Restore view phase`                            
    xây dựng componet tree , khi có request mới . hoặc khôi phục nếu đã được tạo ở request trước đó.
 - 2.`Apply request values phase; process events`    
    các thành phần componet tree được update giá trị được gửi từ request (ví dụ  từ form) 
 - 3`.Process validations phase; process events `    
    xử lý validate các thành phần đăng ký trên cây thành phần , nếu có lỗi sảy ra sẽ được lưu vào hàng đợi chờ Render
 - 4.`Update model values phase; process events  `   
    cập nhật các thuộc tính UI vào thuộc tính của class trên server-site
 - 5.`Invoke application phase; process events`      
    xử lý sự kiện
 - 6.`Render response phase `                        
    render kết quả

# JSF - Managed Beans:
JSF 1.2 thì không thể khai báo qua `@anotation` mà phải khai báo qua  `facesconfig.xml`  (JSF 2.2 thì có thể khai báo được)

## @ManagedBean(name = "helloWorld", eager = true)

`eager = true` : bean  sẽ được tạo trước kho gọi lần đầu tiên , false thì : khi yêu cầu mới được gọi

## Scope Annotations 

dùng để đăng ký phạm vi bean có thể được sử dụng  (nếu không khai báo gì thì defaul  : request)
- `@RequestScoped`  : bean tồn tại trong chu kỳ:  request-response
- `@NoneScoped`     :  ????
- `@ViewScoped`     : tồn tại khi người dùng còn view JSF trên window/tab , tạo khi có yêu cầu request , hủy khi quay lại 1 view khác
- `@SessionScoped`  : tồn tại trên session
- `@ApplicationScoped` : tồn tại khi ứng dụng còn ứng dụng được mở  và    eager = true 
- `@CustomScoped`    : ??????

## @ManagedProperty Annotation   : inject 1 thuộc tính từ nơi khác vào bean
example  : https://www.tutorialspoint.com/jsf/jsf_managed_beans.htm


# JSF - Page Navigation
- dùng để điều hướng khi có click button hoặc link
- có thể config  = file : `faces-config.xml`

## JSF - Basic Tags
```
<html 
   xmlns = "http://www.w3.org/1999/xhtml" 
   xmlns:h = "http://java.sun.com/jsf/html" 
>
```

## JSF - Facelets Tags
```
<html  
   xmlns = "http://www.w3.org/1999/xhtml"  
   xmlns:ui = "http://java.sun.com/jsf/facelets"  
>
```
- 1.`template` Tags
    - `ui:insert`
    - `ui:define` : dùng để khia báo ghi đè lại dổi đối tượng có trong template
    - `ui:include`
    - `ui:composition` :  load template
- 2.`ui:param` Tag : dùng để khai báo truyền tham số cho 1 đối tượng trong JSF
- 3.`Custom` Tag : giúp người dùng tự định nghĩa thư viện riêng
    - `facelet-taglib`
    - `namespace`
    - `tag`
    - `tag-name`
    - `source`
- 4.`ui:remove` Tag : dùng để ngăn thông báo của server hiển thị ở phía client  (nếu dung comment thông thường thì code vẫn hiện thị trong cửa sổ debug)

## JSF - Convertor Tags , Validator Tags
```
<html 
   xmlns = "http://www.w3.org/1999/xhtml" 
   xmlns:f = "http://java.sun.com/jsf/core"  
>
```
- Convertor Tags 
    - `f:convertNumber`
    - `f:convertDateTime`
    - `Custom Convertor`
- Validator Tags
    - `f:validateLength`
    - `f:validateLongRange`
    - `f:validateDoubleRange`
    - `f:validateRegex`
    - `Custom Validator`

## Display DataTable  : hỗ trợ generate table
tham chiếu : https://www.tutorialspoint.com/jsf/jsf_data_tables.htm
## Composite Components  : dùng để tự tạo componet JSF riêng cho mình
tham chiếu  : https://www.tutorialspoint.com/jsf/jsf_composite_components.htm




# **Note : các lưu chú khác khi làm việc**


# cài đặt  1 repo từ file jar có sẵn
```
mvn install:install-file -Dfile=cupertino-1.0.10.jar -DgroupId=org.primefaces.themes -DartifactId=cupertino -Dversion=1.0.10 -Dpackaging=jar
```

# Hibernate
## ***các trạng thái của entity***

<img src="https://www.baeldung.com/wp-content/uploads/2016/07/2016-07-11_13-38-11-1024x551.png">

trong 1 ứng dụng ,  các đối tượng trong 1 session sẽ có 3 trạng thái chính
- `transient` : chỉ là đối tượng được tạo ra , không có liên kết với database  
  ->  dùng trong trường hợp thêm mới
- `persistent` : đối tượng đang có liên kết với database  
(khi tạo liên kết xong, sẽ rơi vào trạng thái offline trên map , sẽ không theo rõi được thay đổi thật ở database , sau khi kết thúc phiên , toàn bộ thay đổi sẽ được update vào DB)
- `detached` : đối tượng đã trước kia nằm trong trạng thái `persistent` , khi  session bị clean , cloese sẽ vào trạng thái này 
(có thể kết nối lại trạng thái `persistent`)

## method
- `session.persist(person);` dùng để khi muốn thêm 1 object vào database
  - là phương thức void , ko có kết quả trả về
  - sẽ không thể `persist` được 1 đối tượng đang nằm trong vùng  `detached`
- `session.save(person);` bản chát giống với `persist()` tuy nhiên sẽ thêm được 1 đối tượng ở vùng `detached` và trả ra 1 id mới.
- `session.merge(person)` : sẽ cập nhật đối tượng `persist` thông qua id.
- `session.update(person);`: chỉ update được đối tường vùng `detached`
-   `session.saveOrUpdate(person);` :  (1 phương thức vạn năng :yum: )
```plantuml
@startuml
start
end
@enduml
```
