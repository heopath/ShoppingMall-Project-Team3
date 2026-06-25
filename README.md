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
| 이름 | 담당 파트 | 상세 담당 페이지 |
|:---:|---|---|
| <span style="white-space: nowrap;">**허민재**</span> | 메인, 상품 프로세스, 회사 소개 | `index.html`, `product/*` (목록, 상세, 검색, 장바구니, 주문), `company/*` |
| <span style="white-space: nowrap;">**양지웅**</span> | 회원 인증 및 마이페이지 | `member/*` (로그인, 가입, 약관), `my/*` (주문내역, 포인트, 쿠폰, 리뷰, 문의 등) |
| <span style="white-space: nowrap;">**정인길**</span> | 고객센터 및 약관/정책 | `cs/*` (공지, FAQ, QNA), `policy/*` (이용약관, 개인정보처리방침 등) |
| <span style="white-space: nowrap;">**최수빈**</span> | Admin 관리자 시스템 | `admin/*` (메인 대시보드, 환경설정, 배너 관리, 상품 등록, 고객센터 답변 등) |
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
각 팀원은 본인 담당 폴더 중심으로 작업하고, 공통 CSS 수정 시 팀원들과 먼저 공유합니다.

작업 전 최신 코드를 pull 받고, main 브랜치 직접 push 대신 PR(Pull Request)을 반영합니다.

### 🎨 CSS 스타일 가이드
공통 영역: css/common.css 필수 포함 후 각 도메인별 메인 CSS 링크

관리자 영역: 공통 CSS (admin-common.css) + 페이지별 CSS (basic.css) 조합 호출

---

<details>
<summary><b> 📊 SQL-DDL </b></summary>

### 📊 SQL-DDL

