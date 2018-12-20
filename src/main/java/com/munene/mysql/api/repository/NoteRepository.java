package com.munene.mysql.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munene.mysql.api.model.Note;


//boostrap jpa during component scan
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	
}
