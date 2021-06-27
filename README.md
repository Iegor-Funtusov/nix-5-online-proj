>> mvn clean install

>> java -jar target\ionio.jar

File with book and file with authors are linked:

-- when we add new book, authors of this book will be created in authors file too and vice versa.

--when work with book, updating is possible only for name of book. When work with authors, it is possible update names of book and authors (if author has some books,
in file with books all ones will have new author). Of course, you can't update book or author if such one already exists.

--If delete book, it will be invisivle in book file, but for author such book will exist. If remove author, all books and author will be deleted (BUT: if book has some authors, it will be invisible in case all authors of this book are "deleted")
