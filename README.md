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


User() [entity]
1. @entity -> DB와의 매핑에 필요함 
2. @GeneratedValue(strategy = GenerationType.IDENTITY)
-> 필드의 값을 자동으로 생성하며, db에서 auto-increment 역할을함
 private Long id;  //JPA에서의 기본 PK로 설정되는 코드
3.  public User() {} 
-> 기본 생성자를 JPA가 무조건 필요로 하기 때문에 필요함! 
매개변수가 있는 생성자의 경우 편의를 위해 (미리 초기화) 로 사용

userService()

userController()

userRepository()

