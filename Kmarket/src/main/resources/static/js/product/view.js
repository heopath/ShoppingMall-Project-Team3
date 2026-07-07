// 카테고리 눌렀을 때 2차 카테고리 열기/닫기
const items = document.querySelectorAll(".category-item");

items.forEach(function (item) {
    const title = item.querySelector(".category-title");
    const sub = item.querySelector(".sub-category");

    if (!title || !sub) {
        return;
    }

    title.addEventListener("click", function () {
        item.classList.toggle("active");
    });
});

// 수량 / 가격 관련 요소
const qtyInput = document.getElementById("qtyInput");
const plusBtn = document.getElementById("plusBtn");
const minusBtn = document.getElementById("minusBtn");

const totalPrice = document.getElementById("totalPrice");
const selectedPrice = document.getElementById("selectedPrice");

const basePrice = document.getElementById("basePrice");
const selectedProductName = document.getElementById("selectedProductName");

const optionSelects = document.querySelectorAll(".option-select");

// 장바구니 / 구매 버튼
const cartBtn = document.querySelector(".btn-cart");
const buyBtn = document.querySelector(".btn-buy");

// 장바구니 모달 요소
const cartModal = document.getElementById("cartModal");
const cartModalClose = document.getElementById("cartModalClose");
const continueShoppingBtn = document.getElementById("continueShoppingBtn");
const goCartBtn = document.getElementById("goCartBtn");

// 가격 표시 형식
function formatPrice(num) {
    return Number(num).toLocaleString() + "원";
}

// 상품 1개 할인 적용 가격
function getUnitPrice() {
    return Number(basePrice.value);
}

// 선택한 옵션 이름 목록
function getSelectedOptionTexts() {
    const selectedOptions = [];

    optionSelects.forEach(function (select) {
        const selectedOption = select.options[select.selectedIndex];

        if (selectedOption.value !== "") {
            selectedOptions.push(selectedOption.text);
        }
    });

    return selectedOptions;
}

// 선택 상품명 갱신
function updateSelectedProductName() {
    const productName = selectedProductName.dataset.productName;
    const selectedOptions = getSelectedOptionTexts();

    if (selectedOptions.length === 0) {
        selectedProductName.textContent = productName;
        return;
    }

    selectedProductName.textContent =
        productName + " / " + selectedOptions.join(" / ");
}

// 수량에 따라 가격 갱신
function updatePrice() {
    const qty = Number(qtyInput.value);
    const unitPrice = getUnitPrice();
    const total = qty * unitPrice;

    selectedPrice.textContent = formatPrice(total);
    totalPrice.textContent = formatPrice(total);

    updateSelectedProductName();
}

// 수량 증가
plusBtn.addEventListener("click", function () {
    qtyInput.value = Number(qtyInput.value) + 1;
    updatePrice();
});

// 수량 감소
minusBtn.addEventListener("click", function () {
    const current = Number(qtyInput.value);

    if (current > 1) {
        qtyInput.value = current - 1;
        updatePrice();
    }
});

// 옵션 선택 시 상품명 / 가격 갱신
optionSelects.forEach(function (select) {
    select.addEventListener("change", updatePrice);
});

// 모든 옵션 선택 여부
function isAllOptionsSelected() {
    for (const select of optionSelects) {
        if (select.value === "") {
            return false;
        }
    }

    return true;
}

// 상세 탭 활성화 표시
const detailTabs = document.querySelectorAll(".detail-tabs a");

detailTabs.forEach(function (tab) {
    tab.addEventListener("click", function () {
        detailTabs.forEach(function (element) {
            element.classList.remove("active");
        });

        tab.classList.add("active");
    });
});

// 모달 열기
function openCartModal() {
    cartModal.classList.add("show");
    cartModal.setAttribute("aria-hidden", "false");
}

// 모달 닫기
function closeCartModal() {
    cartModal.classList.remove("show");
    cartModal.setAttribute("aria-hidden", "true");
}

// 장바구니 버튼
cartBtn.addEventListener("click", async function () {
    if (!isAllOptionsSelected()) {
        alert("상품 옵션을 모두 선택해주세요.");
        return;
    }

    const productNo = Number(
        document.getElementById("productNo").value
    );

    const optionNos = Array.from(optionSelects)
        .map(select => Number(select.value));

    const quantity = Number(qtyInput.value);

    cartBtn.disabled = true;

    try {
        const response = await fetch("/product/cart", {
            method: "POST",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                productNo: productNo,
                optionNos: optionNos,
                quantity: quantity
            })
        });

        // 비로그인 상태에서 Security 로그인 화면으로 이동한 경우
        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const contentType =
            response.headers.get("content-type") || "";

        const data = contentType.includes("application/json")
            ? await response.json()
            : null;

        if (!response.ok || !data || !data.result) {
            alert(data?.message || "장바구니 담기에 실패했습니다.");
            return;
        }

        // DB 저장 성공한 경우만 모달 출력
        openCartModal();

    } catch (error) {
        console.error(error);
        alert("서버 통신 중 오류가 발생했습니다.");

    } finally {
        cartBtn.disabled = false;
    }
});

// 구매하기 버튼
buyBtn.addEventListener("click", function () {
    if (!isAllOptionsSelected()) {
        alert("상품 옵션을 모두 선택해주세요.");
        return;
    }

    console.log("구매 가능");
});

// 모달 닫기 버튼
cartModalClose.addEventListener("click", closeCartModal);

// 계속 쇼핑하기
continueShoppingBtn.addEventListener("click", closeCartModal);

// 장바구니 페이지 이동
goCartBtn.addEventListener("click", function () {


    location.href = "/product/cart";
});

// 검은 배경 클릭 시 닫기
cartModal.querySelector(".cart-modal-dim")
    .addEventListener("click", closeCartModal);

// 최초 가격 출력
updatePrice();