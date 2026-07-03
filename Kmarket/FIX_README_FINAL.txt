이번 로그 기준 수정 내용

1) DB 계정 따옴표 제거
- src/main/resources/application.properties
- spring.datasource.username="kmarket_user" -> spring.datasource.username=kmarket_user
- spring.datasource.password="1234" -> spring.datasource.password=1234

2) CartOption 복합키 수정
- CartOption 엔티티 필드: cartNo, optionNo
- CartOptionId 필드도 cartNo, optionNo 로 맞춤
- CartOptionRepository ID 타입을 CartOptionId 로 수정

3) CouponProduct 복합키 수정
- CouponProduct 엔티티 필드: couponNo, productNo
- CouponProductId 필드도 couponNo, productNo 로 맞춤
- CouponProductRepository ID 타입을 CouponProductId 로 수정

중요
- 기존 프로젝트 폴더에 덮어쓰기만 하면 build/classes 쪽에 예전 .class가 남아 있을 수 있습니다.
- 반드시 IntelliJ에서 Gradle Reload 후 Clean/Rebuild 하거나, 터미널에서 아래 실행:
  .\gradlew.bat clean bootRun

DB 권한 에러가 계속 나면 MySQL에서 다음 확인:
  SELECT user, host FROM mysql.user WHERE user = 'kmarket_user';

필요 시 권한:
  CREATE USER IF NOT EXISTS 'kmarket_user'@'%' IDENTIFIED BY '1234';
  GRANT ALL PRIVILEGES ON board.* TO 'kmarket_user'@'%';
  FLUSH PRIVILEGES;
