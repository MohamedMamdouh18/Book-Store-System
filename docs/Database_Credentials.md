## Users and Permissions
### Manager
- username: Manager
- password: 123456789
- permissions: book, book_author, order, user, sales

### Customer
- username: Customer
- password: 123456
- permissions: book, book_author, user

### Why we do that
- to ensure that each user can access only his/her granted tables with its granted operations and deny any illegal access using SQL injection.
- the image below illustrate how this is applicable, all what you should do from the controller side is to connect using the user type credentials.

![image](https://user-images.githubusercontent.com/73228199/209932092-fe2b011a-877d-480d-aced-4e5a421d1705.png)
