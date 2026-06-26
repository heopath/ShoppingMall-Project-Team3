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
<summary><b> 📊 SQL-DDL </b></summary>

### 📊 SQL-DDL

```plaintext

CREATE DATABASE IF NOT EXISTS kmarket;
USE kmarket;

CREATE TABLE member (
    member_no INT AUTO_INCREMENT PRIMARY KEY, member_id VARCHAR(50) UNIQUE, password VARCHAR(255), name VARCHAR(50) NOT NULL, gender CHAR(1), birth DATE, email VARCHAR(100) UNIQUE, hp VARCHAR(20), role VARCHAR(20) NOT NULL DEFAULT 'USER', grade VARCHAR(20) NOT NULL DEFAULT 'SILVER', point INT NOT NULL DEFAULT 0, status VARCHAR(20) NOT NULL DEFAULT '정상', zip VARCHAR(10), addr1 VARCHAR(255), addr2 VARCHAR(255), last_login_date DATETIME, leave_date DATETIME, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_social (
    social_no BIGINT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, provider VARCHAR(20) NOT NULL, provider_user_id VARCHAR(255) NOT NULL, provider_email VARCHAR(100), reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, UNIQUE (provider, provider_user_id), UNIQUE (member_no, provider), FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE policy (
    policy_no INT AUTO_INCREMENT PRIMARY KEY, policy_type VARCHAR(50) NOT NULL, title VARCHAR(100) NOT NULL, content TEXT NOT NULL, required_yn CHAR(1) NOT NULL DEFAULT 'Y', use_yn CHAR(1) NOT NULL DEFAULT 'Y', update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_agreement (
    agreement_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, policy_no INT NOT NULL, agree_yn CHAR(1) NOT NULL DEFAULT 'Y', agree_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (policy_no) REFERENCES policy(policy_no)
);

CREATE TABLE seller_profile (
    seller_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL UNIQUE, company_name VARCHAR(100) NOT NULL, ceo_name VARCHAR(50) NOT NULL, biz_no VARCHAR(30) NOT NULL UNIQUE, online_sale_no VARCHAR(50), tel VARCHAR(20), fax VARCHAR(20), zip VARCHAR(10), addr1 VARCHAR(255), addr2 VARCHAR(255), status VARCHAR(20) NOT NULL DEFAULT '운영준비', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE category (
    cate_no INT AUTO_INCREMENT PRIMARY KEY, parent_no INT DEFAULT NULL, cate_name VARCHAR(100) NOT NULL, depth TINYINT NOT NULL DEFAULT 1, sort_order INT NOT NULL DEFAULT 0, use_yn CHAR(1) NOT NULL DEFAULT 'Y', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (parent_no) REFERENCES category(cate_no)
);

CREATE TABLE product (
    product_no INT AUTO_INCREMENT PRIMARY KEY, seller_no INT NOT NULL, cate_no INT NOT NULL, product_name VARCHAR(200) NOT NULL, basic_desc VARCHAR(255), manufacturer VARCHAR(100), brand VARCHAR(100), model_name VARCHAR(100), price INT NOT NULL DEFAULT 0, discount_rate INT NOT NULL DEFAULT 0, point INT NOT NULL DEFAULT 0, stock INT NOT NULL DEFAULT 0, delivery_fee INT NOT NULL DEFAULT 0, detail_content TEXT, status VARCHAR(20) NOT NULL DEFAULT '판매중', view_count INT NOT NULL DEFAULT 0, sold_count INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no), FOREIGN KEY (cate_no) REFERENCES category(cate_no)
);

CREATE TABLE product_image (
    image_no INT AUTO_INCREMENT PRIMARY KEY, product_no INT NOT NULL, image_type VARCHAR(30) NOT NULL, image_path VARCHAR(255) NOT NULL, sort_order INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE product_option (
    option_no INT AUTO_INCREMENT PRIMARY KEY, product_no INT NOT NULL, option_name VARCHAR(100) NOT NULL, option_value VARCHAR(100) NOT NULL, add_price INT NOT NULL DEFAULT 0, stock INT NOT NULL DEFAULT 0, use_yn CHAR(1) NOT NULL DEFAULT 'Y', FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE product_notice (
    notice_no INT AUTO_INCREMENT PRIMARY KEY, product_no INT NOT NULL UNIQUE, product_status VARCHAR(100), tax_type VARCHAR(100), receipt_type VARCHAR(100), business_type VARCHAR(100), origin VARCHAR(100), as_info VARCHAR(255), tel VARCHAR(20), detail_notice TEXT, FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE cart (
    cart_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, product_no INT NOT NULL, option_no INT DEFAULT NULL, option_key INT GENERATED ALWAYS AS (IFNULL(option_no, 0)) STORED, quantity INT NOT NULL DEFAULT 1, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, UNIQUE KEY uk_cart_member_product_option (member_no, product_no, option_key), FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (product_no) REFERENCES product(product_no), FOREIGN KEY (option_no) REFERENCES product_option(option_no)
);

CREATE TABLE orders (
    order_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_code VARCHAR(50) NOT NULL UNIQUE, member_no INT NOT NULL, orderer_name VARCHAR(50) NOT NULL, orderer_hp VARCHAR(20) NOT NULL, total_price INT NOT NULL DEFAULT 0, discount_price INT NOT NULL DEFAULT 0, coupon_discount INT NOT NULL DEFAULT 0, delivery_fee INT NOT NULL DEFAULT 0, point_use INT NOT NULL DEFAULT 0, point_save INT NOT NULL DEFAULT 0, pay_price INT NOT NULL DEFAULT 0, payment_method VARCHAR(30), payment_status VARCHAR(30) NOT NULL DEFAULT '결제대기', paid_date DATETIME, cancel_date DATETIME, order_status VARCHAR(30) NOT NULL DEFAULT '주문완료', order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE delivery (
    delivery_no INT AUTO_INCREMENT PRIMARY KEY, order_no BIGINT NOT NULL, seller_no INT NOT NULL, receiver_name VARCHAR(50) NOT NULL, receiver_hp VARCHAR(20) NOT NULL, zip VARCHAR(10), addr1 VARCHAR(255), addr2 VARCHAR(255), courier VARCHAR(50), invoice_no VARCHAR(50), delivery_status VARCHAR(30) NOT NULL DEFAULT '배송준비', memo TEXT, ready_date DATETIME, shipped_date DATETIME, delivered_date DATETIME, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (order_no) REFERENCES orders(order_no), FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no)
);

CREATE TABLE order_item (
    order_item_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_no BIGINT NOT NULL, delivery_no INT DEFAULT NULL, product_no INT NOT NULL, seller_no INT NOT NULL, option_name VARCHAR(100), option_value VARCHAR(100), product_name VARCHAR(200) NOT NULL, product_image VARCHAR(255), price INT NOT NULL DEFAULT 0, discount_rate INT NOT NULL DEFAULT 0, point INT NOT NULL DEFAULT 0, quantity INT NOT NULL DEFAULT 1, total_price INT NOT NULL DEFAULT 0, item_status VARCHAR(30) NOT NULL DEFAULT '주문완료', FOREIGN KEY (order_no) REFERENCES orders(order_no), FOREIGN KEY (delivery_no) REFERENCES delivery(delivery_no), FOREIGN KEY (product_no) REFERENCES product(product_no), FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no)
);

CREATE TABLE order_claim (
    claim_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_item_no BIGINT NOT NULL, member_no INT NOT NULL, claim_type VARCHAR(20) NOT NULL, claim_reason VARCHAR(100) NOT NULL, detail_reason TEXT, status VARCHAR(30) NOT NULL DEFAULT '신청', request_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, process_date DATETIME, FOREIGN KEY (order_item_no) REFERENCES order_item(order_item_no), FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE claim_file (
    claim_file_no BIGINT AUTO_INCREMENT PRIMARY KEY, claim_no BIGINT NOT NULL, ori_name VARCHAR(255) NOT NULL, new_name VARCHAR(255) NOT NULL, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (claim_no) REFERENCES order_claim(claim_no)
);

CREATE TABLE product_review (
    review_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_item_no BIGINT NOT NULL UNIQUE, member_no INT NOT NULL, product_no INT NOT NULL, rating TINYINT NOT NULL, content TEXT NOT NULL, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (order_item_no) REFERENCES order_item(order_item_no), FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE review_image (
    review_image_no BIGINT AUTO_INCREMENT PRIMARY KEY, review_no BIGINT NOT NULL, image_path VARCHAR(255) NOT NULL, sort_order INT NOT NULL DEFAULT 0, FOREIGN KEY (review_no) REFERENCES product_review(review_no)
);

CREATE TABLE member_point (
    point_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, order_no BIGINT DEFAULT NULL, point_type VARCHAR(20) NOT NULL, point_value INT NOT NULL, balance_point INT NOT NULL DEFAULT 0, reason VARCHAR(255), expire_date DATE, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (order_no) REFERENCES orders(order_no)
);

CREATE TABLE coupon (
    coupon_no INT AUTO_INCREMENT PRIMARY KEY, coupon_code VARCHAR(50) NOT NULL UNIQUE, seller_no INT DEFAULT NULL, coupon_type VARCHAR(30) NOT NULL, coupon_name VARCHAR(100) NOT NULL, benefit_type VARCHAR(20) NOT NULL, benefit_value INT NOT NULL DEFAULT 0, min_order_price INT NOT NULL DEFAULT 0, max_discount_price INT NOT NULL DEFAULT 0, issue_limit INT DEFAULT NULL, start_date DATE, end_date DATE, caution TEXT, status VARCHAR(20) NOT NULL DEFAULT '사용', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no)
);

CREATE TABLE coupon_product (
    coupon_no INT NOT NULL, product_no INT NOT NULL, PRIMARY KEY (coupon_no, product_no), FOREIGN KEY (coupon_no) REFERENCES coupon(coupon_no), FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE coupon_issue (
    issue_no BIGINT AUTO_INCREMENT PRIMARY KEY, issue_code VARCHAR(50) NOT NULL UNIQUE, coupon_no INT NOT NULL, member_no INT NOT NULL, order_no BIGINT DEFAULT NULL, status VARCHAR(20) NOT NULL DEFAULT '발급', used_date DATETIME, issue_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (coupon_no) REFERENCES coupon(coupon_no), FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (order_no) REFERENCES orders(order_no)
);

CREATE TABLE banner (
    banner_no INT AUTO_INCREMENT PRIMARY KEY, banner_name VARCHAR(100) NOT NULL, banner_size VARCHAR(50), bg_color VARCHAR(20), link_url VARCHAR(255), banner_position VARCHAR(50) NOT NULL, image_path VARCHAR(255) NOT NULL, sort_order INT NOT NULL DEFAULT 0, start_datetime DATETIME, end_datetime DATETIME, use_yn CHAR(1) NOT NULL DEFAULT 'Y', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cs_category (
    cs_cate_no INT AUTO_INCREMENT PRIMARY KEY, parent_no INT DEFAULT NULL, board_type VARCHAR(20) NOT NULL DEFAULT 'COMMON', cate_name VARCHAR(100) NOT NULL, depth TINYINT NOT NULL DEFAULT 1, sort_order INT NOT NULL DEFAULT 0, use_yn CHAR(1) NOT NULL DEFAULT 'Y', FOREIGN KEY (parent_no) REFERENCES cs_category(cs_cate_no)
);

CREATE TABLE notice (
    notice_no INT AUTO_INCREMENT PRIMARY KEY, writer_no INT NOT NULL, notice_type VARCHAR(50) NOT NULL, title VARCHAR(200) NOT NULL, content TEXT NOT NULL, hit INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, FOREIGN KEY (writer_no) REFERENCES member(member_no)
);

CREATE TABLE faq (
    faq_no INT AUTO_INCREMENT PRIMARY KEY, cs_cate_no INT NOT NULL, writer_no INT NOT NULL, title VARCHAR(200) NOT NULL, content TEXT NOT NULL, hit INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, FOREIGN KEY (cs_cate_no) REFERENCES cs_category(cs_cate_no), FOREIGN KEY (writer_no) REFERENCES member(member_no)
);

CREATE TABLE qna (
    qna_no BIGINT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, order_no BIGINT DEFAULT NULL, cs_cate_no INT NOT NULL, title VARCHAR(200) NOT NULL, content TEXT NOT NULL, answer TEXT, answer_member_no INT DEFAULT NULL, status VARCHAR(20) NOT NULL DEFAULT '검토중', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, answer_date DATETIME, FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (order_no) REFERENCES orders(order_no), FOREIGN KEY (cs_cate_no) REFERENCES cs_category(cs_cate_no), FOREIGN KEY (answer_member_no) REFERENCES member(member_no)
);

CREATE TABLE recruit (
    recruit_no INT AUTO_INCREMENT PRIMARY KEY, writer_no INT NOT NULL, title VARCHAR(200) NOT NULL, department VARCHAR(50), career VARCHAR(50), recruit_type VARCHAR(50), start_date DATE, end_date DATE, note TEXT, status VARCHAR(20) NOT NULL DEFAULT '모집중', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (writer_no) REFERENCES member(member_no)
);

CREATE TABLE site_setting (
    setting_no INT AUTO_INCREMENT PRIMARY KEY, setting_key VARCHAR(100) NOT NULL UNIQUE, setting_value TEXT, update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE company_content (
    content_no INT AUTO_INCREMENT PRIMARY KEY, content_type VARCHAR(30) NOT NULL, title VARCHAR(200) NOT NULL, content TEXT, image_path VARCHAR(255), video_url VARCHAR(255), category_name VARCHAR(100), use_yn CHAR(1) NOT NULL DEFAULT 'Y', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app_version (
    version_no INT AUTO_INCREMENT PRIMARY KEY, version_name VARCHAR(50) NOT NULL, writer_no INT, change_log TEXT NOT NULL, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (writer_no) REFERENCES member(member_no)
);
```
</details>

