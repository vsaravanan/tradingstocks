package com.hackerrank.stocktrade.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;
    private String name;

//    @OneToMany(mappedBy="user")
//    private Set< Trade > trades;
    
    public User() {
    }
    
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    

}
