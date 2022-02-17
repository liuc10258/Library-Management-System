public class Book{
    public String name;
    public String author;
    public boolean isBorrowed;
    public String whoBorrowed;

    public Book(String name, String author, boolean isBorrowed, String whoBorrowed) {
        this.name = name;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.whoBorrowed = whoBorrowed;
    }
    //Get book names
    public String getName() {
        return name;
    }

    //Get author names
    public String getAuthor() {
        return author;
    }

    //Set the book borrowing status
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    //Get the book borrowing status
    public boolean getBorrowed() {
        return isBorrowed;
    }

    //Get the borrower
    public String getWhoBorrowed() {
        return whoBorrowed;
    }

    //Add the borrower
    public String addWhoBorrowed(String firstName, String surname) {
        return whoBorrowed = firstName + " " + surname;
    }

    //Empty the borrower
    public String clearWhoBorrowed() {
        return whoBorrowed = null;
    }

    @Override
    public String toString() {
        return name + '\n' +
                author;
    }

}