---
<details>
<summary><b> 📊 테이블 구조 및 컬럼 설명 </b></summary>

### 📊 테이블 구조 및 컬럼 설명

■ 본 프로젝트는 총 30개의 테이블로 구성되어 있으며, 주요 비즈니스 흐름을 담당하는 핵심 테이블 구조는 다음과 같습니다.

- **member**: 회원(USER, SELLER, ADMIN) 통합 관리 및 권한, 포인트 상태 저장
- **member_social**: 소셜 로그인 연동 (NAVER, KAKAO 등) 식별값 매핑
- **policy / member_agreement**: 이용약관 및 개인정보처리방침 정보와 회원별 약관 동의 이력 관리
- **seller_profile**: 판매자 사업자 및 상점 세부 정보와 관리자 승인 상태 관리
- **category / product**: N차 카테고리 분류 및 상품 상세 정보 (재고, 가격, 할인율 등) 저장
- **product_image / product_option / product_notice**: 상품별 다중 이미지, 단일 구조 옵션 및 상품정보 제공고시 상세 매핑
- **cart**: 사용자별 장바구니에 담긴 상품 및 옵션, 수량 정보 관리
- **orders / order_item**: 전체 주문 정보 및 주문 시점의 실시간 데이터 스냅샷(가격, 옵션 고정) 저장
- **delivery**: 택배사, 송장번호 정보 및 판매자별 실제 배송 상태(배송준비, 배송중, 완료 등) 추적
- **order_claim / claim_file**: 상품별 교환/반품 신청 내역 및 증빙 첨부파일 관리
- **product_review / review_image**: 구매 확정된 주문상품 기준의 별점 평점, 텍스트 및 다중 이미지 리뷰 관리
- **member_point**: 회원의 포인트 적립/사용/환불 세부 이력 및 실시간 잔액 추적
- **coupon / coupon_issue**: 시스템 및 판매자 쿠폰 생성, 회원별 발급 상태 및 사용 이력
- **notice / faq / qna**: 통합 고객센터 게시판 카테고리 연동 및 관리자 1:1 문의 답변 시스템 관리
- **banner / recruit / site_setting / app_version**: 사이트 운영 관리를 위한 배너, 채용 공고, 시스템 설정 키-값 및 앱 버전 히스토리 제어

