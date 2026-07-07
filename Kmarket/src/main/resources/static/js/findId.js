document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("findIdEmailForm");

    const btnEmail = document.getElementById("btnEmail");
    const auth = document.querySelector(".auth");
    const emailResult = document.querySelector(".emailResult");
    const btnConfirm = document.getElementById("btnConfirm");
    const btnNext = document.getElementById("btnNext");

    let isEmailOk = false;

    // 이메일 인증
    btnEmail.addEventListener("click", async function (e) {

        e.preventDefault();

        const name = form.name.value.trim();
        const email = form.email.value.trim();

        if(name === ""){
            alert("이름을 입력해주세요.");
            form.name.focus();
            return;
        }

        if(email === ""){
            alert("이메일을 입력해주세요.");
            form.email.focus();
            return;
        }

        // 이름 + 이메일 존재 여부 확인
        const response = await fetch(
            "/member/find/checkEmail?name=" +
            encodeURIComponent(name) +
            "&email=" +
            encodeURIComponent(email)
        );

        const data = await response.json();

        if(data.count == 0){

            emailResult.innerText = "일치하는 회원정보가 없습니다.";
            emailResult.style.color = "red";
            return;

        }

        // 인증코드 발송
        emailResult.innerText = "인증번호를 발송했습니다.";
        emailResult.style.color = "#83d400";
        
        const sendResponse = await fetch(
            "/member/find/sendCode?email=" +
            encodeURIComponent(email),
            {
                method:"POST"
            }
        );

        await sendResponse.json();

        // auth.style.display = "flex";



    });

    // 인증번호 확인
    btnConfirm.addEventListener("click", async function (e) {

        e.preventDefault();

        const code = form.code.value.trim();

        if (code === "") {
            alert("인증번호를 입력해주세요.");
            form.code.focus();
            return;
        }

        const response = await fetch("/member/find/verifyCode", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                code: code
            })
        });

        const data = await response.json();

        if (data.count == 0) {

            isEmailOk = true;

            emailResult.innerText = "이메일 인증이 완료되었습니다.";
            emailResult.style.color = "#83d400";

        } else {

            isEmailOk = false;

            emailResult.innerText = "인증번호가 일치하지 않습니다.";
            emailResult.style.color = "red";
        }

    });

    // 다음 버튼 클릭 시 아이디 조회
    btnNext.addEventListener("click", async function () {

        if (!isEmailOk) {
            alert("이메일 인증을 완료해주세요.");
            return;
        }

        const response = await fetch("/member/find/findId", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                name: form.name.value,
                email: form.email.value
            })
        });

        const data = await response.json();

        location.href = "/member/find/resultId?memberId=" + encodeURIComponent(data.memberId);

    });

})

