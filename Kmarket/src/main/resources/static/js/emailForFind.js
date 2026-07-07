let isEmailOk = false;

// 인증코드 발송
async function sendCode(email){

    const response = await fetch("/member/find/sendCode?email=" + email);

    return await response.json();

}

// 인증코드 확인
async function verifyCode(code){

    const response = await fetch("/member/find/verifyCode",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            code:code
        })
    });

    return await response.json();

}