```plaintext

CREATE DATABASE IF NOT EXISTS kmarket;
USE kmarket;

CREATE TABLE member (
    member_no INT AUTO_INCREMENT PRIMARY KEY, member_id VARCHAR(50) UNIQUE, password VARCHAR(255),
    name VARCHAR(50) NOT NULL, gender CHAR(1), birth DATE, email VARCHAR(100) UNIQUE, hp VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'USER', grade VARCHAR(20) NOT NULL DEFAULT 'SILVER',
    point INT NOT NULL DEFAULT 0, status VARCHAR(20) NOT NULL DEFAULT '정상', zip VARCHAR(10),
    addr1 VARCHAR(255), addr2 VARCHAR(255), last_login_date DATETIME, leave_date DATETIME,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_social (
    social_no BIGINT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, provider VARCHAR(20) NOT NULL,
    provider_user_id VARCHAR(255) NOT NULL, provider_email VARCHAR(100), reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (provider, provider_user_id), UNIQUE (member_no, provider), FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE policy (
    policy_no INT AUTO_INCREMENT PRIMARY KEY, policy_type VARCHAR(50) NOT NULL, title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL, required_yn CHAR(1) NOT NULL DEFAULT 'Y', use_yn CHAR(1) NOT NULL DEFAULT 'Y',
    update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE member_agreement (
    agreement_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, policy_no INT NOT NULL,
    agree_yn CHAR(1) NOT NULL DEFAULT 'Y', agree_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (policy_no) REFERENCES policy(policy_no)
);

CREATE TABLE seller_profile (
    seller_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL UNIQUE, company_name VARCHAR(100) NOT NULL,
    ceo_name VARCHAR(50) NOT NULL, biz_no VARCHAR(30) NOT NULL UNIQUE, online_sale_no VARCHAR(50), tel VARCHAR(20),
    fax VARCHAR(20), zip VARCHAR(10), addr1 VARCHAR(255), addr2 VARCHAR(255), status VARCHAR(20) NOT NULL DEFAULT '운영준비',
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE category (
    cate_no INT AUTO_INCREMENT PRIMARY KEY, parent_no INT DEFAULT NULL, cate_name VARCHAR(100) NOT NULL,
    depth TINYINT NOT NULL DEFAULT 1, sort_order INT NOT NULL DEFAULT 0, use_yn CHAR(1) NOT NULL DEFAULT 'Y',
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (parent_no) REFERENCES category(cate_no)
);

CREATE TABLE product (
    product_no INT AUTO_INCREMENT PRIMARY KEY, seller_no INT NOT NULL, cate_no INT NOT NULL, product_name VARCHAR(200) NOT NULL,
    basic_desc VARCHAR(255), manufacturer VARCHAR(100), brand VARCHAR(100), model_name VARCHAR(100), price INT NOT NULL DEFAULT 0,
    discount_rate INT NOT NULL DEFAULT 0, point INT NOT NULL DEFAULT 0, stock INT NOT NULL DEFAULT 0, delivery_fee INT NOT NULL DEFAULT 0,
    detail_content TEXT, status VARCHAR(20) NOT NULL DEFAULT '판매중', view_count INT NOT NULL DEFAULT 0, sold_count INT NOT NULL DEFAULT 0,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no), FOREIGN KEY (cate_no) REFERENCES category(cate_no)
);

CREATE TABLE product_image (
    image_no INT AUTO_INCREMENT PRIMARY KEY, product_no INT NOT NULL, image_type VARCHAR(30) NOT NULL,
    image_path VARCHAR(255) NOT NULL, sort_order INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE product_option (
    option_no INT AUTO_INCREMENT PRIMARY KEY, product_no INT NOT NULL, option_name VARCHAR(100) NOT NULL,
    option_value VARCHAR(100) NOT NULL, add_price INT NOT NULL DEFAULT 0, stock INT NOT NULL DEFAULT 0,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y', FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE product_notice (
    notice_no INT AUTO_INCREMENT PRIMARY KEY, product_no INT NOT NULL UNIQUE, product_status VARCHAR(100),
    tax_type VARCHAR(100), receipt_type VARCHAR(100), business_type VARCHAR(100), origin VARCHAR(100),
    as_info VARCHAR(255), tel VARCHAR(20), detail_notice TEXT, FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE cart (
    cart_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, product_no INT NOT NULL, option_no INT DEFAULT NULL,
    quantity INT NOT NULL DEFAULT 1, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (product_no) REFERENCES product(product_no),
    FOREIGN KEY (option_no) REFERENCES product_option(option_no)
);

CREATE TABLE orders (
    order_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_code VARCHAR(50) NOT NULL UNIQUE, member_no INT NOT NULL,
    orderer_name VARCHAR(50) NOT NULL, orderer_hp VARCHAR(20) NOT NULL, total_price INT NOT NULL DEFAULT 0,
    discount_price INT NOT NULL DEFAULT 0, coupon_discount INT NOT NULL DEFAULT 0, delivery_fee INT NOT NULL DEFAULT 0,
    point_use INT NOT NULL DEFAULT 0, point_save INT NOT NULL DEFAULT 0, pay_price INT NOT NULL DEFAULT 0,
    payment_method VARCHAR(30), payment_status VARCHAR(30) NOT NULL DEFAULT '결제대기', paid_date DATETIME,
    cancel_date DATETIME, order_status VARCHAR(30) NOT NULL DEFAULT '주문완료', order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE delivery (
    delivery_no INT AUTO_INCREMENT PRIMARY KEY, order_no BIGINT NOT NULL, seller_no INT NOT NULL, receiver_name VARCHAR(50) NOT NULL,
    receiver_hp VARCHAR(20) NOT NULL, zip VARCHAR(10), addr1 VARCHAR(255), addr2 VARCHAR(255), courier VARCHAR(50),
    invoice_no VARCHAR(50), delivery_status VARCHAR(30) NOT NULL DEFAULT '배송준비', memo TEXT, ready_date DATETIME,
    shipped_date DATETIME, delivered_date DATETIME, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_no) REFERENCES orders(order_no), FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no)
);

CREATE TABLE order_item (
    order_item_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_no BIGINT NOT NULL, delivery_no INT DEFAULT NULL, product_no INT NOT NULL,
    seller_no INT NOT NULL, option_name VARCHAR(100), option_value VARCHAR(100), product_name VARCHAR(200) NOT NULL,
    product_image VARCHAR(255), price INT NOT NULL DEFAULT 0, discount_rate INT NOT NULL DEFAULT 0, point INT NOT NULL DEFAULT 0,
    quantity INT NOT NULL DEFAULT 1, total_price INT NOT NULL DEFAULT 0, item_status VARCHAR(30) NOT NULL DEFAULT '주문완료',
    FOREIGN KEY (order_no) REFERENCES orders(order_no), FOREIGN KEY (delivery_no) REFERENCES delivery(delivery_no),
    FOREIGN KEY (product_no) REFERENCES product(product_no), FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no)
);

CREATE TABLE order_claim (
    claim_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_item_no BIGINT NOT NULL, member_no INT NOT NULL,
    claim_type VARCHAR(20) NOT NULL, claim_reason VARCHAR(100) NOT NULL, detail_reason TEXT, status VARCHAR(30) NOT NULL DEFAULT '신청',
    request_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, process_date DATETIME,
    FOREIGN KEY (order_item_no) REFERENCES order_item(order_item_no), FOREIGN KEY (member_no) REFERENCES member(member_no)
);

CREATE TABLE claim_file (
    claim_file_no BIGINT AUTO_INCREMENT PRIMARY KEY, claim_no BIGINT NOT NULL, ori_name VARCHAR(255) NOT NULL,
    new_name VARCHAR(255) NOT NULL, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (claim_no) REFERENCES order_claim(claim_no)
);

CREATE TABLE product_review (
    review_no BIGINT AUTO_INCREMENT PRIMARY KEY, order_item_no BIGINT NOT NULL UNIQUE, member_no INT NOT NULL,
    product_no INT NOT NULL, rating TINYINT NOT NULL, content TEXT NOT NULL, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_item_no) REFERENCES order_item(order_item_no), FOREIGN KEY (member_no) REFERENCES member(member_no),
    FOREIGN KEY (product_no) REFERENCES product(product_no)
);

CREATE TABLE review_image (
    review_image_no BIGINT AUTO_INCREMENT PRIMARY KEY, review_no BIGINT NOT NULL, image_path VARCHAR(255) NOT NULL,
    sort_order INT NOT NULL DEFAULT 0, FOREIGN KEY (review_no) REFERENCES product_review(review_no)
);

CREATE TABLE member_point (
    point_no INT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, order_no BIGINT DEFAULT NULL, point_type VARCHAR(20) NOT NULL,
    point_value INT NOT NULL, balance_point INT NOT NULL DEFAULT 0, reason VARCHAR(255), expire_date DATE,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (member_no) REFERENCES member(member_no),
    FOREIGN KEY (order_no) REFERENCES orders(order_no)
);

CREATE TABLE coupon (
    coupon_no INT AUTO_INCREMENT PRIMARY KEY, coupon_code VARCHAR(50) NOT NULL UNIQUE, seller_no INT DEFAULT NULL,
    coupon_type VARCHAR(30) NOT NULL, coupon_name VARCHAR(100) NOT NULL, benefit_type VARCHAR(20) NOT NULL, benefit_value INT NOT NULL DEFAULT 0,
    min_order_price INT NOT NULL DEFAULT 0, max_discount_price INT NOT NULL DEFAULT 0, issue_limit INT DEFAULT NULL, start_date DATE,
    end_date DATE, caution TEXT, status VARCHAR(20) NOT NULL DEFAULT '사용', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_no) REFERENCES seller_profile(seller_no)
);

CREATE TABLE coupon_issue (
    issue_no BIGINT AUTO_INCREMENT PRIMARY KEY, issue_code VARCHAR(50) NOT NULL UNIQUE, coupon_no INT NOT NULL, member_no INT NOT NULL,
    order_no BIGINT DEFAULT NULL, status VARCHAR(20) NOT NULL DEFAULT '발급', used_date DATETIME, issue_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (coupon_no) REFERENCES coupon(coupon_no), FOREIGN KEY (member_no) REFERENCES member(member_no), FOREIGN KEY (order_no) REFERENCES orders(order_no)
);

CREATE TABLE banner (
    banner_no INT AUTO_INCREMENT PRIMARY KEY, banner_name VARCHAR(100) NOT NULL, banner_size VARCHAR(50), bg_color VARCHAR(20),
    link_url VARCHAR(255), banner_position VARCHAR(50) NOT NULL, image_path VARCHAR(255) NOT NULL, sort_order INT NOT NULL DEFAULT 0,
    start_datetime DATETIME, end_datetime DATETIME, use_yn CHAR(1) NOT NULL DEFAULT 'Y', reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cs_category (
    cs_cate_no INT AUTO_INCREMENT PRIMARY KEY, parent_no INT DEFAULT NULL, board_type VARCHAR(20) NOT NULL DEFAULT 'COMMON',
    cate_name VARCHAR(100) NOT NULL, depth TINYINT NOT NULL DEFAULT 1, sort_order INT NOT NULL DEFAULT 0, use_yn CHAR(1) NOT NULL DEFAULT 'Y',
    FOREIGN KEY (parent_no) REFERENCES cs_category(cs_cate_no)
);

CREATE TABLE notice (
    notice_no INT AUTO_INCREMENT PRIMARY KEY, writer_no INT NOT NULL, notice_type VARCHAR(50) NOT NULL, title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL, hit INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, FOREIGN KEY (writer_no) REFERENCES member(member_no)
);

CREATE TABLE faq (
    faq_no INT AUTO_INCREMENT PRIMARY KEY, cs_cate_no INT NOT NULL, writer_no INT NOT NULL, title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL, hit INT NOT NULL DEFAULT 0, reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, FOREIGN KEY (cs_cate_no) REFERENCES cs_category(cs_cate_no),
    FOREIGN KEY (writer_no) REFERENCES member(member_no)
);

CREATE TABLE qna (
    qna_no BIGINT AUTO_INCREMENT PRIMARY KEY, member_no INT NOT NULL, order_no BIGINT DEFAULT NULL, cs_cate_no INT NOT NULL,
    title VARCHAR(200) NOT NULL, content TEXT NOT NULL, answer TEXT, answer_member_no INT DEFAULT NULL, status VARCHAR(20) NOT NULL DEFAULT '검토중',
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, answer_date DATETIME, FOREIGN KEY (member_no) REFERENCES member(member_no),
    FOREIGN KEY (order_no) REFERENCES orders(order_no), FOREIGN KEY (cs_cate_no) REFERENCES cs_category(cs_cate_no),
    FOREIGN KEY (answer_member_no) REFERENCES member(member_no)
);

CREATE TABLE recruit (
    recruit_no INT AUTO_INCREMENT PRIMARY KEY, writer_no INT NOT NULL, title VARCHAR(200) NOT NULL, department VARCHAR(50),
    career VARCHAR(50), recruit_type VARCHAR(50), start_date DATE, end_date DATE, note TEXT, status VARCHAR(20) NOT NULL DEFAULT '모집중',
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (writer_no) REFERENCES member(member_no)
);

CREATE TABLE site_setting (
    setting_no INT AUTO_INCREMENT PRIMARY KEY, setting_key VARCHAR(100) NOT NULL UNIQUE, setting_value TEXT,
    update_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE company_content (
    content_no INT AUTO_INCREMENT PRIMARY KEY, content_type VARCHAR(30) NOT NULL, title VARCHAR(200) NOT NULL, content TEXT,
    image_path VARCHAR(255), video_url VARCHAR(255), category_name VARCHAR(100), use_yn CHAR(1) NOT NULL DEFAULT 'Y',
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app_version (
    version_no INT AUTO_INCREMENT PRIMARY KEY, version_name VARCHAR(50) NOT NULL, writer_no INT, change_log TEXT NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (writer_no) REFERENCES member(member_no)
);
```
</details>

