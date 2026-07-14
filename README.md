<img src="https://capsule-render.vercel.app/api?type=waving&color=9ACB34&theme=navy&height=250&section=header&text=ShoppingMall%20Project&fontSize=55&animation=fadeIn&desc=KMarket%20Web%20Application%20Development&descSize=20&descAlignY=65" width="100%" />

# ShoppingMall-project

<details>
<summary><b>📌 1. 프로젝트 소개 및 사용 기술 </b></summary>

### 📝 프로젝트 소개
ShoppingMall-project는 쇼핑몰 화면 설계서를 기반으로 제작하는 웹 페이지 프로젝트입니다.  
사용자가 상품을 조회하고 장바구니에 담아 주문까지 진행하는 쇼핑몰의 기본 흐름을 구성하며, 회원가입/로그인, 마이페이지, 고객센터, 정책 페이지, 관리자 페이지까지 포함한 쇼핑몰 전체 화면 구조를 구현하는 것을 목표로 합니다.
팀원별 담당 영역을 명확하게 분리하여 독립된 폴더에서 작업함으로써 Git 협업 과정의 충돌을 최소화하고 효율적으로 개발을 진행합니다.

### 🛠️ 사용 기술
#### Frontend
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

#### Backend & Database
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">

#### Tools & Collaboration
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
</details>

---

<details>
<summary><b>👥 2. 팀원 및 업무 분배 </b></summary>

### 👥 팀원 명단 및 담당 영역
| 이름 | 담당 파트 | 상세 담당 페이지 및 도메인 | 연관 핵심 DB 테이블 |
|:---:|---|---|---|
| <span style="white-space: nowrap;">**허민재**</span> | **Admin A담당**<br>(메인/상품/주문/설정) | `admin/*` (메인 대시보드, 환경설정, 상품 등록/목록, 주문 관리, 배송/반품 상태 제어 등) | `site_setting`, `category`, `product` 계열 전체, `orders`, `order_item`, `order_status_history`, `delivery` |
| <span style="white-space: nowrap;">**정인길**</span> | **Admin B담당**<br>(상점/회원/쿠폰/CS) | `admin/*` (상점 정보 관리, 회원 목록 조회, 쿠폰 관리, 고객센터 및 1:1 문의 답변, 배너/버전 관리 등) | `member`, `seller_profile`, `coupon`, `coupon_issue`, `cs_category`, `notice`, `faq`, `qna`, `recruit`, `banner`, `app_version` |
| <span style="white-space: nowrap;">**양지웅**</span> | **사용자 쇼핑 프로세스**<br>및 회사/정책 안내 | `index.html` (메인 쇼핑몰 화면)<br>`product/*` (상품 목록, 상세, 검색, 장바구니, 주문/결제 및 완료)<br>`company/*` (회사 소개 및 연혁)<br>`policy/*` (소비자/판매자 약관 페이지) | `category`, `product` 계열 전체, `cart`, `orders`, `order_item`, `payment`, `policy`, `company_content`, `company_history` |
| <span style="white-space: nowrap;">**최수빈**</span> | **회원 인증, 마이페이지**<br>및 사용자 고객센터 | `member/*` (로그인, 회원가입 약관 및 정보 입력)<br>`my/*` (주문내역, 포인트/쿠폰 현황, 나의 리뷰, 문의 내역, 정보 수정)<br>`cs/*` (고객센터 메인, 공지/FAQ 목록, 1:1 문의글 작성) | `member`, `member_social`, `member_agreement`, `member_point`, `coupon_issue`, `orders`, `order_item`, `delivery`, `order_claim`, `claim_file`, `product_review`, `review_image`, `cs_category`, `notice`, `faq`, `qna` |
</details>

---

<details>
<summary><b>📁 3. 프로젝트 폴더 구조 </b></summary>

```plaintext

ShoppingMall-project/
├── index.html
├── member/ (login.html, signup.html, register.html)
├── product/ (list.html, view.html, search.html, cart.html, order.html, complete.html)
├── my/ (home.html, ordered.html, point.html, coupon.html, review.html, qna.html, info.html)
├── cs/ (index.html, notice/, faq/, qna/)
├── policy/ (buyer.html, seller.html, finance.html, location.html, privacy.html)
├── company/ (index.html, introduce.html, notice.html, promote.html, manage.html)
├── admin/ (index.html, config/, product/, cs/)
├── css/ (common.css, main.css, member/, product/, my/, cs/, policy/, company/, admin/)
└── images/
```
</details>

---

### 🔀 Git 브랜치 전략
본 프로젝트는 팀원별 작업 폴더를 분리하여 Git 충돌을 최소화합니다.

```plaintext

main
├── feature/main-product-company (허민재)
├── feature/member-my            (양지웅)
├── feature/cs-policy            (정인길)
└── feature/admin                (최수빈)
```

### 🗄️ 작업 규칙
- 각 팀원은 본인 담당 폴더 중심으로 작업하고, 공통 CSS 수정 시 팀원들과 먼저 공유합니다.

- 작업 전 최신 코드를 pull 받고, main 브랜치 직접 push 대신 PR(Pull Request)을 반영합니다.

### 🎨 CSS 스타일 가이드
- 공통 영역: css/common.css 필수 포함 후 각 도메인별 메인 CSS 링크

- 관리자 영역: 공통 CSS (admin-common.css) + 페이지별 CSS (basic.css) 조합 호출

---

<details>
<summary><b> 📊 테이블 생성 SQL (DDL)</b></summary>

