package db.bookstore;

import Database.Models.Book;
import Database.Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    public static User currentUser;
    public static List<Book> userCart = new ArrayList<>();
}
