// 유효성 검사에 사용할 정규표현식
const reUserid   = /^[a-z]+[a-z0-9]{4,19}$/;
const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
const reCompany = /^[가-힣a-zA-Z0-9()&.\s]{2,50}$/;
const reCeo = /^[가-힣a-zA-Z\s]{2,30}$/;
const reBizNo = /^\d{3}-\d{2}-\d{5}$/;
const reOnlineSaleNo = /^\d{4}-[가-힣A-Za-z0-9]+-\d{4,}$/;
const reTel = /^(0\d{1,2})-\d{3,4}-\d{4}$/;
const reFax = /^(0\d{1,2})-\d{3,4}-\d{4}$/;
const reZip = /^\d{5}$/;

// 유효성 검사 상태변수
let isUseridOk = false;
let isPassOk = false;
let isCompanyOk = false;
let isCeoOk = false;
let isBizNoOk = false;
let isOnlineSaleNoOk = false;
let isFaxOk = false;
let isTelOk = false;
let isAddrOk = false;


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

        if(!isCompanyOk){
            e.preventDefault();
            alert('회사명을 확인하세요.');
            return;
        }

        if(!isCeoOk){
            e.preventDefault();
            alert('대표자명을 확인하세요.');
            return;
        }

        if(!isBizNoOk){
            e.preventDefault();
            alert('사업자등록번호를 확인하세요.');
            return;
        }

        if(!isOnlineSaleNoOk){
            e.preventDefault();
            alert('통신판매업번호를 확인하세요.');
            return;
        }

        if(!isTelOk){
            e.preventDefault();
            alert('전화번호를 확인하세요.');
            return;
        }

        if(!isFaxOk){
            e.preventDefault();
            alert('팩스번호를 확인하세요.');
            return;
        }

        if(!isAddrOk){
            e.preventDefault();
            alert('주소를 확인하세요.');
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

        const value = form.memberId.value;

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
            useridResult.style.color = '#83d400';
            isUseridOk = true;
        }
    }); // 아이디 중복 체크 끝

    //--------------------------
    // 2) 비밀번호 유효성 검사 및 일치여부
    //--------------------------
    const pass1 = document.getElementsByName('password')[0];
    const pass2 = document.getElementsByName('password2')[0];
    const passResult = document.getElementsByClassName('passResult')[0];

    pass1.addEventListener('focusout', function(e){
        e.preventDefault();

        const value = form.password.value;

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

        const value1 = form.password.value;
        const value2 = form.password2.value;

        if(value1 === value2){
            passResult.innerText = '비밀번호가 일치 합니다.';
            passResult.style.color = '#83d400';
            isPassOk = true;
        }else {
            passResult.innerText = '비밀번호가 일치하지 않습니다.';
            passResult.style.color = 'red';
            isPassOk = false;
        }


    });

    //--------------------------
    // 3) 회사명 유효성 검사
    //--------------------------
    const company = document.getElementsByName('companyName')[0];
    const companyResult = document.getElementsByClassName('companyResult')[0];

    company.addEventListener('focusout', function(){

        const value = company.value;

        if(!reCompany.test(value)){
            companyResult.innerText = '회사명이 유효하지 않습니다.';
            companyResult.style.color = 'red';
            isCompanyOk = false;
        }else{
            companyResult.innerText = '';
            isCompanyOk = true;
        }
    });
    //--------------------------
    // 4) 대표자명 유효성 검사
    //--------------------------
    const ceo = document.getElementsByName('ceoName')[0];
    const ceoResult = document.getElementsByClassName('ceoResult')[0];

    ceo.addEventListener('focusout', function(){

        const value = ceo.value;

        if(!reCeo.test(value)){
            ceoResult.innerText = '대표자명이 유효하지 않습니다.';
            ceoResult.style.color = 'red';
            isCeoOk = false;
        }else{
            ceoResult.innerText = '';
            isCeoOk = true;
        }
    });
    //--------------------------
    // 5) 사업자등록번호 유효성 검사
    //--------------------------
    const bizNo = document.getElementsByName('bizNo')[0];
    const bizNoResult = document.getElementsByClassName('bizNoResult')[0];

    bizNo.addEventListener('focusout', function(){

        const value = bizNo.value;

        if(!reBizNo.test(value)){
            bizNoResult.innerText = '사업자등록번호 형식이 올바르지 않습니다.';
            bizNoResult.style.color = 'red';
            isBizNoOk = false;
        }else{
            bizNoResult.innerText = '';
            isBizNoOk = true;
        }
    });
    //--------------------------
    // 6) 통신판매업번호 유효성 검사
    //--------------------------
    const onlineSaleNo = document.getElementsByName('onlineSaleNo')[0];
    const onlineSaleNoResult = document.getElementsByClassName('onlineSaleNoResult')[0];

    onlineSaleNo.addEventListener('focusout', function(){

        const value = onlineSaleNo.value;

        if(!reOnlineSaleNo.test(value)){
            onlineSaleNoResult.innerText = '통신판매업번호 형식이 올바르지 않습니다.';
            onlineSaleNoResult.style.color = 'red';
            isOnlineSaleNoOk = false;
        }else{
            onlineSaleNoResult.innerText = '';
            isOnlineSaleNoOk = true;
        }
    });
    //--------------------------
    // 7) 전화번호 유효성 검사
    //--------------------------
    const tel = document.getElementsByName('tel')[0];
    const telResult = document.getElementsByClassName('telResult')[0];

    tel.addEventListener('focusout', function(){

        const value = tel.value;

        if(!reTel.test(value)){
            telResult.innerText = '전화번호 형식이 올바르지 않습니다.';
            telResult.style.color = 'red';
            isTelOk = false;
        }else{
            telResult.innerText = '';
            isTelOk = true;
        }
    });

    //--------------------------
    // 8) 팩스번호 유효성 검사
    //--------------------------
    const fax = document.getElementsByName('fax')[0];
    const faxResult = document.getElementsByClassName('faxResult')[0];

    fax.addEventListener('focusout', function(){

        const value = fax.value;

        if(!reFax.test(value)){
            faxResult.innerText = '팩스번호 형식이 올바르지 않습니다.';
            faxResult.style.color = 'red';
            isFaxOk = false;
        }else{
            faxResult.innerText = '';
            isFaxOk = true;
        }
    });

    //--------------------------
    // 9) 주소 유효성 검사
    //--------------------------
    const zip = document.getElementsByName('zip')[0];
    const addr1 = document.getElementsByName('addr1')[0];
    // const addr2 = document.getElementsByName('addr2')[0];

    function checkAddress(){

        if(
            reZip.test(zip.value) &&
            addr1.value.trim() !== ''
            // && addr2.value.trim() !== ''
        ){
            isAddrOk = true;
        }else{
            isAddrOk = false;
        }
    }

    zip.addEventListener('input', checkAddress);
    addr1.addEventListener('input', checkAddress);
    addr2.addEventListener('input', checkAddress);
//


}); // DOMContentLoaded 끝