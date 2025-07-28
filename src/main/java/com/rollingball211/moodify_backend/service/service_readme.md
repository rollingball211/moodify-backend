#  service

이 디렉터리는 **비즈니스 로직을 담당하는 서비스 클래스**를 정의합니다.

## 주요 내용
- Controller에서 전달된 요청을 처리
- Repository를 호출하여 DB 작업 수행
- 단순 CRUD 이상의 로직, 트랜잭션, 예외 처리 등을 포함
- 역할 분리: Controller는 입출력 / Repository는 저장 / Service는 판단

## 예시
- `UserService`: 사용자 생성, 조회, 수정, 삭제 등의 로직 처리

## 서비스 계층의 책임
- 비즈니스 규칙 처리
- 예외 상황 제어
- 여러 Repository 조합 처리
- 트랜잭션 경계 설정

## 참고 사항
- `@Service` 어노테이션 사용
- 생성자 주입 방식으로 Repository 사용 (`@Autowired` 대신 생성자 사용 권장)
- 가능한 한 Controller에서 직접 Repository를 호출하지 않도록 함