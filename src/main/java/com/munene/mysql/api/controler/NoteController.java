package com.munene.mysql.api.controler;

import com.munene.mysql.api.exception.RsourceNotFoundException;
import com.munene.mysql.api.model.Note;
import com.munene.mysql.api.repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")

public class NoteController {
	@Autowired
	NoteRepository noteRepository;
	
    // Get All Notes calls JpaRepository’s findAll() method to retrieve all the notes from the database and returns the entire list
	
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
	    return noteRepository.findAll();
	}
    // Create a new Note 
	//bound with the method parameter
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
	    return noteRepository.save(note);
	}

    // Get a Single Note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
	    return noteRepository.findById(noteId)
	            .orElseThrow(() -> new RsourceNotFoundException("Note", "id", noteId));
	}
    // Update a Note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId,
	                                        @Valid @RequestBody Note noteDetails) {

	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new RsourceNotFoundException("Note", "id", noteId));
	    		
	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());

	    Note updatedNote = noteRepository.save(note);
	    return updatedNote;
	}
    // Delete a Note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new RsourceNotFoundException("Note", "id", noteId));

	    noteRepository.delete(note);

	    return ResponseEntity.ok().build();
	}
}
