package com.woowoow.mayrose.domain;

import com.woowoow.mayrose.domain.listener.Auditable;
import com.woowoow.mayrose.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@Getter
//@Setter
//@ToString
@NoArgsConstructor          // JPA에서는 인자 없는 constructor가 반드시 필요함.
@AllArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode          // JPA에서 많이 쓸 일은 없지만, 권고하고 있음.  Delombok 해서 생성되는 코드를  확인해 보자.
@Builder
@Entity
//@EntityListeners( value = { MyEntityListener.class, UserEntityListener.class })
//@EntityListeners( value = { AuditingEntityListener.class, UserEntityListener.class })
// AuditingEntityListener.class 는 BaseEntity class에서 처리함.
@EntityListeners( value = { UserEntityListener.class })
// @Table( name = "User", indexes = {@Index(columnList="name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    //@Column(unique = true, length = 255)
    private String email;

    // 0, 1, 2 Enum의 순서에 따라서 값이 달라지지 않도록 반드시 String 형으로 해야 한다.
    @Enumerated( value = EnumType.STRING)
    private Gender gender;

    //@Column(name = "crtdat")
    //@Column(nullable = false)
    //@Column(updatable = false)          //  update 구문에 set 구문에 들어가지 않음.
    //@CreatedDate                           // JpaAuditing 대상으로 함.
    //private LocalDateTime createdAt;

    //@Column(name = "uptdat")
    //@Column(nullable = false)
    //@Column(insertable = false )        //  insert시에  values 구문에 들어가지 않음.
    //@LastModifiedDate                     // JpaAuditing 대상으로 함.
    //private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    // DB 영속성 처리에서 제외한다.  칼럼도 생성하지 않고, SQL문에 나타나지 않게 하기.
    // 다만 객체에서만 사용한다.
    @Transient
    private String testData;
    // Getter and Setter 만들기
    // 1. 직접 코딩하기.
    // 2. IntelliJ에서 Generate 하기
    // 3. Lombok Anotation 붙이기.  ( 각각의 method에도 적용 가능, class Level에도 적용 가능 )
    //    @Getter, @Setter
    //    @Data
    //    Refactor --> Lombok, Delombok
    // 4. @ToString   --> 직접 toString() override 하게 되면 field추가 될 때마다 직접 toString() 함수를 수정해야만 할 것이야.
    // 5. 생성자 관련 Lombok Annotation
    //    NoArgsConstructor,
    //    AllArgsConstructor,
    //    RequiredArgsConstructor (@NonNull 이 있는 Field 대상)
    // 6. 전부 합쳐
    //    @Data =  @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
    // 7. @Builder
    //    빌드 패턴 지원함.

    // 8. Entity  ( DB로 저장할 수 있는 class 선언,  @Id가 필요함.  @GeneratedValue


    /* Entity Listener 정의....  별도의 EntityListener를 구현해서 @EntityListener로 지정함.
    @PrePersist
    public void PrePersist() {
        System.out.println(">>>>  PrePersist 호출 ");
        this.createdAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate() {
        System.out.println(">>>>  PreUpdate 호출 ");
        this.updatedAt = LocalDateTime.now();
    }

    @PreRemove
    public void PreRemove() {
        System.out.println(">>>>  PreRemove 호출 ");
    }

    @PostPersist
    public void PostPersist() {
        System.out.println(">>>>  PostPersist 호출 ");
    }

    @PostUpdate
    public void PostUpdate() {
        System.out.println(">>>>  PostUpdate 호출 ");
    }


    @PostRemove
    public void PostRemove() {
        System.out.println(">>>>  PostRemove 호출 ");
    }

    @PostLoad
    public void PostLoad() {
        System.out.println(">>>>  PostLoad 호출 ");
    }
    */
}