---

#### 1. member
| 컬럼명 | 설명 |
|---|---|
| `member_no` | 회원을 구분하는 내부 고유 PK 번호 |
| `member_id` | 일반 로그인 시 사용하는 아이디 (소셜 회원은 NULL 가능) |
| `password` | BCrypt로 암호화하여 안전하게 저장하는 비밀번호 |
| `name` | 회원의 실제 성명 |
| `gender` | 회원 성별 (M: 남성, F: 여성 등) |
| `birth` | 회원 생년월일 |
| `email` | 회원 이메일 주소 (UNIQUE) |
| `hp` | 회원 휴대전화 번호 |
| `role` | 시스템 접근 권한 등급 (USER, SELLER, ADMIN) |
| `grade` | 회원 서비스 등급 정보 (SILVER, GOLD 등) |
| `point` | 회원이 현재 사용 가능한 보유 포인트 총합 |
| `status` | 회원의 계정 상태 (정상, 중지, 휴면, 탈퇴 등) |
| `zip` / `addr1` / `addr2` | 회원의 기본 배송지 주소 (우편번호, 기본주소, 상세주소) |
| `last_login_date` | 최근 시스템 로그인 성공 시각 |
| `leave_date` | 회원 탈퇴 처리 및 서비스 종료 시각 |
| `reg_date` | 회원 계정 생성 및 가입일시 |

