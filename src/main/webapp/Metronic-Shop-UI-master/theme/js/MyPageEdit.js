document.addEventListener('DOMContentLoaded', function() {
    const introTextarea = document.getElementById('userDescription');
    const charCount = document.getElementById('charCount');

    // 초기 글자 수 표시
    charCount.textContent = introTextarea.value.length + ' / 200';

    // 입력할 때마다 글자 수 업데이트
    introTextarea.addEventListener('input', function() {
        charCount.textContent = this.value.length + ' / 200';
    });
});

// 주소찾기 팝업 함수
function openAddressPopup() {
    window.open("/API/addressAPI.html", "주소찾기", "width=600,height=500,scrollbars=yes");
}

$(document).ready(function() {
    $.ajax({
        url: '/api/getUserInfo.do',  // 서버에서 정보 가져옴
        type: 'GET',
        success: function(response) {
            if (response.status === 'success') {
                const userDTO = response.userVO;
                const preferenceDTO = response.preferenceVO;

                // 가져온 데이터로 form 채우기
                $('#userDescription').val(userDTO.userDescription || '');
                $('#userNickname').val(userDTO.userNickname || '');
                $('#height').val(userDTO.userHeight || '');
                $('#bodyType').val(userDTO.userBody || '');
                $('select[name="userEducation"]').val(userDTO.userEducation || '');
                $('#job').val(userDTO.userJob || '');
                $('select[name="userReligion"]').val(userDTO.userReligion || '');
                $('#region').val(userDTO.userRegion || '');
                $('select[name="userMbti"]').val(userDTO.userMbti || '');
                $('select[name="userDrink"]').val(userDTO.userDrink != null ? userDTO.userDrink.toString() : '');
                $('select[name="userSmoke"]').val(userDTO.userSmoke != null ? userDTO.userSmoke.toString() : '');

                $('#preferenceHeight').val(preferenceDTO.preferenceHeight || '');
                $('select[name="preferenceBody"]').val(preferenceDTO.preferenceBody || '');
                $('#preferenceAge').val(preferenceDTO.preferenceAge || '');
            } else {
                alert(response.message || '사용자 정보를 가져오지 못했습니다.');
            }
        },
        error: function(error) {
            console.error('사용자 정보 가져오기 실패:', error);
            alert('서버와 통신하는 중 오류가 발생했습니다.');
        }
    });
});