---
<details>
<summary><b> 📊 테이블 구조 및 컬럼 설명 </b></summary>

### 📊 테이블 구조 및 컬럼 설명

본 프로젝트는 총 30개의 테이블로 구성되어 있으며, 주요 비즈니스 흐름을 담당하는 핵심 테이블 구조는 다음과 같습니다.

member: 회원(USER, SELLER, ADMIN) 통합 관리 및 권한, 포인트 상태 저장

member_social: 소셜 로그인 연동 (NAVER, KAKAO 등) 식별값 매핑

policy / member_agreement: 이용약관 및 개인정보처리방침 동의 이력 관리

seller_profile: 판매자 사업자 및 상점 세부 정보

category / product: N차 카테고리 분류 및 상품 정보 (재고, 가격, 할인율)

product_option: 상품별 추가 옵션(색상, 사이즈 등) 및 추가 금액

cart: 사용자별 장바구니에 담긴 상품 및 옵션, 수량 정보

orders / order_item: 전체 주문 정보 및 주문 시점의 스냅샷 데이터(가격, 옵션 고정) 저장

delivery: 택배사, 송장번호, 배송 상태(배송중, 완료 등)

order_claim: 교환/반품 신청 내역 및 처리 상태

product_review: 구매 확정된 주문상품 기준의 리뷰 및 평점 관리

