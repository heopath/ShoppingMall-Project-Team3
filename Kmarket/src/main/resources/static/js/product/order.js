// 카테고리 2차 메뉴 열기/닫기
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

/*
 * 서버에서 내려준 회원 보유 포인트
 */
const availablePoint = Number(
    document.getElementById("availablePoint")?.value || 0
);

/*
 * 회원 등급별 추가 적립률
 */
const memberGrade = document.getElementById("memberGrade")?.value || "FAMILY";

function getGradePointRate(grade) {
    const gradeMap = {
        VVIP: 5,
        VIP: 4,
        GOLD: 3,
        SILVER: 2,
        FAMILY: 1,

        "5": 5,
        "4": 4,
        "3": 3,
        "2": 2,
        "1": 1
    };

    return gradeMap[String(grade).toUpperCase()] || 1;
}

const gradePointRate = getGradePointRate(memberGrade);

let usedPoint = 0;
let usedCoupon = 0;

const pointInput = document.getElementById("pointInput");
const couponSelect = document.getElementById("couponSelect");

const summaryCountEl = document.getElementById("summaryCount");
const productPriceEl = document.getElementById("productPrice");
const discountPriceEl = document.getElementById("discountPrice");
const deliveryPriceEl = document.getElementById("deliveryPrice");
const extraDiscountEl = document.getElementById("extraDiscount");
const payPriceEl = document.getElementById("payPrice");
const earnedPointEl = document.getElementById("earnedPoint");

const deliveryRequest = document.getElementById("deliveryRequest");
const deliveryRequestDirect = document.getElementById("deliveryRequestDirect");

function formatPrice(price) {
    return Number(price || 0).toLocaleString() + "원";
}

/*
 * 배송요청사항 직접입력 처리
 */
deliveryRequest.addEventListener("change", function () {
    const isDirectInput = this.value === "direct";

    deliveryRequestDirect.hidden = !isDirectInput;
    deliveryRequestDirect.disabled = !isDirectInput;

    if (!isDirectInput) {
        deliveryRequestDirect.value = "";
        return;
    }

    deliveryRequestDirect.focus();
});

/*
 * 주문 상품 row에서 실제 계산용 데이터 읽기
 */
function getOrderItems() {
    return Array.from(document.querySelectorAll(".order-product-row"))
        .map(function (row) {
            const price = Number(row.dataset.price || 0);
            const discountRate = Number(row.dataset.discountRate || 0);
            const quantity = Number(row.dataset.quantity || 0);
            const deliveryFee = Number(row.dataset.deliveryFee || 0);
            const point = Number(row.dataset.point || 0);

            const productDiscount = Math.floor(price * discountRate / 100);
            const salePrice = price - productDiscount;
            const subtotal = salePrice * quantity;

            return {
                cartNo: Number(row.dataset.cartNo),
                productNo: Number(row.dataset.productNo),
                productName: row.dataset.productName || "",
                sellerNo: row.dataset.sellerNo
                    ? Number(row.dataset.sellerNo)
                    : null,
                price: price,
                discountRate: discountRate,
                quantity: quantity,
                deliveryFee: deliveryFee,
                point: point,
                salePrice: salePrice,
                subtotal: subtotal
            };
        });
}

/*
 * 현재 주문금액 계산
 */
function calculatePaymentValues() {
    const orderItems = getOrderItems();

    const productCount = orderItems.reduce(function (sum, item) {
        return sum + item.quantity;
    }, 0);

    const productPrice = orderItems.reduce(function (sum, item) {
        return sum + item.price * item.quantity;
    }, 0);

    const productDiscount = orderItems.reduce(function (sum, item) {
        const discountAmount = Math.floor(
            item.price * item.discountRate / 100
        );

        return sum + discountAmount * item.quantity;
    }, 0);

    const deliveryFee = orderItems.reduce(function (sum, item) {
        return sum + item.deliveryFee;
    }, 0);

    const productSaleTotal = productPrice - productDiscount;

    const extraDiscount = usedCoupon + usedPoint;

    const finalPrice = Math.max(
        productSaleTotal + deliveryFee - extraDiscount,
        0
    );

    const productBasePoint = orderItems.reduce(function (sum, item) {
        return sum + item.point * item.quantity;
    }, 0);

    const pointBasePrice = Math.max(
        productSaleTotal - extraDiscount,
        0
    );

    const gradePoint = Math.floor(
        pointBasePrice * gradePointRate / 100
    );

    const totalEarnedPoint = productBasePoint + gradePoint;

    return {
        productCount,
        productPrice,
        productDiscount,
        deliveryFee,
        productSaleTotal,
        extraDiscount,
        finalPrice,
        productBasePoint,
        gradePoint,
        totalEarnedPoint
    };
}

