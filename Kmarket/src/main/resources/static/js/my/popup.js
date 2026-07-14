
document.addEventListener("DOMContentLoaded", function () {

  // 모든 모달 요소를 한 번에 제어하기 위한 상수 배열
  const allModalSelectors = ".order-modal, .seller-modal, .inquiry-modal, .confirm-modal, .review-modal, .return-modal, .exchange-modal";

  // [통합 모달 닫기 함수]
  function closeAllModals() {
    document.querySelectorAll(allModalSelectors).forEach(modal => {
      modal.style.display = "none";
    });
  }

  // [통합 모달 열기 함수]
  function openModal(modalElement) {
    closeAllModals(); // 일단 전부 닫고
    if (modalElement) modalElement.style.display = "flex"; // 타겟만 오픈
  }

  // [모달 변수 선언] - 이 부분이 있어야 openModal() 함수가 작동합니다.
  const orderModal = document.querySelector(".order-modal");
  const sellerModal = document.querySelector(".seller-modal");
  const inquiryModal = document.querySelector(".inquiry-modal");
  const confirmModal = document.querySelector(".confirm-modal#orderConfirmModal"); // ID로 정확히 지정
  const reviewModal = document.querySelector(".review-modal");
  const returnModal = document.querySelector(".return-modal");
  const exchangeModal = document.querySelector(".exchange-modal");
  const requestSuccessModal = document.getElementById("requestSuccessModal");

  // 전역 변수
  let selectedOrderItemNo = null;

  // ========================================================
  // [공통: 닫기 이벤트 바인딩 (섹션 8 통합)]
  // ========================================================
  document.querySelectorAll(".modal-close, .confirm-cancel-btn").forEach(btn => {
    btn.addEventListener("click", closeAllModals);
  });

  document.querySelectorAll(allModalSelectors).forEach(modal => {
    modal.addEventListener("click", (e) => {
      if (e.target === modal) closeAllModals();
    });
  });

  // ========================================================
  // [1. 주문상세 정보 단건 조회]
  // ========================================================
  document.querySelectorAll(".order-link").forEach(function (link) {
    link.addEventListener("click", function (e) {
      e.preventDefault();
      const orderNo = this.dataset.orderNo;

      fetch(`/my/order/detail/${orderNo}`)
          .then(res => res.json())
          .then(order => {
            // 데이터 매핑
            document.getElementById("modalOrderDate").textContent = order.orderDate ? order.orderDate.substring(0, 10) : "";
            document.getElementById("detailOrderDate").textContent = order.orderDate ? order.orderDate.substring(0, 10) : "";
            document.getElementById("detailProductImage").src = order.productImage || "";
            document.getElementById("detailOrderCode").textContent = "주문번호 : " + (order.orderCode || "");
            document.getElementById("detailCompanyName").textContent = order.companyName || "";
            document.getElementById("detailProductName").textContent = order.productName || "";
            document.getElementById("detailQuantity").textContent = (order.quantity || 0) + "개";

            document.getElementById("detailPrice").textContent = (order.price || 0).toLocaleString() + "원";

            const discount = Math.floor((order.price || 0) * ((order.discountRate || 0) / 100));
            document.getElementById("detailDiscount").textContent = "-" + discount.toLocaleString() + "원";

            document.getElementById("detailTotalPrice").textContent = (order.totalPrice || 0).toLocaleString() + "원";
            document.getElementById("detailTotalPrice2").textContent = (order.totalPrice || 0).toLocaleString() + "원";
            document.getElementById("detailDeliveryStatus").textContent = order.deliveryStatus || "";

            document.getElementById("receiverName").textContent = order.receiverName || "";
            document.getElementById("receiverHp").textContent = order.receiverHp || "";
            document.getElementById("receiverAddress").textContent = "[" + (order.zip || "") + "] " + (order.addr1 || "") + " " + (order.addr2 || "");
            document.getElementById("receiverMemo").textContent = order.memo || "";

            openModal(orderModal);
          })
          .catch(err => {
            console.error(err);
            alert("주문정보를 불러오지 못했습니다.");
          });
    });
  });


  // ========================================================
  // [2. 판매자 정보 단건 조회]
  // ========================================================
  document.querySelectorAll(".seller-link").forEach(function(link){
    link.addEventListener("click",function(e){
      e.preventDefault();
      const sellerNo = this.dataset.sellerNo;

      // 2-1. 판매자 정보 API 호출
      fetch(`/my/seller/${sellerNo}`)
          .then(res => res.json())
          .then(seller => {
            // 2-2. 데이터 매핑
            document.getElementById("sellerGrade").textContent = seller.grade || "";
            document.getElementById("sellerCompanyName").textContent = seller.companyName || "";
            document.getElementById("sellerCeo").textContent = seller.ceo || "";
            document.getElementById("sellerHp").textContent = seller.hp || "";
            document.getElementById("sellerFax").textContent = seller.fax || "";
            document.getElementById("sellerEmail").textContent = seller.email || "";
            document.getElementById("sellerBizNo").textContent = seller.bizRegNo || "";
            document.getElementById("sellerAddress").textContent =
                "[" + (seller.zip || "") + "] " + (seller.addr1 || "") + " " + (seller.addr2 || "");

            // 2-3. 통합 모달 관리 함수 호출
            openModal(sellerModal);
          })
          .catch(err => {
            console.error(err);
            alert("판매자 정보를 불러오지 못했습니다.");
          });
    });
  });


  // ========================================================
  // [3. 고객센터 문의하기 관련 로직]
  // ========================================================

  // 3-1. 판매자 모달 내 '문의하기' 클릭 시 문의 유형 카테고리 로드 및 모달 전환
  document.querySelectorAll(".seller-inquiry-btn").forEach(btn => {
    btn.addEventListener("click", async () => {
      const response = await fetch("/my/qna/category");
      if(!response.ok){
        alert("카테고리를 불러오지 못했습니다.");
        return;
      }
      const list = await response.json();
      const area = document.querySelector(".inquiry-type");
      area.innerHTML = "";

      list.forEach((cate, index)=>{
        area.insertAdjacentHTML("beforeend", `
                <label>
                    <input type="radio" name="cateName" value="${cate.csCateNo}" ${index === 0 ? "checked" : ""}>
                    <span>${cate.cateName}</span>
                </label>
            `);
      });

      // 통합 함수 사용하여 문의하기 모달로 전환
      openModal(inquiryModal);
    });
  });

  // 3-2. 작성된 문의 전송 처리
  const btnSubmit = document.querySelector(".btn-submit");
  if (btnSubmit) {
    btnSubmit.addEventListener("click", async () => {
      const checked = document.querySelector("input[name='cateName']:checked");
      if (!checked) { alert("문의 종류를 선택하세요."); return; }

      const data = {
        csCateNo: Number(checked.value),
        title: document.querySelector("#qnaTitle")?.value.trim() || "",
        content: document.querySelector("#qnaContent")?.value.trim() || ""
      };

      if (!data.title) { alert("제목을 입력하세요."); return; }
      if (!data.content) { alert("내용을 입력하세요."); return; }

      const response = await fetch("/my/qna", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        document.querySelector("#qnaTitle").value = "";
        document.querySelector("#qnaContent").value = "";

        const successModal = document.getElementById("requestSuccessModal");
        const successMsg = successModal.querySelector("#requestSuccessMsg");

        if (successMsg) successMsg.textContent = "문의가 정상적으로 등록되었습니다.";

        const okBtn = successModal.querySelector(".confirm-ok-btn");
        okBtn.onclick = () => { window.location.href = "/my/qna"; };

        // 성공 모달 호출
        openModal(successModal);
      } else {
        alert("문의 등록에 실패했습니다.");
      }
    });
  }

  // 3-3. 문의 등록 취소 시 다시 판매자 정보 팝업으로 복귀
  document.querySelectorAll(".inquiry-cancel").forEach(function (btn) {
    btn.addEventListener("click", function () {
      // 취소 시 판매자 정보 모달로 복귀
      openModal(sellerModal);
    });
  });


  // ========================================================
  // [4. 구매확정 프로세스]
  // ========================================================

  // 4-1. 목록의 '구매확정' 링크 클릭 시 작동
  document.querySelectorAll(".confirm-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      selectedOrderItemNo = this.dataset.orderItemNo;
      // 통합 함수 사용
      openModal(confirmModal);
    });
  });

  // 4-3. 실제 구매확정 비동기 요청 처리
  const orderConfirmModal = document.getElementById("orderConfirmModal");
  if (orderConfirmModal) {
    const confirmOkBtn = orderConfirmModal.querySelector(".confirm-ok-btn");
    if (confirmOkBtn) {
      confirmOkBtn.addEventListener("click", async function () {
        if (!selectedOrderItemNo) return;

        try {
          // 1. 서버에 구매확정 요청
          const response = await fetch(`/my/order/confirm/${selectedOrderItemNo}`, { method: "POST" });
          if (!response.ok) throw new Error();

          // 2. 구매확정 모달 닫기
          closeAllModals();

          // 3. 성공 모달 띄우기
          const successModal = document.getElementById("requestSuccessModal");
          successModal.querySelector("#requestSuccessMsg").textContent = "구매확정이 완료되었습니다.";

          // 4. [핵심] 성공 모달의 '확인' 버튼을 눌렀을 때 버튼을 교체하도록 설정
          const okBtn = successModal.querySelector(".confirm-ok-btn");
          okBtn.onclick = () => {
            // 버튼 영역 찾기
            const targetArea = document.querySelector(`.confirm-link[data-order-item-no="${selectedOrderItemNo}"]`)?.closest(".order-btns");

            if (targetArea) {
              // 영역 내부를 '리뷰 작성' 버튼으로 교체
              targetArea.innerHTML = `<a href="javascript:void(0)" class="review-link" data-order-item-no="${selectedOrderItemNo}">리뷰 작성</a>`;
            }

            // 버튼 교체 후 모달 닫기
            closeAllModals();
          };

          openModal(successModal);

        } catch (e) {
          alert("처리 중 오류가 발생했습니다.");
        }
      });
    }
  }


  // ========================================================
  // [5. 상품평(리뷰) 작성 및 이미지 업로드]
  // ========================================================

  // 5-1. '상품평 작성' 버튼 클릭 시 대상 상품 정보 조회 후 모달에 세팅
  document.addEventListener("click", async function (e) {
    // 클릭된 요소가 .review-link 클래스를 가지고 있는지 확인
    if (e.target && e.target.classList.contains("review-link")) {
      e.preventDefault();
      const orderItemNo = e.target.dataset.orderItemNo;

      try {
        const response = await fetch(`/my/review/${orderItemNo}`);
        const data = await response.json();

        // 폼 내의 hidden 필드에 상품 번호 및 상세 데이터 바인딩
        document.querySelector("#reviewOrderItemNo").value = data.orderItemNo;
        document.querySelector("#reviewProductNo").value = data.productNo;
        document.querySelector("#reviewProductName").textContent = data.productName;

        // 모달 오픈
        openModal(reviewModal);
      } catch (e) {
        alert("상품 정보를 불러오지 못했습니다.");
      }
    }
  });

  // 5-2. 바이너리 이미지 파일 포함한 리뷰 데이터 제출
  document.querySelector(".review-submit-btn").addEventListener("click", async function () {
    const rating = document.querySelector("input[name='rating']:checked");
    if (!rating) { alert("별점을 선택하세요."); return; }

    const content = document.querySelector("#reviewContent").value.trim();
    if (content.length < 10) { alert("내용을 10자 이상 입력하세요."); return; }

    const formData = new FormData();
    formData.append("orderItemNo", document.querySelector("#reviewOrderItemNo").value);
    formData.append("productNo", document.querySelector("#reviewProductNo").value);
    formData.append("rating", rating.value);
    formData.append("content", content);

    document.querySelectorAll("input[name='images']").forEach(file => {
      if (file.files.length > 0) formData.append("images", file.files[0]);
    });

    try {
      const response = await fetch("/my/review", { method: "POST", body: formData });
      if (!response.ok) throw new Error();

      document.querySelector("#reviewForm").reset();
      closeAllModals(); // 리뷰 모달 닫기

      const successModal = document.getElementById("requestSuccessModal");
      successModal.querySelector("#requestSuccessMsg").textContent = "상품평이 정상적으로 등록되었습니다.";

      // [핵심] 성공 모달 확인 버튼 클릭 시 UI 변경
      const okBtn = successModal.querySelector(".confirm-ok-btn");
      const orderItemNo = document.querySelector("#reviewOrderItemNo").value;

      okBtn.onclick = () => {
        const reviewLink = document.querySelector(`.review-link[data-order-item-no="${orderItemNo}"]`);
        if (reviewLink) {
          const parent = reviewLink.parentElement;
          // 1. 기존 클래스 review-link 대신 새로운 상태 클래스(review-done) 사용
          // 2. 버튼 형태 유지
          parent.innerHTML = `<a href="/my/review" class="review-done-btn">리뷰 보기</a>`;
        }
        closeAllModals();
      };

      openModal(successModal);

    } catch (e) {
      alert("상품평 등록에 실패했습니다.");
    }
  });


  // ========================================================
  // [6. 클레임 유틸리티 기능 - 반품 및 교환 레이어 제어]
  // ========================================================

  // 6-1. 통화 서식 변환 공통 유틸 함수
  function formatComma(num) {
    return Number(num || 0).toLocaleString() + '원';
  }

  // 6-2. 버튼 클릭 시 데이터를 모달 내부에 주입하는 함수
  function fillModalData(modal, dataset) {
    if (!modal) return;
    const modalDate = modal.querySelector(".modal-date");
    const modalImg = modal.querySelector(".modal-img");
    const modalOrderNo = modal.querySelector(".modal-order-no");
    const modalProductName = modal.querySelector(".modal-product-name");
    const modalQuantity = modal.querySelector(".modal-quantity");
    const modalPrice = modal.querySelector(".modal-price");
    const modalOriginPrice = modal.querySelector(".modal-origin-price");
    const modalDiscount = modal.querySelector(".modal-discount");
    const modalTotalPrice = modal.querySelector(".modal-total-price");

    if (modalDate) modalDate.textContent = dataset.date || '-';
    if (modalImg) modalImg.src = dataset.productImage || '';
    if (modalOrderNo) modalOrderNo.textContent = "주문번호 : " + (dataset.orderNo || '-');
    if (modalProductName) modalProductName.textContent = dataset.productName || '';
    if (modalQuantity) modalQuantity.textContent = "수량 : " + (dataset.quantity || '0') + "개";
    if (modalPrice) modalPrice.textContent = formatComma(dataset.price);
    if (modalOriginPrice) modalOriginPrice.textContent = "판매가 " + formatComma(dataset.price);
    if (modalDiscount) modalDiscount.textContent = "할인 " + (dataset.discount || 0) + "%";
    if (modalTotalPrice) modalTotalPrice.textContent = "결제금액 " + formatComma(dataset.totalPrice);
  }

  // 6-3. 목록 내 '반품신청' 버튼 이벤트 트리거
  document.querySelectorAll(".return-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      e.stopPropagation();
      selectedOrderItemNo = this.dataset.orderItemNo;

      if (returnModal) {
        fillModalData(returnModal, this.dataset);
        // 통합 함수 사용하여 호출
        openModal(returnModal);
      }
    });
  });

  // 6-4. 목록 내 '교환신청' 버튼 이벤트 트리거
  document.querySelectorAll(".exchange-link").forEach(function (btn) {
    btn.addEventListener("click", function (e) {
      e.preventDefault();
      e.stopPropagation();
      selectedOrderItemNo = this.dataset.orderItemNo;

      if (exchangeModal) {
        fillModalData(exchangeModal, this.dataset);
        // 통합 함수 사용하여 호출
        openModal(exchangeModal);
      }
    });
  });


  // ========================================================
  // [7. 실제 클레임 처리 API 통신 프로세스]
  // ========================================================

  // 7-1. 실제 반품 신청 서버 전송 기능
  const returnSubmitBtn = document.getElementById("returnSubmitBtn");
  if (returnSubmitBtn) {
    returnSubmitBtn.addEventListener("click", async function () {
      if (!selectedOrderItemNo) return;

      const returnType = document.querySelector("input[name='returnType']:checked")?.value;
      const reason = document.getElementById("returnReason")?.value.trim();
      const fileInput = document.getElementById("returnFile");
      if (!reason) { alert("반품 사유를 입력해 주세요."); return; }

      const formData = new FormData();
      formData.append("orderItemNo", selectedOrderItemNo);
      formData.append("returnType", returnType);
      formData.append("reason", reason);
      if (fileInput?.files[0]) formData.append("returnFile", fileInput.files[0]);

      try {
        const response = await fetch("/my/order/return", { method: "POST", body: formData });
        if (!response.ok) throw new Error();

        // 모든 모달 닫기 후 성공 모달 오픈
        closeAllModals();
        const successModal = document.getElementById("requestSuccessModal");
        const successMsg = successModal.querySelector("#requestSuccessMsg");
        if (successMsg) successMsg.textContent = "신청이 정상적으로 완료되었습니다.";

        // 버튼 클릭 시 UI 변경 로직 추가
        const okBtn = successModal.querySelector(".confirm-ok-btn");
        okBtn.onclick = () => {
          // 새로고침 대신 화면 즉시 변경
          const targetArea = document.querySelector(`.return-link[data-order-item-no="${selectedOrderItemNo}"]`)?.closest(".order-btns");
          if (targetArea) {
            targetArea.innerHTML = `<span class="status-txt">반품신청</span>`;
          }
          closeAllModals();
          // 필요시 window.location.reload(); 를 여기에 넣어 완전 동기화 가능
        };

        openModal(successModal);
      } catch (e) {
        alert("반품 신청 처리 중 오류가 발생했습니다.");
      }
    });
  }

  // 7-2. 실제 교환 신청 서버 전송 기능
  const exchangeSubmitBtn = document.getElementById("exchangeSubmitBtn");
  if (exchangeSubmitBtn) {
    exchangeSubmitBtn.addEventListener("click", async function () {
      if (!selectedOrderItemNo) return;

      // 1. 여기서 선택된 교환 사유(교환 타입)를 가져옵니다.
      const exchangeType = document.querySelector("input[name='exchangeType']:checked")?.value;
      const reason = document.getElementById("exchangeReason")?.value.trim();
      const fileInput = document.getElementById("exchangeFile");
      if (!reason) { alert("교환 사유를 입력해 주세요."); return; }

      const formData = new FormData();
      formData.append("orderItemNo", selectedOrderItemNo);

      // [핵심 수정] 컨트롤러의 @RequestParam("exchangeType")과 이름을 맞춤
      formData.append("exchangeType", exchangeType);

      formData.append("reason", reason); // 여기는 동일하게 reason
      if (fileInput?.files[0]) formData.append("exchangeFile", fileInput.files[0]);

      try {
        const response = await fetch("/my/order/exchange", { method: "POST", body: formData });
        if (!response.ok) throw new Error();

        closeAllModals();
        const successModal = document.getElementById("requestSuccessModal");
        successModal.querySelector("#requestSuccessMsg").textContent = "교환 신청이 정상적으로 완료되었습니다.";
        openModal(successModal);
      } catch (e) {
        alert("교환 신청 처리 중 오류가 발생했습니다.");
      }
    });
  }

  // [성공 모달 확인/닫기 버튼 처리 - 공통]
  const successModal = document.getElementById("requestSuccessModal");
  if (successModal) {
    const reloadHandler = () => {
      // 1. 새로고침하기 전에 모든 버튼 영역을 숨겨버림 (잔상 방지)
      const allBtnAreas = document.querySelectorAll(".order-btns");
      allBtnAreas.forEach(area => {
        area.style.display = 'none';
      });

      // 2. 페이지 새로고침
      window.location.reload();
    };

    successModal.querySelector(".confirm-ok-btn").addEventListener("click", reloadHandler);
    successModal.querySelector(".modal-close").addEventListener("click", reloadHandler);
  }

  // ========================================================
  // [8. 공통 모달 폐쇄 처리 시스템]
  // ========================================================

  // 8-1. 모든 닫기 버튼(X, 취소) 클릭 시 통합 닫기
  document.querySelectorAll(".modal-close, .confirm-cancel-btn").forEach(btn => {
    btn.addEventListener("click", closeAllModals);
  });

  // 8-2. 배경(백드롭) 클릭 시 통합 닫기
  document.querySelectorAll(allModalSelectors).forEach(modal => {
    modal.addEventListener("click", (e) => {
      if (e.target === modal) closeAllModals();
    });
  });

});