member_point: 회원의 적립/사용 내역 관리

coupon / coupon_issue: 시스템 및 판매자 쿠폰 생성, 회원 발급 및 사용 이력

notice / faq / qna: 통합 고객센터 게시판 및 1:1 문의 답변 시스템

#### 1. member
| 컬럼명 | 설명 |
|---|---|
| `member_no` | 회원을 구분하는 내부 PK 번호 |
| `member_id` | 일반 로그인 시 사용하는 아이디, 소셜 회원은 NULL 가능 |
| `password` | BCrypt로 암호화하여 저장하는 비밀번호 |
| `role` | USER, SELLER, ADMIN 권한 구분 |
| `grade` | 회원 등급 정보 |
| `point` | 현재 보유 포인트 총합 |
| `status` | 정상, 중지, 휴면, 탈퇴 상태 |
| `last_login_date` | 마지막 로그인 시각 |
| `leave_date` | 회원 탈퇴 처리 시각 |

#### 2. member_social
| 컬럼명 | 설명 |
|---|---|
| `provider` | NAVER, KAKAO, GOOGLE 중 로그인 제공자 |
| `provider_user_id` | 소셜 로그인 업체가 제공하는 회원 고유 식별값 |
| `provider_email` | 소셜 로그인 과정에서 전달받은 이메일 |
| `UNIQUE(provider, provider_user_id)` | 하나의 소셜 계정이 여러 KMarket 회원에 연결되는 것을 방지 |
| `UNIQUE(member_no, provider)` | 한 회원이 같은 소셜 제공자를 중복 연동하는 것을 방지 |

