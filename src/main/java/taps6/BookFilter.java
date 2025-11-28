package taps6;

public interface BookFilter {
    boolean filter(Library<?>.Book book);
}
