// 제출하기 버튼 클릭 이벤트 핸들러
$('button[value="report-submit"]').click(function (event) {
    // 신고 사유 체크박스 선택 여부 확인
    var reportReasonChecked = $('input[name="REPORT_REASON"]:checked').length > 0;

    // 동의 체크박스 선택 여부 확인
    var agreeChecked = $('#AGREE').is(':checked');

    // 필수 조건 충족 여부 확인
    if (!reportReasonChecked || !agreeChecked) {
        alert('신고 사유를 선택하고 동의 체크박스를 선택해 주세요.'); // 알림 메시지 표시
        return; // 함수 종료
    }

    // 비동기 요청 보내기
    $.ajax({
        type: 'POST',
        url: 'report-submit.php', // 실제 제출 처리 URL
        data: {
            reportReason: $('input[name="REPORT_REASON"]:checked').val(),
            reportDescription: $('textarea[name="REPORT_DESCRIPTION"]').val()
        },
        success: function (response) {
            alert('신고가 접수되었습니다.'); // 성공 메시지 표시
            // 추가 처리 로직 작성
        },
        error: function (xhr, status, error) {
            alert('신고 제출에 실패했습니다. 다시 시도해 주세요.'); // 실패 메시지 표시
            // 오류 처리 로직 작성
        }
    });
});