#### 3. policy
| 컬럼명 | 설명 |
|---|---|
| `policy_type` | BUYER, SELLER, PRIVACY 등 약관 구분값 |
| `required_yn` | 회원가입 시 반드시 동의해야 하는 약관인지 여부 |
| `use_yn` | 현재 화면에 노출하여 사용하는 약관인지 여부 |

#### 4. member_agreement
| 컬럼명 | 설명 |
|---|---|
| `member_no` | 약관에 동의한 회원 번호 |
| `policy_no` | 동의한 약관 번호 |
| `agree_yn` | 동의 여부 |
| `agree_date` | 동의한 날짜와 시간 |

#### 5. seller_profile
| 컬럼명 | 설명 |
|---|---|
| `member_no` | 판매자 계정과 1:1로 연결되는 회원 번호 |
| `company_name` | 판매 상점의 상호명 |
| `ceo_name` | 사업자 대표자명 |
| `biz_no` | 사업자등록번호 |
| `online_sale_no` | 통신판매업 신고번호 |
| `status` | 운영준비, 승인대기, 운영중, 중단 상태 |

#### 6. category
| 컬럼명 | 설명 |
|---|---|
| `parent_no` | 2차 카테고리일 때 상위 1차 카테고리 번호 |
| `depth` | 1은 1차 카테고리, 2는 2차 카테고리 |
| `sort_order` | 화면에서 카테고리를 보여주는 순서 |
| `use_yn` | 카테고리 사용 여부 |

