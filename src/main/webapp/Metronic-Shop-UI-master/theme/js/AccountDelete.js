document.addEventListener('DOMContentLoaded', function() {
    var agreeCheckbox = document.getElementById('agreeTerms');
    var withdrawButton = document.getElementById('withdrawButton');
    var withdrawForm = document.getElementById('withdrawForm');

    // 초기 버튼 상태 설정
    withdrawButton.disabled = true;
    withdrawButton.style.backgroundColor = '#cccccc';
    withdrawButton.style.cursor = 'not-allowed';

    // 체크박스 상태 변경 감지
    agreeCheckbox.addEventListener('change', function() {
        if (this.checked) {
            withdrawButton.disabled = false;
            withdrawButton.style.backgroundColor = '#e94d1c';
            withdrawButton.style.cursor = 'pointer';
        } else {
            withdrawButton.disabled = true;
            withdrawButton.style.backgroundColor = '#cccccc';
            withdrawButton.style.cursor = 'not-allowed';
        }
    });

    // 폼 제출 이벤트
    withdrawForm.addEventListener('submit', function(e) {
        if (!agreeCheckbox.checked) {
            e.preventDefault();
            alert('약관에 동의해야 탈퇴가 가능합니다.');
            return false;
        }

        if (!confirm('정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
            e.preventDefault();
            return false;
        }
    });
});
