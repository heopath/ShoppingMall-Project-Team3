document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("changePasswordForm");
    const btnChange = document.getElementById("btnChange");

    const password = document.getElementById("password");
    const passwordConfirm = document.getElementById("passwordConfirm");

    const passResult = document.querySelector(".passResult");

    // const rePass = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,16}$/;
    const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;

    let isPassOk = false;

    // 새 비밀번호 입력
    password.addEventListener("input", function () {

        if (password.value === "") {
            passResult.innerText = "";
            isPassOk = false;
            return;
        }

        if (!rePass.test(password.value)) {
            passResult.innerText = "영문, 숫자, 특수문자를 포함하여 5~16자로 입력해주세요.";
            passResult.style.color = "red";
            isPassOk = false;

        } else {

            passResult.innerText = "사용 가능한 비밀번호입니다.";
            passResult.style.color = "#83d400";
            isPassOk = true;

        }

    });

    // 새 비밀번호 확인
    passwordConfirm.addEventListener("input", function () {

        if (passwordConfirm.value === "") {
            return;
        }

        if (password.value !== passwordConfirm.value) {

            passResult.innerText = "비밀번호가 일치하지 않습니다.";
            passResult.style.color = "red";
            isPassOk = false;

        } else if (rePass.test(password.value)) {

            passResult.innerText = "비밀번호가 일치합니다.";
            passResult.style.color = "#83d400";
            isPassOk = true;

        }

    });

    // 비밀번호 변경
    btnChange.addEventListener("click", async function (e) {

        e.preventDefault();

        if (!rePass.test(password.value)) {

            alert("비밀번호 형식을 확인해주세요.");
            password.focus();
            return;

        }

        if (password.value !== passwordConfirm.value) {

            alert("비밀번호가 일치하지 않습니다.");
            passwordConfirm.focus();
            return;

        }

        const response = await fetch("/member/find/changePassword", {

            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                memberId: form.memberId.value,
                password: password.value
            })

        });

        const data = await response.json();

        if (data.result > 0) {

            alert("비밀번호가 변경되었습니다.");
            location.href = "/member/login";

        } else {

            alert("비밀번호 변경에 실패했습니다.");

        }

    });

});