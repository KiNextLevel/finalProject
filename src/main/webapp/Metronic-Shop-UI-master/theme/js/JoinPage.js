// 폼 유효성 검사
document.addEventListener('DOMContentLoaded', function () {
    $('#userInfoForm').submit(function (e) {
        const nickname = $('#nickname').val();
        const gender = $('#gender').val();
        const birth = $('#birth').val();
        const region = $('#region').val();
        const profile = $('#profile').val();

        console.log('닉네임: ' + nickname);
        console.log('성별: ' + gender);
        console.log('생일: ' + birth);
        console.log('지역: ' + region);
        console.log('프로필: ' + profile);
        const dob = new Date(birth);
        const today = new Date();
        let age = today.getFullYear() - dob.getFullYear();
        console.log("나이 :" + age);

        if (!nickname || !gender || !birth || !region || !profile) {
            alert('필수 항목을 모두 입력해주세요.');
            e.preventDefault();
            return false;
        }
        if (age <= 17) { // 최소 나이 제한
            alert('18살 이상부터 가입하실 수 있습니다');
            e.preventDefault();
            return false;
        }
        if (dob.getFullYear() <= 1900) { // 최대 나이 제한
            alert('1900년 이하로는 입력하실 수 없습니다');
            e.preventDefault();
            return false;
        }
        return true;
    });
});

// 주소찾기 팝업 함수
function openAddressPopup() {
    window.open("/API/addressAPI.html", "주소찾기", "width=600,height=500,scrollbars=yes");
}
