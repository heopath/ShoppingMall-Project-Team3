document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("findPwEmailForm");

    const btnEmail = document.getElementById("btnEmail");
    const btnConfirm = document.getElementById("btnConfirm");
    const btnNext = document.getElementById("btnNext");


    const auth = document.querySelector(".auth");
    const emailResult = document.querySelector(".emailResult");

    let isEmailOk = false;

    btnEmail.addEventListener("click", async function (e) {

        e.preventDefault();

        const memberId = form.memberId.value;
        const email = form.email.value;

        const response = await fetch("/member/find/checkMember?memberId="
            + memberId +
            "&email=" +
            email);

        const data = await response.json();

        if (data.count == 0) {

            emailResult.innerText = "회원정보가 없습니다.";
            emailResult.style.color = "red";
            return;
        }


        emailResult.innerText = "인증번호를 발송했습니다.";
        emailResult.style.color = "#83d400";
        const response2 = await fetch("/member/find/sendCode", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                email: email
            })
        });

        await response2.json();

        auth.style.display = "block";


    });

    btnConfirm.addEventListener("click", async function (e) {

        e.preventDefault();

        const response = await fetch("/member/find/verifyCode", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                code: form.code.value
            })
        });

        const result = await response.json();

        if (result.count == 0) {

            isEmailOk = true;
            emailResult.innerText = "이메일 인증이 완료되었습니다.";
            emailResult.style.color = "#83d400";

        } else {

            isEmailOk = false;
            emailResult.innerText = "인증번호가 일치하지 않습니다.";
            emailResult.style.color = "red";
        }
    });

    btnNext.addEventListener("click", function () {

        if (!isEmailOk) {
            alert("이메일 인증을 완료해주세요.");
            return;
        }

        location.href = "/member/find/changePassword?memberId="
            + encodeURIComponent(form.memberId.value);

    });
})