#### 2. member_social
| 컬럼명 | 설명 |
|---|---|
| `social_no` | 소셜 연동 정보를 구분하는 내부 PK 번호 |
| `member_no` | 연동된 kmarket 서비스의 회원 번호 (FK) |
| `provider` | 소셜 로그인 제공자 구분 (NAVER, KAKAO, GOOGLE 등) |
| `provider_user_id` | 소셜 로그인 업체 측에서 전달받은 회원 고유 식별값 |
| `provider_email` | 소셜 로그인 과정에서 전달받은 소셜 계정 이메일 |
| `reg_date` | 소셜 연동 최초 인증 및 등록 일시 |
| `UNIQUE(provider, provider_user_id)` | 하나의 소셜 계정이 시스템 내 여러 계정으로 중복 연동되는 것 방지 |
| `UNIQUE(member_no, provider)` | 한 회원이 동일한 소셜 제공자를 중복하여 다중 연동하는 것 방지 |

#### 3. policy
| 컬럼명 | 설명 |
|---|---|
| `policy_no` | 약관 및 정책 항목을 구분하는 내부 PK 번호 |
| `policy_type` | BUYER(구매자), SELLER(판매자), PRIVACY(개인정보) 등 약관 카테고리 구분값 |
| `title` | 소비자 또는 판매자 약관 제목 |
| `content` | 약관 본문 상세 텍스트 내용 |
| `required_yn` | 회원가입 시 반드시 동의해야 하는 필수 약관 여부 (Y/N) |
| `use_yn` | 현재 시스템 화면에 노출하여 활성화 중인 약관인지 여부 (Y/N) |
| `update_date` | 약관 정보가 최신으로 개정 및 수정된 시각 |

#### 4. member_agreement
| 컬럼명 | 설명 |
|---|---|
| `agreement_no` | 회원의 약관 동의 내역을 구분하는 내부 PK 번호 |
| `member_no` | 약관에 동의 행위를 진행한 회원 번호 (FK) |
| `policy_no` | 동의 처리된 정책 및 약관 번호 (FK) |
| `agree_yn` | 약관 내용에 동의했는지 여부 (Y/N) |
| `agree_date` | 회원이 약관 동의를 완료한 일시 |

#### 5. seller_profile
| 컬럼명 | 설명 |
|---|---|
| `seller_no` | 판매자 상점을 구분하는 고유 식별 PK 번호 |
| `member_no` | 판매자 권한을 가진 회원 계정과 1:1 매칭되는 회원 번호 (FK, UNIQUE) |
| `company_name` | 상점명 또는 법인 회사 상호명 |
| `ceo_name` | 사업자등록증 상의 대표자명 |
| `biz_no` | 사업자등록번호 (UNIQUE) |
| `online_sale_no` | 전자상거래 통신판매업 신고번호 |
| `tel` / `fax` | 상점 고객센터 전화번호 및 팩스 번호 |
| `zip` / `addr1` / `addr2` | 사업장 소재지 주소 (우편번호, 기본주소, 상세주소) |
| `status` | 판매자 입점 및 운영 상태 (운영준비, 승인대기, 운영중, 중단 등) |
| `reg_date` | 판매자 입점 신청 등록 일시 |

#### 6. category
| 컬럼명 | 설명 |
|---|---|
| `cate_no` | 상품 카테고리를 분류하는 내부 고유 PK 번호 |
| `parent_no` | 하위 카테고리일 때 상위 1차 카테고리를 지정하는 번호 (자기참조 FK, 1차는 NULL) |
| `cate_name` | 카테고리 한글 및 영문 명칭 |
| `depth` | 분류 단계 수준 (1: 1차 대분류, 2: 2차 소분류) |
| `sort_order` | 프론트엔드 네비게이션 및 사이드바 노출 정렬 순서 |
| `use_yn` | 카테고리 및 소속 상품 활성화 노출 여부 (Y/N) |
| `reg_date` | 카테고리 데이터 생성 일시 |

#### 7. product
| 컬럼명 | 설명 |
|---|---|
| `product_no` | 등록된 상품을 구분하는 고유 PK 번호 |
| `seller_no` | 상품을 등록하고 소유한 판매자 상점 번호 (FK) |
| `cate_no` | 상품이 속해있는 최종 2차 카테고리 번호 (FK) |
| `product_name` | 판매 상품 명칭 |
| `basic_desc` | 상품 목록 및 카드 뷰에 노출될 텍스트 형식의 간략한 한 줄 소개 |
| `manufacturer` / `brand` | 상품 제조사 및 유통 브랜드명 |
| `model_name` | 상품 모델명 명시 |
| `price` | 할인 적용 전 상품 정가 |
| `discount_rate` | 상품 판매 할인율 (0~100%) |
| `point` | 상품 최종 구매 확정 시 적립해 줄 개별 포인트 수치 |
| `stock` | 옵션이 존재하지 않는 상품의 단독 실재고 수량 |
| `delivery_fee` | 해당 상품 주문 시 부과되는 배송비 고정 금액 |
| `detail_content` | 상품 상세 설명 본문 에디터 HTML 및 텍스트 데이터 |
| `status` | 현재 상품 판매 상태 (판매중, 판매중지, 품절, 삭제 등) |
| `view_count` | 상품 상세 정보 페이지 누적 조회수 |
| `sold_count` | 누적 주문 확정 판매 수량 |
| `reg_date` | 최초 상품 등록 일시 |
| `update_date` | 상품 정보 최근 수정 일시 |

