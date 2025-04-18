// 폼 제출 전 유효성 검사
$("#reportForm").submit(function(e) {
    // 최소 하나의 체크박스가 선택되었는지 확인
    if($('input[name="reason"]:checked').length === 0) {
        alert("최소 하나 이상의 신고 사유를 선택해주세요.");
        e.preventDefault();
        return false;
    }

    // 상세 내용이 입력되었는지 확인
    if($('textarea[name="description"]').val().trim() === '') {
        alert("상세 내용을 입력해주세요.");
        $('textarea[name="description"]').focus();
        e.preventDefault();
        return false;
    }

    // 동의 체크박스 확인
    if(!$('#agree').is(':checked')) {
        alert("신고 정보 안내에 동의해주세요.");
        e.preventDefault();
        return false;
    }

    return true;
});