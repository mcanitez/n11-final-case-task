#  Java Spring N11 Final Project

## Proje'nin Amacı

* **Kullanıcı Kaydı:** Kullanıcılar isim, soyisim, latitude ve longitude gibi bilgilerle kaydedilebilir.
* **Yorumlar:** Kullanıcılar restoranlar hakkında 1 ile 5 arasında puan ve metin yorumları yapabilirler.
* **Kullanıcı İşlemleri:** Kullanıcılar kaydedilebilir, silinebilir ve bilgileri güncellenebilir.
* **Yorum İşlemleri:** Kullanıcı yorumları kaydedilebilir, silinebilir ve yorum metni ile puan güncellenebilir.
* **Restaurant İşlemleri:** Restaurant kaydedilebilir, silinebilir ve bilgileri güncellenebilir.Bunun yanında tüm restaurant bilgileri getirilebilir.
* **Restoran Önerisi API:** Kullanıcıların konumları ve restoranların puanları göz önünde bulundurularak, 3 adet restoran önerisi sunar.
10 km'den uzak restoranlar önerilmez.
Daha yakın restoranlar, mesafelerine göre değerlendirilir. Restoran puanı %70, mesafe puanı ise %30 ağırlıkta değerlendirilir.


## Proje Yapılandırması ve Bağımlılıkları:

 ### Bağımlılıklar:
 * Spring Initializr kullanarak User & Restaurant adında 2  tane ayrı Spring Boot projesi oluşturdum.
 * İçerisinde ```Spring Web, Spring Data JPA, Lombok, Postgresql driver, Validation, OpenFeign, Mapstruct, Solr``` dependencyler ekleyerek projemi oluşturmaya başladım.
 * Maven dependency management sistemini kullanarak tüm dependencyleri install edebilirsiniz.

### Port Ayarı:
- User Microservice için varsayılan port `8080`'dir.
- Restaurant Microservice için varsayılan port `8089`'dir.

## Kullanım

### **1.Kullanıcı Kaydetme:**

Kullanıcı bilgilerini POST isteği ile /api/v1/users endpointine göndererek kaydedebilirsiniz. İsteğin içeriği JSON formatında olmalıdır ve aşağıdaki örnekteki gibi olmalıdır:

{ <br>
    "firstName": "John", <br>
    "lastName": "Doe", <br>
    "email": "John.doe@gmail.com",<br>
    "phoneNumber": "(496) 355-85",<br>
    "latitude": 41.0082, <br>
    "longitude": 28.9784 <br>
    "gender": "MALE"<br>
} <br>
Başarı durumunda, yeni kullanıcının kimlik bilgileriyle birlikte başarılı bir yanıt alırsınız.

### **2.Restaurant Kaydetme:**
Restoran bilgilerini POST isteği ile /api/v1/restaurants endpointine göndererek kaydedebilirsiniz. İsteğin içeriği JSON formatında olmalıdır ve aşağıdaki örnekteki gibi olmalıdır:

{<br>
    "name": "N11 Restaurant",<br>
    "latitude": 41.0082,<br>
    "longitude": 28.9784,<br>
    "score": "FOUR"<br>
}<br>

Başarı durumunda, yeni restoranın kimlik bilgileriyle birlikte başarılı bir yanıt alırsınız.

### **3.Kullanıcıya Restoran Önerisi Alma:**

Kullanıcının konumunu ve tercihlerini POST isteği ile /api/v1/users/recommend-restaurants endpointine göndererek restoran önerileri alabilirsiniz.
Kullanıcının konumuna yakın aldığı puanlar dahilinde 3 restaurant kullanıcıya sunulur. İsteğin içeriği JSON formatında olmalıdır ve aşağıdaki örnekteki gibi olmalıdır:

{<br>
    "latitude": 41.0082,<br>
    "longitude": 28.9784<br>
}<br>

### Postman ile Test Etme
Bu projeyi test etmek için Postman veya Swagger kullanabilirsiniz. Postman, API test etme işlemlerinizi basit, hızlı ve güvenilir bir şekilde gerçekleştirmenizi sağlar.