#### 8. product_image
| 컬럼명 | 설명 |
|---|---|
| `image_no` | 상품 첨부 이미지를 구분하는 내부 PK 번호 |
| `product_no` | 이미지가 소속된 대상 상품 번호 (FK) |
| `image_type` | 이미지 유형 지정 (THUMB1, THUMB2, THUMB3, MAIN, DETAIL 등) |
| `image_path` | 파일 스토리지 또는 서버 내 저장된 이미지 리소스의 상대/절대 경로 |
| `sort_order` | 다중 상세 이미지 출력 시 보여주는 정렬 순번 |
| `reg_date` | 이미지 파일 등록 일시 |

#### 9. product_option
| 컬럼명 | 설명 |
|---|---|
| `option_no` | 단일 구조 상품 옵션을 구분하는 내부 PK 번호 |
| `product_no` | 옵션이 소속된 대상 상품 번호 (FK) |
| `option_name` | 옵션 종류 타이틀 명칭 (예: 색상, 사이즈, 용량 등) |
| `option_value` | 사용자가 선택할 실제 옵션값 (예: 블랙, XL, 500ml 등) |
| `add_price` | 해당 옵션 선택 시 기본 상품 정가(`product.price`)에 추가 합산될 금액 |
| `stock` | 해당 단일 선택 옵션의 독립적인 실재고 수량 |
| `use_yn` | 해당 옵션 사용 및 프론트 노출 여부 (Y/N) |

#### 10. product_notice
| 컬럼명 | 설명 |
|---|---|
| `notice_no` | 상품정보 제공고시 데이터를 구분하는 내부 PK 번호 |
| `product_no` | 고시 정보가 매핑된 대상 상품 번호 (FK, UNIQUE) |
| `product_status` | 제품 상태 정보 (새상품, 중고 등) |
| `tax_type` | 과세 여부 및 면세 상품 구분 안내 |
| `receipt_type` | 영수증 및 현금영수증 발행 기준 안내 |
| `business_type` | 판매자 사업자 형태 구분 안내 |
| `origin` | 상품 원산지 정보 |
| `as_info` | A/S 책임자와 품질보증 기준 안내 |
| `tel` | 고객센터 및 A/S 상담 전화번호 |
| `detail_notice` | 기타 법령에 따른 제공고시 추가 기술 항목 |

#### 11. cart
| 컬럼명 | 설명 |
|---|---|
| `cart_no` | 장바구니 적재 항목을 구분하는 고유 PK 번호 |
| `member_no` | 장바구니를 소유한 로그인 회원 번호 (FK) |
| `product_no` | 장바구니에 담은 대상 상품 번호 (FK) |
| `option_no` | 선택한 상품의 단일 옵션 번호 (FK, 옵션이 없을 경우 NULL 허용) |
| `quantity` | 장바구니에 담긴 동일 구성 상품의 주문 희망 수량 |
| `reg_date` | 최초 장바구니 담기 완료 시각 |
| `update_date` | 장바구니 내부 수량 수정 및 최신 갱신 일시 |

#### 12. orders
| 컬럼명 | 설명 |
|---|---|
| `order_no` | 주문 마스터 정보를 구분하는 내부 고유 PK 번호 |
| `order_code` | 외부 및 영수증, 고객 알림에 표기되는 고유 주문 코드 (UNIQUE) |
| `member_no` | 주문을 진행한 소비자 회원 번호 (FK) |
| `orderer_name` | 주문 시점에 직접 입력한 주문자 성명 |
| `orderer_hp` | 주문 시점에 직접 입력한 주문자 연락처 |
| `total_price` | 주문에 포함된 전체 상품들의 순수 정가 합산 금액 |
| `discount_price` | 상품 자체 할인율 적용에 의해 차감된 총 할인 합계 금액 |
| `coupon_discount` | 발급 쿠폰 적용에 의해 추가 차감된 쿠폰 할인 금액 |
| `delivery_fee` | 주문 건에 포함된 총 배송비 비용 합산 금액 |
| `point_use` | 주문 결제 단계에서 회원이 사용 처리한 적립 포인트 수치 |
| `point_save` | 주문 결제 및 구매 확정 이후 최종 적립 예정인 총 포인트 수치 |
| `pay_price` | 할인, 쿠폰, 포인트를 모두 제하고 최종 승인 결제된 실 금액 |
| `payment_method` | 결제 수단 구분 (신용카드, 계좌이체, 무통장입금, 카카오페이 등) |
| `payment_status` | 결제 처리 진행 상태 (결제대기, 결제완료, 결제취소, 환불완료 등) |
| `paid_date` | 최종 결제 승인 및 입금 확인 완료 시각 |
| `cancel_date` | 결제 취소 또는 전액 환불 처리가 실행된 시각 |
| `order_status` | 주문 마스터의 전체 대표 상태 (주문완료, 부분배송, 전체배송완료, 전체취소 등) |
| `order_date` | 최초 주문서 생성 및 접수 일시 |

#### 13. delivery
| 컬럼명 | 설명 |
|---|---|
| `delivery_no` | 판매자별 배송 묶음 단위를 구분하는 고유 PK 번호 |
| `order_no` | 배송 정보가 속한 대상 주문 번호 (FK) |
| `seller_no` | 배송을 책임지고 발송하는 주체인 판매자 번호 (FK) |
| `receiver_name` | 화물을 수령할 수하인 이름 |
| `receiver_hp` | 수하인 휴대전화 연락처 |
| `zip` / `addr1` / `addr2` | 화물 배송 목적지 주소 (우편번호, 기본주소, 상세주소) |
| `courier` | 배송 담당 택배사명 |
| `invoice_no` | 운송장 번호 |
| `delivery_status` | 판매자 기준의 물류 실제 상태 (배송준비, 배송중, 배송완료 등) |
| `memo` | 배송 요청 사항 및 택배 기사 전달 메시지 |
| `ready_date` | 배송준비(출고대기) 상태 전환 시각 |
| `shipped_date` | 택배사 인계 및 운송장 배송 출발 시각 |
| `delivered_date` | 배송완료 실 배달 완료 시각 |
| `reg_date` | 주문 완료에 의한 배송 도메인 정보 최초 생성 일시 |

