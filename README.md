# ShoppingMall-project

## 📌 프로젝트 소개

ShoppingMall-project는 쇼핑몰 화면 설계서를 기반으로 제작하는 웹 페이지 프로젝트입니다.  
사용자가 상품을 조회하고 장바구니에 담아 주문까지 진행하는 쇼핑몰의 기본 흐름을 구성하며, 회원가입/로그인, 마이페이지, 고객센터, 정책 페이지, 관리자 페이지까지 포함한 쇼핑몰 전체 화면 구조를 구현하는 것을 목표로 합니다.

이번 프로젝트는 팀원별 담당 영역을 명확하게 분리하여 각자 독립된 폴더에서 작업할 수 있도록 구성했습니다. 이를 통해 Git 협업 과정에서 발생할 수 있는 충돌을 최소화하고, 각 파트별 책임 범위를 분명하게 나누어 효율적으로 개발을 진행합니다.

---

## 🛠️ 사용 기술

- HTML5
- CSS3
- JavaScript
- Git / GitHub
- Figma 화면 설계서 기반 구현

---

## 👥 팀원

| 이름 | 담당 파트 |
|---|---|
| 허민재 | ... |
| 양지웅 | ... |
| 정인길 |... |
| 최수빈 | ... |

---

## 📋 업무 분배

### 👤 허민재 - 메인, 상품 프로세스 및 회사 소개

페이지를 담당합니다.

#### 담당 영역

- 쇼핑몰 메인 화면

#### 담당 페이지

```plaintext
index.html
```

---

### 👤 양지웅 - 회원 인증 및 마이페이지

영역을 담당합니다.

#### 담당 영역

- 로그인 및 회원가입

#### 담당 페이지

```plaintext
member/login.html
```

---

### 👤 정인길 - 고객센터 및 약관/정책

 페이지를 담당합니다.

#### 담당 영역

- 고객센터 메인


#### 담당 페이지

```plaintext
cs/index.html

```

---

### 👤 최수빈 - Admin 관리자 웹 시스템

화면을 담당합니다.

#### 담당 영역

- 관리자 메인 대시보드


#### 담당 페이지

```plaintext
admin/index.html

```

---

## 📁 프로젝트 폴더 구조

```plaintext
ShoppingMall-project/
├── index.html
│
├── member/
│   ├── login.html
│   ├── signup.html
│   └── register.html
│
├── product/
│   ├── list.html
│   ├── view.html
│   ├── search.html
│   ├── cart.html
│   ├── order.html
│   └── complete.html
│
├── my/
│   ├── home.html
│   ├── ordered.html
│   ├── point.html
│   ├── coupon.html
│   ├── review.html
│   ├── qna.html
│   └── info.html
│
├── cs/
│   ├── index.html
│   ├── notice/
│   │   ├── list.html
│   │   └── view.html
│   ├── faq/
│   │   ├── list.html
│   │   └── view.html
│   └── qna/
│       ├── list.html
│       ├── view.html
│       └── write.html
│
├── policy/
│   ├── buyer.html
│   ├── seller.html
│   ├── finance.html
│   ├── location.html
│   └── privacy.html
│
├── company/
│   ├── index.html
│   ├── introduce.html
│   ├── notice.html
│   ├── promote.html
│   └── manage.html
│
├── admin/
│   ├── index.html
│   ├── config/
│   │   ├── info.html
│   │   └── banner.html
│   ├── product/
│   │   ├── list.html
│   │   └── register.html
│   └── cs/
│       ├── notice/
│       │   ├── list.html
│       │   ├── view.html
│       │   ├── write.html
│       │   └── modify.html
│       ├── faq/
│       │   ├── list.html
│       │   ├── view.html
│       │   ├── write.html
│       │   └── modify.html
│       └── qna/
│           ├── list.html
│           ├── view.html
│           └── reply.html
│
├── css/
│   ├── common.css          
│   ├── main.css           
│   │
│   ├── member/            
│   │   ├── login.css
│   │   ├── signup.css
│   │   └── register.css
│   │
│   ├── product/           
│   │   ├── list.css
│   │   ├── view.css
│   │   ├── search.css
│   │   ├── cart.css
│   │   ├── order.css
│   │   └── complete.css
│   │
│   ├── my/                
│   │   ├── my-common.css   
│   │   ├── home.css
│   │   ├── ordered.css
│   │   ├── point.css
│   │   ├── coupon.css
│   │   ├── review.css
│   │   ├── qna.css
│   │   └── info.css
│   │
│   ├── cs/                 
│   │   ├── index.css      
│   │   ├── notice/
│   │   │   ├── list.css
│   │   │   └── view.css
│   │   ├── faq/
│   │   │   ├── list.css
│   │   │   └── view.css
│   │   └── qna/
│   │       ├── list.css
│   │       ├── view.css
│   │       └── write.css
│   │
│   ├── policy/             
│   │   ├── buyer.css
│   │   ├── seller.css
│   │   ├── finance.css
│   │   ├── location.css
│   │   └── privacy.css
│   │
│   ├── company/            
│   │   ├── index.css
│   │   ├── introduce.css
│   │   ├── notice.css
│   │   ├── promote.css
│   │   └── manage.css
│   │
│   └── admin/              
│       ├── admin-common.css
│       ├── config/
│       │   ├── info.css
│       │   ├── basic.css
│       │   ├── policy.css
│       │   ├── banner.css
│       │   ├── category.css
│       │   └── version.css
│       ├── product/
│       │   ├── list.css
│       │   └── register.css
│       └── cs/
│           ├── notice/
│           │   ├── list.css
│           │   ├── view.css
│           │   ├── write.css
│           │   └── modify.css
│           ├── faq/
│           │   ├── list.css
│           │   ├── view.css
│           │   ├── write.css
│           │   └── modify.css
│           └── qna/
│               ├── list.css
│               ├── view.css
│               └── reply.css
│
└── images/
```

