// 전역 변수 (JSP에서 설정된 값 사용)
let allUsers = window.allUsers;
let filteredUsers = [];
let start = 0;
const limit = 9;
let currentUserEmail = window.currentUserEmail;
const currentYear = new Date().getFullYear();

jQuery(document).ready(function () {
    Layout.init();
    Layout.initOWL();
    Layout.initTwitter();
    Layout.initImageZoom();
    Layout.initTouchspin();
    Layout.initUniform();

    initSliders();
    applyFilters();

    $('#load-more-btn').on('click', function () {
        loadMoreUsers();
    });

    $('input[name="gender"], input[name="distance"], input[name="religion"], input[name="smoking"]').on('change', applyFilters);
});

// 알림 데이터를 전역 변수로 설정 (JSP에서 전달된 JSON 문자열을 파싱)
const alertDatas = JSON.parse(window.alertDatasJson || '[]');
console.log("알람" + alertDatas);

// 알림 번호를 받아 읽음 상태로 업데이트하는 함수
function markAsRead(alertNumber) {
    console.log("로그: 알림 번호 [" + alertNumber + "]");
    $.ajax({
        url: "/updateAlertStatus",
        type: "POST",
        data: { alertNumber: alertNumber },
        dataType: 'json',
        success: function (response) {
            console.log("로그: 상태 업데이트 성공 [" + response + "]");
            // 상태 클래스만 변경 (CSS는 별도로 관리)
            $("#alert-" + alertNumber)
                .removeClass('unread')
                .addClass('read')
                .find(".alert-status")
                .text(""); // 읽음 상태에서는 텍스트 제거

            // alertDatas 배열 업데이트
            const alert = alertDatas.find(a => a.alertNumber === alertNumber);
            if (alert) {
                alert.alertIsWatch = true;
            }

            checkAllAlertsRead();
        },
        error: function () {
            console.log("비동기 처리 실패");
        }
    });
}

// 모든 알림이 읽혔는지 확인하고 UI 업데이트
function checkAllAlertsRead() {
    let allRead = true;
    alertDatas.forEach(function (alert) {
        if (!alert.alertIsWatch) {
            allRead = false;
        }
    });
    if (allRead) {
        $(".top-cart-info").hide();
    } else {
        $(".top-cart-info").show();
    }
}

// 알림 목록 렌더링 함수
function renderAlerts() {
    const $scroller = $(".scroller");
    $scroller.empty();

    if (alertDatas.length === 0) {
        $scroller.append('<li class="alert-empty"><p>받은 알림이 아직 없습니다</p></li>');
    } else {
        alertDatas.forEach(function (data) {
            const alertHtml = `
                <li id="alert-${data.alertNumber}" class="alert-item ${data.alertIsWatch ? 'read' : 'unread'}" onclick="markAsRead(${data.alertNumber})">
                    <span class="alert-status">${data.alertIsWatch ? '' : 'new'}</span>
                    <a href="javascript:void(0);" class="alert-content">${data.alertContent}</a>
                    <i class="alert-date">${data.alertDate}</i>
                </li>
            `;
            $scroller.append(alertHtml);
        });
    }
}

// 페이지 로드 시 실행
$(document).ready(function () {
    renderAlerts();
    checkAllAlertsRead();
});

// 창 크기 변경 시 카드 높이를 조정
$(window).on('resize', function () {
    equalizeCardHeights();
});

// 창 로드 완료 시 카드 높이를 조정
$(window).on('load', function () {
    equalizeCardHeights();
});

// 카드 높이를 동일하게 맞추는 함수 (반응형 레이아웃 지원)
function equalizeCardHeights() {
    $('.product-item').css('height', 'auto'); // 모든 카드 높이 초기화

    // 화면 너비에 따라 한 행에 표시할 카드 수 결정
    const cardsPerRow = $(window).width() > 992 ? 3 : ($(window).width() > 768 ? 2 : 1);

    // 각 행별로 카드 높이를 맞춤
    for (let i = 0; i < $('.product-item').length; i += cardsPerRow) {
        let maxHeight = 0;

        // 현재 행에서 가장 높은 카드 높이 찾기
        for (let j = 0; j < cardsPerRow; j++) {
            if (i + j < $('.product-item').length) {
                const cardHeight = $('.product-item').eq(i + j).outerHeight();
                maxHeight = Math.max(maxHeight, cardHeight);
            }
        }

        // 현재 행의 모든 카드 높이를 최대 높이로 설정
        for (let j = 0; j < cardsPerRow; j++) {
            if (i + j < $('.product-item').length) {
                $('.product-item').eq(i + j).css('height', maxHeight + 'px');
            }
        }
    }
}