#### 14. order_item
| 컬럼명 | 설명 |
|---|---|
| `order_item_no` | 주문에 포함된 개별 상품 상세 라인을 구분하는 고유 PK 번호 |
| `order_no` | 소속된 주문 마스터 번호 (FK) |
| `delivery_no` | 판매자 배송 묶음 정보와 연결되는 배송 번호 (FK, 출고 전 NULL 가능) |
| `product_no` | 주문 대상 원본 상품 번호 (FK) |
| `seller_no` | 주문 대상 상품의 소유 판매자 번호 (FK) |
| `option_name` / `option_value` | 주문 시점에 선택했던 옵션 명칭 및 옵션값 스냅샷 |
| `product_name` | 주문 당시 시점의 원본 상품명 데이터 스냅샷 (무결성 유지용) |
| `product_image` | 주문 당시 시점의 대표 썸네일 이미지 파일 경로 스냅샷 |
| `price` | 주문 시점의 개별 상품 정가 스냅샷 |
| `discount_rate` | 주문 시점의 상품 할인율 스냅샷 |
| `point` | 주문 시점의 개별 적립 포인트 수치 스냅샷 |
| `quantity` | 해당 단일 품목·옵션의 최종 구매 주문 수량 |
| `total_price` | 수량과 할인이 모두 계산된 해당 라인 상품의 최종 결제 금액 |
| `item_status` | 개별 품목 단위의 취소/교환/반품 처리 상태 (주문완료, 취소완료, 반품신청, 반품완료 등) |

#### 15. order_claim
| 컬럼명 | 설명 |
|---|---|
| `claim_no` | 반품 및 교환 클레임 신청 건을 구분하는 고유 PK 번호 |
| `order_item_no` | 클레임이 제기된 대상 세부 주문 상품 번호 (FK) |
| `member_no` | 클레임을 접수한 소비자 회원 번호 (FK) |
| `claim_type` | 클레임 성격 분류 (반품, 교환 등) |
| `claim_reason` | 사유 분류 타이틀 (단순변심, 상품불량, 오배송, 택배파손 등) |
| `detail_reason` | 사용자가 마이페이지에 직접 기재한 상세 불만 및 요구 사유 |
| `status` | 클레임 처리 진행 프로세스 상태 (신청, 승인, 반려, 수거중, 완료 등) |
| `request_date` | 최초 클레임 신청 및 접수 시각 |
| `process_date` | 판매자 또는 관리자에 의해 최종 클레임 처리가 완결된 시각 |

#### 16. claim_file
| 컬럼명 | 설명 |
|---|---|
| `claim_file_no` | 클레임 첨부 이미지를 구분하는 내부 PK 번호 |
| `claim_no` | 파일이 소속된 반품/교환 신청 번호 (FK) |
| `ori_name` | 사용자가 스마트폰 또는 PC에서 업로드한 최초 원본 파일명 |
| `new_name` | 파일 시스템 내 중복 난수 방지를 위해 해싱 변환된 서버 저장용 파일명 |
| `reg_date` | 파일 업로드 완료 일시 |

#### 17. product_review
| 컬럼명 | 설명 |
|---|---|
| `review_no` | 작성된 상품 리뷰 게시글을 구분하는 내부 PK 번호 |
| `order_item_no` | 리뷰 작성이 허가된 유일한 대상 주문상품 세부 번호 (FK, UNIQUE 제한으로 1회 한정) |
| `member_no` | 리뷰를 작성한 구매자 회원 번호 (FK) |
| `product_no` | 리뷰가 노출될 대상 원본 상품 번호 (FK) |
| `rating` | 구매 만족도 평점 점수 (1점 ~ 5점 필수 기입) |
| `content` | 상품 사용 후기 리뷰 텍스트 본문 |
| `reg_date` | 리뷰 최종 등록 일시 |

#### 18. review_image
| 컬럼명 | 설명 |
|---|---|
| `review_image_no` | 리뷰 첨부 이미지를 구분하는 내부 PK 번호 |
| `review_no` | 이미지가 포함된 대상 상품 리뷰 번호 (FK) |
| `image_path` | 서버에 업로드되어 저장된 리뷰 포토 이미지 경로 |
| `sort_order` | 포토 리뷰 슬라이드 노출 정렬 순서 |

#### 19. member_point
| 컬럼명 | 설명 |
|---|---|
| `point_no` | 포인트 증감 변동 이력을 구분하는 내부 PK 번호 |
| `member_no` | 포인트 변동이 발생한 회원의 번호 (FK) |
| `order_no` | 쇼핑몰 구매 결제 혹은 결제 취소와 연동된 경우의 주문 마스터 번호 (연동 없으면 NULL 가능) |
| `point_type` | 포인트 변동 성격 분류 (적립, 사용, 차감, 환불 등) |
| `point_value` | 실제 증감 변동된 포인트 금액 수치 |
| `balance_point` | 변동 내역이 반영된 시점 직후 계산된 실시간 최종 잔여 포인트 잔액 |
| `reason` | 포인트 변동 사유 요약 (예: 상품 구매 적립, 이벤트 참여, 쿠폰 전환 등) |
| `expire_date` | 적립된 포인트가 소멸 처리될 유효기간 만료일 |
| `reg_date` | 포인트 변동 이력 로그 기록 일시 |

