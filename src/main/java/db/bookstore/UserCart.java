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

        inStock.put(isbn, book.getStock());
        bookData.put(isbn, book);
        if (inCart.containsKey(isbn)) {
            if (Objects.equals(inCart.get(isbn), inStock.get(isbn)))
                return false;
            inCart.put(isbn, inCart.get(isbn) + 1);
        } else
            inCart.put(isbn, 1);
        return true;
    }

    public List<Book> getCartList(){
        List<Book> cartBooks = new ArrayList<>() ;
        for(Book book : bookData.values()){
            book.setStock(inCart.get(book.getIsbn()));
            cartBooks.add(book);
        }
        return cartBooks;
    }
}