/*
 * 오른쪽 최종결제정보 갱신
 */
function updatePaymentSummary() {
    const values = calculatePaymentValues();

    summaryCountEl.textContent = values.productCount;
    productPriceEl.textContent = formatPrice(values.productPrice);

    discountPriceEl.textContent =
        values.productDiscount > 0
            ? "-" + formatPrice(values.productDiscount)
            : "0원";

    deliveryPriceEl.textContent = formatPrice(values.deliveryFee);

    extraDiscountEl.textContent =
        values.extraDiscount > 0
            ? "-" + formatPrice(values.extraDiscount)
            : "0원";

    payPriceEl.textContent = formatPrice(values.finalPrice);

    earnedPointEl.innerHTML =
        `<strong>${values.totalEarnedPoint.toLocaleString()}점</strong>
         <small>상품 ${values.productBasePoint.toLocaleString()}점 + 등급 ${values.gradePoint.toLocaleString()}점</small>`;
}

/*
 * 쿠폰 적용 가능 상품 조회
 *
 * 현재 정책:
 * ORDER 쿠폰은 전체 상품 적용
 * PRODUCT 쿠폰은 sellerNo 기준 적용
 * FREE_SHIPPING 쿠폰은 배송비 적용
 */
function getEligibleItems(coupon, orderItems) {
    if (coupon.couponType === "ORDER") {
        return orderItems;
    }

    if (coupon.couponType === "FREE_SHIPPING") {
        if (coupon.sellerNo === null) {
            return orderItems;
        }

        return orderItems.filter(function (item) {
            return item.sellerNo === coupon.sellerNo;
        });
    }

    if (coupon.couponType === "PRODUCT") {
        if (coupon.sellerNo === null) {
            return [];
        }

        return orderItems.filter(function (item) {
            return item.sellerNo === coupon.sellerNo;
        });
    }

    return [];
}

/*
 * 쿠폰 할인 계산
 */
function calculateCouponDiscount(coupon, eligibleItems) {
    if (eligibleItems.length === 0) {
        return {
            discount: 0,
            appliedProductName: "",
            message: ""
        };
    }

    const eligibleProductTotal = eligibleItems.reduce(function (sum, item) {
        return sum + item.subtotal;
    }, 0);

    const eligibleDeliveryTotal = eligibleItems.reduce(function (sum, item) {
        return sum + item.deliveryFee;
    }, 0);

    let discount = 0;
    let appliedProductName = "";
    let message = "";

    if (
        coupon.couponType === "FREE_SHIPPING"
        || coupon.benefitType === "FREE_SHIPPING"
    ) {
        discount = eligibleDeliveryTotal;
        message = "배송비 무료 쿠폰이 적용되었습니다.";
    }

    else if (coupon.benefitType === "AMOUNT") {
        discount = Math.min(
            coupon.benefitValue,
            eligibleProductTotal
        );

        if (coupon.couponType === "PRODUCT" && eligibleItems.length === 1) {
            appliedProductName = eligibleItems[0].productName;
            message = `"${appliedProductName}" 상품에 쿠폰이 적용되었습니다.`;
        } else if (coupon.couponType === "PRODUCT") {
            message = "상품 할인 쿠폰이 적용되었습니다.";
        } else {
            message = "주문상품 할인 쿠폰이 적용되었습니다.";
        }
    }

    else if (coupon.benefitType === "RATE") {
        if (coupon.couponType === "ORDER") {
            discount = Math.floor(
                eligibleProductTotal * coupon.benefitValue / 100
            );

            message = "주문상품 할인 쿠폰이 적용되었습니다.";
        }

        else if (coupon.couponType === "PRODUCT") {
            let maxDiscount = 0;
            let targetItem = null;

            eligibleItems.forEach(function (item) {
                const itemDiscount = Math.floor(
                    item.subtotal * coupon.benefitValue / 100
                );

                if (itemDiscount > maxDiscount) {
                    maxDiscount = itemDiscount;
                    targetItem = item;
                }
            });

            discount = maxDiscount;

            if (targetItem) {
                appliedProductName = targetItem.productName;
                message = `"${appliedProductName}" 상품에 쿠폰이 적용되었습니다.`;
            }
        }
    }

    if (coupon.maxDiscountPrice > 0) {
        discount = Math.min(discount, coupon.maxDiscountPrice);
    }

    return {
        discount: discount,
        appliedProductName: appliedProductName,
        message: message
    };
}

