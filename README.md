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

**2025/08/01**
- DTO에 대한 자세한 이해

**2025/08/02**
- MoodMusicMappingRepository 제작
- findByMood를 이용해 특정 기분에 연결된 음악 매핑들 리스트 조회
2. 음악 추천 서비스 RecommendationService 제작
3. 음악 추천 컨트롤러  RecommendController 제작

**2025/08/03**
1. PostMan API 테스트
2. 기본 DB 세팅 (JPA) - application.properties
3. swagger "http://localhost:8080/swagger-ui/index.html#/"
4. swagger 상세설명 수정 @Tag 및 @Operation,@Parameter 어노테이션 사용

**2025/08/04**
1. 모든 API SWAGGER 수정 및 작성
2. DTO 구조 정리
3. 예외 전역 처리	(오류 처리 통일 @RestControllerAdvice 활용)

**2025/08/05**
1. DTO 컨트롤러 변경 (수정 필요, 보안성 UP)
2. 예외 전역 처리 수정


**2025/08/09**
1. 예외 전역 처리 수정 완료
2. 전체 코드 점검 및 수정사항 확인

**2025/08/10**
1. 응답 DTO 통일
2. 추천 로직 보안(-DB에서 N개, (LIMIT QUERY 처리))
3. 예외처리 (404 추가)
4. 시간 처리 통일
---
**2025/08/11**
- 매핑 서비스 및 컨트롤러 작성
- DTO 생성

**2025/08/12**
- music, mood_music_mapping 데이터 450개 모두 작성 완료
- API 테스팅

**2025/08/13**
- 서버 에러 수정 완료
- API 최종 테스트 및 프론트 작업 시작

**2025/08/14**
- Spring Security 작업

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

🔍 **주요 어노테이션 사용 정리**
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
  - **Why DTO?**
  - Entity는 내부 설계도와 같은 역할을 한다, 즉 DB구조 그대로 반영된 객체를 의미한다.
  - 민감정보를 포함하거나, 프론트에 영향을 줄 수 잇기 때문에 원하는 필드만 작성할 수 있는 DTO를 사용한다.
  - Stream.map.collect를 이용해 Entity를 Dto로 변환한다.
```
 .map(MoodLogResponseDTO::new)은 아래와 같다.
.map(moodLog -> new MoodLogResponseDTO(moodLog))
즉 moodLog를 인자로 받는 생성자가 있어야 실행 가능하다.
```
- DTO 는 보안 / 캡술화 뿐만 아니라 외부에 보낼 용도로 정제된 객체로 변환하는 작업이다. 

- postman 테스트
```
JSON :
{
  "userId": 1,
  "moodId": 2
}

--OUTPUT--
{
    "id": 1,
    "username": "ball",
    "moodName": "sad",
    "createdAt": "2025-08-03T22:15:13.5450972"
}

```

**😀Swagger**
1. swagger 상세설명 수정 @Tag 및 @Operation,@Parameter 어노테이션 사용
- **@Tag(name = "user-controller", description = "사용자 관련 API")**
- **@Operation(summary = "모든 사용자 조회")**
- **public ResponseEntity<User> getUser(@Parameter(description = "사용자 ID") @PathVariable Long id)**

**0804**
```
현재 DTO를 사용하는 객체에서 entity가 노출되는 문제가 있음
작동방식에는 문제가 없으나 보안상 이유로 entity 노출 대신 DTO를 사용하는게 바람직함

클라이언트가 id, password, role, createdAt 등 노출되면 안 되는 값을 조작해서 보내는 게 가능
유지보수가 어려움
	=> Entity가 바뀔 때마다 API 요청/응답 구조도 바뀌게 되어, API가 불안정해짐, DTO를 쓰는 이유가 사라짐
```

