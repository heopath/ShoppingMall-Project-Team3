document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".side-cat .category-title").forEach(function (button) {
        button.addEventListener("click", function () {
            const item = button.closest(".category-item");
            const submenu = item ? item.querySelector(".sub-category") : null;
            if (!submenu) return;

            const opening = submenu.hidden;

            document.querySelectorAll(".side-cat .sub-category").forEach(function (other) {
                other.hidden = true;
                const otherButton = other.closest(".category-item").querySelector(".category-title");
                otherButton.setAttribute("aria-expanded", "false");
                other.closest(".category-item").classList.remove("active");
            });

            submenu.hidden = !opening;
            button.setAttribute("aria-expanded", String(opening));
            item.classList.toggle("active", opening);
        });
    });
});
