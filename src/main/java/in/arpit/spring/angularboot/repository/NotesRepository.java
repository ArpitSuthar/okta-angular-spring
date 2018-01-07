package in.arpit.spring.angularboot.repository;

import in.arpit.spring.angularboot.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note,Long> {
    public List<Note> findAllByUser(String user);
}
