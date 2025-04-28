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

            $.ajax({
                url: '/updateProfileImage.do',
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    console.log('서버 응답:', response);
                    if (response.success) {
                        alert(response.message);
                        window.location.href = '/myPage.do';
                    } else {
                        alert('업로드 실패: ' + response.message);
                    }
                },
                error: function (error) {
                    console.error('프로필 이미지 업로드 중 오류:', error);
                    alert('서버 오류가 발생했습니다.');
                }
            });
        }
    });

    $('.sidebar-menu .list-group-item').on('click', function () {
        $('.sidebar-menu .list-group-item').removeClass('active');
        $(this).addClass('active');
    });
});