// 나이와 키 필터 슬라이더를 초기화하는 함수
function initSliders() {
    // 나이 슬라이더 설정 (0~100세)
    $('#age-slider-range').slider({
        range: true,
        min: 0,
        max: 100,
        values: [0, 100], // 초기값: 0~100세
        slide: function (event, ui) {
            $('#ageAmount').val(ui.values[0] + ' - ' + ui.values[1]); // 슬라이더 값 표시
        },
        change: function (event, ui) {
            applyFilters(); // 슬라이더 변경 시 필터 적용
        }
    });
    $('#ageAmount').val($('#age-slider-range').slider('values', 0) + ' - ' + $('#age-slider-range').slider('values', 1));

    // 키 슬라이더 설정 (130~220cm)
    $('#height-slider-range').slider({
        range: true,
        min: 130,
        max: 220,
        values: [130, 220], // 초기값: 130~220cm
        slide: function (event, ui) {
            $('#heightAmount').val(ui.values[0] + ' - ' + ui.values[1]); // 슬라이더 값 표시
        },
        change: function (event, ui) {
            applyFilters(); // 슬라이더 변경 시 필터 적용
        }
    });
    $('#heightAmount').val($('#height-slider-range').slider('values', 0) + ' - ' + $('#height-slider-range').slider('values', 1));
}

// allUsers 데이터 확인 (디버깅용)
console.log(allUsers);

// 사용자 데이터를 필터링하는 함수
function applyFilters() {
    // 필터 조건 수집
    const selectedGenders = $('input[name="gender"]:checked').map(function () {
        return $(this).val();
    }).get(); // 선택된 성별
    const selectedDistance = $('input[name="distance"]:checked').val(); // 선택된 거리
    const ageRange = $('#age-slider-range').slider('values'); // 나이 범위
    const heightRange = $('#height-slider-range').slider('values'); // 키 범위
    const selectedReligions = $('input[name="religion"]:checked').map(function () {
        return $(this).val();
    }).get(); // 선택된 종교
    const selectedSmoking = $('input[name="smoking"]:checked').map(function () {
        return $(this).val();
    }).get(); // 선택된 흡연 여부

    // 현재 사용자의 위도/경도 (JSP에서 전달된 값)
    const currentUserLatitude = parseFloat(window.currentUserLatitude);
    const currentUserLongitude = parseFloat(window.currentUserLongitude);

    console.log("Current User Lat/Lon:", currentUserLatitude, currentUserLongitude);

    // 사용자 데이터를 필터링
    filteredUsers = allUsers.filter(user => {
        const birthYear = parseInt(user.userBirth) || 0;
        const userAge = birthYear ? currentYear - birthYear : 0; // 나이 계산
        const userGenderStr = user.userGender ? "남" : "여"; // 성별 문자열 변환
        const userSmokeStr = user.userSmoke ? "흡연" : "비흡연"; // 흡연 여부 문자열 변환

        // 사용자 위도/경도
        const userLatitude = parseFloat(user.userLatitude);
        const userLongitude = parseFloat(user.userLongitude);

        // 현재 사용자와의 거리 계산
        const userDistance = calculateDistance(
            currentUserLatitude,
            currentUserLongitude,
            userLatitude,
            userLongitude
        );

        // 필터 조건 확인
        const passesRole = user.userRole === 0; // 역할이 0인지 (일반 사용자)
        const passesEmail = user.userEmail !== currentUserEmail; // 본인 제외
        const passesGender = selectedGenders.length === 0 || selectedGenders.includes(userGenderStr); // 성별 조건
        const passesDistance = userDistance <= parseInt(selectedDistance) || selectedDistance === '100'; // 거리 조건
        const passesAge = userAge >= ageRange[0] && userAge <= ageRange[1]; // 나이 조건
        const passesHeight = user.userHeight >= heightRange[0] && user.userHeight <= heightRange[1]; // 키 조건
        const passesReligion = selectedReligions.length === 0 || selectedReligions.includes(user.userReligion); // 종교 조건
        const passesSmoking = selectedSmoking.length === 0 || selectedSmoking.includes(userSmokeStr); // 흡연 조건

        // 모든 조건을 만족하는 사용자만 반환
        return (
            passesRole &&
            passesEmail &&
            passesGender &&
            passesDistance &&
            passesAge &&
            passesHeight &&
            passesReligion &&
            passesSmoking
        );
    });

    console.log("Filtered Users:", filteredUsers);
    start = 0; // 페이지네이션 초기화
    $('#product-list').empty(); // 기존 목록 초기화
    loadInitialUsers(); // 초기 사용자 로드
}

