<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-32x32.png">
    <meta charset="UTF-8">
    <title>알림창</title>
    <!-- SweetAlert2 CDN 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        /* 성공 시 확인 버튼 스타일 */
        .swal2-confirm.success {
            background-color: #28a745 !important; /* 초록색 */
            color: white !important;
            border: none !important;
            padding: 8px 16px !important;
            border-radius: 5px !important;
            font-size: 16px !important;
        }
        .swal2-confirm.success:hover {
            background-color: #218838 !important; /* 호버 시 더 어두운 초록색 */
        }

        /* 실패 시 확인 버튼 스타일 */
        .swal2-confirm.error {
            background-color: #dc3545 !important; /* 빨간색 */
            color: white !important;
            border: none !important;
            padding: 8px 16px !important;
            border-radius: 5px !important;
            font-size: 16px !important;
        }
        .swal2-confirm.error:hover {
            background-color: #c82333 !important; /* 호버 시 더 어두운 빨간색 */
        }
    </style>
</head>
<body>

<script type="text/javascript">
    console.log("로그 [${url}]");
    if (${flag}) {
        // 성공 시
        Swal.fire({
            title: '${msg}',
            icon: 'success',
            iconColor: '#28a745',
            confirmButtonText: '확인',
            customClass: {
                confirmButton: 'swal2-confirm success'
            }
        }).then((result) => {
            if (result.isConfirmed) {
                var url = "${url}";
                location.href = url;
            }
        });
    } else {
        // 실패 시
        Swal.fire({
            title: '${msg}',
            icon: 'error',
            iconColor: '#dc3545',
            confirmButtonText: '확인',
            customClass: {
                confirmButton: 'swal2-confirm error'
            }
        }).then((result) => {
            if (result.isConfirmed) {
                history.go(-1);
            }
        });
    }
</script>

</body>
</html>