/**
 * 회원가입 유효성 검증 파일
 */
// 유효성 검사에 사용할 정규표현식
const reUserid   = /^[a-z]+[a-z0-9]{4,19}$/g;
const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
const reName  = /^[가-힣]{2,10}$/
const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

// 유효성 검사 상태변수
let isUseridOk = false;
let isPassOk = false;
let isNameOk = false;
let isNickOk = false;
let isEmailOk = false;
let isHpOk = false;


document.addEventListener('DOMContentLoaded', function(){

    const form = document.getElementsByTagName('form')[0];

    // 최종 폼 전송하기
    form.addEventListener('submit', function(e){

        if(!isUseridOk){
            e.preventDefault();
            alert('아이디를 확인하세요.');
            return;
        }

        if(!isPassOk){
            e.preventDefault();
            alert('비밀번호를 확인하세요.');
            return;
        }

        if(!isNameOk){
            e.preventDefault();
            alert('이름를 확인하세요.');
            return;
        }

        if(!isNickOk){
            e.preventDefault();
            alert('별명을 확인하세요.');
            return;
        }

        if(!isEmailOk){
            e.preventDefault();
            alert('이메일을 확인하세요.');
            return;
        }

        if(!isHpOk){
            e.preventDefault();
            alert('휴대폰을 확인하세요.');
            return;
        }
    });

    //--------------------------
    // 1) 아이디 유효성 검사(중복체크 포함)
    //--------------------------
    const btnUserid = document.getElementById('btnUserid');
    const useridResult = document.getElementsByClassName('useridResult')[0];

    btnUserid.addEventListener('click', async function(e){
        e.preventDefault();

        const value = form.userid.value;

        // 아이디 유효성 검사
        if(!value.match(reUserid)){
            useridResult.innerText = '아이디가 유효하지 않습니다.';
            useridResult.style.color = 'red';
            return;
        }

        // 아이디 중복 여부 요청하기
        const response = await fetch('/user/check?type=userid&value=' + value);
        const data = await response.json();
        console.log(data);

        if(data.count > 0){
            useridResult.innerText = '이미 사용중인 아이디 입니다.';
            useridResult.style.color = 'red';
            isUseridOk = false;
        }else{
            useridResult.innerText = '사용 가능한 아이디 입니다.';
            useridResult.style.color = 'green';
            isUseridOk = true;
        }
    }); // 아이디 중복 체크 끝

    //--------------------------
    // 2) 비밀번호 유효성 검사 및 일치여부
    //--------------------------
    const pass1 = document.getElementsByName('pass')[0];
    const pass2 = document.getElementsByName('pass2')[0];
    const passResult = document.getElementsByClassName('passResult')[0];

    pass1.addEventListener('focusout', function(e){
        e.preventDefault();

        const value = form.pass.value;

        if(!value.match(rePass)){
            passResult.innerText = '비밀번호가 유효하지 않습니다.';
            passResult.style.color = 'red';
            return;
        }else {
            passResult.innerText = '';
        }

    });

    pass2.addEventListener('focusout', function(e){
        e.preventDefault();

        const value1 = form.pass.value;
        const value2 = form.pass2.value;

        if(value1 === value2){
            passResult.innerText = '비밀번호가 일치 합니다.';
            passResult.style.color = 'green';
            isPassOk = true;
        }else {
            passResult.innerText = '비밀번호가 일치하지 않습니다.';
            passResult.style.color = 'red';
            isPassOk = false;
        }
    });

    //--------------------------
    // 3) 이름 유효성 검사
    //--------------------------
    const name = document.getElementsByName('name')[0];
    const nameResult = document.getElementsByClassName('nameResult')[0];

    name.addEventListener('focusout', function(e){
        e.preventDefault();

        const value = form.name.value;

        if(!value.match(reName)){
            nameResult.innerText = '이름이 유효하지 않습니다.';
            nameResult.style.color = 'red';
            isNameOk = false;
        }else {
            nameResult.innerText = '';
            isNameOk = true;
        }
    });

    //--------------------------
    // 4) 별명 유효성 검사(중복 체크 포함)
    //--------------------------
    const btnNick = document.getElementById('btnNick');
    const nickResult = document.getElementsByClassName('nickResult')[0];

    btnNick.addEventListener('click', async function(e){
        e.preventDefault();

        const value = form.nick.value;

        if(!value.match(reNick)){
            nickResult.innerText = '별명이 유효하지 않습니다.';
            nickResult.style.color = 'red';
            isNickOk = false;
        }

        // 별명 중복 여부 요청하기
        const response = await fetch('/user/check?type=nick&value=' + value);
        const data = await response.json();
        console.log(data);

        if(data.count > 0){
            nickResult.innerText = '이미 사용중인 별명 입니다.';
            nickResult.style.color = 'red';
            isNickOk = false;
        }else{
            nickResult.innerText = '사용 가능한 별명 입니다.';
            nickResult.style.color = 'green';
            isNickOk = true;
        }
    }); // 별명 중복 체크 끝


    //--------------------------
    // 5) 이메일 인증 확인(중복체크 포함)
    //--------------------------
    const btnEmail = document.getElementById('btnEmail');
    const btnConfirm = document.getElementById('btnConfirm');
    const auth = document.getElementsByClassName('auth')[0];
    const emailResult = document.getElementsByClassName('emailResult')[0];

    let preventDblClick = false; // 이중 클릭 방지 상태 변수

    btnEmail.addEventListener('click', async function(e){
        e.preventDefault();

        // 이중 클릭 방지
        if(preventDblClick){
            return;
        }

        console.log('이중 클릭 방지!!!');

        const value = form.email.value;

        if(!value.match(reEmail)){
            emailResult.innerText = '이메일이 유효하지 않습니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
            return;
        }

        preventDblClick = true;

        // 이메일 인증코드 요청하기(중복여부 검사 포함)
        const response = await fetch('/user/check?type=email&value='+value);
        const data = await response.json();
        console.log(data);

        if(data.count > 0){
            emailResult.innerText = '이미 사용중인 이메일 입니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
        }else{
            emailResult.innerText = '이메일 인증코드를 확인 하세요.';
            emailResult.style.color = 'green';
            auth.style.display = 'block'; // 인증코드 입력필드 노출
        }

    });


    // 인증코드 확인버튼 클릭
    btnConfirm.addEventListener('click', async function(e){
        e.preventDefault();

        const value = form.code.value;

        // JSON 생성
        const jsonData = {
            "code": value
        };

        // 이메일 인증코드 전송하기(인증코드 검증)
        const response = await fetch('/user/check', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jsonData),
        });
        const data = await response.json();
        console.log(data);

        if(data.count > 0){
            emailResult.innerText = '인증코드가 잘못 되었습니다.';
            emailResult.style.color = 'red';
            isEmailOk = false;
        }else{
            emailResult.innerText = '이메일이 인증 되었습니다.';
            emailResult.style.color = 'green';
            isEmailOk = true;
        }
    });

    //--------------------------
    // 6) 휴대폰 유효성 검사
    //--------------------------
    const inputHp = document.getElementsByName('hp')[0];
    const hpResult = document.getElementsByClassName('hpResult')[0];

    inputHp.addEventListener('focusout', async function(e){
        e.preventDefault();
        //console.log('focusout!!');

        const value = form.hp.value;

        if(!value.match(reHp)){
            hpResult.innerText = '휴대폰이 유효하지 않습니다.';
            hpResult.style.color = 'red';
            isHpOk = false;
            return;
        }

        // 휴대폰 중복 여부 요청하기
        const response = await fetch('/user/check?type=hp&value='+value);
        const data = await response.json();
        console.log(data);

        if(data.count > 0){
            hpResult.innerText = '이미 사용중인 휴대폰 입니다.';
            hpResult.style.color = 'red';
            isHpOk = false;
        }else{
            hpResult.innerText = '사용할 수 있는 휴대폰 입니다.';
            hpResult.style.color = 'green';
            isHpOk = true;
        }
    }); // 휴대폰 유효성 검사 끝

}); // DOMContentLoaded 끝