#### 20. coupon
| 컬럼명 | 설명 |
|---|---|
| `coupon_no` | 쿠폰 마스터 정보를 구분하는 내부 PK 번호 |
| `coupon_code` | 쿠폰 발급/등록 시 검증에 활용되는 고유 난수 코드 (UNIQUE) |
| `seller_no` | 쿠폰을 발행한 주체인 판매자 번호 (전체 공통 쿠폰인 경우 NULL 처리) |
| `coupon_type` | 쿠폰 적용 범위 분류 (개별상품할인, 주문상품할인, 배송비무료 등) |
| `coupon_name` | 사용자에게 노출될 쿠폰 명칭 (예: [첫구매] 3,000원 할인 쿠폰 등) |
| `benefit_type` | 혜택 연산 방식 구분 (금액할인, 비율할인, 무료배송) |
| `benefit_value` | 실제 차감될 고정 할인 금액 혹은 할인 비율(%) 수치 |
| `min_order_price` | 쿠폰을 적용하기 위해 만족해야 하는 최소 주문 금액 허들 |
| `max_discount_price` | 비율할인(%) 적용 시 차감될 수 있는 최대 할인 한도 제한 금액 |
| `issue_limit` | 해당 쿠폰이 시스템 전체에서 최대로 발급될 수 있는 총 수량 제한 (제한 없으면 NULL) |
| `start_date` / `end_date` | 쿠폰 다운로드 및 유효 기간의 시작일과 종료일 |
| `caution` | 쿠폰 사용 시 제약 사항 및 예외 카테고리 유의사항 텍스트 |
| `status` | 쿠폰 자체의 운영 활성화 상태 (사용, 중지 등) |
| `reg_date` | 최초 쿠폰 템플릿 생성 일시 |

#### 21. coupon_issue
| 컬럼명 | 설명 |
|---|---|
| `issue_no` | 회원별 개별 발급된 쿠폰 인스턴스를 구분하는 내부 고유 PK 번호 |
| `issue_code` | 회원 보유 쿠폰함 내에서 식별되는 쿠폰 개별 식별코드 (UNIQUE) |
| `coupon_no` | 발급 대상 원본 쿠폰 마스터 번호 (FK) |
| `member_no` | 쿠폰을 다운로드하여 보유하게 된 수혜 회원 번호 (FK) |
| `order_no` | 쿠폰을 최종 사용하여 결제 처리를 마친 주문 번호 (미사용 시 NULL) |
| `status` | 회원의 쿠폰 소유 상태 상태 (발급, 사용, 만료, 종료 등) |
| `used_date` | 결제 단계에서 쿠폰이 사용 처리된 실제 시각 |
| `issue_date` | 회원이 쿠폰을 다운로드하거나 지급받은 일시 |

#### 22. banner
| 컬럼명 | 설명 |
|---|---|
| `banner_no` | 노출 배너 데이터를 구분하는 내부 PK 번호 |
| `banner_name` | 배너 식별용 운영 관리 명칭 |
| `banner_size` | 프론트 노출 권장 가로 및 세로 크기 명시 (예: 960x400) |
| `bg_color` | 배너 래퍼 영역을 채울 기본 배경색 헥스(HEX) 값 |
| `link_url` | 배너 클릭 시 브라우저가 이동할 내부/외부 랜딩 페이지 주소 |
| `banner_position` | 프론트엔드 배치 위치 식별 코드 (MAIN1, PRODUCT1, MEMBER1, MY1 등) |
| `image_path` | 배너용 그래픽 이미지 파일 저장 경로 |
| `sort_order` | 동일 구역 내 복수 배너 존재 시 슬라이드 및 노출 우선 정렬 순서 |
| `start_datetime` / `end_datetime` | 배너가 화면에 노출을 시작하고 자동 종료될 타임라인 일시 |
| `use_yn` | 배너의 실시간 강제 노출 혹은 숨김 제어 여부 (Y/N) |
| `reg_date` | 배너 리소스 최초 등록 일시 |

#### 23. cs_category
| 컬럼명 | 설명 |
|---|---|
| `cs_cate_no` | 고객센터 서비스 카테고리를 분류하는 내부 고유 PK 번호 |
| `parent_no` | 2차 고객 카테고리일 때 상위 1차 카테고리를 지정하는 번호 (자기참조 FK, 1차는 NULL) |
| `board_type` | 해당 분류가 속할 게시판 대유형 지정 (COMMON[공지], FAQ[자주묻는질문], QNA[1:1문의]) |
| `cate_name` | 고객센터 메뉴용 분류 명칭 (예: 회원/미디어, 주문/결제, 반품/환불 등) |
| `depth` | 분류 단계 수준 (1: 대분류 메뉴, 2: 소분류 메뉴) |
| `sort_order` | 고객센터 화면 서브메뉴 출력 정렬 순서 |
| `use_yn` | 해당 상담 및 게시판 분류 활성화 여부 (Y/N) |

#### 24. notice
| 컬럼명 | 설명 |
|---|---|
| `notice_no` | 전사 공지사항 게시글을 구분하는 내부 PK 번호 |
| `writer_no` | 공지사항을 등록한 최고 관리자 권한의 회원 번호 (FK) |
| `notice_type` | 공지사항 세부 분류 코드 (일반공지, 이벤트공지, 서비스점검 등) |
| `title` | 공지사항 게시글 제목 |
| `content` | 공지사항 상세 에디터 내용 본문 텍스트 |
| `hit` | 게시글 누적 프론트엔드 조회수 |
| `reg_date` | 공지사항 최초 게시 등록 일시 |
| `update_date` | 공지사항 내용 최근 수정 및 업데이트 일시 |

