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

// 쿠폰 모달 요소
const couponModalOpenBtn = document.getElementById("couponModalOpenBtn");
const couponModal = document.getElementById("couponModal");
const couponModalClose = document.getElementById("couponModalClose");
const couponModalList = document.getElementById("couponModalList");

// 품절 관련 요소
const productStock = Number(
    document.getElementById("productStock")?.value || 0
);

const isSoldOut = productStock <= 0;

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
    const current = Number(qtyInput.value);

    if (isSoldOut) {
        alert("품절된 상품입니다.");
        return;
    }

    if (current >= productStock) {
        alert(`최대 ${productStock}개까지 구매할 수 있습니다.`);
        return;
    }

    qtyInput.value = current + 1;
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
    if (isSoldOut) {
        alert("품절된 상품입니다.");
        return;
    }

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
        cartBtn.disabled = isSoldOut;
    }
});

// 구매하기 버튼
buyBtn.addEventListener("click", async function (e) {
    e.preventDefault();

    if (isSoldOut) {
        alert("품절된 상품입니다.");
        return;
    }

    if (!isAllOptionsSelected()) {
        alert("상품 옵션을 모두 선택해주세요.");
        return;
    }

    const productNo = Number(
        document.getElementById("productNo").value
    );

    const optionNos = Array.from(optionSelects)
        .map(select => Number(select.value))
        .filter(value => value > 0);

    const quantity = Number(qtyInput.value || 1);

    if (!productNo) {
        alert("상품 정보가 올바르지 않습니다.");
        return;
    }

    if (quantity <= 0) {
        alert("수량을 확인하세요.");
        return;
    }

    buyBtn.disabled = true;

    try {
        const response = await fetch("/product/order/direct", {
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

        // 비로그인 상태에서 로그인 페이지로 리다이렉트된 경우
        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const contentType = response.headers.get("content-type") || "";

        const data = contentType.includes("application/json")
            ? await response.json()
            : null;

        if (!response.ok || !data || data.status !== "success") {
            alert(data?.message || "바로구매 처리 중 오류가 발생했습니다.");
            return;
        }

        location.href = data.redirectUrl;

    } catch (error) {
        console.error(error);
        alert("서버 통신 중 오류가 발생했습니다.");

    } finally {
        buyBtn.disabled = isSoldOut;
    }
});

// 쿠폰 모달 열기
function openCouponModal() {
    couponModal.classList.add("is-open");
    couponModal.setAttribute("aria-hidden", "false");
    document.body.style.overflow = "hidden";
}

// 쿠폰 모달 닫기
function closeCouponModal() {
    couponModal.classList.remove("is-open");
    couponModal.setAttribute("aria-hidden", "true");
    document.body.style.overflow = "";
}

// 쿠폰 모달 버튼 클릭
if (couponModalOpenBtn && couponModal && couponModalList) {
    couponModalOpenBtn.addEventListener("click", async function () {
        const sellerNo = Number(couponModalOpenBtn.dataset.sellerNo);

        if (!sellerNo) {
            alert("판매자 정보가 없습니다.");
            return;
        }

        openCouponModal();
        await loadCouponModal(sellerNo);
    });
}

// 쿠폰 모달 닫기 버튼
if (couponModalClose) {
    couponModalClose.addEventListener("click", closeCouponModal);
}

// 쿠폰 모달 검은 배경 클릭 시 닫기
if (couponModal) {
    couponModal.addEventListener("click", function (e) {
        if (e.target.classList.contains("coupon-modal-dim")) {
            closeCouponModal();
        }
    });
}

// 쿠폰 목록 불러오기
async function loadCouponModal(sellerNo) {
    couponModalList.innerHTML = `
        <div class="coupon-modal-empty">쿠폰을 불러오는 중입니다.</div>
    `;

    try {
        const response = await fetch(`/product/coupon/modal?sellerNo=${encodeURIComponent(sellerNo)}`, {
            method: "GET",
            credentials: "same-origin",
            headers: {
                "Accept": "application/json"
            }
        });

        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const contentType = response.headers.get("content-type") || "";

        const data = contentType.includes("application/json")
            ? await response.json()
            : null;

        if (!response.ok || !data || !data.result) {
            couponModalList.innerHTML = `
                <div class="coupon-modal-error">
                    ${escapeHtml(data?.message || "쿠폰 목록을 불러오지 못했습니다.")}
                </div>
            `;
            return;
        }

        renderCouponList(data.coupons || []);

    } catch (error) {
        console.error(error);

        couponModalList.innerHTML = `
            <div class="coupon-modal-error">
                쿠폰 목록을 불러오는 중 오류가 발생했습니다.
            </div>
        `;
    }
}

// 쿠폰 목록 렌더링
function renderCouponList(coupons) {
    if (coupons.length === 0) {
        couponModalList.innerHTML = `
            <div class="coupon-modal-empty">현재 받을 수 있는 쿠폰이 없습니다.</div>
        `;
        return;
    }

    const firstCoupons = coupons.filter(coupon => coupon.firstCoupon);
    const sellerCoupons = coupons.filter(coupon => !coupon.firstCoupon);

    let html = "";

    if (firstCoupons.length > 0) {
        html += `<div class="coupon-section-title">첫 이용 쿠폰</div>`;

        firstCoupons.forEach(function (coupon) {
            html += createCouponItemHtml(coupon);
        });
    }

    if (sellerCoupons.length > 0) {
        html += `<div class="coupon-section-title">판매자 등록 쿠폰</div>`;

        sellerCoupons.forEach(function (coupon) {
            html += createCouponItemHtml(coupon);
        });
    }

    couponModalList.innerHTML = html;
}

// 쿠폰 1개 HTML 생성
function createCouponItemHtml(coupon) {
    const issued = coupon.issued === true;

    return `
        <div class="coupon-modal-item ${coupon.firstCoupon ? "is-first" : ""}">
            <div class="coupon-modal-info">
                <div class="coupon-modal-discount">
                    <strong>${escapeHtml(coupon.discountText || "할인")}</strong>
                    <span>쿠폰</span>
                </div>

                <em class="coupon-modal-name">
                    ${escapeHtml(coupon.couponName || "판매자 쿠폰")}
                </em>

                <p class="coupon-modal-condition">
                    ${escapeHtml(coupon.conditionText || "조건 없이 사용 가능")}
                </p>
            </div>

            <button type="button"
                    class="coupon-modal-issue-btn"
                    data-coupon-no="${coupon.couponNo}"
                    ${issued ? "disabled" : ""}>
                ${issued ? "발급완료" : "쿠폰받기"}
            </button>
        </div>
    `;
}

// 쿠폰받기 버튼 클릭
if (couponModalList) {
    couponModalList.addEventListener("click", async function (e) {
        const issueBtn = e.target.closest(".coupon-modal-issue-btn");

        if (!issueBtn) {
            return;
        }

        const couponNo = Number(issueBtn.dataset.couponNo);

        if (!couponNo) {
            alert("쿠폰 정보를 확인할 수 없습니다.");
            return;
        }

        await issueCoupon(couponNo, issueBtn);
    });
}

// 쿠폰 발급
async function issueCoupon(couponNo, issueBtn) {
    issueBtn.disabled = true;
    issueBtn.textContent = "발급중";

    try {
        const response = await fetch("/product/coupon/issue", {
            method: "POST",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({
                couponNo: couponNo
            })
        });

        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const contentType = response.headers.get("content-type") || "";

        const data = contentType.includes("application/json")
            ? await response.json()
            : null;

        if (response.status === 401) {
            alert(data?.message || "로그인이 필요합니다.");
            issueBtn.disabled = false;
            issueBtn.textContent = "쿠폰받기";
            return;
        }

        if (!response.ok || !data || !data.result) {
            alert(data?.message || "쿠폰 발급에 실패했습니다.");
            issueBtn.disabled = false;
            issueBtn.textContent = "쿠폰받기";
            return;
        }

        alert(data.message || "쿠폰이 발급되었습니다.");

        issueBtn.disabled = true;
        issueBtn.textContent = "발급완료";

    } catch (error) {
        console.error(error);
        alert("쿠폰 발급 중 오류가 발생했습니다.");

        issueBtn.disabled = false;
        issueBtn.textContent = "쿠폰받기";
    }
}

// HTML escape
function escapeHtml(value) {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

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