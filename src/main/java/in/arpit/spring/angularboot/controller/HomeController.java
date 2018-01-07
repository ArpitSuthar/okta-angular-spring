package in.arpit.spring.angularboot.controller;

import com.google.common.collect.Lists;
import in.arpit.spring.angularboot.entity.Note;
import in.arpit.spring.angularboot.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private NotesRepository repository;

    @GetMapping("/note")
    public List<Note> home(Principal principal){
        List<Note> notes = repository.findAllByUser(principal.getName());
        if (notes.isEmpty()) {
            return Lists.newArrayList();
        } else {
            return notes;
        }
    }
}