// 두 지점 간 거리를 계산하는 함수 (Haversine 공식 사용, 단위: km)
function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371; // 지구 반지름 (km)
    const dLat = (lat2 - lat1) * Math.PI / 180; // 위도 차이를 라디안으로 변환
    const dLon = (lon2 - lon1) * Math.PI / 180; // 경도 차이를 라디안으로 변환
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c; // 최종 거리 계산
    return distance;
}

// 초기 사용자 목록을 로드하는 함수
function loadInitialUsers() {
    const usersToDisplay = filteredUsers.slice(start, start + limit); // 현재 페이지에 표시할 사용자 (limit 개수만큼)
    updateProductList(usersToDisplay, false); // 사용자 목록 렌더링 (덧붙이지 않음)
    start += limit; // 다음 페이지로 이동
    updateLoadMoreButton(); // 더 보기 버튼 상태 업데이트
}

//페이지네이션
function loadMoreUsers() {
    const usersToDisplay = filteredUsers.slice(start, start + limit); // 다음 페이지 사용자
    updateProductList(usersToDisplay, true); // 사용자 목록 렌더링 (덧붙임)
    start += limit; // 다음 페이지로 이동
    updateLoadMoreButton(); // 더 보기 버튼 상태 업데이트
}

// 더 보기 버튼의 상태를 업데이트하는 함수
function updateLoadMoreButton() {
    if (start >= filteredUsers.length) {
        // 더 이상 표시할 사용자가 없으면 버튼 비활성화
        $('#load-more-btn').text('더 이상 없습니다').prop('disabled', true);
    } else {
        // 표시할 사용자가 남아 있으면 버튼 활성화
        $('#load-more-btn').text('더 보기').prop('disabled', false).show();
    }
}

// 사용자 목록을 화면에 렌더링하는 함수
function updateProductList(users, append) {
    if (!users || users.length === 0) {
        $('#product-list').html('<p>회원이 없습니다</p>'); // 사용자 데이터가 없으면 메시지 표시
        return;
    }

    let productListHtml = '';
    users.forEach(function (data) {
        if (!data.userEmail || !data.userNickname) {
            console.log("Invalid user data:", data); // 데이터가 없으면 스킵
            return;
        }
        console.log("Rendering user:", data.userEmail, data.userNickname, data.userHeight, data.userDescription);
        let year = currentYear - parseInt(data.userBirth); // 사용자 나이 계산
        console.log("나이 출력" + year);

        // 사용자 카드 HTML 생성
        productListHtml += `
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="${data.userProfile || 'default.jpg'}" class="img-responsive" alt="userImage">
                        <div class="product-page-cart">
                            <button class="btn btn-primary" type="submit">메시지 보내기</button>
                            <a href="userDetailPage.do?userEmail=${data.userEmail}" class="btn btn-default">프로필 보기</a>
                        </div>
                    </div>
                    <h3><a href="userDetailPage.do?userEmail=${data.userEmail}">닉네임: <strong>${data.userNickname}</strong></a></h3>
                    <div class="region">지역: ${data.userRegion ? data.userRegion.split(' ')[0] : 'N/A'}</div>
                    <div class="age">나이: ${year}세</div>
                    <div class="description">소개: ${data.userDescription || '설명 없음'}</div>
                </div>
            </div>
        `;
    });

    // append가 true면 기존 목록에 추가, false면 새로 렌더링
    if (append) {
        $('#product-list').append(productListHtml);
    } else {
        $('#product-list').html(productListHtml);
    }
    equalizeCardHeights(); // 카드 높이 조정
}