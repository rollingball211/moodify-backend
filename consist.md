#시스템 단계적 구성하는 consist.md
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
