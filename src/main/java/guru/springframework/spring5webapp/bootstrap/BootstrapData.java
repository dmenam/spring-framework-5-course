package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Publisher
        Publisher publisher = new Publisher();
        publisher.setName("John");
        publisher.setCity("Guadalajara");
        publisher.setAddressLine1("La paz 6543");

        //Save Publisher entities in database
        publisherRepository.save(publisher);

        //Create book and author
        Book book1 = new Book("Librito 1", "68453168735");
        Author author1 = new Author("David","Mena");

        //Relate author to book
        author1.getBooks().add(book1);

        //Relate book to author
        book1.getAuthors().add(author1);

        //Relate book to publisher
        book1.setPublisher(publisher);

        //Relate publisher to book
        publisher.getBooks().add(book1);

        //Create book and author
        Book book2 = new Book("Librito 2", "68713187616");
        Author author2 = new Author("Sara","Mena");

        //Relate author to book
        author2.getBooks().add(book2);

        //Relate book to author
        book2.getAuthors().add(author2);

        //Relate book to publisher
        book2.setPublisher(publisher);

        //Relate publisher to book
        publisher.getBooks().add(book2);

        //Save Author entities in database
        authorRepository.save(author1);
        authorRepository.save(author2);

        //Save Book entities in database
        bookRepository.save(book1);
        bookRepository.save(book2);

        //Save Publisher entities in database
        publisherRepository.save(publisher);


        //Output
        System.out.println("Starting App");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publisher books: " + publisher.getBooks().size());
    }
}