**0809**
```
@RestContollerAdvice
--
@ControllerAdvice + @ResponseBody가 합쳐진 어노테이션
프로젝트의 모든 컨트롤러에서 발생하는 예외를 한 곳에서 처리
반환값을 자동으로 JSON 변환해서 응답

=> 모든 컨트롤러의 예외처리를 이 클래스가 대신 한다! 라는 의미

@ExceptionHandler( className)
지정한 예외 타입이 발생할 경우 안의 메서드가 호출됨
여러개의 handler를 만들어 예외 종류별로 다른 응답 만들기 가능!
---------------------------------------------------------
예외 처리 방식
1. 컨트롤러에서 throw new IllegalArgumentException("잘못된 값") 발생
2. Spring이 @RestControllerAdvice에 등록된 @ExceptionHandler(IllegalArgumentException.class)를 찾음
3. 해당 메서드 실행 → JSON 형태로 에러 응답 반환
```

**0810**
1. LocalDateTime 타입인 createdAt
- JSON 변환 시 기본 ISO 포맷(YYYY-MM THH:MM:SS)으로 나옴
```
문제점
1. T가 끼어있어 프론트 Parse가 불편할 가능성 존재
2. 밀리초 포함 여부가 일정하지 않을 수 있음
3. 타임존 정보를 포함하지 않음!
- 서버/클라이언트 타임존이 다를 경우 시간 달라질 가능성 존재 
=> 서버 UTC, 프론트 KST(UTC+9) 면 9시간 시간차이 발생
4. API 마다 다른 포맷으로 날짜가 나오면 프론트에서 일일히 처리해야 함
```
- @JsonFormat으로 처리해야함
```
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
```
- String 변환
```
 this.createdAt = moodLog.getCreatedAt()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
```
- MOODIFY 프로젝트의 경우 백엔드/프론트가 분리되어있어 DTO에서 포맷 지정해 보내는게 좋음

```
DTO
DTO 필드는 도메인 객체가 아니라, 도메인의 필드 값(원시 타입 또는 String 등) 만 가짐

DTO Stream 
return moods.stream()
       .map(mood -> new MoodResponseDTO(mood.getId(), mood.getName()))
       .collect(Collectors.toList());
       
 List<Mood> -> 스트림 변환 후 각 원소들을 다시 MoodResponseDTO로 매핑하고
 다시 리스트로 변환한다.
 
```

**0812**

```
복습
ResponseEntity
-> Spring에서 Http 상태 코드 + 응답 바디를 함께 다루는 클래스

@PathVariable Long moodId
-> URL 경로 .../{moodId} 에서 {moodId} 부분을 Long 타입으로 받음


.map(mood -> ResponseEntity.ok(moodMusicMappingService.getMusicByMood(mood)))
map -> optional 안에 값이 있는 경우 값을 변환
여기에서는 mood가 존재할 시 200 OK + 바디에 값을 담아 반호나


orElseGet()
:Optional 안에 값이 없을 경우 실행할 지연 로직 지정
orElse()는 인자로 준 것을 미리 계산하나, orElseGet()의 경우엔 값이 없을때만 람다를 실행함.

```

**0813**
- 치명적인 에러 해결 (트러블슈팅)
```
1. 스프링부트의 버전과 스웨거의 버전이 달라 
void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'
에러 발생, 

에러 확인시점  : 
(기존 swagger에서 @Tag,@optional) 을 단 내용들이 정상적으로 반영되지 않았음)
-> @Tag,@optional 을 달면서 swagger 페이지에 에러가 확인됨 (500 error)
-> 아래의 시도를 거쳐서 해결함

시도 1 . 스프링부트의 버전 바꾸면서 스웨거 버전 테스트
1. 2.0 ->  2.6.0 ( 에러 발생)
2. v3/api 마찬가지로 실행 안됨

시도 2. 모든 스프링 캐시 파일 제거 후 dependenct에서 force 나  Constraints로 버전 강제함
=> NoSuchMethodError 발생
=> .\gradlew dependencyInsight --dependency org.springframework:spring-webmvc --configuration runtimeClasspath --no-daemon --console=plain
명령어로 dependency를 확인할 때 스프링 버전이 6.2로 가져오는 것을 확인 
=> 버전 변화를 주기 위해 스프링의 모든 파일 web/core/beans.. (6.2, 6.8 ) 파일 제거 후  캐시 제거 후 다시 위 명령어 실행
=> 버전 변화가 없는 것을 확인 
=> 스프링 버전은 변하지 않고 (아마 상위 옵션이 있는듯 함), 스웨거를 사용하는 버전은 변함

시도 3. 원래 있던 버전을 찾아 clone 후, 빌드 캐시를 진행, 스프링 버전은 바뀌지 않는걸 확인했기에 스웨거 버전 변경
=> 에러 해결 및 body , response 에 요청했을 때, 정상적으로 api 응답이 이루어짐(백엔드 성공)
=> ui 변경을 위해서 ctrl+f5를 이용해 캐시 제거 후 정상적 동작 확인!

```

