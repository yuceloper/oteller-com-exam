# OtellerCom Mikroservis Uygulaması

Bu proje, Spring Boot 3, Java 17, PostgreSQL, Kafka, Docker, JWT ve Spring Cloud Gateway kullanılarak geliştirilmiş bir mikroservis mimarili otel rezervasyon sistemidir. Sistem; otel yönetimi, oda işlemleri, rezervasyon işlemleri ve kullanıcı kimlik doğrulama mekanizmalarını içermektedir.

## Proje Yapısı

- **API Gateway (`api-gateway`)**
- **Hotel Service (`hotel-service`)**
- **Auth Service (`auth-service`)**
- **Notification Service**

## Teknolojiler

- Java 17
- Docker ve Docker Compose
- PostgreSQL
- Maven
- IntelliJ IDEA 

## Projeyi Başlatma

### 1. Docker Servislerini Başlatın

```bash
docker-compose up --build

```
# 🧪 OtellerCom API Test Rehberi (IntelliJ IDEA `.http` Dosyası Kullanımı)

Bu rehber, IntelliJ IDEA içinde yer alan `.http` dosyası yardımıyla OtellerCom mikroservislerini nasıl test edebileceğinizi adım adım anlatmaktadır.

# ⚠️ ÖNEMLİ UYARI

**Zaman kısıtlı olduğu için ve testlerin sürdürülebilirliğini sağlamak adına validasyonlar basit tutulmuştur.**

---

## 🔧 Gereksinimler

- IntelliJ IDEA 
- OtellerCom mikroservislerinin çalışıyor olması

## 📁 gateway-service-http.http Dosyası Nedir?

`gateway-service-http.http` dosyası, IntelliJ IDEA içerisinde doğrudan REST API isteklerini test etmenizi sağlar. Postman'e alternatif olarak bunu tercih ettim, yine de collection paylaşacağım.

## ✅ Adım Adım Test Süreci

### 1. Kullanıcı Kaydı

```http
POST http://localhost:8080/auth-service/auth/register
Content-Type: application/json

{
  "username": "yuceloper",
  "password": "OtellerCom1234"
}
```

### 2. Giriş Yap ve Token Al

```http
POST http://localhost:8080/auth-service/auth/login
Content-Type: application/json

{
  "username": "yuceloper",
  "password": "OtellerCom1234"
}
```

# Token şu formatta olmalı
```http
Authorization: Bearer <JWT_TOKENIN_BURAYA_YAPIŞTIR>
```

# Otel Listeleme
``` http
GET http://localhost:8080/hotel-service/api/hotels
Authorization: Bearer <JWT_TOKEN>

```


# Yeni Otel Ekleme
``` http
POST http://localhost:8080/hotel-service/api/hotels
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>

{
  "name": "OtellerCom Otel 1",
  "address": "oteller com yanı siyah sokak pembe otel",
  "starRating": 1.0
}

```

# Oda Ekleme
``` http
POST http://localhost:8080/hotel-service/api/rooms
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>

{
  "hotelId": 1,
  "roomNumber": "3",
  "capacity": 1,
  "pricePerNight": 100.0
}
```

# Oda Listeleme
``` http
GET http://localhost:8080/hotel-service/api/rooms
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>

{
  "hotelId": 1
}
```

# Yeni Rezervasyon Oluşturma
``` http
POST http://localhost:8080/hotel-service/api/reservations
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>

{
  "hotelId": 1,
  "roomId": 4,
  "guestName": "yuceloper",
  "checkInDate": "2025-05-03",
  "checkOutDate": "2025-05-04"
}
```

# Rezervasyonları Listeleme
``` http
GET http://localhost:8080/hotel-service/api/reservations
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>

{
  "hotelId": 1,
  "roomId": 4,
  "guestName": "yuceloper",
  "checkInDate": "2025-05-03",
  "checkOutDate": "2025-05-04"
}
```