```sql
CREATE DATABASE IF NOT EXISTS kmarket;
USE kmarket;

-- 1. 회원
CREATE TABLE `member` (
  `member_no` int NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `hp` varchar(20) DEFAULT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'USER',
  `grade` varchar(20) NOT NULL DEFAULT 'SILVER',
  `point` int NOT NULL DEFAULT '0',
  `status` varchar(20) NOT NULL DEFAULT '정상',
  `zip` varchar(10) DEFAULT NULL,
  `addr1` varchar(255) DEFAULT NULL,
  `addr2` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `leave_date` datetime DEFAULT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_no`),
  UNIQUE KEY `member_id` (`member_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 2. 소셜 로그인 연동
CREATE TABLE `member_social` (
  `social_no` bigint NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `provider` varchar(20) NOT NULL,
  `provider_user_id` varchar(255) NOT NULL,
  `provider_email` varchar(100) DEFAULT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`social_no`),
  UNIQUE KEY `provider` (`provider`,`provider_user_id`),
  UNIQUE KEY `member_no` (`member_no`,`provider`),
  CONSTRAINT `member_social_ibfk_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 3. 약관
CREATE TABLE `policy` (
  `policy_no` int NOT NULL AUTO_INCREMENT,
  `policy_type` varchar(50) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `required_yn` char(1) NOT NULL DEFAULT 'Y',
  `use_yn` char(1) NOT NULL DEFAULT 'Y',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`policy_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 4. 판매자 상점 정보
CREATE TABLE `seller_profile` (
  `seller_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `ceo_name` varchar(50) NOT NULL,
  `biz_no` varchar(30) NOT NULL,
  `online_sale_no` varchar(50) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `addr1` varchar(255) DEFAULT NULL,
  `addr2` varchar(255) DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT '운영준비',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seller_no`),
  UNIQUE KEY `member_no` (`member_no`),
  UNIQUE KEY `biz_no` (`biz_no`),
  CONSTRAINT `seller_profile_ibfk_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 5. 상품 카테고리
CREATE TABLE `category` (
  `cate_no` int NOT NULL AUTO_INCREMENT,
  `parent_no` int DEFAULT NULL,
  `cate_name` varchar(100) NOT NULL,
  `depth` tinyint NOT NULL DEFAULT '1',
  `sort_order` int NOT NULL DEFAULT '0',
  `use_yn` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`cate_no`),
  KEY `parent_no` (`parent_no`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_no`) REFERENCES `category` (`cate_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 6. 상품
CREATE TABLE `product` (
  `product_no` int NOT NULL AUTO_INCREMENT,
  `seller_no` int NOT NULL,
  `cate_no` int NOT NULL,
  `product_name` varchar(200) NOT NULL,
  `basic_desc` varchar(255) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `price` int NOT NULL DEFAULT '0',
  `discount_rate` int NOT NULL DEFAULT '0',
  `point` int NOT NULL DEFAULT '0',
  `stock` int NOT NULL DEFAULT '0',
  `delivery_fee` int NOT NULL DEFAULT '0',
  `status` varchar(20) NOT NULL DEFAULT '판매중',
  `view_count` int NOT NULL DEFAULT '0',
  `sold_count` int NOT NULL DEFAULT '0',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_no`),
  KEY `seller_no` (`seller_no`),
  KEY `cate_no` (`cate_no`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`seller_no`) REFERENCES `seller_profile` (`seller_no`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`cate_no`) REFERENCES `category` (`cate_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 7. 상품 이미지
CREATE TABLE `product_image` (
  `image_no` int NOT NULL AUTO_INCREMENT,
  `product_no` int NOT NULL,
  `image_type` varchar(30) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `sort_order` int NOT NULL DEFAULT '0',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`image_no`),
  KEY `product_no` (`product_no`),
  CONSTRAINT `product_image_ibfk_1` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 8. 상품 옵션
CREATE TABLE `product_option` (
  `option_no` int NOT NULL AUTO_INCREMENT,
  `product_no` int NOT NULL,
  `option_group_no` tinyint NOT NULL,
  `option_name` varchar(100) NOT NULL,
  `option_value` varchar(100) NOT NULL,
  `sort_order` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`option_no`),
  UNIQUE KEY `uk_product_option_value` (`product_no`,`option_group_no`,`option_value`),
  CONSTRAINT `product_option_ibfk_1` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 9. 상품정보 제공고시
CREATE TABLE `product_notice` (
  `notice_no` int NOT NULL AUTO_INCREMENT,
  `product_no` int NOT NULL,
  `product_status` varchar(100) DEFAULT NULL,
  `tax_type` varchar(100) DEFAULT NULL,
  `receipt_type` varchar(100) DEFAULT NULL,
  `business_type` varchar(100) DEFAULT NULL,
  `origin` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`notice_no`),
  UNIQUE KEY `product_no` (`product_no`),
  CONSTRAINT `product_notice_ibfk_1` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 10. 장바구니
CREATE TABLE `cart` (
  `cart_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `product_no` int NOT NULL,
  `option_signature` varchar(100) NOT NULL DEFAULT '',
  `quantity` int NOT NULL DEFAULT '1',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_no`),
  UNIQUE KEY `uk_cart_member_product_option` (`member_no`,`product_no`,`option_signature`),
  KEY `product_no` (`product_no`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 11. 장바구니 선택 옵션
CREATE TABLE `cart_option` (
  `cart_no` int NOT NULL,
  `option_no` int NOT NULL,
  PRIMARY KEY (`cart_no`,`option_no`),
  KEY `option_no` (`option_no`),
  CONSTRAINT `cart_option_ibfk_1` FOREIGN KEY (`cart_no`) REFERENCES `cart` (`cart_no`),
  CONSTRAINT `cart_option_ibfk_2` FOREIGN KEY (`option_no`) REFERENCES `product_option` (`option_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 12. 주문
CREATE TABLE `orders` (
  `order_no` bigint NOT NULL AUTO_INCREMENT,
  `order_code` varchar(50) NOT NULL,
  `member_no` int NOT NULL,
  `orderer_name` varchar(50) NOT NULL,
  `orderer_hp` varchar(20) NOT NULL,
  `total_price` int NOT NULL DEFAULT '0',
  `discount_price` int NOT NULL DEFAULT '0',
  `coupon_discount` int NOT NULL DEFAULT '0',
  `delivery_fee` int NOT NULL DEFAULT '0',
  `point_use` int NOT NULL DEFAULT '0',
  `point_save` int NOT NULL DEFAULT '0',
  `pay_price` int NOT NULL DEFAULT '0',
  `payment_method` varchar(30) DEFAULT NULL,
  `payment_status` varchar(30) NOT NULL DEFAULT '결제대기',
  `paid_date` datetime DEFAULT NULL,
  `cancel_date` datetime DEFAULT NULL,
  `order_status` varchar(30) NOT NULL DEFAULT '주문완료',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_no`),
  UNIQUE KEY `order_code` (`order_code`),
  KEY `member_no` (`member_no`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 13. 배송
CREATE TABLE `delivery` (
  `delivery_no` int NOT NULL AUTO_INCREMENT,
  `order_no` bigint NOT NULL,
  `seller_no` int NOT NULL,
  `receiver_name` varchar(50) NOT NULL,
  `receiver_hp` varchar(20) NOT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `addr1` varchar(255) DEFAULT NULL,
  `addr2` varchar(255) DEFAULT NULL,
  `courier` varchar(50) DEFAULT NULL,
  `invoice_no` varchar(50) DEFAULT NULL,
  `delivery_fee` int NOT NULL DEFAULT '0',
  `delivery_status` varchar(30) NOT NULL DEFAULT '배송준비',
  `memo` text,
  `receipt_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shipped_date` datetime DEFAULT NULL,
  `delivered_date` datetime DEFAULT NULL,
  PRIMARY KEY (`delivery_no`),
  KEY `seller_no` (`seller_no`),
  KEY `idx_delivery_order_no` (`order_no`),
  CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `delivery_ibfk_2` FOREIGN KEY (`seller_no`) REFERENCES `seller_profile` (`seller_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 14. 주문 상품 상세
CREATE TABLE `order_item` (
  `order_item_no` bigint NOT NULL AUTO_INCREMENT,
  `order_no` bigint NOT NULL,
  `delivery_no` int DEFAULT NULL,
  `product_no` int NOT NULL,
  `seller_no` int NOT NULL,
  `product_name` varchar(200) NOT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `price` int NOT NULL DEFAULT '0',
  `discount_rate` int NOT NULL DEFAULT '0',
  `coupon_discount` int NOT NULL DEFAULT '0',
  `point` int NOT NULL DEFAULT '0',
  `quantity` int NOT NULL DEFAULT '1',
  `total_price` int NOT NULL DEFAULT '0',
  `item_status` varchar(30) NOT NULL DEFAULT '주문완료',
  `purchase_confirm_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_item_no`),
  KEY `order_no` (`order_no`),
  KEY `delivery_no` (`delivery_no`),
  KEY `product_no` (`product_no`),
  KEY `seller_no` (`seller_no`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`delivery_no`) REFERENCES `delivery` (`delivery_no`),
  CONSTRAINT `order_item_ibfk_3` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`),
  CONSTRAINT `order_item_ibfk_4` FOREIGN KEY (`seller_no`) REFERENCES `seller_profile` (`seller_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 15. 주문상품 선택 옵션
CREATE TABLE `order_item_option` (
  `order_item_option_no` bigint NOT NULL AUTO_INCREMENT,
  `order_item_no` bigint NOT NULL,
  `option_group_no` tinyint NOT NULL,
  `option_name` varchar(100) NOT NULL,
  `option_value` varchar(100) NOT NULL,
  PRIMARY KEY (`order_item_option_no`),
  UNIQUE KEY `uk_order_item_option_group` (`order_item_no`,`option_group_no`),
  CONSTRAINT `order_item_option_ibfk_1` FOREIGN KEY (`order_item_no`) REFERENCES `order_item` (`order_item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 16. 반품 / 교환 신청
CREATE TABLE `order_claim` (
  `claim_no` bigint NOT NULL AUTO_INCREMENT,
  `order_item_no` bigint NOT NULL,
  `member_no` int NOT NULL,
  `claim_type` varchar(20) NOT NULL,
  `claim_reason` varchar(100) NOT NULL,
  `detail_reason` text,
  `status` varchar(30) NOT NULL DEFAULT '신청',
  `request_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `process_date` datetime DEFAULT NULL,
  PRIMARY KEY (`claim_no`),
  KEY `order_item_no` (`order_item_no`),
  KEY `member_no` (`member_no`),
  CONSTRAINT `order_claim_ibfk_1` FOREIGN KEY (`order_item_no`) REFERENCES `order_item` (`order_item_no`),
  CONSTRAINT `order_claim_ibfk_2` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 17. 반품 / 교환 첨부파일
CREATE TABLE `claim_file` (
  `claim_file_no` bigint NOT NULL AUTO_INCREMENT,
  `claim_no` bigint NOT NULL,
  `ori_name` varchar(255) NOT NULL,
  `new_name` varchar(255) NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`claim_file_no`),
  KEY `claim_no` (`claim_no`),
  CONSTRAINT `claim_file_ibfk_1` FOREIGN KEY (`claim_no`) REFERENCES `order_claim` (`claim_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 18. 상품 리뷰
CREATE TABLE `product_review` (
  `review_no` bigint NOT NULL AUTO_INCREMENT,
  `order_item_no` bigint NOT NULL UNIQUE,
  `member_no` int NOT NULL,
  `product_no` int NOT NULL,
  `rating` tinyint NOT NULL,
  `content` text NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_no`),
  UNIQUE KEY `order_item_no` (`order_item_no`),
  KEY `member_no` (`member_no`),
  KEY `product_no` (`product_no`),
  CONSTRAINT `product_review_ibfk_1` FOREIGN KEY (`order_item_no`) REFERENCES `order_item` (`order_item_no`),
  CONSTRAINT `product_review_ibfk_2` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `product_review_ibfk_3` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 19. 리뷰 이미지
CREATE TABLE `review_image` (
  `review_image_no` bigint NOT NULL AUTO_INCREMENT,
  `review_no` bigint NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `sort_order` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`review_image_no`),
  KEY `review_no` (`review_no`),
  CONSTRAINT `review_image_ibfk_1` FOREIGN KEY (`review_no`) REFERENCES `product_review` (`review_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 20. 회원 포인트 이력
CREATE TABLE `member_point` (
  `point_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `order_no` bigint DEFAULT NULL,
  `point_type` varchar(20) NOT NULL,
  `point_value` int NOT NULL,
  `balance_point` int NOT NULL DEFAULT '0',
  `reason` varchar(255) DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`point_no`),
  KEY `member_no` (`member_no`),
  KEY `order_no` (`order_no`),
  CONSTRAINT `member_point_ibfk_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `member_point_ibfk_2` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 21. 쿠폰
CREATE TABLE `coupon` (
  `coupon_no` int NOT NULL AUTO_INCREMENT,
  `coupon_code` varchar(50) NOT NULL,
  `seller_no` int DEFAULT NULL,
  `coupon_type` varchar(30) NOT NULL,
  `coupon_name` varchar(100) NOT NULL,
  `benefit_type` varchar(20) NOT NULL,
  `benefit_value` int NOT NULL DEFAULT '0',
  `min_order_price` int NOT NULL DEFAULT '0',
  `max_discount_price` int NOT NULL DEFAULT '0',
  `issue_limit` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `caution` text,
  `status` varchar(20) NOT NULL DEFAULT '사용',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`coupon_no`),
  UNIQUE KEY `coupon_code` (`coupon_code`),
  KEY `seller_no` (`seller_no`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`seller_no`) REFERENCES `seller_profile` (`seller_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 22. 쿠폰 적용 상품
CREATE TABLE `coupon_product` (
  `coupon_no` int NOT NULL,
  `product_no` int NOT NULL,
  PRIMARY KEY (`coupon_no`,`product_no`),
  KEY `product_no` (`product_no`),
  CONSTRAINT `coupon_product_ibfk_1` FOREIGN KEY (`coupon_no`) REFERENCES `coupon` (`coupon_no`),
  CONSTRAINT `coupon_product_ibfk_2` FOREIGN KEY (`product_no`) REFERENCES `product` (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 23. 회원 쿠폰 발급 이력
CREATE TABLE `coupon_issue` (
  `issue_no` bigint NOT NULL AUTO_INCREMENT,
  `issue_code` varchar(50) NOT NULL,
  `coupon_no` int NOT NULL,
  `member_no` int NOT NULL,
  `order_no` bigint DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT '발급',
  `used_date` datetime DEFAULT NULL,
  `issue_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`issue_no`),
  UNIQUE KEY `issue_code` (`issue_code`),
  KEY `coupon_no` (`coupon_no`),
  KEY `member_no` (`member_no`),
  KEY `order_no` (`order_no`),
  CONSTRAINT `coupon_issue_ibfk_1` FOREIGN KEY (`coupon_no`) REFERENCES `coupon` (`coupon_no`),
  CONSTRAINT `coupon_issue_ibfk_2` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `coupon_issue_ibfk_3` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 24. 배너
CREATE TABLE `banner` (
  `banner_no` int NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(100) NOT NULL,
  `banner_size` varchar(50) DEFAULT NULL,
  `bg_color` varchar(20) DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `banner_position` varchar(50) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `sort_order` int NOT NULL DEFAULT '0',
  `start_datetime` datetime DEFAULT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `use_yn` char(1) NOT NULL DEFAULT 'Y',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`banner_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 25. 고객센터 카테고리
CREATE TABLE `cs_category` (
  `cs_cate_no` int NOT NULL AUTO_INCREMENT,
  `parent_no` int DEFAULT NULL,
  `cate_name` varchar(100) NOT NULL,
  `depth` tinyint NOT NULL DEFAULT '1',
  `sort_order` int NOT NULL DEFAULT '0',
  `use_yn` char(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`cs_cate_no`),
  KEY `parent_no` (`parent_no`),
  CONSTRAINT `cs_category_ibfk_1` FOREIGN KEY (`parent_no`) REFERENCES `cs_category` (`cs_cate_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 26. 공지사항
CREATE TABLE `notice` (
  `notice_no` int NOT NULL AUTO_INCREMENT,
  `writer_no` int NOT NULL,
  `notice_type` varchar(50) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_no`),
  KEY `writer_no` (`writer_no`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`writer_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 27. 자주 묻는 질문
CREATE TABLE `faq` (
  `faq_no` int NOT NULL AUTO_INCREMENT,
  `cs_cate_no` int NOT NULL,
  `writer_no` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`faq_no`),
  KEY `cs_cate_no` (`cs_cate_no`),
  KEY `writer_no` (`writer_no`),
  CONSTRAINT `faq_ibfk_1` FOREIGN KEY (`cs_cate_no`) REFERENCES `cs_category` (`cs_cate_no`),
  CONSTRAINT `faq_ibfk_2` FOREIGN KEY (`writer_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 28. 1:1 문의
CREATE TABLE `qna` (
  `qna_no` bigint NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `cs_cate_no` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `answer` text,
  `answer_member_no` int DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT '검토중',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `answer_date` datetime DEFAULT NULL,
  PRIMARY KEY (`qna_no`),
  KEY `member_no` (`member_no`),
  KEY `cs_cate_no` (`cs_cate_no`),
  KEY `answer_member_no` (`answer_member_no`),
  CONSTRAINT `qna_ibfk_1` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`),
  CONSTRAINT `qna_ibfk_2` FOREIGN KEY (`cs_cate_no`) REFERENCES `cs_category` (`cs_cate_no`),
  CONSTRAINT `qna_ibfk_3` FOREIGN KEY (`answer_member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 29. 채용 공고
CREATE TABLE `recruit` (
  `recruit_no` int NOT NULL AUTO_INCREMENT,
  `writer_no` int NOT NULL,
  `title` varchar(200) NOT NULL,
  `department` varchar(50) DEFAULT NULL,
  `career` varchar(50) DEFAULT NULL,
  `recruit_type` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `note` text,
  `status` varchar(20) NOT NULL DEFAULT '모집중',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`recruit_no`),
  KEY `writer_no` (`writer_no`),
  CONSTRAINT `recruit_ibfk_1` FOREIGN KEY (`writer_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 30. 사이트 기본 설정
CREATE TABLE `site_setting` (
  `setting_no` int NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(100) NOT NULL,
  `setting_value` text,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`setting_no`),
  UNIQUE KEY `setting_key` (`setting_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 31. 회사소개 / 문화 / 소식 / 미디어 콘텐츠
CREATE TABLE `company_content` (
  `content_no` int NOT NULL AUTO_INCREMENT,
  `content_type` varchar(30) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text,
  `image_path` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `category_name` varchar(100) DEFAULT NULL,
  `use_yn` char(1) NOT NULL DEFAULT 'Y',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`content_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 32. 버전 관리
CREATE TABLE `app_version` (
  `version_no` int NOT NULL AUTO_INCREMENT,
  `version_name` varchar(50) NOT NULL,
  `writer_no` int DEFAULT NULL,
  `change_log` text NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`version_no`),
  KEY `writer_no` (`writer_no`),
  CONSTRAINT `app_version_ibfk_1` FOREIGN KEY (`writer_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 33. 방문자수 관리
CREATE TABLE `visit_daily` (
  `visit_date` date NOT NULL,
  `visit_count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`visit_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 34. 고객 알림 관리 (★ 신규 추가)
CREATE TABLE `notification` (
  `notification_no` bigint NOT NULL AUTO_INCREMENT,
  `member_no` int NOT NULL,
  `notification_type` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(255) NOT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `order_no` bigint DEFAULT NULL,
  `delivery_no` int DEFAULT NULL,
  `event_key` varchar(100) NOT NULL,
  `read_yn` char(1) NOT NULL DEFAULT 'N',
  `reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `read_date` datetime DEFAULT NULL,
  PRIMARY KEY (`notification_no`),
  UNIQUE KEY `uk_notification_event_key` (`event_key`),
  KEY `idx_notification_member` (`member_no`,`read_yn`,`reg_date`),
  KEY `idx_notification_order` (`order_no`),
  KEY `idx_notification_delivery` (`delivery_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
</details>

---
<details>
<summary><b> 📊 테이블 구조 및 컬럼 설명 </b></summary>

### 📊 테이블 구조 및 컬럼 설명

■ 본 프로젝트는 총 33개의 테이블로 구성되어 있으며, 주요 비즈니스 흐름을 담당하는 핵심 테이블 구조는 다음과 같습니다.

- **member**: 회원(USER, SELLER, ADMIN) 통합 관리 및 권한, 포인트 상태 저장
- **member_social**: 소셜 로그인 연동 (NAVER, KAKAO, GOOGLE 등) 식별값 매핑
- **policy**: 쇼핑몰 이용약관 및 개인정보처리방침 마스터 데이터 관리
- **seller_profile**: 판매자 사업자 정보 및 입점 상점 세부 정보 관리
- **category**: N차 상품 카테고리 계층 구조 분류 및 노출 순서 제어
- **product / product_image**: 상품 기본 정보(재고, 정가, 할인율) 및 멀티미디어 썸네일 경로 관리
- **product_option**: 상품별 추가 선택 항목(색상, 사이즈 등) 구조 관리
- **product_notice**: 소비자 보호를 위한 전자상거래 상품정보 제공고시 규격 저장
- **cart / cart_option**: 사용자별 장바구니에 담긴 상품 정보 및 다중 선택 옵션 매핑
- **orders / order_item / order_item_option**: 전체 주문 정보 및 주문 시점의 스냅샷 데이터(가격, 옵션값 고정) 저장
- **delivery**: 입점 판매자별/출고처별 분할 배송 및 송장번호, 배송 상태 추적 관리
- **order_claim / claim_file**: 상품별 반품, 교환 클레임 신청 내역 및 증빙용 첨부파일 관리
- **product_review / review_image**: 구매 확정 상품 기준의 포토 후기, 평점 및 이미지 관리
- **member_point**: 회원의 포인트 적립, 사용, 회수 등 정밀 원장 이력 관리
- **coupon / coupon_product / coupon_issue**: 프로모션 쿠폰 생성, 적용 상품 바인딩 및 회원 발급/사용 이력 추적
- **cs_category / notice / faq / qna**: 통합 고객센터 카테고리 분류 및 공지사항, FAQ, 1:1 문의 답변 프로세스 관리
- **recruit**: 쇼핑몰 기업 채용 공고 게시판 관리
- **site_setting**: 사이트명, 대표 연락처 등 시스템 전반의 동적 환경설정 관리
- **company_content**: 회사 소개, 기업 문화, 보도 자료 및 미디어 콘텐츠 아카이빙
- **app_version**: 플랫폼 시스템 소프트웨어 버전 및 릴리즈 노트 체인지로그 관리
- **visit_daily**: 통계 분석을 위한 일별 방문자수 데이터 관리
- **notification**: 주문 진행 현황, 배송 상태 업데이트 등 회원별 동적 맞춤 알림 발송 관리 (★ 신규 추가)

---

#### 1. member (회원)
| 컬럼명 | 설명 |
|---|---|
| `member_no` | 회원 PK (내부 식별 고유 번호) |
| `member_id` | 일반 로그인 아이디 (소셜 가입 회원은 NULL 가능) |
| `password` | BCrypt로 암호화하여 저장하는 일반 로그인 비밀번호 |
| `name` | 회원 실명 |
| `gender` | 회원 성별 (M: 남성, F: 여성 등) |
| `birth` | 회원 생년월일 |
| `email` | 회원 이메일 주소 (UNIQUE) |
| `hp` | 회원 휴대폰 번호 |
| `role` | 권한 구분 (USER: 일반 회원, SELLER: 판매자, ADMIN: 관리자) |
| `grade` | 회원 등급 (SILVER, GOLD, VIP 등) |
| `point` | 현재 회원이 보유하여 사용 가능한 최종 잔여 포인트 수치 |
| `status` | 회원 계정 상태 (정상, 중지, 휴면, 탈퇴) |
| `zip` | 우편번호 |
| `addr1` | 도로명/지번 기본 주소 |
| `addr2` | 상세 주소 (동·호수 등) |
| `last_login_date` | 마지막 로그인 일시 |
| `leave_date` | 회원 탈퇴 처리 완료 일시 |
| `reg_date` | 회원 가입 일시 |

#### 2. member_social (소셜 로그인 연동)
| 컬럼명 | 설명 |
|---|---|
| `social_no` | 소셜 로그인 연동 PK |
| `member_no` | 연결되는 일반 회원 번호 (FK) |
| `provider` | 소셜 로그인 제공처 (NAVER, KAKAO, GOOGLE 등) |
| `provider_user_id` | 소셜 제공사측에서 발급한 사용자의 유니크한 고유 식별값 |
| `provider_email` | 소셜 로그인 과정에서 전달받은 소셜 계정 이메일 |
| `reg_date` | 소셜 계정 연동 및 연동 최초 일시 |

#### 3. policy (약관)
| 컬럼명 | 설명 |
|---|---|
| `policy_no` | 약관 PK |
| `policy_type` | 약관 구분 식별자 (BUYER: 구매자 이용약관, SELLER: 판매자 이용약관, PRIVACY: 개인정보처리방침 등) |
| `title` | 약관 제목 |
| `content` | 약관 본문 내용 (마크다운 혹은 HTML 텍스트) |
| `required_yn` | 가입 시 필수 동의 항목 여부 (Y: 필수, N: 선택) |
| `use_yn` | 현재 시점에 화면 노출 및 적용 중인 약관인지 여부 (Y/N) |
| `update_date` | 약관이 마지막으로 개정 및 수정된 시각 |

#### 4. seller_profile (판매자 상점 정보)
| 컬럼명 | 설명 |
|---|---|
| `seller_no` | 판매자 상점 정보 PK |
| `member_no` | 판매자 권한 계정과 연결되는 회원 번호 (FK, UNIQUE) |
| `company_name` | 상호명 및 법인명 |
| `ceo_name` | 대표자 성명 |
| `biz_no` | 사업자등록번호 (UNIQUE) |
| `online_sale_no` | 통신판매업 신고번호 |
| `tel` | 판매자 고객센터 전화번호 및 A/S 대표 연락처 |
| `fax` | 판매자 팩스번호 |
| `zip` | 사업장 주소 우편번호 |
| `addr1` | 사업장 기본 주소 |
| `addr2` | 사업장 상세 주소 |
| `status` | 판매 상점 상태 (운영준비, 승인대기, 운영중, 중단) |
| `reg_date` | 판매자 상점 최초 신청 및 등록 일시 |

#### 5. category (상품 카테고리)
| 컬럼명 | 설명 |
|---|---|
| `cate_no` | 상품 카테고리 PK |
| `parent_no` | 상위 카테고리 번호 (1차 카테고리일 경우 NULL, 2차일 경우 상위 1차 번호 참조) |
| `cate_name` | 카테고리 노출 명칭 |
| `depth` | 카테고리 차수 계층 단계 구분 (1: 1차 대분류, 2: 2차 중분류) |
| `sort_order` | 카테고리 메뉴의 화면 출력 및 정렬 우선순위 |
| `use_yn` | 카테고리 활성화 및 노출 여부 (Y/N) |

#### 6. product (상품)
| 컬럼명 | 설명 |
|---|---|
| `product_no` | 상품 PK |
| `seller_no` | 상품을 공급 및 등록한 판매자 번호 (FK) |
| `cate_no` | 상품이 속한 최종 2차 카테고리 번호 (FK) |
| `product_name` | 상품명 |
| `basic_desc` | 상품 목록 리스트 뷰에 표기될 짤막한 한 줄 소개 요약글 |
| `brand` | 상품 제조 브랜드 명칭 |
| `price` | 할인 적용 전 상품 정가 (소비자가) |
| `discount_rate` | 상품 할인율 (퍼센트 단위 수치) |
| `point` | 해당 상품 구매 시 최종 지급 및 적립할 포인트 레이트 수치 |
| `stock` | 상품의 전체 실재고 수량 (모든 옵션이 공통으로 사용하는 재고 원장) |
| `delivery_fee` | 상품 기본 기본 배송비 비용 |
| `status` | 현재 판매 상태 제어 코드 (판매중, 판매중지, 품절) |
| `view_count` | 상품 상세 페이지 누적 조회수 (Hit) |
| `sold_count` | 누적 판매 및 출고 수량 |
| `reg_date` | 상품 최초 등록 시각 |

#### 7. product_image (상품 이미지)
| 컬럼명 | 설명 |
|---|---|
| `image_no` | 상품 이미지 PK |
| `product_no` | 이미지가 소속된 대상 상품 번호 (FK) |
| `image_type` | 이미지 역할 종류 구분 (THUMB1, THUMB2, THUMB3, MAIN, DETAIL 목록 및 본문 구분) |
| `image_path` | 파일 스토리지 서버에 저장된 물리 파일 상대 경로 |
| `sort_order` | 특히 상세 이미지 여러 장 노출 시 화면 출력 배열 순서 |
| `reg_date` | 이미지 파일 업로드 등록 시각 |

#### 8. product_option (상품 옵션)
| 컬럼명 | 설명 |
|---|---|
| `option_no` | 상품 옵션 PK |
| `product_no` | 옵션 항목이 종속된 대상 상품 번호 (FK) |
| `option_group_no`| 화면 UI 구성을 위한 옵션 대분류 그룹 그룹화 번호 (1: 색상, 2: 사이즈 등) |
| `option_name` | 옵션 종류 타이틀 표기명 (ex: "색상", "사이즈") |
| `option_value` | 사용자가 마주할 드롭다운 실제 선택 명칭 (ex: "블랙", "100") |
| `sort_order` | 드롭다운 셀렉트 박스 내부에서의 출력 순서 정렬값 |

#### 9. product_notice (상품정보 제공고시)
| 컬럼명 | 설명 |
|---|---|
| `notice_no` | 상품정보 제공고시 PK |
| `product_no` | 제공고시 명세가 연동되는 대상 상품 번호 (FK, UNIQUE) |
| `product_status` | 상품의 상태 고시 문구 (신상품, 중고품 등) |
| `tax_type` | 과세 형태 구분 문구 (과세상품, 면세상품) |
| `receipt_type` | 영수증 발행 가능 범위 안내 문구 |
| `business_type` | 판매자 형태 구분 안내 (개인사업자, 법인사업자) |
| `origin` | 제조국 및 최종 원산지 정보 문구 |

#### 10. cart (장바구니)
| 컬럼명 | 설명 |
|---|---|
| `cart_no` | 장바구니 PK |
| `member_no` | 장바구니에 상품을 담은 주체인 회원 번호 (FK) |
| `product_no` | 장바구니에 담긴 원본 상품 번호 (FK) |
| `option_signature`| 선택한 다중 옵션 번호 조합을 정렬 후 쉼표로 결합한 고유식별문자열 (중복 적재 방지용 내부 연산 필드) |
| `quantity` | 장바구니에 담은 희망 상품 수량 |
| `reg_date` | 장바구니 최초 적재 시각 |

#### 11. cart_option (장바구니 선택 옵션)
| 컬럼명 | 설명 |
|---|---|
| `cart_no` | 선택 옵션이 묶이는 부모 장바구니 번호 (FK) |
| `option_no` | 회원이 장바구니에 담을 때 명시한 개별 상품 옵션 번호 (FK) |

#### 12. orders (주문)
| 컬럼명 | 설명 |
|---|---|
| `order_no` | 주문 PK (시스템 관리용 고유 내부 번호) |
| `order_code` | 외부 및 사용자단 영수증에 출력 표기할 노출용 유니크 주문 번호 |
| `member_no` | 주문 결제를 수행한 구매 주체 회원 번호 (FK) |
| `orderer_name` | 결제 당시 기입한 주문자 성명 |
| `orderer_hp` | 결제 당시 기입한 주문자 연락처 휴대폰 번호 |
| `total_price` | 주문에 포함된 모든 상품들의 순수 정가 금액 총합계 |
| `discount_price` | 상품 자체 할인율 정책으로 차감된 금액의 총합계 |
| `coupon_discount`| 회원이 사용 적용한 쿠폰 혜택으로 할인된 총 누적 차감 금액 |
| `delivery_fee` | 주문 전체 건에 매겨진 실질 배송비 부과 비용 합계 |
| `point_use` | 주문 결제 시 차감 차용한 회원의 포인트 사용량 |
| `point_save` | 주문 결제 확정 후 향후 적립 지급 대기 예정인 포인트 총액 |
| `pay_price` | 최종 PG 결제창에 연동되어 실제로 회원이 지불한 실결제 금액 |
| `payment_method` | 결제 수단 종류 (신용카드, 계좌이체, 무통장입금, 카카오페이 등) |
| `payment_status` | 결제 재무 상태 정보 제어 코드 (결제대기, 결제완료, 결제취소, 환불완료) |
| `paid_date` | PG사 승인 처리가 완결되어 입금이 확정된 시간 |
| `cancel_date` | 결제 취소 또는 전액 환불 처리가 완료된 시간 |
| `order_status` | 주문 마스터 트랜잭션 전체를 대표하는 현재 프로세스 흐름 상태 |
| `order_date` | 최초 주문서 인스턴스 생성 일시 |

#### 13. delivery (배송)
| 컬럼명 | 설명 |
|---|---|
| `delivery_no` | 배송 PK |
| `order_no` | 배송 정보가 종속된 결제 주문 번호 (FK) |
| `seller_no` | 해당 택배 출고 배송 건을 직접 책임 처리하는 입점 판매자 번호 (FK) |
| `receiver_name` | 배송지 목적지 수령인 성명 |
| `receiver_hp` | 수령인 비상 연락처 |
| `zip` | 수령 주소지 우편번호 |
| `addr1` | 수령 주소지 기본 주소 |
| `addr2` | 수령 주소지 상세 주소 명시 |
| `courier` | 배송 담당 택배사 명칭 (ex: CJ대한통운, 한진택배) |
| `invoice_no` | 출고 시 입력 매킹 완료된 화물 운송장번호 |
| `delivery_fee` | 해당 판매자가 배송을 보내기 위해 책정한 개별 물류 배송비 비용 |
| `delivery_status`| 실제 물류 처리 상태 (배송준비, 배송중, 배송완료) |
| `memo` | 배송 기사님께 남기는 전달 현장 요청 메시지 |
| `receipt_date` | 배송 접수 및 송장 발행일시 |
| `shipped_date` | 집하가 완료되어 터미널 배송이 시작된 최초 출발 시간 |
| `delivered_date` | 최종 배송원 배송 완료 처리 시각 |

#### 14. order_item (주문 상품 상세)
| 컬럼명 | 설명 |
|---|---|
| `order_item_no` | 주문상품 PK |
| `order_no` | 주문상품 항목들이 속해있는 부모 주문 번호 (FK) |
| `delivery_no` | 해당 단일 상품이 합포장 출고 처리된 타겟 배송 번호 (FK, 미출고 시 NULL) |
| `product_no` | 근간이 되는 원본 상품 번호 (FK) |
| `seller_no` | 주문 결제 시점 당시의 공급 판매자 번호 (FK) |
| `product_name` | 주문 결제 시점 당시 원본 상품에서 복사 아카이빙해온 상품명 스냅샷 |
| `product_image` | 주문 결제 시점 당시 원본 상품의 메인 대표 썸네일 이미지 경로 스냅샷 |
| `price` | 주문 결제 시점 당시 변경 전 상품 단일 원본 정가 스냅샷 |
| `discount_rate` | 주문 결제 시점 당시 원본 상품의 단일 할인율 수치 스냅샷 |
| `coupon_discount`| 해당 개별 상품 품목 단독에 직접적으로 매킹 적용된 쿠폰 차감 할인액 |
| `point` | 해당 개별 상품 구매 확정 시 최종 누적 합산될 적립용 보상 포인트 피드값 |
| `quantity` | 해당 단일 상품 품목의 실제 구매 수량 |
| `total_price` | 수량과 할인을 정밀 가산하여 결정된 해당 주문 상품의 최종 정산 단가 총합액 |
| `item_status` | 개별 상품 단위 독립 클레임 추적 상태값 (주문완료, 구매확정, 취소, 반품, 교환) |
| `purchase_confirm_date`| 사용자가 직접 구매확정 버튼을 누르거나 자동 적치 완료되어 확정된 최종 시각 |

#### 15. order_item_option (주문상품 선택 옵션)
| 컬럼명 | 설명 |
|---|---|
| `order_item_option_no`| 주문상품 선택 옵션 PK |
| `order_item_no` | 사용자가 선택 복사해온 기준 타겟 주문상품 번호 (FK) |
| `option_group_no`| 색상, 사이즈 등 복사 시점의 옵션 성격 대분류 인덱스 코드 |
| `option_name` | 결제 시점의 옵션 종류 명칭 (ex: "색상") |
| `option_value` | 결제 시점의 구체적 옵션 선택값 명칭 (ex: "화이트") |

#### 16. order_claim (반품 / 교환 신청)
| 컬럼명 | 설명 |
|---|---|
| `claim_no` | 반품 또는 교환 신청 마스터 PK |
| `order_item_no` | 클레임 철회 혹은 처리가 발생한 대상 단일 주문상품 번호 (FK) |
| `member_no` | 환불/교환 접수를 발의한 주체 회원 번호 (FK) |
| `claim_type` | 클레임 성격 분류 코드 (RETURN: 반품/환불, EXCHANGE: 사이즈/색상 교환) |
| `claim_reason` | 드롭다운 선택형 정형화 사유 요약 (단순변심, 상품불량, 오배송 등) |
| `detail_reason` | 소비자가 텍스트 영역에 직접 서술 타이핑한 구체적 명세 사유 내용 |
| `status` | 클레임 접수 처리 워크플로우 제어 (신청, 승인, 반려, 수거중, 완료) |
| `request_date` | 클레임 최초 인스턴스 인입 접수 시각 |
| `process_date` | 판매자 혹은 시스템 총괄자가 승인/반려/완료 판정을 내려 완결 지은 시각 |

#### 17. claim_file (반품 / 교환 첨부파일)
| 컬럼명 | 설명 |
|---|---|
| `claim_file_no` | 반품 또는 교환 입증용 첨부파일 PK |
| `claim_no` | 증빙 파일 서류들이 종속 바인딩된 대상 클레임 신청 번호 (FK) |
| `ori_name` | 사용자 로컬 PC 기준 업로드 원본 파일명 명칭 |
| `new_name` | 서버 내부 난수화 암호 가공 처리된 난수 변경 파일명 명칭 |
| `reg_date` | 파일 물리 스토리지 업로드 완료 시각 |

#### 18. product_review (상품 리뷰)
| 컬럼명 | 설명 |
|---|---|
| `review_no` | 상품 리뷰 PK |
| `order_item_no` | 리뷰 대상 타겟 주문상품 번호 (FK, 데이터 정합성 보장 1:1 영수증 UNIQUE) |
| `member_no` | 리뷰 문서를 직접 서술 기입한 작성자 회원 번호 (FK) |
| `product_no` | 후기 통계 누적 노출 대상이 되는 원본 상품 마스터 번호 (FK) |
| `rating` | 만족도 스코어 부여 점수 수치 (1점 최저부터 5점 만점 기준 별점) |
| `content` | 리뷰 본문 텍스트 텍스트 내용 |
| `reg_date` | 리뷰 작성 및 전송 완료 시각 |

#### 19. review_image (리뷰 이미지)
| 컬럼명 | 설명 |
|---|---|
| `review_image_no`| 리뷰 이미지 첨부파일 PK |
| `review_no` | 이미지가 포함 결합되어 노출될 대상 부모 리뷰 번호 (FK) |
| `image_path` | 이미지 파일 서버 물리 저장 상대 경로 |
| `sort_order` | 포토 후기 멀티 노출 시 가로 출력 순서 |

#### 20. member_point (회원 포인트 이력)
| 컬럼명 | 설명 |
|---|---|
| `point_no` | 포인트 이력 원장 PK |
| `member_no` | 포인트 증감 변동 파이프라인 대상 타겟 회원 번호 (FK) |
| `order_no` | 쇼핑몰 물건 구매로 파생 및 소모 연동된 경우 연결 지을 결제 주문 번호 (FK, 일반 지급 시 NULL) |
| `point_type` | 변동 가산 구분 식별자 코드 (적립, 사용, 차감, 환불) |
| `point_value` | 이번 트랜잭션 건으로 실질 변동 가감 처리되는 포인트 가치량 수치 |
| `balance_point` | 해당 변동 처리가 완결 반영된 직후 시점의 정밀 잔여 최종 스냅샷 포인트 총합 |
| `reason` | 적립/소모 사유 표기 문구 (회원가입 축하, 상품 구매확정 적립, 결제 사용 차감 등) |
| `expire_date` | 적립 포인트 정책에 따른 해당 포인트의 자연 소멸 예정 마감 기한일 |
| `reg_date` | 포인트 이력 트랜잭션 기록 반영 시각 |

#### 21. coupon (쿠폰)
| 컬럼명 | 설명 |
|---|---|
| `coupon_no` | 쿠폰 마스터 정보 PK |
| `coupon_code` | 프로모션 난수화 입력 식별 코드 및 쿠폰 마스터 번호 (UNIQUE) |
| `seller_no` | 쿠폰 발행 주체인 입점 판매자 번호 (전체 공통 및 본사 쿠폰일 시 NULL) |
| `coupon_type` | 쿠폰 적용 가능 대상 바운더리 구분 스코프 (개별상품할인, 주문상품할인, 배송비무료) |
| `coupon_name` | 고객이 다운로드 화면에서 확인할 쿠폰 정식 명칭 |
| `benefit_type` | 혜택 가산 연산 산식 종류 구분 식별자 (금액할인, 비율할인, 무료배송) |
| `benefit_value` | 실제 깎아줄 할인 절대 금액 혹은 정밀 할인율 비율 수치 |
| `min_order_price`| 쿠폰을 장바구니에 먹이기 위해 충족해야 할 최소 결제 주문 하한선 금액 |
| `max_discount_price`| 비율할인형 쿠폰 조건 충족 시 최대로 방어 차단할 커트라인 한도 할인 금액 |
| `issue_limit` | 총 마스터 쿠폰 풀에서 최대로 발행 허용 가능한 수량 제한값 (제한 없을 시 NULL) |
| `start_date` | 쿠폰의 동적 활성화 사용 시작 가능 기준일 |
| `end_date` | 쿠폰이 소멸하는 최종 사용 유효 기한 마감일 |
| `caution` | 쿠폰 하단에 기재될 사용 조건 예외 처리 유의사항 안내문 |
| `status` | 쿠폰 자체의 발급 가능 운영 상태 정보 (사용, 종료) |
| `reg_date` | 관리자가 쿠폰 마스터 풀을 최초 개설 등록한 시각 |

#### 22. coupon_product (쿠폰 적용 상품)
| 컬럼명 | 설명 |
|---|---|
| `coupon_no` | 발급 범위가 '개별상품할인'인 타겟 원본 쿠폰 번호 (FK) |
| `product_no` | 해당 전용 쿠폰으로 할인을 받을 수 있게 매핑 바인딩 처리한 타겟 상품 번호 (FK) |

#### 23. coupon_issue (회원 쿠폰 발급 이력)
| 컬럼명 | 설명 |
|---|---|
| `issue_no` | 회원 쿠폰 발급 이력 PK |
| `issue_code` | 회원 지갑 내부의 개별 고유 낱개 발행 쿠폰 일련 식별 일련번호 (UNIQUE) |
| `coupon_no` | 모태가 되는 부모 원본 쿠폰 마스터 번호 (FK) |
| `member_no` | 쿠폰을 다운로드하여 보유 중인 실제 회원 번호 (FK) |
| `order_no` | 이미 사용 완료한 쿠폰인 경우 적용되어 태워진 결제 주문 번호 (FK, 미사용 시 NULL) |
| `status` | 회원이 지닌 개별 쿠폰 인스턴스 현재 상태 (발급, 사용, 만료, 종료) |
| `used_date` | 결제창에서 실질적으로 쿠폰 소모 처리를 완결 지은 시각 |
| `issue_date` | 사용자가 다운로드 버튼을 눌러 보관함에 쿠폰을 수령 적치한 시각 |

#### 24. banner (배너)
| 컬럼명 | 설명 |
|---|---|
| `banner_no` | 전시 배너 PK |
| `banner_name` | 관리 시스템 식별 및 통계용 배너 내부 관리 명칭 |
| `banner_size` | 프론트 가이드라인 권장 가로x세로 해상도 크기 정보 스펙 |
| `bg_color` | 풀와이드 배너의 경우 양측을 채울 CSS 헥사 배경색 코드값 |
| `link_url` | 배너 이미지 클릭 시 랜딩 이동할 아웃바운드/인바운드 목적지 주소 |
| `banner_position`| 템플릿 영역 렌더링 노출 레이아웃 위치값 코드 (MAIN1, PRODUCT1, MEMBER1, MY1) |
| `image_path` | 배너 에셋 이미지 보관 물리 저장 파일 경로 |
| `sort_order` | 슬라이더 등 동일 영역 복수 배너 순환 노출 시 가중치 출력 순서 |
| `start_datetime` | 배너 마케팅 노출 개시 자동 타이머 시작 일시 |
| `end_datetime` | 배너 노출 자동 오프 타이머 마감 일시 |
| `use_yn` | 시스템 노출 강제 토글 플래그 (Y: 노출, N: 숨김) |
| `reg_date` | 배너 최초 등록 시각 |

#### 25. cs_category (고객센터 카테고리)
| 컬럼명 | 설명 |
|---|---|
| `cs_cate_no` | 고객센터 대분류/소분류 분류 체계 PK |
| `parent_no` | 계층형 구조용 상위 고객센터 카테고리 코드 (1차 메뉴 시 NULL) |
| `cate_name` | 고객이 분류 탭에서 마주할 직관적인 메뉴 명칭 (회원, 주문/결제, 배송, 반품/환불 등) |
| `depth` | 고객센터 메뉴 차수 깊이 (1: 대분류 탭, 2: 세부 속성 분류 소분류) |
| `sort_order` | 고객센터 메인 화면의 메뉴 탭 좌측 출력 및 정렬 배열 순서 |
| `use_yn` | 해당 상담 및 고시 분류 카테고리 운영 가동 여부 (Y/N) |

#### 26. notice (공지사항)
| 컬럼명 | 설명 |
|---|---|
| `notice_no` | 공지사항 게시글 PK |
| `writer_no` | 공지를 기안 작성한 관리 권한을 지닌 마스터 회원 번호 (FK) |
| `notice_type` | 공지사항 성격 분류 태그 속성값 (일반공지, 이벤트공지, 점검공지) |
| `title` | 공지사항 게시글 제목 |
| `content` | 공지사항 상세 안내 본문 텍스트 내역 |
| `hit` | 게시글 단독 누적 단순 조회 뷰 카운트 수치 |
| `reg_date` | 공지사항 초안 작성 및 최초 발행 시각 |
| `update_date` | 공지 내용에 오타 등이 있어 관리자가 재수정한 최종 갱신 시각 |

#### 27. faq (자주 묻는 질문)
| 컬럼명 | 설명 |
|---|---|
| `faq_no` | FAQ 게시글 PK |
| `cs_cate_no` | FAQ 게시글이 포지셔닝될 최종 타겟 고객센터 상담 분류 번호 (FK) |
| `writer_no` | FAQ 명세를 작성 기입한 시스템 운영 관리자 회원 번호 (FK) |
| `title` | 빈번하게 발생하는 요약 질문 형태의 타이틀 제목 |
| `content` | 질문에 대해 완결형으로 서술해둔 정형화된 정답 및 답변 안내 본문 |
| `hit` | FAQ 문서 단순 누적 조회수 |
| `reg_date` | FAQ 최초 작성 보관 등록 시각 |
| `update_date` | FAQ 최신 정책 변경으로 내용 문구를 마지막으로 가공 수정한 시각 |

#### 28. qna (1:1 문의)
| 컬럼명 | 설명 |
|---|---|
| `qna_no` | 1:1 민원 문의 신청 PK |
| `member_no` | 질문 민원을 접수 인입시킨 작성 사용자 회원 번호 (FK) |
| `cs_cate_no` | 문의할 영역으로 사용자가 선택 지정한 상담 분류 속성 코드 (FK) |
| `title` | 고객 문의 제목 |
| `content` | 고객 문의 세부 텍스트 서술 본문 내용 |
| `answer` | 고객센터 전담 상담사 혹은 관리자가 검토 후 하단에 달아준 공식 답변 내용 |
| `answer_member_no`| 실제 답변 데이터 입력 처리를 수행한 담당 관리자 회원 번호 (FK, 미답변 시 NULL) |
| `status` | 민원 처리 대응 내부 파이프라인 지표 제어 코드 (검토중, 답변완료) |
| `reg_date` | 사용자가 문의하기 완료 버튼을 누른 최초 접수 시각 |
| `answer_date` | 관리자가 답변을 최종 작성 등록 완료하여 고객에게 알림이 전출된 시각 |

#### 29. recruit (채용 공고)
| 컬럼명 | 설명 |
|---|---|
| `recruit_no` | 채용 공고 마스터 PK |
| `writer_no` | 인사 직무 채용 공고를 게시 등록한 사내 담당 관리자 회원 번호 (FK) |
| `title` | 채용 공고 포스팅 제목 |
| `department` | 신규 충원 및 포지션이 발생하는 사내 소속 모집 부서 명칭 |
| `career` | 채용 자격 요건 요구 경력 조건 필터값 명칭 (신입, 경력, 경력무관) |
| `recruit_type` | 근로 계약 고용 형태 구분값 명칭 (정규직, 계약직, 인턴) |
| `start_date` | 채용 전형 서류 접수 자동 개시 시작일 |
| `end_date` | 채용 공고 서류 접수 자동 셧다운 마감일 |
| `note` | 직무 기술서(JD), 전형 절차, 우대 사항 등 채용 공고 세부 안내 본문 내용 |
| `status` | 현재 전형의 모니터링 노출 상태 (모집중, 마감) |
| `reg_date` | 채용 공고 포스팅 생성 일시 |

#### 30. site_setting (사이트 기본 설정)
| 컬럼명 | 설명 |
|---|---|
| `setting_no` | 사이트 환경설정 고유 엔티티 PK |
| `setting_key` | 설정의 역할을 구분짓는 네임스페이스 상수형 룩업 유니크 키 (ex: 'site_name', 'company_tel') |
| `setting_value` | 키에 매칭되어 동적으로 프론트 전역에 뿌려질 세부 셋팅 텍스트/HTML 실제값 값 |
| `update_date` | 설정 데이터를 마지막으로 변경 저장한 시각 |

#### 31. company_content (회사 소개 콘텐츠)
| 컬럼명 | 설명 |
|---|---|
| `content_no` | 회사 브랜드 아카이브 콘텐츠 PK |
| `content_type` | 기업 콘텐츠 노출 섹션 도메인 속성 구분자 (INTRO: 회사소개, CULTURE: 기업문화, STORY: 성장스토리, MEDIA: 언론보도) |
| `title` | 보도자료 및 홍보 포스팅 타이틀 제목 |
| `content` | 롱폼 형태의 홍보 브랜딩 본문 상세 내용 |
| `image_path` | 홍보 그리드 및 본문에 렌더링될 대표 배너 이미지 파일 저장 경로 |
| `video_url` | 유튜브, 비메오 등 외부 미디어 스트리밍 공유 링크 URL 주소 |
| `category_name` | 소식과 이야기 혹은 뉴스 미디어 내부의 미세 세부 서브 분류 명칭 |
| `use_yn` | 홍보 게시물 프론트 전역 아카이브 노출 활성화 여부 (Y/N) |
| `reg_date` | 기업 홍보 아카이브 포스팅 최초 작성 일시 (최신 뉴스 5개 렌더링 연산 기준 정렬값) |

#### 32. app_version (버전 관리)
| 컬럼명 | 설명 |
|---|---|
| `version_no` | 소프트웨어 버전 제어 인덱스 PK |
| `version_name` | 배포 및 업데이트 명시 규격화 버전명 명칭 (ex: "v1.0.0", "v1.2.4") |
| `writer_no` | 릴리즈 노트를 전산 등록한 전담 시스템 백엔드 개발자/관리자 회원 번호 (FK) |
| `change_log` | 이번 패치 배포에서 신규 추가/버그 수정/기능 개선 완결된 상세 명세 체인지로그 텍스트 |
| `reg_date` | 소프트웨어 버전 릴리즈 전산 배포 등록 완료 시각 |

#### 33. visit_daily (방문자수 관리)
| 컬럼명 | 설명 |
|---|---|
| `visit_date` | 비즈니스 일자 달력 기준 방문 날짜 (PK, DATE 형식 연-월-일) |
| `visit_count` | 해당 하루 동안 쇼핑몰 전역에 접속 트래픽을 일으킨 유니크 총 방문자 수 수치 카운터 |

#### 34. notification (고객 알림 관리 - ★ 신규 추가)
| 컬럼명 | 설명 |
|---|---|
| `notification_no` | 알림 레코드 PK (BIGINT형 고유 식별 번호) |
| `member_no` | 알림을 수신하는 대상 회원 번호 (FK) |
| `notification_type` | 알림의 성격 및 도메인 구분 코드 (ex: ORDER, DELIVERY, PROMOTION, CS 등) |
| `title` | 푸시 및 알림창에 렌더링될 요약 제목 |
| `content` | 알림 메시지 상세 본문 안내 문구 |
| `link_url` | 사용자가 알림 클릭 시 이동할 랜딩 페이지 주소 링크 경로 |
| `order_no` | 주문 진행 상황 알림일 경우 추적 바인딩되는 주문 번호 (미연관 시 NULL, FK) |
| `delivery_no` | 배송 정보 업데이트 알림일 경우 추적 바인딩되는 배송 번호 (미연관 시 NULL, FK) |
| `event_key` | 시스템 내 중복 전송 방지 및 비즈니스 이벤트 매핑용 유니크 룩업 키 (UNIQUE) |
| `read_yn` | 수신 회원의 알림 읽음 처리 여부 플래그 플래그 (Y: 읽음, N: 안읽음) |
| `reg_date` | 알림 최초 인입 및 발송 적치 완료 시각 |
| `read_date` | 회원이 해당 알림을 확인하고 읽은 시각 (미읽음 시 NULL) |

</details>

---

### ✅ 작업 진행 순서
- Figma 화면 설계서 분석

- 프로젝트 폴더 구조 세팅 및 공통 레이아웃/CSS 작성

- 담당 페이지 HTML 마크업 및 CSS 적용

- 전체 화면 링크 연결 및 UI 검수

- AWS RDS(MySQL) DB 테이블 구축 및 Spring Boot 연동

* 일정 및 작업 상세 내용은 notion 참고 : https://app.notion.com/p/2-6c4346ade5b783118cd7810fef52109d

### 🎯 프로젝트 목표
- 쇼핑몰 IA 구조에 맞는 완벽한 프론트엔드 화면 구현

- 담당 영역별 분리 작업을 통한 효율적인 백엔드 확장 및 평화로운 Git 협업

- 실무 비즈니스 로직(결제 스냅샷, 환불, 포인트, 쿠폰)을 지원하는 무결성 DB 구축

<img src="https://capsule-render.vercel.app/api?type=waving&color=9ACB34&theme=navy&height=150&section=footer&text=Thank%20you%20for%20reading!&fontSize=30&animation=fadeIn" width="100%" />
