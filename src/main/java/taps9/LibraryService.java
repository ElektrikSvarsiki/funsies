package taps9;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private int year;
    private double rating;
    private boolean isAvailable;

    public Book(String title, String author, int year, double rating, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", year=" + year + ", rating=" + rating + ", isAvailable=" + isAvailable + '}';
    }
}

class BorrowRecord {
    private Book book;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;

    public BorrowRecord(Book book, LocalDate borrowedDate, LocalDate returnedDate) {
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public Book getBook() { return book; }
    public LocalDate getBorrowedDate() { return borrowedDate; }
    public LocalDate getReturnedDate() { return returnedDate; }

    @Override
    public String toString() {
        return "BorrowRecord{" + "book=" + book + ", borrowedDate=" + borrowedDate + ", returnedDate=" + returnedDate + '}';
    }
}

class User {
    private String name;
    private int age;
    private List<BorrowRecord> borrowHistory;

    public User(String name, int age, List<BorrowRecord> borrowHistory) {
        this.name = name;
        this.age = age;
        this.borrowHistory = borrowHistory == null ? new ArrayList<>() : borrowHistory;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + ", borrowHistory=" + borrowHistory + '}';
    }
}

public class LibraryService {
    private final List<Book> books;

    public LibraryService(List<Book> books) {
        this.books = books == null ? Collections.emptyList() : books;
    }

    public Optional<Book> findRecommendedBookForUser(User user) {
        if (user == null) return Optional.empty();
        List<BorrowRecord> history = user.getBorrowHistory();
        if (history == null || history.isEmpty()) return Optional.empty();
        Map<String, Long> authorCount = history.stream()
                .map(BorrowRecord::getBook)
                .filter(Objects::nonNull)
                .map(Book::getAuthor)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (authorCount.isEmpty()) return Optional.empty();
        String topAuthor = authorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        if (topAuthor == null) return Optional.empty();
        return books.stream()
                .filter(b -> topAuthor.equals(b.getAuthor()))
                .max(Comparator.comparingDouble(Book::getRating));
    }

    public static Optional<User> findTopReaderOfMonth(List<User> users, int month, int year) {
        if (users == null || users.isEmpty()) return Optional.empty();
        YearMonth ym;
        try { ym = YearMonth.of(year, month); } catch (Exception e) { return Optional.empty(); }
        return users.stream()
                .map(u -> new AbstractMap.SimpleEntry<>(u, u.getBorrowHistory().stream()
                        .map(BorrowRecord::getBorrowedDate)
                        .filter(Objects::nonNull)
                        .filter(d -> YearMonth.from(d).equals(ym))
                        .count()))
                .filter(e -> e.getValue() > 0)
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public double averageRating() {
        return books.stream().mapToDouble(Book::getRating).average().orElse(0.0);
    }

    public List<Book> availableAfterYear(int year) {
        return books.stream().filter(b -> b.getYear() > year && b.isAvailable()).collect(Collectors.toList());
    }

    public static String mostFrequentlyBorrowedTitle(List<User> users) {
        return users.stream()
                .flatMap(u -> u.getBorrowHistory().stream())
                .map(BorrowRecord::getBook)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Book::getTitle, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static Map<String, List<Book>> currentlyReadingPerUser(List<User> users) {
        return users.stream()
                .collect(Collectors.toMap(User::getName,
                        u -> u.getBorrowHistory().stream()
                                .filter(r -> r.getReturnedDate() == null)
                                .map(BorrowRecord::getBook)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList())));
    }

    public Map<String, List<Book>> groupBooksByAuthorAfter1950() {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor,
                        Collectors.filtering(b -> b.getYear() > 1950, Collectors.toList())));
    }

    public static Set<String> uniqueAuthorsReadByUsers(List<User> users) {
        return users.stream()
                .flatMap(u -> u.getBorrowHistory().stream())
                .map(r -> r.getBook().getAuthor())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public static Comparator<Book> ratingYearTitleComparator() {
        return Comparator
                .comparing((Book b) -> b.getRating())
                .thenComparingInt(Book::getYear)
                .thenComparing(Book::getTitle)
                .reversed();
    }
}
