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

  // 문의하기 버튼 클릭
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

      list.forEach((cate,index)=>{

        area.insertAdjacentHTML("beforeend",`

                <label>
                    <input
                        type="radio"
                        name="cateName"
                        value="${cate.csCateNo}"
                        ${index===0 ? "checked" : ""}
                    >
                    <span>${cate.cateName}</span>
                </label>

            `);

      });

      sellerModal.style.display="none";
      inquiryModal.style.display="flex";

    });

  });

  // 문의 등록
  document.querySelector(".btn-submit").addEventListener("click", async ()=>{

    const checked=document.querySelector("input[name='cateName']:checked");

    if(!checked){
      alert("문의 종류를 선택하세요.");
      return;
    }

    const data={

      csCateNo:Number(checked.value),
      title:document.querySelector("#qnaTitle").value.trim(),
      content:document.querySelector("#qnaContent").value.trim()

    };

    if(!data.title){
      alert("제목을 입력하세요.");
      return;
    }

    if(!data.content){
      alert("내용을 입력하세요.");
      return;
    }

    const response=await fetch("/my/qna",{

      method:"POST",
      headers:{
        "Content-Type":"application/json"
      },
      body:JSON.stringify(data)

    });

    if(response.ok){

      alert("문의가 등록되었습니다.");

      inquiryModal.style.display="none";

      document.querySelector("#qnaTitle").value="";
      document.querySelector("#qnaContent").value="";

    }else{

      alert("문의 등록에 실패했습니다.");

    }

  });

  // 문의 취소
  document.querySelectorAll(".inquiry-cancel").forEach(function (btn) {
    btn.addEventListener("click", function () {
      inquiryModal.style.display = "none";
      sellerModal.style.display = "flex";
    });
  });



// 구매확정 모달 열기
  let selectedOrderItemNo = null;
  document.querySelectorAll(".confirm-link").forEach(function (btn) {

    btn.addEventListener("click", function (e) {

      e.preventDefault();

      selectedOrderItemNo = this.dataset.orderItemNo;

      confirmModal.style.display = "flex";
    });

  });

// 구매확정 취소
  document.querySelectorAll(".confirm-cancel-btn").forEach(function (btn) {

    btn.addEventListener("click", function () {

      confirmModal.style.display = "none";
      selectedOrderItemNo = null;

    });

  });

// 구매확정
  document.querySelector(".confirm-ok-btn").addEventListener("click", async function () {

    try {

      const response = await fetch(`/my/order/confirm/${selectedOrderItemNo}`, {
        method: "POST"
      });

      if (!response.ok) {
        throw new Error();
      }

      alert("구매확정이 완료되었습니다.");

      confirmModal.style.display = "none";

      location.reload();

    } catch (e) {

      alert("구매확정에 실패했습니다.");

    }

  });

// 상품평 모달 열기
  document.querySelectorAll(".review-link").forEach(function (btn) {

    btn.addEventListener("click", async function (e) {

      e.preventDefault();

      const orderItemNo = this.dataset.orderItemNo;

      try {

        const response = await fetch(`/my/review/${orderItemNo}`);

        const data = await response.json();

        document.querySelector("#reviewOrderItemNo").value = data.orderItemNo;
        document.querySelector("#reviewProductNo").value = data.productNo;
        document.querySelector("#reviewProductName").textContent = data.productName;

        reviewModal.style.display = "flex";

      } catch (e) {

        alert("상품 정보를 불러오지 못했습니다.");

      }

    });

  });


// 상품평 등록
  document.querySelector(".review-submit-btn").addEventListener("click", async function () {

    const rating = document.querySelector("input[name='rating']:checked");

    if (!rating) {
      alert("별점을 선택하세요.");
      return;
    }

    const content = document.querySelector("#reviewContent").value.trim();

    if (content.length < 10) {
      alert("내용을 10자 이상 입력하세요.");
      return;
    }

    const formData = new FormData();

    formData.append("orderItemNo", document.querySelector("#reviewOrderItemNo").value);
    formData.append("productNo", document.querySelector("#reviewProductNo").value);
    formData.append("rating", rating.value);
    formData.append("content", content);

    document.querySelectorAll("input[name='images']").forEach(file => {

      if (file.files.length > 0) {
        formData.append("images", file.files[0]);
      }

    });

    try {

      const response = await fetch("/my/review", {
        method: "POST",
        body: formData
      });

      if (!response.ok) {
        throw new Error();
      }

      alert("상품평이 등록되었습니다.");

      reviewModal.style.display = "none";

      document.querySelector("#reviewForm").reset();
      document.querySelector("#reviewProductName").textContent = "";

      // 작성한 상품은 버튼 변경
      const reviewLink = document.querySelector(
          `.review-link[data-order-item-no="${document.querySelector("#reviewOrderItemNo").value}"]`
      );

      if (reviewLink) {
        reviewLink.outerHTML = "<span>작성완료</span>";
      }

    } catch (e) {

      alert("상품평 등록에 실패했습니다.");

    }

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