//변수 (window 객체를 직접 참조)
const currentUserEmail = window.currentUserEmail;
const currentYear = new Date().getFullYear();
let filteredUsers = [];
let start = 0;
const limit = 9;

jQuery(document).ready(function () {
    Layout.init();
    Layout.initOWL();
    Layout.initTwitter();
    Layout.initImageZoom();
    Layout.initTouchspin();
    Layout.initUniform();

    initSliders();

    $('input[name="gender"], input[name="distance"], input[name="religion"], input[name="smoking"]').on('change', applyFilters);

    $('#load-more-btn').on('click', function () {
        loadMoreUsers();
    });

});

// 알림 번호를 받아 읽음 상태로 업데이트하는 함수
function markAsRead(alertNumber) {
    console.log("로그: 알림 번호 [" + alertNumber + "]");
    $.ajax({
        url: "/updateAlertStatus.do",
        type: "POST",
        data: { alertNumber: alertNumber },
        dataType: 'json',
        success: function (response) {
            console.log("로그: 상태 업데이트 성공 [" + response + "]");
            $("#alert-" + alertNumber)
                .removeClass('unread')
                .addClass('read')
                .find(".alert-status")
                .text("");

            // window.alertDatasJson 배열 업데이트
            window.alertDatasJson = window.alertDatasJson.map(a => {
                if (a.alertNumber === alertNumber) {
                    return { ...a, alertIsWatch: true };
                }
                return a;
            });

            checkAllAlertsRead();
        },
        error: function () {
            console.log("비동기 처리 실패");
        }
    });
}

