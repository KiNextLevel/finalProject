function setFormAction(event, button) {
    event.preventDefault(); // 기본 폼 제출 방지
    var form = button.closest("form"); // 현재 클릭된 버튼이 속한 form 찾기
    var selectId = form.querySelector("select").id; // 선택된 select 요소의 ID 가져오기
    var selectedAction = document.getElementById(selectId).value; // 선택된 옵션 값 가져오기
    form.action = selectedAction; // 폼의 action을 동적으로 설정
    form.submit(); // 폼 제출
}