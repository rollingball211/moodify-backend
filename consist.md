#시스템 단계적 구성하는 consist.md
Client (Postman / FE 요청)
↓
Controller (요청 받음)
↓
DTO (요청 파싱)
↓
Service (비즈니스 로직 처리)
↓
Entity (JPA 도메인 객체 생성/수정)
↓
Repository (DB 저장/조회)
↓
Entity → DTO (필요시 응답 형태로 가공)
↓
Controller → Client (응답)


**0728** 
사용자는 자신의 기분을 선택하거나 기록한다 (예: 행복, 우울, 화남 등)
기분에 따라 음악을 추천받는다 (혹은 직접 등록해둔 플레이리스트 재생)
음악을 감상하거나 저장할 수 있다

Domain에 추가되어야 할 것들

Entity	설명
User	사용자 정보 (이미 있음)
Mood	기분 종류 (행복, 우울, 불안 등)
Music	음악 정보 (제목, 아티스트, URL 등)
MoodLog	유저가 어떤 기분을 기록했는지 (기록 시간 포함)
MoodMusicMapping	특정 기분에 어떤 음악들이 연결되는지 (추천 연결 정보)

**Mood**
MoodRepository
MoodService
MoodController


**0729**

Mood , User create API 응답 200 -> 201 코드 리팩토링 
Music - Service/Controller/Repository 코드 작성

1. MoodLog: 사용자 기분 기록 로그
📌 목적:
사용자가 어떤 기분(Mood)을 언제 기록했는지 저장하는 기록
📂 구조:
User (누가)
Mood (어떤 기분)
createdAt (언제)

로그 생성 (POST)
사용자별 Mood 로그 조회 (GET /api/mood-logs/user/{userId})
특정 기간 내 로그 조회 (선택사항)

- dto > MoodLogDTO 생성 

2. MoodMusicMapping: 기분 ↔ 음악 매핑 테이블
📌 목적:
특정 기분(Mood) 에 어떤 음악(Music) 을 연결해줄지를 저장
📂 구조:
Mood
Music
매핑 추가 (POST)
특정 기분에 대한 추천 음악 조회 (GET /api/moods/{id}/music)

**사용자 최근 기분 기준으로 음악 추천 API**
1. MoodLog Table에서 해당 유저의 가장 최근 로그 1개 조회
2. 해당 로그의 MoodID에 매핑된 음악 리스트 조회
3. 랜덤하게 1~3개 반환

**개발과정**
1. MoodMusicMappingRepository 제작 ( MoodMusicMapping의 기본 CRUD 제공)
- findByMood를 이용해 특정 기분에 연결된 음악 매핑들 리스트 조회
2. 추천 서비스 RecommendationService 제작
3. 