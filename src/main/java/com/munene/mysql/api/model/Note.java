package com.munene.mysql.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
//make the class persistent
@Entity
//provide details of the table the entity will be mapped to
@Table(name = "notes")

//enable auto provisioning
@EntityListeners(AuditingEntityListener.class)

//serialize to and from json
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)

public class Note implements Serializable{
	//define primary key
	@Id
	//define pkey generation strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    //define property of the column
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

//	public Object getTitle() {
//		// TODO Auto-generated method stub
//		return title;
//	}
//
//	public Object getContent() {
//		// TODO Auto-generated method stub
//		return content;
//	}
//
//	public void setTitle(Object title) {
//		// TODO Auto-generated method stub
//		return;
//	}
//
//	public void setContent(Object content2) {
//		// TODO Auto-generated method stub
//		
//	}
}
