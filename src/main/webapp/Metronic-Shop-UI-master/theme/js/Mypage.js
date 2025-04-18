$(document).ready(function () {
    // 파일 선택 시 자동으로 폼 제출됨 (onchange="this.form.submit();")

    // 선택적: 파일 선택 전 확인 메시지 표시
    $('.image-overlay').click(function (event) {
        event.preventDefault(); // 기본 동작 방지

        if (confirm('프로필 이미지를 변경하시겠습니까?')) {
            $('#profileUpload').click();
        }
    });

    // 사이드바 메뉴 클릭 시 부드러운 스크롤 이동
    $('.sidebar-menu a').click(function (e) {
        e.preventDefault(); // 기본 링크 동작 방지

        // 모든 메뉴 항목에서 active 클래스 제거
        $('.sidebar-menu li').removeClass('active');

        // 클릭한 메뉴 항목에 active 클래스 추가
        $(this).parent('li').addClass('active');

        // 대상 요소의 ID 가져오기
        var targetId = $(this).attr('href');

        // 대상 요소의 위치 계산 (상단에서 약간 여백 추가)
        var targetPosition = $(targetId).offset().top - 20;

        // 부드러운 스크롤 애니메이션
        $('html, body').animate({
            scrollTop: targetPosition
        }, 800); // 800ms 동안 애니메이션 실행
    });

    // 스크롤 시 현재 보고 있는 섹션에 따라 사이드바 메뉴 활성화
    $(window).scroll(function () {
        var scrollPosition = $(window).scrollTop();

        // 각 섹션을 확인하여 현재 보이는 섹션의 메뉴 활성화
        $('.info-card').each(function () {
            var currentSection = $(this);
            var sectionTop = currentSection.offset().top - 100; // 약간의 오프셋 적용
            var sectionId = '#' + currentSection.attr('id');

            if (scrollPosition >= sectionTop) {
                $('.sidebar-menu li').removeClass('active');
                $('.sidebar-menu a[href="' + sectionId + '"]').parent('li').addClass('active');
            }
        });
    });
});
function confirmDelete() {
    if(confirm('정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
        window.location.href = 'deleteAccount.do';
    }
}