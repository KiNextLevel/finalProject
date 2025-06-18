package com.example.common.biz.alert.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("AlertRepository")
public interface AlertRepository extends JpaRepository<AlertEntity, Integer> {
    // 특정 사용자의 알림 목록 조회 (SELECTALL 대체)
    //JPQL 사용
    @Query("SELECT a FROM AlertEntity a WHERE a.userEmail = :email ORDER BY a.alertNumber DESC")
    List<AlertEntity> getAlertList(@Param("email") String email);   //JPQL에 email 적용
    //@Param: :email에 매핑
    //String email: 전달받는 값

    // 알림 읽음 여부 업데이트 (UPDATE_ISWATCH 대체)
    @Transactional  //@Modifying과 함께 사용
    @Modifying    //DB 변경 일어나는 쿼리 실행
    @Query("UPDATE AlertEntity a SET a.alertIsWatch = true WHERE a.alertNumber = :alertNum")
    int update(@Param("alertNum") int alertNum);
}
