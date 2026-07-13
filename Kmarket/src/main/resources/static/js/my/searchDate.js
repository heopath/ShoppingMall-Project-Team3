document.addEventListener("DOMContentLoaded", function () {
  // 현재 경로 가져오기
  const currentPath = window.location.pathname;

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

  // 조회 후 페이지 새로고침 되어도 active 적용
  const params = new URLSearchParams(location.search);

  const period = params.get("period");
  const startDate = params.get("startDate");
  const endDate = params.get("endDate");

  if (startDate) {
    document.getElementById("startDate").value = startDate;
  }

  if (endDate) {
    document.getElementById("endDate").value = endDate;
  }

  document
      .querySelectorAll(".period-btn-group button")
      .forEach(btn => btn.classList.remove("active"));

  switch (period) {

    case "week":
      document.querySelector(".quick-period button:nth-child(1)")?.classList.add("active");
      break;

    case "15day":
      document.querySelector(".quick-period button:nth-child(2)")?.classList.add("active");
      break;

    case "1month":
      document.querySelector(".quick-period button:nth-child(3)")?.classList.add("active");
      break;

    case "custom":
      break;

    default:

      if (period && period.startsWith("month-")) {

        const month = period.replace("month-", "") + "월";

        document.querySelectorAll(".month-period button").forEach(btn => {

          if (btn.textContent === month) {
            btn.classList.add("active");
          }

        });

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
    const year = new Date().getFullYear();

    const start = new Date(year, month - 1, 1);
    const end = new Date(year, month, 0);

    const startDate = start.toISOString().split("T")[0];
    const endDate = end.toISOString().split("T")[0];

    location.href =
        `${currentPath}?pg=1&period=month-${month}&startDate=${startDate}&endDate=${endDate}`;
  });

  // 1주일 / 15일 / 1개월
  document.querySelectorAll(".quick-period button").forEach(function (btn) {

    btn.addEventListener("click", function () {

      const today = new Date();
      const start = new Date();
      let period = "";

      if (btn.textContent === "1주일") {
        start.setDate(today.getDate() - 7);
        period = "week";
      } else if (btn.textContent === "15일") {
        start.setDate(today.getDate() - 15);
        period = "15day";
      } else {
        start.setMonth(today.getMonth() - 1);
        period = "1month";
      }

      const startDate = start.toISOString().split("T")[0];
      const endDate = today.toISOString().split("T")[0];

      location.href =
          `${currentPath}?pg=1&period=${period}&startDate=${startDate}&endDate=${endDate}`;
    });

  });

  // 직접조회
  document
      .querySelector(".period-search-btn")
      .addEventListener("click", function () {

        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;

        if (!startDate || !endDate) {
          alert("기간을 선택하세요.");
          return;
        }

        const start = new Date(startDate);
        const end = new Date(endDate);

        if (start > end) {
          alert("시작일이 종료일보다 늦을 수 없습니다.");
          return;
        }

        const diff = (end - start) / (1000 * 60 * 60 * 24);

        if (diff > 365) {
          alert("최대 1년까지만 조회 가능합니다.");
          return;
        }

        location.href =
            `${currentPath}?pg=1&period=custom&startDate=${startDate}&endDate=${endDate}`;
      });
});