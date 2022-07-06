package com.woowoow.mayrose.domain.listener;

import com.woowoow.mayrose.domain.User;
import com.woowoow.mayrose.domain.UserHistory;
import com.woowoow.mayrose.repository.UserHistoryRepository;
import com.woowoow.mayrose.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

// Listener에서는 @AuthWired로 Bean을 주입해 줄 수가 없쬬요.   ApplicationContext를 이용해서 getBean으로 가져와야 합니다.
@Component
public class UserEntityListener {

//    @Autowired
//    private UserHistoryRepository userHistoryRepository ;

    @PrePersist
    @PreUpdate
    public void PreUpdate(Object o) {
        System.out.println(">>>> UserEntityListener::@PreUpdate 호출 : User테이블 insert 및 update로 인하여 UserHistory 테이블의 데이타가 insert 되려고 합니다.");
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User)o;

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
