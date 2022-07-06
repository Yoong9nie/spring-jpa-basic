package com.woowoow.mayrose.domain;

import com.woowoow.mayrose.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

//@EntityListeners(value = MyEntityListener.class)
// @EntityListeners( value = AuditingEntityListener.class )
// MyEntityListener는 Auditable 이라는 객체가 던져지면 createdAt, updatedAt에  값을 채운다.
public class UserHistory extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String name;
    private String email;

    /*
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    */
}
