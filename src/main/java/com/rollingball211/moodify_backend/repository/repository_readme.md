# 🗃 repository

이 디렉터리는 **데이터베이스와의 접근을 담당하는 인터페이스(JPA Repository)** 들을 정의하는 공간입니다.

## 주요 내용
- Spring Data JPA의 `JpaRepository<T, ID>`를 상속받는 Repository 인터페이스 정의
- DB CRUD 작업을 추상화하여 직접 SQL을 작성하지 않아도 됨 (ORM)
- 필요시 `@Query` 어노테이션으로 JPQL/SQL 작성 가능

## 예시
- `UserRepository`: `User` 엔티티의 DB 접근을 담당

## 자주 쓰는 기본 메서드
- `save(entity)`: 저장/수정
- `findById(id)`: ID로 조회
- `findAll()`: 전체 조회
- `deleteById(id)`: 삭제

## 참고 사항
- 실질적인 구현체는 Spring이 자동 생성해 주입 (`@Autowired` 또는 생성자 주입)