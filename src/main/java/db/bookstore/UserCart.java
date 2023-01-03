package db.bookstore;

import Database.Models.Book;

import java.util.*;

public class UserCart {
    Map<String, Integer> inStock;
    Map<String, Integer> inCart;
    Map<String, Book> bookData;

    public UserCart() {
        this.inStock = new HashMap<>();
        this.inCart = new HashMap<>();
        this.bookData = new HashMap<>();
    }

    public boolean addBook(Book book) {
        String isbn = book.getIsbn();

        if (inCart.containsKey(isbn)) {
            if (Objects.equals(inCart.get(isbn), inStock.get(isbn)))
                return false;
            inCart.put(isbn, inCart.get(isbn) + 1);
        } else {
            inCart.put(isbn, 1);
            inStock.put(isbn, book.getStock());
            bookData.put(isbn, book);
        }
        return true;
    }

    public void removeBook(Book book) {
        String isbn = book.getIsbn();
        int cart = inCart.get(isbn);
        if (cart == 1) {
            inCart.remove(isbn);
            inStock.remove(isbn);
            bookData.remove(isbn);
        } else
            inCart.put(isbn, cart - 1);
    }

    public Float getCartPrice() {
        float total = 0;
        for (Book book : bookData.values()) {
            total += (inCart.get(book.getIsbn())) * book.getPrice();
        }
        return total;
    }

    public List<Book> getCartList() {
        List<Book> cartBooks = new ArrayList<>();
        for (Book book : bookData.values()) {
            book.setStock(inCart.get(book.getIsbn()));
            cartBooks.add(book);
        }
        return cartBooks;
    }

    public void emptyCart() {
        inCart = new HashMap<>();
        inStock = new HashMap<>();
        bookData = new HashMap<>();
    }
}