/*
 * 포인트 사용
 */
document.getElementById("pointUseBtn").addEventListener("click", function () {
    const inputPoint = Number(pointInput.value || 0);

    if (availablePoint <= 0) {
        alert("사용 가능한 포인트가 없습니다.");
        pointInput.value = "";
        usedPoint = 0;
        updatePaymentSummary();
        return;
    }

    if (!inputPoint) {
        alert("사용할 포인트를 입력하세요.");
        return;
    }

    if (inputPoint < 5000) {
        alert("포인트는 5,000점 이상부터 사용할 수 있습니다.");
        return;
    }

    if (inputPoint > availablePoint) {
        alert("보유 포인트를 초과할 수 없습니다.");
        pointInput.value = availablePoint;
        return;
    }

    const values = calculatePaymentValues();

    const payableBeforePoint =
        values.productSaleTotal
        + values.deliveryFee
        - usedCoupon;

    if (inputPoint > payableBeforePoint) {
        alert("결제금액보다 많은 포인트를 사용할 수 없습니다.");
        pointInput.value = payableBeforePoint;
        return;
    }

    usedPoint = inputPoint;

    updatePaymentSummary();

    alert("포인트가 적용되었습니다.");
});

/*
 * 쿠폰 사용
 */
document.getElementById("couponUseBtn").addEventListener("click", function () {
    const selectedOption =
        couponSelect.options[couponSelect.selectedIndex];

    const couponIssueNo = Number(selectedOption.value || 0);

    if (couponIssueNo === 0) {
        usedCoupon = 0;
        document.getElementById("couponApplyInfo").textContent = "";
        updatePaymentSummary();
        alert("쿠폰 적용을 해제했습니다.");
        return;
    }

    const coupon = {
        couponIssueNo: couponIssueNo,
        couponType: selectedOption.dataset.couponType,
        sellerNo: selectedOption.dataset.sellerNo
            ? Number(selectedOption.dataset.sellerNo)
            : null,
        benefitType: selectedOption.dataset.benefitType,
        benefitValue: Number(selectedOption.dataset.benefitValue || 0),
        minOrderPrice: Number(selectedOption.dataset.minOrder || 0),
        maxDiscountPrice: Number(selectedOption.dataset.maxDiscount || 0)
    };

    const orderItems = getOrderItems();

    if (orderItems.length === 0) {
        alert("주문 상품이 없습니다.");
        couponSelect.value = "0";
        usedCoupon = 0;
        updatePaymentSummary();
        return;
    }

    const eligibleItems = getEligibleItems(coupon, orderItems);

    if (eligibleItems.length === 0) {
        alert("이 주문에 적용할 수 없는 쿠폰입니다.");
        couponSelect.value = "0";
        usedCoupon = 0;
        document.getElementById("couponApplyInfo").textContent = "";
        updatePaymentSummary();
        return;
    }

    const eligibleProductTotal = eligibleItems.reduce(function (sum, item) {
        return sum + item.subtotal;
    }, 0);

    if (coupon.minOrderPrice > 0 && eligibleProductTotal < coupon.minOrderPrice) {
        alert(
            "해당 쿠폰은 최소 주문금액 "
            + formatPrice(coupon.minOrderPrice)
            + " 이상부터 사용할 수 있습니다."
        );

        couponSelect.value = "0";
        usedCoupon = 0;
        document.getElementById("couponApplyInfo").textContent = "";
        updatePaymentSummary();
        return;
    }

    const couponResult = calculateCouponDiscount(
        coupon,
        eligibleItems
    );

    if (couponResult.discount <= 0) {
        alert("적용 가능한 할인금액이 없습니다.");

        couponSelect.value = "0";
        usedCoupon = 0;
        document.getElementById("couponApplyInfo").textContent = "";

        updatePaymentSummary();
        return;
    }

    usedCoupon = couponResult.discount;

    /*
     * 쿠폰을 새로 적용했을 때 포인트 사용액이 결제금액을 초과하면 초기화
     */
    const values = calculatePaymentValues();

    if (usedPoint > values.productSaleTotal + values.deliveryFee - usedCoupon) {
        usedPoint = 0;
        pointInput.value = "";
        alert("쿠폰 적용으로 결제금액이 변경되어 사용 포인트를 초기화했습니다.");
    }

    updatePaymentSummary();

    document.getElementById("couponApplyInfo").textContent =
        couponResult.message
        + "\n할인금액: "
        + formatPrice(couponResult.discount);

    alert("쿠폰이 적용되었습니다.");
});

