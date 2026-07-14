(function () {
    const loading = document.getElementById("pageLoading");

    if (!loading) {
        return;
    }

    function hideLoading() {
        loading.classList.add("is-hidden");

        window.setTimeout(function () {
            loading.remove();
        }, 400);
    }

    if (document.readyState === "complete") {
        window.setTimeout(hideLoading, 180);
    } else {
        window.addEventListener("load", function () {
            window.setTimeout(hideLoading, 180);
        }, {once: true});
    }

    // 외부 이미지나 리소스가 응답하지 않더라도 화면을 계속 가리지 않는다.
    window.setTimeout(hideLoading, 4500);
})();
