document.addEventListener("DOMContentLoaded", function () {
    const header = document.getElementById("campaignHeader");
    const pageProgress = document.getElementById("pageProgress");
    const journey = document.querySelector(".brand-journey");
    const brandTrack = document.getElementById("brandTrack");
    const brandProgress = document.getElementById("brandProgress");
    const brandCurrent = document.getElementById("brandCurrent");
    const brandSlides = Array.from(document.querySelectorAll(".brand-slide"));
    const reducedMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;

    function clamp(value, min, max) {
        return Math.min(Math.max(value, min), max);
    }

    function updateBrandJourney() {
        if (!journey || !brandTrack) return;

        const rect = journey.getBoundingClientRect();
        const distance = journey.offsetHeight - window.innerHeight;
        const progress = clamp(-rect.top / Math.max(distance, 1), 0, 1);
        const activeIndex = Math.min(Math.floor(progress * brandSlides.length), brandSlides.length - 1);

        brandTrack.style.transform = `translate3d(${-progress * 80}%, 0, 0)`;
        brandProgress.style.width = (progress * 100) + "%";
        brandCurrent.textContent = String(activeIndex + 1).padStart(2, "0");

        brandSlides.forEach(function (slide, index) {
            slide.classList.toggle("is-active", index === activeIndex);
        });
    }

    function updateScroll() {
        const top = window.scrollY;
        const scrollable = document.documentElement.scrollHeight - window.innerHeight;
        header.classList.toggle("scrolled", top > 30);
        pageProgress.style.width = (scrollable > 0 ? top / scrollable * 100 : 0) + "%";
        updateBrandJourney();
    }

    const revealObserver = new IntersectionObserver(function (entries) {
        entries.forEach(function (entry) {
            if (entry.isIntersecting) {
                entry.target.classList.add("is-visible");
                revealObserver.unobserve(entry.target);
            }
        });
    }, { threshold: 0.14 });

    document.querySelectorAll(".reveal").forEach(function (element) {
        revealObserver.observe(element);
    });

    const gachaButton = document.getElementById("gachaButton");
    const gachaVisual = document.getElementById("gachaVisual");
    const gachaResult = document.getElementById("gachaResult");
    const messages = [
        "오늘의 행운: 시원한 코카-콜라 한 잔!",
        "오늘의 행운: 기분까지 상쾌한 스프라이트!",
        "오늘의 행운: 컬러풀한 환타 타임!",
        "오늘의 행운: 에너지가 필요한 순간, 파워에이드!",
        "오늘의 행운: 가볍게 토레타 한 모금!"
    ];
    let playing = false;

    gachaButton.addEventListener("click", function () {
        if (playing) return;
        playing = true;
        gachaButton.disabled = true;
        gachaResult.textContent = "캡슐이 돌아가는 중...";
        gachaVisual.classList.remove("playing");
        void gachaVisual.offsetWidth;
        gachaVisual.classList.add("playing");

        window.setTimeout(function () {
            const result = messages[Math.floor(Math.random() * messages.length)];
            gachaResult.textContent = result;
            gachaButton.disabled = false;
            playing = false;
        }, reducedMotion ? 50 : 720);
    });

    if (!reducedMotion) {
        document.querySelectorAll(".image-track figure").forEach(function (card) {
            card.addEventListener("pointermove", function (event) {
                const rect = card.getBoundingClientRect();
                const x = (event.clientX - rect.left) / rect.width - .5;
                const y = (event.clientY - rect.top) / rect.height - .5;
                card.style.transform = `perspective(900px) rotateY(${x * 5}deg) rotateX(${-y * 5}deg)`;
            });
            card.addEventListener("pointerleave", function () {
                card.style.transform = "";
            });
        });
    }

    window.addEventListener("scroll", updateScroll, { passive: true });
    window.addEventListener("resize", updateBrandJourney);
    updateScroll();
});