#### 7. product
| 컬럼명 | 설명 |
|---|---|
| `seller_no` | 상품을 등록한 판매자 번호 |
| `cate_no` | 상품이 속한 최종 2차 카테고리 번호 |
| `basic_desc` | 상품 목록에 보여줄 한 줄 소개 |
| `price` | 할인 전 상품 정가 |
| `discount_rate` | 상품 할인율 |
| `point` | 해당 상품 구매 시 적립되는 포인트 |
| `stock` | 옵션이 없는 기본 상품 재고 |
| `delivery_fee` | 해당 상품의 배송비 |
| `detail_content` | 상품 상세 페이지 본문 |
| `status` | 판매중, 판매중지, 품절, 삭제 상태 |
| `view_count` | 상품 상세 조회수 |
| `sold_count` | 누적 판매 수량 |

#### 8. product_image
| 컬럼명 | 설명 |
|---|---|
| `image_type` | THUMB1, THUMB2, THUMB3, MAIN, DETAIL 구분 |
| `image_path` | 서버에 저장된 이미지 파일 경로 |
| `sort_order` | 상세 이미지 출력 순서 |

#### 9. product_option
| 컬럼명 | 설명 |
|---|---|
| `option_name` | 색상, 사이즈, 용량 등 옵션 종류 |
| `option_value` | 블랙, XL, 500ml 등 실제 선택값 |
| `add_price` | 옵션 선택 시 추가되는 금액 |
| `stock` | 해당 옵션 조합의 재고 |
| `use_yn` | 옵션 사용 여부 |

#### 10. product_notice
| 컬럼명 | 설명 |
|---|---|
| `product_status` | 상품 상태 또는 제품 상태 안내 |
| `tax_type` | 과세 여부 |
| `receipt_type` | 현금영수증 발급 가능 여부 |
| `business_type` | 판매자 사업자 구분 |
| `origin` | 원산지 |
| `as_info` | A/S 책임자 및 연락처 |
| `detail_notice` | 기타 제공고시 상세 내용 |

#### 11. cart
| 컬럼명 | 설명 |
|---|---|
| `option_no` | 선택한 상품 옵션, 옵션이 없으면 NULL |
| `quantity` | 장바구니에 담은 수량 |
| `update_date` | 수량을 수정한 가장 최근 시간 |

#### 12. orders
| 컬럼명 | 설명 |
|---|---|
| `order_code` | 사용자에게 보여주는 주문번호 |
| `orderer_name` | 주문 당시 주문자 이름 |
| `orderer_hp` | 주문 당시 주문자 연락처 |
| `total_price` | 상품 가격 합계 |
| `discount_price` | 상품 자체 할인 금액 합계 |
| `coupon_discount` | 쿠폰으로 할인된 금액 |
| `point_use` | 주문 시 사용한 포인트 |
| `point_save` | 주문 완료 후 적립될 포인트 |
| `pay_price` | 실제 최종 결제 금액 |
| `payment_method` | 신용카드, 계좌이체, 무통장입금, 카카오페이 등 |
| `payment_status` | 결제대기, 결제완료, 결제취소, 환불완료 상태 |
| `paid_date` | 결제가 완료된 시간 |
| `cancel_date` | 결제 취소 또는 환불 처리 시간 |
| `order_status` | 주문 전체의 대표 상태 |

#### 13. delivery
| 컬럼명 | 설명 |
|---|---|
| `seller_no` | 판매자별 배송 처리를 위한 판매자 번호 |
| `courier` | 택배사명 |
| `invoice_no` | 송장번호 |
| `delivery_status` | 배송준비, 배송중, 배송완료 등 현재 상태 |
| `ready_date` | 배송준비 상태가 된 시간 |
| `shipped_date` | 배송이 시작된 시간 |
| `delivered_date` | 배송완료 처리 시간 |
| `memo` | 배송 요청사항 |

