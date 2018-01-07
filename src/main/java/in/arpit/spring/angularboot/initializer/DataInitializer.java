package in.arpit.spring.angularboot.initializer;

import com.google.common.collect.Lists;
import in.arpit.spring.angularboot.entity.Note;
import in.arpit.spring.angularboot.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private NotesRepository repository;

    public DataInitializer(@Autowired NotesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> strings = Lists.newArrayList("Note 1", "Note 2", "Note 3");
        strings.forEach(string -> repository.save(new Note(string,"user")));
        repository.findAll().forEach(System.out::println);

    }
}
