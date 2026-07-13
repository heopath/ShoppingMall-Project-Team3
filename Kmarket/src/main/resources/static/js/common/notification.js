document.addEventListener("DOMContentLoaded", function () {
    const wrap = document.getElementById("notificationWrap");
    const button = document.getElementById("notificationButton");
    const panel = document.getElementById("notificationPanel");
    const closeButton = document.getElementById("notificationPanelClose");
    const list = document.getElementById("notificationList");
    const badge = document.getElementById("notificationBadge");
    const summary = document.getElementById("notificationSummary");

    if (!wrap || !button || !panel || !list) {
        return;
    }

    let notifications = [];

    function escapeHtml(value) {
        return String(value ?? "")
            .replaceAll("&", "&amp;")
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll('"', "&quot;")
            .replaceAll("'", "&#039;");
    }

    function formatDate(value) {
        if (!value) {
            return "";
        }

        const date = new Date(value);
        if (Number.isNaN(date.getTime())) {
            return "";
        }

        return new Intl.DateTimeFormat("ko-KR", {
            month: "numeric",
            day: "numeric",
            hour: "2-digit",
            minute: "2-digit"
        }).format(date);
    }

    function updateCount(unreadCount) {
        const count = Number(unreadCount || 0);
        badge.textContent = count > 99 ? "99+" : String(count);
        badge.hidden = count === 0;
        summary.textContent = count > 0
            ? `읽지 않은 알림 ${count}개`
            : "새 알림이 없습니다.";
    }

    function render() {
        if (notifications.length === 0) {
            list.innerHTML = '<p class="notification-empty">도착한 알림이 없습니다.</p>';
            return;
        }

        list.innerHTML = notifications.map(function (item) {
            const unread = item.readYn !== "Y";
            return `
                <article class="notification-item ${unread ? "unread" : "read"}"
                         data-notification-no="${item.notificationNo}"
                         data-link-url="${escapeHtml(item.linkUrl || "")}">
                    <span class="notification-dot" aria-hidden="true"></span>
                    <div class="notification-main">
                        <div class="notification-title-row">
                            <strong class="notification-title">${escapeHtml(item.title)}</strong>
                            <span class="notification-state">${unread ? "읽지 않음" : "읽음"}</span>
                        </div>
                        <p class="notification-content">${escapeHtml(item.content)}</p>
                        <time class="notification-date">${escapeHtml(formatDate(item.regDate))}</time>
                    </div>
                    <button type="button"
                            class="notification-delete"
                            data-delete-no="${item.notificationNo}"
                            aria-label="알림 삭제">×</button>
                </article>
            `;
        }).join("");
    }

    async function loadNotifications() {
        try {
            const response = await fetch("/api/notifications", {
                headers: {"Accept": "application/json"},
                credentials: "same-origin"
            });

            if (!response.ok) {
                throw new Error("알림 조회 실패");
            }

            const data = await response.json();
            notifications = data.notifications || [];
            updateCount(data.unreadCount);
            render();
        } catch (error) {
            console.error(error);
            list.innerHTML = '<p class="notification-empty">알림을 불러오지 못했습니다.</p>';
        }
    }

    function closePanel() {
        panel.hidden = true;
        button.setAttribute("aria-expanded", "false");
    }

    button.addEventListener("click", async function () {
        const willOpen = panel.hidden;
        panel.hidden = !willOpen;
        button.setAttribute("aria-expanded", String(willOpen));

        if (willOpen) {
            await loadNotifications();
        }
    });

    closeButton.addEventListener("click", closePanel);

    document.addEventListener("click", function (event) {
        if (!panel.hidden && !wrap.contains(event.target)) {
            closePanel();
        }
    });

    list.addEventListener("click", async function (event) {
        const deleteButton = event.target.closest(".notification-delete");

        if (deleteButton) {
            event.stopPropagation();
            const notificationNo = deleteButton.dataset.deleteNo;
            const response = await fetch(`/api/notifications/${notificationNo}`, {
                method: "DELETE",
                credentials: "same-origin"
            });

            if (response.ok) {
                await loadNotifications();
            } else {
                alert("알림을 삭제하지 못했습니다.");
            }
            return;
        }

        const item = event.target.closest(".notification-item");
        if (!item) {
            return;
        }

        const notificationNo = item.dataset.notificationNo;
        const linkUrl = item.dataset.linkUrl;

        if (item.classList.contains("unread")) {
            await fetch(`/api/notifications/${notificationNo}/read`, {
                method: "POST",
                credentials: "same-origin"
            });
        }

        if (linkUrl) {
            location.href = linkUrl;
        } else {
            await loadNotifications();
        }
    });

    loadNotifications();
});