#### 14. order_item
| 컬럼명 | 설명 |
|---|---|
| `delivery_no` | 판매자별 배송 정보와 연결되는 번호 |
| `option_name` | 주문 당시의 옵션명 |
| `option_value` | 주문 당시의 옵션값 |
| `product_name` | 주문 당시 상품명 스냅샷 |
| `product_image` | 주문 당시 상품 썸네일 경로 |
| `price` | 주문 당시 상품 정가 |
| `discount_rate` | 주문 당시 할인율 |
| `total_price` | 해당 주문상품의 최종 금액 |
| `item_status` | 주문상품 단위 상태 |

#### 15. order_claim
| 컬럼명 | 설명 |
|---|---|
| `claim_type` | 반품 또는 교환 구분 |
| `claim_reason` | 단순변심, 불량, 오배송 등 선택 사유 |
| `detail_reason` | 사용자가 작성한 상세 사유 |
| `status` | 신청, 승인, 반려, 수거중, 완료 상태 |
| `process_date` | 최종 처리 완료 시간 |

#### 16. claim_file
| 컬럼명 | 설명 |
|---|---|
| `ori_name` | 사용자가 업로드한 원본 파일명 |
| `new_name` | 서버에 저장될 때 변경한 파일명 |
| `claim_no` | 해당 반품 또는 교환 신청 번호 |

#### 17. product_review
| 컬럼명 | 설명 |
|---|---|
| `order_item_no` | 구매한 주문상품 번호, 한 주문상품당 리뷰 1개 제한 |
| `rating` | 1점부터 5점까지의 상품 평점 |
| `content` | 리뷰 본문 |

#### 18. review_image
| 컬럼명 | 설명 |
|---|---|
| `image_path` | 서버에 저장된 리뷰 첨부 이미지 경로 |
| `sort_order` | 리뷰 이미지 노출 순서 |

#### 19. member_point
| 컬럼명 | 설명 |
|---|---|
| `order_no` | 주문으로 발생한 포인트일 때 연결되는 주문번호 |
| `point_type` | 적립, 사용, 차감, 환불 구분 |
| `point_value` | 변동된 포인트 수치 |
| `balance_point` | 변동 후 남은 포인트 |
| `reason` | 포인트 지급·차감 이유 |
| `expire_date` | 포인트 만료일 |

#### 20. coupon
| 컬럼명 | 설명 |
|---|---|
| `seller_no` | NULL이면 관리자가 발급한 공통 쿠폰 |
| `coupon_type` | 개별상품할인, 주문상품할인, 배송비무료 구분 |
| `benefit_type` | 금액할인, 비율할인, 무료배송 구분 |
| `benefit_value` | 할인 금액 또는 할인율 |
| `min_order_price` | 쿠폰 사용이 가능한 최소 주문 금액 |
| `max_discount_price` | 비율 할인 시 최대 할인 가능 금액 |
| `issue_limit` | 발급 가능한 최대 수량, NULL이면 제한 없음 |
| `caution` | 쿠폰 사용 유의사항 |

#### 21. coupon_issue
| 컬럼명 | 설명 |
|---|---|
| `issue_code` | 회원별로 발급된 개별 쿠폰 식별번호 |
| `order_no` | 쿠폰을 사용한 주문번호 |
| `status` | 발급, 사용, 만료, 종료 상태 |
| `used_date` | 쿠폰 사용 시간 |
| `issue_date` | 쿠폰 발급 시간 |

#### 22. banner
| 컬럼명 | 설명 |
|---|---|
| `banner_size` | 배너 권장 가로·세로 크기 |
| `bg_color` | 배너 배경색 값 |
| `link_url` | 배너 클릭 시 이동할 주소 |
| `banner_position` | MAIN1, PRODUCT1, MEMBER1, MY1 등 노출 위치 |
| `image_path` | 서버에 저장된 배너 이미지 경로 |
| `sort_order` | 같은 위치에서 배너가 출력되는 순서 |
| `start_datetime` | 배너 노출 시작일시 |
| `end_datetime` | 배너 노출 종료일시 |
| `use_yn` | Y면 노출, N이면 숨김 |

