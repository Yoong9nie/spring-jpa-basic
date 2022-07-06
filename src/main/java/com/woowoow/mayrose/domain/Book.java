package com.woowoow.mayrose.domain;

import com.woowoow.mayrose.domain.listener.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
//@EntityListeners( value = MyEntityListener.class )
@EntityListeners( value = AuditingEntityListener.class )
public class Book implements Auditable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*
    @PrePersist
    public void PrePersist() {
        System.out.println(">>>>  PrePersist 호출 ");
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate() {
        System.out.println(">>>>  PreUpdate 호출 ");
        this.updatedAt = LocalDateTime.now();
    }
   */
}
