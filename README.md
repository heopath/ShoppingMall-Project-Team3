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

## 📋 업무 분배

### 👤 허민재 - 메인, 상품 프로세스 및 회사 소개

사용자가 처음 진입하는 쇼핑몰 메인 화면과 상품 구매 흐름, 회사 소개 페이지를 담당합니다.

#### 담당 영역

- 쇼핑몰 메인 화면
- 상품 목록, 상세, 검색 결과 화면
- 장바구니, 주문/결제, 주문 완료 화면
- 회사 소개 및 기업 정보 관련 화면

#### 담당 페이지

```plaintext
index.html
product/list.html
product/view.html
product/search.html
product/cart.html
product/order.html
product/complete.html
company/
```

---

### 👤 양지웅 - 회원 인증 및 마이페이지

사용자의 가입, 로그인 기능과 로그인 이후 개인 활동 내역을 확인하는 마이페이지 영역을 담당합니다.

#### 담당 영역

- 로그인 및 회원가입
- 약관 동의
- 주문내역, 포인트, 쿠폰 조회
- 리뷰, 문의 내역 관리
- 회원정보 수정

#### 담당 페이지

```plaintext
member/login.html
member/signup.html
member/register.html
my/home.html
my/ordered.html
my/point.html
my/coupon.html
my/review.html
my/qna.html
my/info.html
```

---

### 👤 정인길 - 고객센터 및 약관/정책

게시판 중심의 고객센터 화면과 쇼핑몰 운영에 필요한 약관 및 정책 페이지를 담당합니다.

#### 담당 영역

- 고객센터 메인
- 공지사항 목록 및 상세보기
- FAQ 목록 및 상세보기
- 1:1 문의 목록, 상세보기, 글쓰기
- 구매자/판매자 약관 및 개인정보처리방침

#### 담당 페이지

```plaintext
cs/index.html
cs/notice/list.html
cs/notice/view.html
cs/faq/list.html
cs/faq/view.html
cs/qna/list.html
cs/qna/view.html
cs/qna/write.html
policy/buyer.html
policy/seller.html
policy/finance.html
policy/location.html
policy/privacy.html
```

---

### 👤 최수빈 - Admin 관리자 웹 시스템

쇼핑몰 운영진과 판매자가 사용하는 관리자 백오피스 화면을 담당합니다.

#### 담당 영역

- 관리자 메인 대시보드
- 환경설정 및 배너 관리
- 상품 목록 및 상품 등록
- 공지사항, FAQ, 문의 관리
- 고객 게시글 답변 처리

#### 담당 페이지

```plaintext
admin/index.html
admin/config/info.html
admin/config/banner.html
admin/product/list.html
admin/product/register.html
admin/cs/notice/list.html
admin/cs/notice/view.html
admin/cs/notice/write.html
admin/cs/notice/modify.html
admin/cs/faq/list.html
admin/cs/faq/view.html
admin/cs/faq/write.html
admin/cs/faq/modify.html
admin/cs/qna/list.html
admin/cs/qna/view.html
admin/cs/qna/reply.html
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
│   ├── product.css
│   ├── my.css
│   ├── cs.css
│   ├── policy.css
│   └── admin.css
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

## 📝 커밋 메시지 예시

```plaintext
feat: 상품 목록 페이지 마크업 추가
feat: 고객센터 공지사항 목록 화면 구현
style: 마이페이지 CSS 적용
fix: 장바구니 페이지 이미지 경로 수정
docs: README 업무 분배 내용 추가
```

---

## 👥 팀원

| 이름 | 담당 파트 |
|---|---|
| 허민재 | 메인, 상품 프로세스, 회사 소개 |
| 양지웅 | 회원 인증, 마이페이지 |
| 정인길 | 고객센터, 약관/정책 |
| 최수빈 | 관리자 웹 시스템 |

---

## 📌 프로젝트 목표

- 쇼핑몰 IA 구조에 맞는 화면 구현
- 담당 영역 분리를 통한 효율적인 협업
- Git 충돌을 최소화한 팀 프로젝트 진행
- 실제 쇼핑몰 서비스 흐름에 가까운 화면 구성
- 추후 JSP/Servlet 기반 동적 웹 프로젝트로 확장 가능한 구조 설계
