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

const imageSvg = `
    <svg viewBox="0 0 24 24" fill="#ccc">
        <path d="M21 19V5a2 2 0 00-2-2H5a2 2 0 00-2 2v14a2 2 0 002 2h14a2 2 0 002-2zM8.5 13.5l2.5 3.01L14.5 12l4.5 6H5l3.5-4.5z"/>
    </svg>
`;

let cartItems = [];

const cartList = document.getElementById("cartList");
const checkAll = document.getElementById("checkAll");
const deleteSelectedBtn = document.getElementById("deleteSelected");
const orderBtn = document.getElementById("orderBtn");

function formatPrice(price) {
    return Number(price || 0).toLocaleString() + "원";
}

function getDiscountPrice(item) {
    return Math.floor(
        Number(item.price || 0) * (Number(item.discountRate || 0) / 100)
    );
}

function getSalePrice(item) {
    return Number(item.price || 0) - getDiscountPrice(item);
}

function escapeHtml(value) {
    return String(value ?? "")
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

/*
 * 로그인한 회원의 실제 장바구니 데이터 조회
 * GET /product/cart/items
 */
async function loadCartItems() {
    try {
        const response = await fetch("/product/cart/items", {
            method: "GET",
            credentials: "same-origin"
        });

        if (response.redirected) {
            location.href = response.url;
            return;
        }

        if (!response.ok) {
            throw new Error("장바구니 목록을 불러오지 못했습니다.");
        }

        const data = await response.json();

        // 처음 장바구니 진입 시 전체 선택
        cartItems = data.map(function (item) {
            return {
                ...item,
                checked: true
            };
        });

        renderCart();

    } catch (error) {
        console.error(error);

        cartList.innerHTML = `
            <div class="empty-cart">
                장바구니 정보를 불러오지 못했습니다.
            </div>
        `;

        updateSummary();
    }
}

/*
 * 수량 DB 변경
 * PATCH /product/cart/{cartNo}
 */
async function changeCartQuantity(cartNo, quantity) {
    try {
        const response = await fetch(`/product/cart/${cartNo}`, {
            method: "PATCH",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                quantity: quantity
            })
        });

        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const data = await response.json().catch(() => null);

        if (!response.ok || !data || !data.result) {
            alert(data?.message || "수량 변경에 실패했습니다.");
            return;
        }

        cartItems = cartItems.map(function (item) {
            if (item.cartNo === cartNo) {
                return {
                    ...item,
                    quantity: data.quantity
                };
            }

            return item;
        });

        renderCart();

    } catch (error) {
        console.error(error);
        alert("수량 변경 중 오류가 발생했습니다.");
    }
}

function renderCart() {
    cartList.innerHTML = "";

    if (cartItems.length === 0) {
        cartList.innerHTML = `
            <div class="empty-cart">
                장바구니에 담긴 상품이 없습니다.
            </div>
        `;

        checkAll.checked = false;
        checkAll.indeterminate = false;
        checkAll.disabled = true;

        updateSummary();
        return;
    }

    checkAll.disabled = false;

    const allChecked = cartItems.every(function (item) {
        return item.checked;
    });

    const anyChecked = cartItems.some(function (item) {
        return item.checked;
    });

    checkAll.checked = allChecked;
    checkAll.indeterminate = !allChecked && anyChecked;

    cartItems.forEach(function (item) {
        const salePrice = getSalePrice(item);
        const itemTotal =
            salePrice * Number(item.quantity) + Number(item.deliveryFee || 0);

        const imageHtml = item.imagePath
            ? `<img src="${escapeHtml(item.imagePath)}"
                    alt="${escapeHtml(item.productName)}">`
            : imageSvg;

        const descriptionText = item.basicDesc
            ? `<em class="cart-description">
                    ${escapeHtml(item.basicDesc)}
               </em>`
            : "";

        const optionText = item.optionText
            ? `<em class="cart-option">
                    [옵션] : ${escapeHtml(item.optionText)}
               </em>`
            : "";

        const row = document.createElement("article");
        row.className = "cart-item";

        row.innerHTML = `
            <label class="check-wrap">
                <input
                    type="checkbox"
                    class="item-check"
                    data-cart-no="${item.cartNo}"
                    ${item.checked ? "checked" : ""}
                />
                <span></span>
            </label>

            <a href="/product/view?productNo=${item.productNo}"
               class="cart-product">

                <span class="cart-thumb">
                    ${imageHtml}
                </span>

                <span class="cart-product-text">
                    <b>${escapeHtml(item.productName)}</b>
                    ${descriptionText}
                    ${optionText}
                </span>
            </a>

            <div class="quantity-box">
                <button type="button"
                        class="qty-minus"
                        data-cart-no="${item.cartNo}">
                    -
                </button>

                <input type="text"
                       value="${item.quantity}"
                       readonly />

                <button type="button"
                        class="qty-plus"
                        data-cart-no="${item.cartNo}">
                    +
                </button>
            </div>

            <span class="cart-price">
                ${formatPrice(item.price)}
            </span>

            <span class="cart-discount">
                ${item.discountRate || 0}%
            </span>

            <span class="cart-point">
                ${Number(item.point || 0).toLocaleString()}
            </span>

            <span class="cart-delivery">
                ${Number(item.deliveryFee) === 0
            ? "무료배송"
            : formatPrice(item.deliveryFee)}
            </span>

            <strong class="cart-total">
                ${formatPrice(itemTotal)}
            </strong>
        `;

        cartList.appendChild(row);
    });

    bindCartEvents();
    updateSummary();
}

