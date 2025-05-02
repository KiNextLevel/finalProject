$(document).ready(function () {
    $('#profileForm').on('submit', function (e) {
        e.preventDefault();

        const file = document.getElementById('profileUpload').files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (event) {
                document.getElementById('profileImage').src = event.target.result;
            };
            reader.readAsDataURL(file);

            const formData = new FormData();
            formData.append('profileImage', file);

            // 비동기 Ajax 요청 시작
            $.ajax({
                url: '/updateProfileImage.do',   // 요청을 보낼 서버 URL
                type: 'POST',                    // HTTP 메서드 (POST 방식으로 파일 전송)
                data: formData,                  // 서버로 보낼 데이터(FormData 객체)
                contentType: false,              // 브라우저가 자동으로 Content-Type 설정하도록 함 (multipart/form-data)
                processData: false,              // jQuery가 formData를 문자열로 변환하지 않도록 설정

                // 요청 성공 시 호출되는 콜백 함수
                success: function (response) {
                    console.log('서버 응답:', response); // 서버로부터 받은 응답을 콘솔에 출력

                    // 서버 응답 객체(response) 안에 success 속성이 true일 경우
                    if (response.success) {
                        alert(response.message);        // 성공 메시지를 알림창으로 표시
                        window.location.href = '/myPage.do'; // 마이페이지로 강제 이동
                    } else {
                        alert('업로드 실패: ' + response.message); // 실패 시 실패 메시지를 알림창으로 표시
                    }
                },

                // 요청 실패(에러) 시 호출되는 콜백 함수
                error: function (error) {
                    console.error('프로필 이미지 업로드 중 오류:', error); // 에러 내용을 콘솔에 출력
                    alert('서버 오류가 발생했습니다.'); // 사용자에게 서버 오류 알림
                }
            });
        }
    });

    // 사이드바 메뉴 항목(.sidebar-menu 안 .list-group-item)을 클릭할 때 이벤트 리스너 등록
    $('.sidebar-menu .list-group-item').on('click', function () {
        // 모든 메뉴 항목의 'active' 클래스를 제거 (모두 비활성화)
        $('.sidebar-menu .list-group-item').removeClass('active');

        // 클릭한 메뉴 항목(this)만 'active' 클래스를 추가 (활성화)
        $(this).addClass('active');
    });
});