package online.book.store.bookstore;

import java.math.BigDecimal;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Book book = new Book();
            book.setIsbn("123456789");
            book.setAuthor("John Doe");
            book.setTitle("Book 1");
            book.setCoverImage("Cover Image");
            book.setDescription("Book Description");
            book.setPrice(BigDecimal.TEN);

            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
