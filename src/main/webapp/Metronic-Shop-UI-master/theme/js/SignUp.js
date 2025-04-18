// 페이지 로드 시 실행
document.addEventListener('DOMContentLoaded', function() {
    // 전화번호 입력 필드 가져오기
    const phoneNumberField = document.querySelector('input[name="userPhone"]');
    if(phoneNumberField) {
        // 전화번호 입력 필드에 input 이벤트 리스너 추가
        phoneNumberField.addEventListener('input', function(e) {
            // 숫자만 추출
            const numbers = this.value.replace(/[^0-9]/g, '');

            // 추출된 숫자가 없으면 빈 값 설정
            if (numbers.length === 0) {
                this.value = '';
                return;
            }

            // 전화번호 형식에 맞게 하이픈 추가
            let formattedNumber = '';

            if (numbers.length <= 3) {
                formattedNumber = numbers;
            } else if (numbers.length <= 7) {
                formattedNumber = numbers.substring(0, 3) + '-' + numbers.substring(3);
            } else {
                formattedNumber = numbers.substring(0, 3) + '-' +
                    numbers.substring(3, 7) + '-' +
                    numbers.substring(7, 11);
            }

            // 최대 13자리로 제한
            this.value = formattedNumber.substring(0, 13);
        });
    }

    // 폼 제출 이벤트 리스너 추가
    const signUpForm = document.getElementById('signupform');
    if (signUpForm) {
        signUpForm.addEventListener('submit', function(event) {
            // 중복확인 버튼 클릭이 아니고, 회원가입 제출인 경우
            if (!event.submitter || !event.submitter.name || event.submitter.name !== 'checkIdBtn') {
                // ID 중복 확인 여부 확인
                if (document.getElementById('idChecked').value !== 'Y') {
                    event.preventDefault();
                    alert('아이디 중복 확인을 해주세요.');
                    return;
                }

                // 전화번호 형식 검사
                const phoneNumber = document.getElementById('m_phone').value;
                const phoneRegex = /^010-\d{4}-\d{4}$/;
                if (!phoneRegex.test(phoneNumber)) {
                    event.preventDefault();
                    alert('올바른 전화번호 형식이 아닙니다.');
                    return;
                }

                // 이메일 형식 검사 (선택적)
                const emailId = document.getElementById('m_mail').value;
                if (emailId) {
                    const emailRegex = /^[a-zA-Z0-9_-]+$/;
                    if (!emailRegex.test(emailId)) {
                        event.preventDefault();
                        alert('올바른 이메일 형식이 아닙니다.');
                        return;
                    }

                    // 이메일 도메인이 있는 경우, 전체 이메일 생성
                    const emailDomain = document.getElementById('emailDomain');
                    if (emailDomain) {
                        const fullEmail = emailId + '@' + emailDomain.value;

                        // 숨겨진 입력 필드 생성
                        const hiddenEmail = document.createElement('input');
                        hiddenEmail.type = 'hidden';
                        hiddenEmail.name = 'email';
                        hiddenEmail.value = fullEmail;
                        signUpForm.appendChild(hiddenEmail);
                    }
                }
            }
        });
    }
});
