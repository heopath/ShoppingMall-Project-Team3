document.addEventListener("DOMContentLoaded", function () {
  const monthGroup = document.querySelector(".month-period");

  // 최근 5개월 생성
  if (monthGroup) {
    monthGroup.innerHTML = "";

    const now = new Date();

    for (let i = 0; i < 5; i++) {
      const date = new Date(now.getFullYear(), now.getMonth() - i, 1);

      const month = date.getMonth() + 1;

      const btn = document.createElement("button");

      btn.type = "button";
      btn.textContent = month + "월";

      monthGroup.appendChild(btn);
    }
  }

  // 공통 active 처리
  document.addEventListener("click", function (e) {
    if (
      e.target.matches(".quick-period button") ||
      e.target.matches(".month-period button")
    ) {
      document
        .querySelectorAll(".period-btn-group button")
        .forEach(function (btn) {
          btn.classList.remove("active");
        });

      e.target.classList.add("active");
    }
  });

  // 최근 5개월
  document.addEventListener("click", function (e) {
    if (!e.target.matches(".month-period button")) return;

    const month = parseInt(e.target.textContent);

    const now = new Date();
    const year = now.getFullYear();

    const start = new Date(year, month - 1, 1);
    const end = new Date(year, month, 0);

    document.getElementById("startDate").value = start
      .toISOString()
      .split("T")[0];

    document.getElementById("endDate").value = end.toISOString().split("T")[0];
  });

  // 1주일 / 15일 / 1개월
  document.querySelectorAll(".quick-period button").forEach(function (btn) {
    btn.addEventListener("click", function () {
      const today = new Date();
      const start = new Date();

      if (btn.textContent === "1주일") {
        start.setDate(today.getDate() - 7);
      }

      if (btn.textContent === "15일") {
        start.setDate(today.getDate() - 15);
      }

      if (btn.textContent === "1개월") {
        start.setMonth(today.getMonth() - 1);
      }

      document.getElementById("startDate").value = start
        .toISOString()
        .split("T")[0];

      document.getElementById("endDate").value = today
        .toISOString()
        .split("T")[0];
    });
  });

  // 직접조회
  document
    .querySelector(".period-search-btn")
    .addEventListener("click", function () {
      const start = new Date(document.getElementById("startDate").value);

      const end = new Date(document.getElementById("endDate").value);

      if (start > end) {
        alert("시작일이 종료일보다 늦을 수 없습니다.");
        return;
      }

      const diff = (end - start) / (1000 * 60 * 60 * 24);

      if (diff > 365) {
        alert("최대 1년까지만 조회 가능합니다.");
        return;
      }

      console.log("조회 실행");
    });
});
