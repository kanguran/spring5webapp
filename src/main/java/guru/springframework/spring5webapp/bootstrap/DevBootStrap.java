package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public DevBootStrap (AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        Publisher andresPublisher = new Publisher("Publisher", "Addres11");
        publisherRepository.save(andresPublisher);

        Author andres = new Author("Andres", "Kangur");
        Book andresBook = new Book ("My Spring POJO", "34532534534", andresPublisher );

        andres.getBooks().add(andresBook);
        andresBook.getAuthors().add(andres);

        authorRepository.save(andres);
        bookRepository.save(andresBook);

        Publisher levPublisher = new Publisher("Lev's Publisher", "Lev addres");
        publisherRepository.save(levPublisher);

        Author lev = new Author("Lev", "Tolstoj");
        Book levBook = new Book ("Были", "978-5-9268-1948-6", levPublisher);

        lev.getBooks().add(levBook);

        authorRepository.save(lev);
        bookRepository.save(levBook);
    }
}
