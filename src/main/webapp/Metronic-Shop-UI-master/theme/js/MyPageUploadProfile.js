
$(document).ready(function() {
    // 프로필 이미지 업로드 처리
    document.getElementById('profileUpload').addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(event) {
                document.getElementById('profileImage').src = event.target.result;
            };
            reader.readAsDataURL(file);

            // 여기에 이미지 업로드 AJAX 코드 추가
            // const formData = new FormData();
            // formData.append('profileImage', file);
            // $.ajax({
            //     url: '/api/user/profile-image',
            //     type: 'POST',
            //     data: formData,
            //     contentType: false,
            //     processData: false,
            //     success: function(response) {
            //         console.log('프로필 이미지가 성공적으로 업로드되었습니다.');
            //     },
            //     error: function(error) {
            //         console.error('프로필 이미지 업로드 중 오류가 발생했습니다:', error);
            //     }
            // });
        }
    });

    // 사이드바 메뉴 활성화
    $('.sidebar-menu .list-group-item').on('click', function() {
        $('.sidebar-menu .list-group-item').removeClass('active');
        $(this).addClass('active');
    });
});