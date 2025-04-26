# 🎓 EduManage

Aplikacija za upravljanje studentskim upisima, ocjenama i kolegijima, izrađena koristeći **Spring Boot** i **Docker**.

### 🔗 Live Demo
👉 [EduManage - Pogledaj uživo](http://130.162.235.231:8080/students)

---

## 📋 Opis

**EduManage** omogućuje:
- Pregled, unos, uređivanje i brisanje studenata.
- Upravljanje kolegijima i profesorima.
- Upravljanje upisima studenata na kolegije i unos ocjena.
- Jednostavno sučelje za administraciju obrazovnih podataka.

---

## 🛠️ Tehnologije korištene

- **Spring Boot** (backend razvoj)
- **Spring Data JPA** (rad s bazom podataka)
- **Thymeleaf** (renderiranje frontend dijela)
- **MySQL** (baza podataka)
- **Docker & Docker Compose** (orchestration i deployment)
- **Spring Security** (osnovna sigurnost)
- **Bootstrap** (responsivni dizajn)

---

## 🚀 Pokretanje projekta lokalno

### Opcija 1: Pokretanje pomoću Dockera

1. Kloniraj repozitorij:
   ```bash
   git clone https://github.com/Kico611/EduManage.git
   ```

2. Uđi u direktorij projekta:
   ```bash
   cd EduManage
   ```

3. Pokreni Docker kontejnere:
   ```bash
   docker-compose up --build
   ```

4. Aplikacija će biti dostupna na:
   ```
   http://localhost:8080/students
   ```

> Napomena: Docker Compose će automatski podići i MySQL bazu i Spring Boot aplikaciju.

---

### Opcija 2: Pokretanje ručno (bez Dockera)

1. Osiguraj da MySQL server radi lokalno.
2. Postavi konfiguraciju baze u `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/edumanage
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Pokreni aplikaciju:
   ```bash
   ./mvnw spring-boot:run
   ```

## 📑 Funkcionalnosti

- ✅ CRUD operacije za studente, kolegije i profesore
- ✅ Upravljanje upisima i ocjenama
- ✅ Validacija obrazaca
- ✅ Responsivni dizajn
- ✅ Dockerizirana aplikacija
- ✅ Automatsko podizanje baze podataka i aplikacije pomoću Docker Compose-a

---

