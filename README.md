# moodify-backend

Next.js + Spring Framework로 구성된 음악 추천 프로그램 - moodify 백엔드 파트

---

## 📅 개발 이력

**2025/07/25**
- 기본 API 테스팅
- User Entity / Repository / Service / Controller 파일 생성

**2025/07/28**
- domain 패키지에 Mood를 포함한 총 4개의 클래스 추가
- Mood Entity / Repository/ Servcie / Controller 파일 생성

**2025/07/29**
- Mood , User create API 응답 200 -> 201 코드 리팩토링
- Music - Service/Controller/Repository 코드 작성
- MoodLog Service/Controller/Repository 코드 작성 
- DTO 생성 후 리팩토링, 코드 다시보기 필요
---

## ⚙️ 프로젝트 진행 도중 학습한 내용

### 📄 build.gradle 설정

```gradle
implementation 'org.springframework.boot:spring-boot-starter-web'
// 웹 기능 활성화

implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
// JPA 도구 세팅

implementation 'com.h2database:h2'
// 테스트용 인메모리 DB (H2)
```
### USER Part ###
### 🧱 UserEntity
@Entity
- DB와의 매핑에 필요함
@GeneratedValue(strategy = GenerationType.IDENTITY)

필드 값을 자동 생성하며, DB에서 auto-increment 역할 수행

예: private Long id; (JPA 기본 PK)

- public User() {}

- JPA에서 반드시 요구하는 기본 생성자, 매개변수 생성자는 편의상 미리 초기화 용도로 사용,
상황에 따라 protected 접근 제어자도 사용 가능

### 📚 UserRepository
public interface UserRepository extends JpaRepository<User, Long> { }
- JpaRepository는 JPA DB 작업 메서드를 미리 구현해둔 인터페이스로, 다음과 같은 메서드를 제공:

메서드 설명
- save(User user)	저장 또는 수정
- findById(Long id)	ID로 조회
- findAll()	전체 조회
- deleteById(Long id)	ID로 삭제
- count()	총 개수 반환
- existsById(Long id)	해당 ID 존재 여부 확인

### UserService
- UserRepository에 정의된 인터페이스 메서드들을 호출하여 비즈니스 로직을 처리

### 🧭 UserController
1. @RestController
- REST API 컨트롤러 역할

2. @ResponseBody 
- 반환값을 HTTP Body로 직렬화하여 응답

3. @RequestMapping("~/...")
- 모든 메서드의 기본 경로 지정

4. @RequestBody
- HTTP 요청 바디(JSON)를 User 객체로 자동 변환 (역직렬화)

5. ResponseEntity는 응답 상태 코드와 응답 바디를 함께 반환
- 예: 200 OK, 404 Not Found

🔍 주요 어노테이션 사용 정리
- /users/123	@PathVariable	URL 경로에서 값 추출
- /users?id=123&pw=abc123	@RequestParam	URL 쿼리 스트링에서 값 추출
- JSON body	@RequestBody	HTTP Body(JSON) → 객체 변환

사용 예 요약:

특정 자원 조회: @PathVariable → /users/123

검색 조건, 필터 등: @RequestParam → /search?name=홍길동

회원가입, 로그인 등 복잡한 입력값: @RequestBody

### Optional이란?
null 체크를 안전하게 하기 위해 사용하는 Java 기능
- null-check 강제함(컴파일 단계)
- nullPointerException 방지
- map / filter / orElse 등으로 코드 처리 가능 !!

### **0728**

**Mood**
@ManyToOne
- 여러 개의 MoodLog가 하나의 User에 연결되는 구조 (N:1 관계)

### 🧾 MoodController
return ResponseEntity.status(HttpStatus.CREATED).body(saved);
HttpStatus.CREATED

- HTTP 상태 코드 201: "요청이 성공적으로 처리되었고, 새로운 리소스가 생성되었음"

✅ REST API 설계 원칙
새로운 자원을 생성했을 때는 201(Created) 상태 코드와 함께 생성된 자원 정보를 반환하는 것이 좋음
프론트엔드는 이 응답을 받아 생성된 Mood 정보를 즉시 화면에 반영할 수 있음


### **0729**

* POST 요청으로 새 리소스가 생성되면
* 서버는 201 Created 상태 코드를 반환하는 게 표준 권장사항이다
 - 기존 create 함수들을 바꿔주며, getId() 를 모두 작성해줘야함. refactor 진행
 - User/Mood controller 모두 완료

**MoodLog Part**
- DTO 생성
- orElseThrow()는 Optional 안에 값이 있으면 그 값을 꺼내고,값이 없으면 예외를 던진다.
- ```
     public MoodLog createMoodLog (Long userId, Long moodId) {
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new IllegalArgumentException("user not found" + userId));
        Mood mood = moodRepository.findById(moodId)
                .orElseThrow( () -> new IllegalArgumentException(("mood not found") + moodId));  //orElseThrow()는 Optional 안에 값이 있으면 그 값을 꺼내고,값이 없으면 예외를 던진다.

        MoodLog moodLog = new MoodLog();
        moodLog.setUser(user);
        moodLog.setMood(mood);
        moodLog.setCreatedAt(LocalDateTime.now());

        return moodLogRepository.save(moodLog);
    }
  ```
  