/*
 * 결제하기
 */
document.getElementById("paymentBtn").addEventListener("click", async function () {
    const paymentBtn = this;

    const receiverName = document.getElementById("receiverName").value.trim();
    const receiverHp = document.getElementById("receiverHp").value.trim();
    const zip = document.getElementById("zip").value.trim();
    const addr1 = document.getElementById("addr1").value.trim();
    const addr2 = document.getElementById("addr2").value.trim();

    if (!receiverName || !receiverHp || !zip || !addr1 || !addr2) {
        alert("배송정보를 모두 입력하세요.");
        return;
    }

    const selectedPayment = document.querySelector('input[name="payment"]:checked');

    if (!selectedPayment) {
        alert("결제수단을 선택하세요.");
        return;
    }

    const deliveryRequestValue = deliveryRequest.value === "direct"
        ? deliveryRequestDirect.value.trim()
        : deliveryRequest.value;

    if (!deliveryRequestValue) {
        alert("배송 요청사항을 입력하세요.");
        if (deliveryRequest.value === "direct") {
            deliveryRequestDirect.focus();
        } else {
            deliveryRequest.focus();
        }
        return;
    }

    const cartNos = [...new Set(
        getOrderItems()
            .map(function (item) {
                return item.cartNo;
            })
            .filter(function (cartNo) {
                return Number.isInteger(cartNo) && cartNo > 0;
            })
    )];

    if (cartNos.length === 0) {
        alert("주문 상품이 없습니다.");
        return;
    }

    const selectedCouponIssueNo = Number(couponSelect.value || 0);

    const requestBody = {
        cartNos: cartNos,

        receiverName: receiverName,
        receiverHp: receiverHp,
        zip: zip,
        addr1: addr1,
        addr2: addr2,
        deliveryRequest: deliveryRequestValue,

        paymentMethod: selectedPayment.value,

        couponIssueNo: selectedCouponIssueNo > 0
            ? selectedCouponIssueNo
            : null,

        usedPoint: usedPoint
    };

    if (!confirm("결제하시겠습니까?")) {
        return;
    }

    paymentBtn.disabled = true;
    paymentBtn.textContent = "주문 처리 중...";

    try {
        const response = await fetch("/product/order", {
            method: "POST",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const data = await response.json().catch(function () {
            return null;
        });

        if (!response.ok || !data || !data.result) {
            alert(data?.message || "주문 처리에 실패했습니다.");

            paymentBtn.disabled = false;
            paymentBtn.textContent = "결제하기";

            return;
        }

        location.href = `/product/complete?orderNo=${data.orderNo}`;

    } catch (error) {
        console.error(error);
        alert("주문 처리 중 오류가 발생했습니다.");

        paymentBtn.disabled = false;
        paymentBtn.textContent = "결제하기";
    }
});

updatePaymentSummary();