#### 25. faq
| 컬럼명 | 설명 |
|---|---|
| `faq_no` | 자주 묻는 질문(FAQ) 게시글을 구분하는 내부 PK 번호 |
| `cs_cate_no` | FAQ가 배치될 전용 고객센터 카테고리 번호 (FK, board_type='FAQ' 연동) |
| `writer_no` | FAQ를 분석 및 구성해 등록한 관리자 회원 번호 (FK) |
| `title` | FAQ 질문(Question) 영역 타이틀 제목 |
| `content` | FAQ 답변(Answer) 영역 상세 텍스트 내용 |
| `hit` | 해당 FAQ 해결 안내글 누적 조회수 |
| `reg_date` | FAQ 최초 게시 등록 일시 |
| `update_date` | FAQ 수정 및 보완 업데이트 일시 |

#### 26. qna
| 컬럼명 | 설명 |
|---|---|
| `qna_no` | 1:1 고객 문의 게시글을 구분하는 내부 고유 PK 번호 |
| `member_no` | 문의를 작성하고 제출한 일반/판매자 회원 번호 (FK) |
| `order_no` | 상품 주문이나 배송과 결부된 문의일 경우 매핑되는 주문 마스터 번호 (일반 문의는 NULL 가능) |
| `cs_cate_no` | 문의 내용에 부합하는 고객센터 카테고리 번호 (FK, board_type='QNA' 연동) |
| `title` | 회원이 기재한 문의글 제목 |
| `content` | 회원이 기재한 1:1 문의 구체적 질문 본문 내역 |
| `answer` | 관리자 또는 담당 CS 운영자가 작성한 공식 답변 텍스트 내용 |
| `answer_member_no` | 답변 처리를 직접 완결한 관리자 회원 번호 (FK, 답변 대기 시 NULL) |
| `status` | 문의 건의 실시간 처리 상태 프로세스 (검토중, 답변완료 등) |
| `reg_date` | 회원의 1:1 문의 최초 글 접수 완료 일시 |
| `answer_date` | 관리자 공식 답변 등록 완료 일시 |

#### 27. recruit
| 컬럼명 | 설명 |
|---|---|
| `recruit_no` | 기업 채용 공고 게시글을 구분하는 내부 PK 번호 |
| `writer_no` | 채용 공고를 작성한 인사/운영 관리자 회원 번호 (FK) |
| `title` | 채용 공고 타이틀 제목 |
| `department` | 채용 타겟 집단 모집 부서 (예: 개발부, 디자인부, 마케팅팀 등) |
| `career` | 지원 요구 경력 조건 구분 (신입, 경력, 경력무관 등) |
| `recruit_type` | 고용 형태 속성 분류 (정규직, 계약직, 인턴 등) |
| `start_date` / `end_date` | 원서 접수 모집 시작 기한 및 마감 기한일 |
| `note` | 직무 기술서 및 채용 요건 상세 기술 본문 내용 |
| `status` | 채용 공고 접수 진행 상태 (모집중, 마감 등) |
| `reg_date` | 채용 공고 등록 일시 |

#### 28. site_setting
| 컬럼명 | 설명 |
|---|---|
| `setting_no` | 글로벌 사이트 메타 설정을 구분하는 내부 PK 번호 |
| `setting_key` | 설정값 파싱을 위한 고유 키 문자열 (예: site_name, company_tel 등 UNIQUE) |
| `setting_value` | 지정된 설정 키에 할당되는 동적 데이터 및 설정값 본문 (TEXT) |
| `update_date` | 관리자에 의해 사이트 설정 정보가 변경된 최신 갱신 일시 |

#### 29. company_content
| 컬럼명 | 설명 |
|---|---|
| `content_no` | 회사 소개 미디어 콘텐츠를 구분하는 내부 PK 번호 |
| `content_type` | 소개 페이지 구역 분류 코드 (INTRO[회사소개], CULTURE[기업문화], STORY[연혁/소식], MEDIA[미디어]) |
| `title` | 회사 콘텐츠 타이틀 제목 |
| `content` | 세부 설명 텍스트 및 본문 스크립트 |
| `image_path` | 홍보용 대표 이미지 파일 경로 |
| `video_url` | 홍보 미디어 영역에 출력될 외부 스트리밍(유튜브 등) 영상 URL 링크 |
| `category_name` | 회사 소식 및 미디어 보도 자료의 세부 세션 분류명 |
| `use_yn` | 회사 소개 화면 내 노출 활성화 여부 (Y/N) |
| `reg_date` | 소개글 및 보도 콘텐츠 등록 일시 |

#### 30. app_version
| 컬럼명 | 설명 |
|---|---|
| `version_no` | 시스템 빌드 및 앱 버전 이력을 구분하는 내부 PK 번호 |
| `version_name` | 배포 빌드 소프트웨어 버전 명칭 (예: v1.0.0, v1.1.0 등) |
| `writer_no` | 버전 배포 로그를 등록한 담당 개발/관리자 회원 번호 (FK) |
| `change_log` | 해당 배포 빌드에서 추가·수정 및 픽스된 릴리즈 노트 변경 세부 사항 |
| `reg_date` | 버전 릴리즈 등록 및 배포 일시 |

---

### ✅ 작업 진행 순서
- Figma 화면 설계서 분석

- 프로젝트 폴더 구조 세팅 및 공통 레이아웃/CSS 작성

- 담당 페이지 HTML 마크업 및 CSS 적용

- 전체 화면 링크 연결 및 UI 검수

- AWS RDS(MySQL) DB 테이블 구축 및 Spring Boot 연동

### 🎯 프로젝트 목표
- 쇼핑몰 IA 구조에 맞는 완벽한 프론트엔드 화면 구현

- 담당 영역별 분리 작업을 통한 효율적인 백엔드 확장 및 평화로운 Git 협업

- 실무 비즈니스 로직(결제 스냅샷, 환불, 포인트, 쿠폰)을 지원하는 무결성 DB 구축

<img src="https://capsule-render.vercel.app/api?type=waving&color=9ACB34&theme=navy&height=150&section=footer&text=Thank%20you%20for%20reading!&fontSize=30&animation=fadeIn" width="100%" />
