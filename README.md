# moodify-backend
Next.js + Spirng Framework로 구성된 음악 추천 프로그램 - moodify 백엔드 파트

2025/07/25 
기본 API 테스팅,
User Entity /repository / service / controller 파일 생성

프로젝트 진행 도중 학습한 내용

build.gradle (file)

implementation 'org.springframework.boot:spring-boot-starter-web'
-web 기능을 가능하게 해줌

DB 관련 세팅
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
- jpa 도구 세팅
	implementation 'com.h2database:h2'
- 테스트용 인메모리 DB (h2)


User [entity]
1. @entity -> DB와의 매핑에 필요함 
2. @GeneratedValue(strategy = GenerationType.IDENTITY)
-> 필드의 값을 자동으로 생성하며, db에서 auto-increment 역할을함
 private Long id;  //JPA에서의 기본 PK로 설정되는 코드
3.  public User() {} 
-> 기본 생성자를 JPA가 무조건 필요로 하기 때문에 필요함! 
매개변수가 있는 생성자의 경우 편의를 위해 (미리 초기화) 로 사용
상황에 따라서는 protected까지 사용할 수 있음.

userRepository
1. public interface UserRepository extends JpaRepository<User, Long> {

}
-> JpaRepository는 JPA DB작업 method를 미리 구현해둔 인터페이스임,구현 시 아래와 같은 인터페이스 작성 가능
------------------------------
save(User user)	저장 또는 수정
findById(Long id)	ID로 조회
findAll()	전체 조회
deleteById(Long id)	ID로 삭제
count()	총 개수
existsById(Long id)	존재 여부 확인
--------------------------------
userService 
UserRepository 에 있는 interface들을 호출해 비즈니스 로직을 담당함

userController()
1. @RestController
-REST API의 Controller
@ResponseBody의 조합이므로 반환값을 HTTP Body로 직렬화후 반환함
-> 프론트 요청 올 시 Json으로 응답함

2. RequestMapping("~/~)
모든 메서드 기본경로 지정

3. @RequestBody
-> 요청 바디(JSON)를 User 객체로 자동 변환해줌 (역직렬화)
@ResponseEntity는 응답 상태 + 바디를 함께 리턴
ex) (200 ok.. 404 not found)

4.  🔹 @PathVariable → URL 경로 자체에서 값 추출
	🔹 @RequestParam → URL 뒤에 붙는 쿼리 스트링에서 값 추출
	🔹 @RequestBody → HTTP 요청 바디(JSON)에서 객체로 역직렬화

형태	어노테이션	예시 URL	설명
/users/123	-> **@PathVariable**	/users/{id}	경로 중 일부 (리소스 지정용)
/users?id=123&pw=abc123	- > **@RequestParam**	/users?id=123	쿼리 파라미터 추출
JSON body	**@RequestBody**	POST, PUT 등에서 사용	바디에서 사용함

특정 자원 조회 (/users/123)	@PathVariable
검색 조건, 필터, 옵션 (/search?name=홍길동)	@RequestParam
로그인, 회원가입 같은 복잡한 입력값	@RequestBody


Optional?
null-check를 하기 위해 사용함
- null-check 강제함(컴파일 단계)
- nullPointerException 방지
- map / filter / orElse 등으로 코드 처리 가능 !!
