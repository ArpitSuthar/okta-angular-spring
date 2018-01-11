package in.arpit.spring.angularboot.controller;

import com.google.common.collect.Lists;
import in.arpit.spring.angularboot.entity.Note;
import in.arpit.spring.angularboot.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

import java.security.Principal;
import java.util.List;

@RestController
public class HomeController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OAuth2ClientContext clientContext;

    @Autowired
    private NotesRepository repository;

    @GetMapping("/note")
    @PreAuthorize("hasAuthority('Everyone')")
    public List<Note> home(Principal principal){
        LOGGER.info(principal.getName());
        LOGGER.info(((OAuth2Authentication) principal).getUserAuthentication().getAuthorities().toString());
        LOGGER.info(String.valueOf(((OAuth2Authentication) principal).isAuthenticated()));
        LOGGER.info(String.valueOf(clientContext.getAccessToken().isExpired()));
        List<Note> notes = repository.findAll();
        if (notes.isEmpty()) {
            return Lists.newArrayList();
        } else {
            return notes;
        }
    }
}