function bindCartEvents() {
    const itemChecks = document.querySelectorAll(".item-check");

    // 개별 체크박스
    itemChecks.forEach(function (checkbox) {
        checkbox.addEventListener("change", function () {
            const cartNo = Number(this.dataset.cartNo);
            const isChecked = this.checked; // 체크 상태를 먼저 저장

            cartItems = cartItems.map(function (item) {
                if (item.cartNo === cartNo) {
                    return {
                        ...item,
                        checked: isChecked
                    };
                }

                return item;
            });

            const allChecked = cartItems.length > 0
                && cartItems.every(function (item) {
                    return item.checked;
                });

            const anyChecked = cartItems.some(function (item) {
                return item.checked;
            });

            checkAll.checked = allChecked;
            checkAll.indeterminate = !allChecked && anyChecked;

            updateSummary();
        });
    });

    // 수량 증가
    document.querySelectorAll(".qty-plus").forEach(function (button) {
        button.addEventListener("click", function () {
            const cartNo = Number(this.dataset.cartNo);

            const cartItem = cartItems.find(function (item) {
                return item.cartNo === cartNo;
            });

            if (!cartItem) {
                return;
            }

            changeCartQuantity(
                cartNo,
                Number(cartItem.quantity) + 1
            );
        });
    });

    // 수량 감소
    document.querySelectorAll(".qty-minus").forEach(function (button) {
        button.addEventListener("click", function () {
            const cartNo = Number(this.dataset.cartNo);

            const cartItem = cartItems.find(function (item) {
                return item.cartNo === cartNo;
            });

            if (!cartItem) {
                return;
            }

            if (Number(cartItem.quantity) <= 1) {
                return;
            }

            changeCartQuantity(
                cartNo,
                Number(cartItem.quantity) - 1
            );
        });
    });
}

function updateSummary() {
    const selectedItems = cartItems.filter(function (item) {
        return item.checked;
    });

    const productCount = selectedItems.reduce(function (sum, item) {
        return sum + Number(item.quantity);
    }, 0);

    const productPrice = selectedItems.reduce(function (sum, item) {
        return sum + Number(item.price) * Number(item.quantity);
    }, 0);

    const discountPrice = selectedItems.reduce(function (sum, item) {
        return sum + getDiscountPrice(item) * Number(item.quantity);
    }, 0);

    const deliveryFee = selectedItems.reduce(function (sum, item) {
        return sum + Number(item.deliveryFee || 0);
    }, 0);

    const point = selectedItems.reduce(function (sum, item) {
        return sum + Number(item.point || 0) * Number(item.quantity);
    }, 0);

    const total = productPrice - discountPrice + deliveryFee;

    document.getElementById("summaryCount").textContent = productCount;
    document.getElementById("summaryProductPrice").textContent = formatPrice(productPrice);
    document.getElementById("summaryDiscount").textContent = "-" + formatPrice(discountPrice);
    document.getElementById("summaryDelivery").textContent = formatPrice(deliveryFee);
    document.getElementById("summaryTotal").textContent = formatPrice(total);
    document.getElementById("summaryPoint").textContent = point.toLocaleString();
}

// 전체 선택
checkAll.addEventListener("change", function () {
    const isChecked = this.checked;

    cartItems = cartItems.map(function (item) {
        return {
            ...item,
            checked: isChecked
        };
    });

    document.querySelectorAll(".item-check").forEach(function (checkbox) {
        checkbox.checked = isChecked;
    });

    checkAll.indeterminate = false;

    updateSummary();
});

// 선택 삭제
deleteSelectedBtn.addEventListener("click", async function () {
    const selectedItems = cartItems.filter(function (item) {
        return item.checked;
    });

    if (selectedItems.length === 0) {
        alert("삭제할 상품을 선택하세요.");
        return;
    }

    if (!confirm("선택한 상품을 장바구니에서 삭제하시겠습니까?")) {
        return;
    }

    const cartNos = selectedItems.map(function (item) {
        return item.cartNo;
    });

    try {
        const response = await fetch("/product/cart", {
            method: "DELETE",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                cartNos: cartNos
            })
        });

        if (response.redirected) {
            location.href = response.url;
            return;
        }

        const data = await response.json().catch(() => null);

        if (!response.ok || !data || !data.result) {
            alert(data?.message || "장바구니 삭제에 실패했습니다.");
            return;
        }

        cartItems = cartItems.filter(function (item) {
            return !cartNos.includes(item.cartNo);
        });

        renderCart();

    } catch (error) {
        console.error(error);
        alert("장바구니 삭제 중 오류가 발생했습니다.");
    }
});

// 주문하기
orderBtn.addEventListener("click", function () {
    const selectedCartNos = cartItems
        .filter(function (item) {
            return item.checked;
        })
        .map(function (item) {
            return item.cartNo;
        });

    if (selectedCartNos.length === 0) {
        alert("주문할 상품을 선택하세요.");
        return;
    }

    const params = new URLSearchParams();

    selectedCartNos.forEach(function (cartNo) {
        params.append("cartNo", cartNo);
    });

    location.href = `/product/order?${params.toString()}`;
});

loadCartItems();