#### 23. cs_category
| 컬럼명 | 설명 |
|---|---|
| `parent_no` | 2차 카테고리일 경우 상위 1차 카테고리 번호 |
| `board_type` | COMMON, FAQ, QNA 중 적용 범위 |
| `depth` | 1차 또는 2차 카테고리 구분 |
| `sort_order` | 고객센터 메뉴 노출 순서 |
| `use_yn` | 카테고리 사용 여부 |

#### 24. notice
| 컬럼명 | 설명 |
|---|---|
| `writer_no` | 공지사항을 작성한 관리자 회원번호 |
| `notice_type` | 일반공지, 이벤트공지, 점검공지 등 공지 유형 |
| `hit` | 공지사항 조회수 |

#### 25. faq
| 컬럼명 | 설명 |
|---|---|
| `cs_cate_no` | FAQ가 속하는 고객센터 카테고리 번호 |
| `writer_no` | FAQ를 작성한 관리자 회원번호 |
| `hit` | FAQ 조회수 |

#### 26. qna
| 컬럼명 | 설명 |
|---|---|
| `member_no` | 문의를 작성한 회원 번호 |
| `order_no` | 주문 관련 문의일 때 연결되는 주문번호 |
| `cs_cate_no` | 문의가 속하는 고객센터 카테고리 번호 |
| `answer` | 관리자 답변 내용 |
| `answer_member_no` | 답변을 작성한 관리자 회원번호 |
| `status` | 검토중 또는 답변완료 상태 |
| `answer_date` | 답변이 등록된 시간 |

#### 27. recruit
| 컬럼명 | 설명 |
|---|---|
| `writer_no` | 채용공고를 작성한 관리자 회원번호 |
| `department` | 모집 부서 |
| `career` | 신입, 경력 등 지원 경력 조건 |
| `recruit_type` | 정규직, 계약직, 인턴 등 고용형태 |
| `start_date` | 모집 시작일 |
| `end_date` | 모집 마감일 |
| `note` | 채용 공고 상세 내용 |
| `status` | 모집중 또는 마감 상태 |

#### 28. site_setting
| 컬럼명 | 설명 |
|---|---|
| `setting_key` | site_name, company_tel처럼 설정을 구분하는 키 |
| `setting_value` | 키에 대응하는 실제 설정값 |
| `update_date` | 마지막 수정 시간 |

#### 29. company_content
| 컬럼명 | 설명 |
|---|---|
| `content_type` | INTRO, CULTURE, STORY, MEDIA 콘텐츠 구분 |
| `image_path` | 콘텐츠 대표 이미지 경로 |
| `video_url` | 유튜브 등 외부 영상 링크 |
| `category_name` | 소식 또는 미디어의 세부 분류명 |
| `use_yn` | 화면 노출 여부 |
| `reg_date` | 최신글 5개 조회 시 기준이 되는 등록일 |

#### 30. app_version
| 컬럼명 | 설명 |
|---|---|
| `version_name` | v1.0.0 같은 버전명 |
| `writer_no` | 버전 정보를 등록한 관리자 회원번호 |
| `change_log` | 해당 버전에서 추가·수정된 내용 |
</details>

---

### ✅ 작업 진행 순서
Figma 화면 설계서 분석

프로젝트 폴더 구조 세팅 및 공통 레이아웃/CSS 작성

담당 페이지 HTML 마크업 및 CSS 적용

전체 화면 링크 연결 및 UI 검수

AWS RDS(MySQL) DB 테이블 구축 및 Spring Boot 연동

### 🎯 프로젝트 목표
쇼핑몰 IA 구조에 맞는 완벽한 프론트엔드 화면 구현

담당 영역별 분리 작업을 통한 효율적인 백엔드 확장 및 평화로운 Git 협업

실무 비즈니스 로직(결제 스냅샷, 환불, 포인트, 쿠폰)을 지원하는 무결성 DB 구축

<img src="https://capsule-render.vercel.app/api?type=waving&color=9ACB34&theme=navy&height=150&section=footer&text=Thank%20you%20for%20reading!&fontSize=30&animation=fadeIn" width="100%" />
