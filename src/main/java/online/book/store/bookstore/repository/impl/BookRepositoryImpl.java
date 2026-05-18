package online.book.store.bookstore.repository.impl;

import java.util.List;
import java.util.Optional;
import online.book.store.bookstore.exception.DataProcessingException;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error saving book into DB " + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT b FROM Book b",Book.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error fetching all books from DB", e);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Book book = session.createQuery(
                            "SELECT b FROM Book b WHERE b.id = :id",
                            Book.class)
                    .setParameter("id", id)
                    .uniqueResult();

            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new DataProcessingException("Error fetching book by id from DB", e);
        }
    }
}
