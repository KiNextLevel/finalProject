// 사용자 위치 기반 지도를 생성하고, 반경 원을 표시하는 함수
// lat: 사용자 위도, lng: 사용자 경도
function initUserMap(lat, lng) {
    // 지도 객체 생성
    // 중심 좌표는 사용자의 위도(lat), 경도(lng)로 설정
    const map = new kakao.maps.Map(document.getElementById('map'), {
        center: new kakao.maps.LatLng(lat, lng),
        level: 7  // 지도 크기

    });


    // 사용자 위치를 중심으로 반경 원(circle) 표시
    const circle = new kakao.maps.Circle({
        center: new kakao.maps.LatLng(lat, lng), // 원의 중심 좌표
        radius: 3000,                            // 반지름 (단위: 미터) → 3000m = 3km
        strokeWeight: 2,                         // 원 테두리 두께
        strokeColor: '#FF5A5A',                  // 원 테두리 색상 (붉은색 계열)
        fillColor: '#FFCCCC',                    // 원 내부 색상 (연한 붉은색)
        fillOpacity: 0.3                         // 원 내부 투명도 (0: 투명 ~ 1: 불투명)
    });

    // 생성한 원(circle)을 지도 위에 표시
    circle.setMap(map);
}
