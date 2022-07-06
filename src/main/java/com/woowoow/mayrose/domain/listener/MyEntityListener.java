package com.woowoow.mayrose.domain.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {
    @PrePersist
    public void prePersist(Object o) {
        if ( o instanceof Auditable ) {
            System.out.println(">>>> MyEntityListener::@PrePersist 호출");
            ((Auditable)o).setCreatedAt(LocalDateTime.now());
            ((Auditable)o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o) {
        if ( o instanceof Auditable ) {
            System.out.println(">>>> MyEntityListener::@PreUpdate");
            ((Auditable)o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PostPersist
    public void postPersist(Object o) {
        if ( o instanceof Auditable ) {
            System.out.println(">>>> MyEntityListener::@PostPersist 호출 : 새로운 ROW가 insert 되었습니다.");
        }
    }

    @PostUpdate
    public void postUpdate(Object o) {
        if ( o instanceof Auditable ) {
            System.out.println(">>>> MyEntityListener::@PostUpate 호출 : ROW가 Update 되었습니다.");
        }
    }
}