---

## 🧩 주요 기능

### 1. 메인 및 상품 영역

- 쇼핑몰 메인 화면 구성
- 상품 목록 조회
- 상품 상세 정보 확인
- 상품 검색 결과 화면 구성
- 장바구니 화면 구성
- 주문/결제 및 주문 완료 화면 구성

### 2. 회원 및 마이페이지 영역

- 로그인 화면 구성
- 회원가입 약관 동의 화면 구성
- 회원가입 정보 입력 화면 구성
- 주문내역, 포인트, 쿠폰 화면 구성
- 나의 리뷰 및 문의 내역 화면 구성
- 회원정보 수정 화면 구성

### 3. 고객센터 및 정책 영역

- 고객센터 메인 화면 구성
- 공지사항, FAQ, 문의하기 게시판 화면 구성
- 문의하기 글쓰기 화면 구성
- 구매자/판매자 약관 및 개인정보처리방침 화면 구성

### 4. 관리자 영역

- 관리자 메인 대시보드 구성
- 환경설정 및 배너 관리 화면 구성
- 상품 목록 및 상품 등록 화면 구성
- 공지사항, FAQ, 문의 관리 화면 구성

---

## 🔀 Git 협업 방식

본 프로젝트는 팀원별 작업 폴더를 분리하여 Git 충돌을 최소화하는 방식으로 진행합니다.

### 브랜치 전략 예시

```plaintext
main
├── feature/main-product-company
├── feature/member-my
├── feature/cs-policy
└── feature/admin
```

### 작업 규칙

- 각 팀원은 본인 담당 폴더 중심으로 작업합니다.
- 공통 CSS 수정 시 팀원들과 먼저 공유합니다.
- 작업 전 항상 최신 코드를 pull 받은 뒤 작업합니다.
- 기능 단위로 commit을 작성합니다.
- main 브랜치 직접 push를 지양하고 PR 또는 병합 요청을 통해 반영합니다.

## 🎨 CSS 마크업 및 스타일 가이드

팀원 간 일관된 스타일을 유지하고, 추후 동적 웹 프로젝트(Spring Boot / JSP 등)로의 원활한 확장을 위해 아래의 CSS 작성 규칙을 준수합니다.

### 1. CSS 파일 구조 및 호출 규칙
- **공통/일반 영역**: `css/common.css`를 필수로 포함하고, 각 도메인별 메인 CSS를 링크합니다.
- **관리자(Admin) 영역**: 구조적 복잡도를 낮추기 위해 공통 레이아웃과 페이지별 CSS를 분리하여 **각 HTML마다 최소 2개 이상의 CSS**를 조합하여 호출합니다.
  - *예시: 공통 CSS (`admin-common.css`) + 페이지별 CSS (`basic.css`)*

---

## ✅ 작업 진행 순서

1. Figma 화면 설계서 확인
2. 프로젝트 폴더 구조 생성
3. 공통 레이아웃 및 CSS 작성
4. 담당 페이지별 HTML 마크업 작성
5. 페이지별 CSS 적용
6. 화면 간 링크 연결
7. 전체 화면 검수 및 수정
8. Git 병합 및 최종 정리

---

## 📌 프로젝트 목표

- 쇼핑몰 IA 구조에 맞는 화면 구현
- 담당 영역 분리를 통한 효율적인 협업
- Git 충돌을 최소화한 팀 프로젝트 진행
- 실제 쇼핑몰 서비스 흐름에 가까운 화면 구성
- 추후 JSP/Servlet 기반 동적 웹 프로젝트로 확장 가능한 구조 설계
