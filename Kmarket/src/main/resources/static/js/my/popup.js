document.addEventListener("DOMContentLoaded", function () {

  const orderModal = document.querySelector(".order-modal");
  const sellerModal = document.querySelector(".seller-modal");
  const inquiryModal = document.querySelector(".inquiry-modal");
  const confirmModal = document.querySelector(".confirm-modal");
  const reviewModal = document.querySelector(".review-modal");
  const returnModal = document.querySelector(".return-modal");
  const exchangeModal = document.querySelector(".exchange-modal");

  // ===========================
  // 주문상세 조회
  // ===========================
  document.querySelectorAll(".order-link").forEach(function (link) {

    link.addEventListener("click", function (e) {

      e.preventDefault();

      const orderNo = this.dataset.orderNo;

      fetch(`/my/order/detail/${orderNo}`)
          .then(res => res.json())
          .then(order => {

            document.getElementById("modalOrderDate").textContent =
                order.orderDate ? order.orderDate.substring(0, 10) : "";

            document.getElementById("detailOrderDate").textContent =
                order.orderDate ? order.orderDate.substring(0, 10) : "";

            document.getElementById("detailProductImage").src =
                order.productImage || "";

            document.getElementById("detailOrderCode").textContent =
                "주문번호 : " + (order.orderCode || "");

            document.getElementById("detailCompanyName").textContent =
                order.companyName || "";

            document.getElementById("detailProductName").textContent =
                order.productName || "";

            document.getElementById("detailQuantity").textContent =
                (order.quantity || 0) + "개";

            document.getElementById("detailPrice").textContent =
                (order.price || 0).toLocaleString() + "원";

            const discount =
                Math.floor((order.price || 0) * ((order.discountRate || 0) / 100));

            document.getElementById("detailDiscount").textContent =
                "-" + discount.toLocaleString() + "원";

            document.getElementById("detailTotalPrice").textContent =
                (order.totalPrice || 0).toLocaleString() + "원";

            document.getElementById("detailTotalPrice2").textContent =
                (order.totalPrice || 0).toLocaleString() + "원";

            document.getElementById("detailDeliveryStatus").textContent =
                order.deliveryStatus || "";

            document.getElementById("receiverName").textContent =
                order.receiverName || "";

            document.getElementById("receiverHp").textContent =
                order.receiverHp || "";

            document.getElementById("receiverAddress").textContent =
                "[" + (order.zip || "") + "] "
                + (order.addr1 || "") + " "
                + (order.addr2 || "");

            document.getElementById("receiverMemo").textContent =
                order.memo || "";

            orderModal.style.display = "flex";

          })
          .catch(err => {
            console.error(err);
            alert("주문정보를 불러오지 못했습니다.");
          });

    });

  });

  // 판매자정보
  document.querySelectorAll(".seller-link").forEach(function(link){

    link.addEventListener("click",function(e){

      e.preventDefault();

      const sellerNo = this.dataset.sellerNo;

      fetch(`/my/seller/${sellerNo}`)
          .then(res => res.json())
          .then(seller => {

            document.getElementById("sellerGrade").textContent =
                seller.grade || "";

            document.getElementById("sellerCompanyName").textContent =
                seller.companyName || "";

            document.getElementById("sellerCeo").textContent =
                seller.ceo || "";

            document.getElementById("sellerHp").textContent =
                seller.hp || "";

            document.getElementById("sellerFax").textContent =
                seller.fax || "";

            document.getElementById("sellerEmail").textContent =
                seller.email || "";

            document.getElementById("sellerBizNo").textContent =
                seller.bizRegNo || "";

            document.getElementById("sellerAddress").textContent =
                "[" + (seller.zip || "") + "] "
                + (seller.addr1 || "") + " "
                + (seller.addr2 || "");

            sellerModal.style.display = "flex";

          })
          .catch(err => {
            console.error(err);
            alert("판매자 정보를 불러오지 못했습니다.");
          });

    });

  });

  // 문의하기
  document.querySelectorAll(".seller-inquiry-btn").forEach(function (btn) {
    btn.addEventListener("click", function () {
      sellerModal.style.display = "none";
      inquiryModal.style.display = "flex";
    });
  });

  // 문의 취소
  document.querySelectorAll(".inquiry-cancel").forEach(function (btn) {
    btn.addEventListener("click", function () {
      inquiryModal.style.display = "none";
      sellerModal.style.display = "flex";
    });
  });

  // 구매확정
  document.querySelectorAll(".confirm-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      confirmModal.style.display = "flex";
    });
  });

  document.querySelectorAll(".confirm-cancel-btn").forEach(function (btn) {
    btn.addEventListener("click", function () {

      const modal = btn.closest(
          ".confirm-modal, .review-modal, .return-modal, .exchange-modal"
      );

      if (modal) {
        modal.style.display = "none";
      }

    });
  });

  const confirmOkBtn = document.querySelector(".confirm-ok-btn");

  if (confirmOkBtn) {
    confirmOkBtn.addEventListener("click", function () {
      alert("구매확정이 완료되었습니다.");
      confirmModal.style.display = "none";
    });
  }

  // 상품평
  document.querySelectorAll(".review-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      reviewModal.style.display = "flex";
    });
  });

  // 반품
  document.querySelectorAll(".return-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      returnModal.style.display = "flex";
    });
  });

  // 교환
  document.querySelectorAll(".exchange-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      exchangeModal.style.display = "flex";
    });
  });

  // X 버튼
  document.querySelectorAll(".modal-close").forEach(function (btn) {

    btn.addEventListener("click", function () {

      const modal = btn.closest(
          ".order-modal, .seller-modal, .inquiry-modal, .confirm-modal, .review-modal, .return-modal, .exchange-modal"
      );

      if (modal) {
        modal.style.display = "none";
      }

    });

  });

  // 배경 클릭
  document.querySelectorAll(
      ".order-modal, .seller-modal, .inquiry-modal, .confirm-modal, .review-modal, .return-modal, .exchange-modal"
  ).forEach(function (modal) {

    modal.addEventListener("click", function (e) {

      if (e.target === modal) {
        modal.style.display = "none";
      }

    });

  });

});