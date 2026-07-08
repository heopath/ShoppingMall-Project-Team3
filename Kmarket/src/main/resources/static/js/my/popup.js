document.addEventListener("DOMContentLoaded", function () {
  const orderModal = document.querySelector(".order-modal");
  const sellerModal = document.querySelector(".seller-modal");
  const inquiryModal = document.querySelector(".inquiry-modal");
  const confirmModal = document.querySelector(".confirm-modal");
  const reviewModal = document.querySelector(".review-modal");
  const returnModal = document.querySelector(".return-modal");
  const exchangeModal = document.querySelector(".exchange-modal");

  // 주문상세
  document.querySelectorAll(".order-link").forEach(function (link) {
    link.addEventListener("click", function (e) {
      e.preventDefault();
      orderModal.style.display = "flex";
    });
  });

  // 판매자정보
  document.querySelectorAll(".seller-link").forEach(function (link) {
    link.addEventListener("click", function (e) {
      e.preventDefault();
      sellerModal.style.display = "flex";
    });
  });

  // 문의하기
  document.querySelectorAll(".seller-inquiry-btn").forEach(function (btn) {
    btn.addEventListener("click", function () {
      sellerModal.style.display = "none";
      inquiryModal.style.display = "flex";
    });
  });

  // 문의 등록
  // const inquiryForm = document.querySelector(".inquiry-form");

  // if (inquiryForm) {
  //   inquiryForm.addEventListener("submit", function (e) {
  //     e.preventDefault();

  //     alert("문의가 등록되었습니다.");

  //     inquiryModal.style.display = "none";
  //     inquiryForm.reset();
  //   });
  // }

  // 문의 취소
  document.querySelectorAll(".inquiry-cancel").forEach(function (btn) {
    btn.addEventListener("click", function () {
      inquiryModal.style.display = "none";
      sellerModal.style.display = "flex";
    });
  });

  // 구매확정 버튼
  document.querySelectorAll(".confirm-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();

      confirmModal.style.display = "flex";
    });
  });

  // 구매확정 취소
  document.querySelectorAll(".confirm-cancel-btn").forEach(function (btn) {
    btn.addEventListener("click", function () {
      const modal = btn.closest(
        ".confirm-modal, .review-modal, .return-modal, .exchange-modal",
      );

      if (modal) {
        modal.style.display = "none";
      }
    });
  });

  // 구매확정 확인
  document
    .querySelector(".confirm-ok-btn")
    .addEventListener("click", function () {
      alert("구매확정이 완료되었습니다.");

      confirmModal.style.display = "none";
    });

  // 상품평 작성
  document.querySelectorAll(".review-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();

      reviewModal.style.display = "flex";
    });
  });

  // 반품 신청
  document.querySelectorAll(".return-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();

      returnModal.style.display = "flex";
    });
  });

  // 교환 신청
  document.querySelectorAll(".exchange-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();

      exchangeModal.style.display = "flex";
    });
  });

  // X 닫기 버튼
  document.querySelectorAll(".modal-close").forEach(function (btn) {
    btn.addEventListener("click", function () {
      const modal = btn.closest(
        ".order-modal, .seller-modal, .inquiry-modal, .confirm-modal, .review-modal, .return-modal, .exchange-modal",
      );

      if (modal) {
        modal.style.display = "none";
      }
    });
  });

  // 배경 클릭 닫기
  document
    .querySelectorAll(
      ".order-modal, .seller-modal, .inquiry-modal, .confirm-modal, .review-modal, .return-modal, .exchange-modal",
    )
    .forEach(function (modal) {
      modal.addEventListener("click", function (e) {
        if (e.target === modal) {
          modal.style.display = "none";
        }
      });
    });
});
