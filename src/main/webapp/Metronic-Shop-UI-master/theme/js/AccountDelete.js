document.addEventListener('DOMContentLoaded', function() {
    var agreeCheckbox = document.getElementById('agreeTerms');
    var withdrawButton = document.querySelector('.btn-withdraw');

    withdrawButton.disabled = true;
    withdrawButton.style.backgroundColor = '#cccccc';
    withdrawButton.style.cursor = 'not-allowed';

    agreeCheckbox.addEventListener('change', function() {
        if (this.checked) {
            withdrawButton.disabled = false;
            withdrawButton.style.backgroundColor = '#e8834b';
            withdrawButton.style.cursor = 'pointer';
        } else {
            withdrawButton.disabled = true;
            withdrawButton.style.backgroundColor = '#cccccc';
            withdrawButton.style.cursor = 'not-allowed';
        }
    });

    withdrawButton.addEventListener('click', function(e) {
        if (!agreeCheckbox.checked) {
            e.preventDefault();
            alert('약관에 동의해야 탈퇴가 가능합니다.');
            return false;
        }

        if (confirm('정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
            window.location.href = 'deleteUser.do';
        }
    });
});
