package com.woowoow.mayrose.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
}