// 모든 알림이 읽혔는지 확인하고 UI 업데이트
function checkAllAlertsRead() {
    let allRead = window.alertDatasJson.every(alert => alert.alertIsWatch === true || alert.alertIsWatch === "true");
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

    if (window.alertDatasJson.length === 0) {
        $scroller.append('<li class="alert-empty"><p>받은 알림이 아직 없습니다</p></li>');
    } else {
        window.alertDatasJson.forEach(function (data) {
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
    $('.product-item').css('height', 'auto');

    const cardsPerRow = $(window).width() > 992 ? 3 : ($(window).width() > 768 ? 2 : 1);

    for (let i = 0; i < $('.product-item').length; i += cardsPerRow) {
        let maxHeight = 0;

        for (let j = 0; j < cardsPerRow; j++) {
            if (i + j < $('.product-item').length) {
                const cardHeight = $('.product-item').eq(i + j).outerHeight();
                maxHeight = Math.max(maxHeight, cardHeight);
            }
        }

        for (let j = 0; j < cardsPerRow; j++) {
            if (i + j < $('.product-item').length) {
                $('.product-item').eq(i + j).css('height', maxHeight + 'px');
            }
        }
    }
}

// 나이와 키 필터 슬라이더를 초기화하는 함수
function initSliders() {
    $('#age-slider-range').slider({
        range: true,
        min: 18,
        max: 90,
        values: [18, 90],
        slide: function (event, ui) {
            $('#ageAmount').val(ui.values[0] + ' - ' + ui.values[1]);
        },
        change: function () {
            applyFilters();
        }
    });
    $('#ageAmount').val($('#age-slider-range').slider('values', 0) + ' - ' + $('#age-slider-range').slider('values', 1));

    $('#height-slider-range').slider({
        range: true,
        min: 130,
        max: 220,
        values: [130, 220],
        slide: function (event, ui) {
            $('#heightAmount').val(ui.values[0] + ' - ' + ui.values[1]);
        },
        change: function () {
            applyFilters();
        }
    });
    $('#heightAmount').val($('#height-slider-range').slider('values', 0) + ' - ' + $('#height-slider-range').slider('values', 1));
}

// 사용자 데이터를 필터링하는 함수
function applyFilters() {
    const selectedGenders = $('input[name="gender"]:checked').map(function () {
        return $(this).val();
    }).get();
    const selectedDistance = $('input[name="distance"]:checked').val();
    const ageRange = $('#age-slider-range').slider('values');
    const heightRange = $('#height-slider-range').slider('values');
    const selectedReligions = $('input[name="religion"]:checked').map(function () {
        return $(this).val();
    }).get();
    const selectedSmoking = $('input[name="smoking"]:checked').map(function () {
        return $(this).val();
    }).get();

    const currentUserLatitude = parseFloat(window.currentUserLatitude);
    const currentUserLongitude = parseFloat(window.currentUserLongitude);

    console.log('Selected Genders:', selectedGenders);
    console.log('Selected Distance:', selectedDistance);
    console.log('Age Range:', ageRange);
    console.log('Height Range:', heightRange);
    console.log('Selected Religions:', selectedReligions);
    console.log('Selected Smoking:', selectedSmoking);
    console.log("Current User Lat/Lon:", currentUserLatitude, currentUserLongitude);

    filteredUsers = window.allUsers.filter(user => {
        const birthYear = parseInt(user.userBirth) || 0;
        const userAge = birthYear ? currentYear - birthYear : 0;
        const userGenderStr = user.userGender === 1 ? "남" : "여";
        const userSmokeStr = user.userSmoke === 1 ? "흡연" : "비흡연";

        const userLatitude = parseFloat(user.userLatitude);
        const userLongitude = parseFloat(user.userLongitude);

        const userDistance = calculateDistance(
            currentUserLatitude,
            currentUserLongitude,
            userLatitude,
            userLongitude
        );

        const passesRole = user.userRole === 0;
        const passesEmail = user.userEmail !== currentUserEmail;
        const passesGender = selectedGenders.length === 0 || selectedGenders.includes(userGenderStr);
        const passesDistance = userDistance <= parseInt(selectedDistance) || selectedDistance === '100';
        const passesAge = userAge >= ageRange[0] && userAge <= ageRange[1];
        const passesHeight = user.userHeight >= heightRange[0] && user.userHeight <= heightRange[1];
        const passesReligion = selectedReligions.length === 0 || selectedReligions.includes(user.userReligion);
        const passesSmoking = selectedSmoking.length === 0 || selectedSmoking.includes(userSmokeStr);

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
    start = 0;
    $('#product-list').empty();
    loadInitialUsers();
}

function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371;
    const dLat = (lat2 - lat1) * Math.PI / 180;
    const dLon = (lon2 - lon1) * Math.PI / 180;
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c;
    return distance;
}

function loadInitialUsers() {
    const usersToDisplay = filteredUsers.slice(start, start + limit);
    updateProductList(usersToDisplay, false);
    start += limit;
    updateLoadMoreButton();
}

function loadMoreUsers() {
    const usersToDisplay = filteredUsers.slice(start, start + limit);
    updateProductList(usersToDisplay, true);
    start += limit;
    updateLoadMoreButton();
}

function updateLoadMoreButton() {
    if (start >= filteredUsers.length) {
        $('#load-more-btn').text('회원이 더 이상 없습니다').prop('disabled', true);
    } else {
        $('#load-more-btn').text('더 보기').prop('disabled', false);
    }
}

function updateProductList(users, append) {
    if (!users || users.length === 0) {
        $('#product-list').html('<p>회원이 없습니다</p>');
        return;
    }

    let productListHtml = '';
    users.forEach(function (data) {
        if (!data.userEmail || !data.userNickname) {
            console.log("Invalid user data:", data);
            return;
        }
        // console.log("Rendering user:", data.userEmail, data.userNickname, data.userHeight, data.userDescription);
        let year = currentYear - parseInt(data.userBirth);
        // console.log("나이 출력" + year);

        productListHtml += `
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="${data.userProfile || 'default.jpg'}" class="img-responsive" alt="userImage">
                        <div class="product-page-cart">
<!--                            <button class="btn btn-primary" type="submit">메시지 보내기</button>-->
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

    if (append) {
        $('#product-list').append(productListHtml);
    } else {
        $('#product-list').html(productListHtml);
    }
    equalizeCardHeights();
}