**0814**

- 스프링 시큐리티 코드 해석
```

import org.springframework.beans.factory.annotation.Value;
-> appilcation.properties or 환경변수 값을 가져오기 위해 사용함

@Configuration
-> 설정 클래스임을 나타내는 어노테이션

@Bean
-> 스프링 컨테이너에 객체를 

@EnableWebSecurity
-> 스프링 시큐리티를 활성화


@Value("${app.cors.allowed-origins:http://localhost:3000}")
private String allowedOriginsProp;

-> application.properties에서 app.cors.allowed-origins 값을 읽어옴.
-> 값이 없다면 뒤에 주소를 사용함 (기본값)


public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
-> 어떤 보안 규칙을 적용할지 결정 (filterchain)

http.csrf(csrf -> csrf.disable())
-> CSRF(사이트 간 요청 위조) 보호 비활성화. 
-> REST API의 경우 세션 or 쿠키를 잘 사용하지 않음

.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
-> 세션을 생성하지 않고, 요청마다 인증 정보를 담아 보내도록 설정(stateless)


.authorizeHttpRequests(auth -> auth
.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
.requestMatchers("/api/auth/**").permitAll()
.requestMatchers("/api/public/**").permitAll()
.anyRequest().authenticated()

-> 요청별 접근 권한 설정. 스웨거 , 로그인, 공개 api 제외한 나머지 요청은 인증이 필요함

.cors(Customizer.withDefaults());

-> 아래 corsConfigurationSource() 에서 정의한 값을 사용함

return http.build(); 
-> 필터 체인 반환
```

- CORS 정책 설정 메서드
```
List<String> origins = Arrays.stream(allowedOriginsProp.split(","))
        .map(String::trim)
        .filter(s -> !s.isBlank())
        .toList();
        
-> allowedOriginsProp 값을 쉼표로 나눠서 list로 변환.

CorsConfiguration cfg = new CorsConfiguration();
cfg.setAllowedOrigins(origins);

-> 허용할 도메인 리스트 설정함


cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
-> 허용할 메서드 설정

cfg.setAllowedHeaders(List.of("Authorization","Content-Type","Accept"));
-> 요청에서 허용할 헤더 설정

cfg.setAllowCredentials(true);
-> 쿠키 및 인증 정보 포함한 요청 허용

cfg.setMaxAge(3600L);
-> CORS preFilght(?) 요청 결과 캐싱 (초 단위) 

UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
source.registerCorsConfiguration("/**", cfg);
return source;

-> 모든 경로에 대해 위의 cors 정책 허용

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

-> 비밀번호 해싱용 PasswordEncoder 빈 등록.
```


```
CORS prefilght? (gpt) 
📌 CORS preflight란?

브라우저에서 서버에 API 요청을 보내기 전에 “이 요청 해도 돼?” 하고 먼저 확인하는 요청입니다.

이 확인 요청이 바로 OPTIONS 메서드입니다.

예: fetch로 POST 요청을 보내는데, 헤더에 Authorization 같은 커스텀 헤더가 있으면 브라우저는 먼저

OPTIONS /api/data
Origin: http://localhost:3000
Access-Control-Request-Method: POST
Access-Control-Request-Headers: Authorization


이렇게 확인 요청을 보냅니다.

📌 캐싱하는 이유

이 preflight 요청은 매 요청마다 보내면 느려집니다.

setMaxAge(3600L)은 이 확인 결과를 3600초(1시간) 동안 브라우저에 캐싱하도록 하는 설정입니다.

즉, 같은 API 요청을 1시간 동안은 OPTIONS 없이 바로 보낼 수 있습니다.

💡 비유: 음식점에서 “이 쿠폰 써도 돼요?” 하고 물어봤더니
사장님이 “1시간 동안은 안 물어봐도 돼요” 하는 것과 같습니다.
```