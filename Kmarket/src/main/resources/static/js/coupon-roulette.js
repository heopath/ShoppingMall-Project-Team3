document.addEventListener("DOMContentLoaded", function () {
    const wheel = document.getElementById("couponRoulette");
    const button = document.getElementById("rouletteSpinBtn");
    const result = document.getElementById("rouletteResult");

    if (!wheel || !button || !result) return;

    let rotation = 0;
    let prizes = [];

    async function loadPrizes() {
        try {
            const response = await fetch("/coupon/roulette/prizes", {
                headers: { "Accept": "application/json" }
            });
            const data = await response.json();
            prizes = Array.isArray(data.prizes) ? data.prizes : [];

            const labels = wheel.querySelectorAll(".roulette-label");
            labels.forEach(function (label, index) {
                const prize = prizes[index % Math.max(prizes.length, 1)];
                label.textContent = prize ? prize.couponName : "발급 가능한 쿠폰 없음";
                label.title = prize ? `${prize.couponName} (${prize.discountText})` : "";
                label.dataset.couponNo = prize ? String(prize.couponNo) : "";
            });

            if (prizes.length === 0) {
                button.disabled = true;
                result.textContent = "현재 발급 가능한 쿠폰이 없습니다.";
            }
        } catch (error) {
            button.disabled = true;
            result.textContent = "쿠폰 목록을 불러오지 못했습니다.";
        }
    }

    loadPrizes();

    button.addEventListener("click", async function () {
        if (button.disabled) return;

        button.disabled = true;
        result.textContent = "행운의 쿠폰을 추첨하고 있어요...";

        try {
            const response = await fetch("/coupon/roulette/spin", {
                method: "POST",
                headers: { "Accept": "application/json" }
            });
            const data = await response.json();

            if (response.status === 401) {
                result.textContent = data.message || "로그인이 필요합니다.";
                if (window.confirm("로그인 후 룰렛을 이용할 수 있습니다. 로그인하시겠습니까?")) {
                    window.location.href = "/member/login";
                }
                return;
            }

            if (!response.ok || !data.result) {
                result.textContent = data.message || "룰렛을 이용할 수 없습니다.";
                return;
            }

            const prizeIndex = prizes.findIndex(function (prize) {
                return Number(prize.couponNo) === Number(data.couponNo);
            });
            const segmentIndex = prizeIndex >= 0 ? prizeIndex : 0;
            const segmentCenter = segmentIndex * 60 + 30;
            const targetAngle = (360 - segmentCenter) % 360;
            rotation = Math.ceil(rotation / 360) * 360 + 1440 + targetAngle;
            wheel.style.transform = `rotate(${rotation}deg)`;
            result.textContent = "룰렛이 돌아가는 중...";

            window.setTimeout(function () {
                result.textContent = `🎉 ${data.discountText} · ${data.couponName} 발급 완료!`;
                button.textContent = "한 번 더 돌리기";
                loadPrizes();
            }, 4100);
        } catch (error) {
            result.textContent = "쿠폰 발급 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.";
        } finally {
            window.setTimeout(function () {
                button.disabled = false;
            }, 4200);
        }
    });
});
