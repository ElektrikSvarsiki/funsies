package taps12.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class Book {
    private String title;
    public Book(String title) { this.title = title; }
    public String getTitle() { return title; }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Iterator<Book> iterator() {
        return new Iterator<>() {
            private int current = 0;

            public boolean hasNext() {
                return current < books.size();
            }

            public Book next() {
                if (!hasNext()) throw new NoSuchElementException();
                return books.get(current++);
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Book A"));
        library.addBook(new Book("Book B"));
        library.addBook(new Book("Book C"));

        Iterator<Book> it = library.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().getTitle());
        }
    }

    final class finalbook {

    }
}
