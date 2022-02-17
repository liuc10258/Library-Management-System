import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SortedArrayList<Object> extends ArrayList<Object> {
    //The constructor
    public ArrayList<Book> library = new ArrayList<>();

    public Book getBook(int index) { return library.get(index); }

    public int getSize() { return library.size(); }

    //The constructor
    public ArrayList<User> users = new ArrayList<>();

    public User getUser(int index) { return users.get(index); }

    public int getUSize() { return users.size(); }

    //Check if someone is trying to borrow the fourth book
    public boolean checkNOBMax(int numberOfBook) { return numberOfBook + 1 <= 3; }

    //Check if someone is trying to return a book when he/she haven't borrowed any book
    public boolean checkNOBMin(int numberOfBook) { return numberOfBook - 1 >= 0; }

    //Sorted Insertion of Books
    public void addSomeBooks(Book book) {
        if (library.isEmpty()) {//Insert directly if the library is empty
            library.add(0, book);
        } else {
            for (int ii = 0; ii < library.size(); ii++) {//Traversing the library
                //Compare the author name in the data to the author in the ArrayList
                if (getBook(ii).getAuthor().compareTo(book.author) < 0) {
                    //Iterate through the library for author comparison
                    while (ii + 1 < library.size() && getBook(ii + 1).getAuthor().compareTo(book.author) < 0) {
                        ii += 1;
                    }
                    library.add(ii + 1, book);
                    break;
                } else {
                    library.add(ii, book);
                    break;
                }
            }
        }
    }

    //Sorted Insertion of Users
    public void addSomeUsers(User user) {
        if (users.isEmpty()) {//Insert directly if the users is empty
            users.add(0, user);
        } else {
            for (int j = 0; j < users.size(); j++) {//Traversing the users
                //Compare the surname in the data to the surname in the ArrayList
                if (getUser(j).getSurname().compareTo(user.surname) < 0) {
                    //Iterate through the users for author comparison
                    while (j + 1 < users.size() && getUser(j).getSurname().compareTo(user.surname) < 0) {
                        j += 1;
                    }
                    users.add(j + 1, user);
                    break;
                } else {
                    //Determine if the user has the same surname
                    if ((getUser(j).getSurname().equals(user.surname))) {
                        //Compare the first name if the user has the same surname
                        if (getUser(j).getFirstName().compareTo(user.firstName) < 0) {
                            users.add(j + 1, user);
                            break;
                        } else {
                            users.add(j, user);
                            break;
                        }
                    }
                    users.add(j, user);
                    break;
                }
            }
        }
    }

    //Read data from a Txt file
    public ArrayList<java.lang.String> readTxt(BufferedReader br) {
        ArrayList<java.lang.String> records = new ArrayList<>();
        Scanner Sc = new Scanner(br);
        //Read file data by line
        while (Sc.hasNextLine()) {
            records.add(Sc.nextLine());
        }
        return records;
    }

    //Adds the read Book data to ArrayList<Book>
    public void parserBook(ArrayList<java.lang.String> records) {
        //Crop off the part of the User information
        records.remove(0);
        int q = records.size();
        for (int j = records.indexOf("4") + 1; j < q; j++) {
            records.remove(records.indexOf("4") + 1);
        }
        records.remove(records.indexOf("4"));
        //Adds the trimmed ArrayList assignment to ArrayList<Book>
        for (java.lang.String line : records) {
            java.lang.String[] elements = line.split(",");
            addSomeBooks(new Book(elements[0], elements[1], false, null));
        }
    }

    //Adds the read User data to ArrayList<User>
    public void parserUser(ArrayList<java.lang.String> records) {
        //Crop off the part of the Book information
        records.remove(0);
        int q = records.size();
        for (int j = records.indexOf("4") + 1; j > 0; j--) {
            records.remove(0);
        }
        //Adds the trimmed ArrayList assignment to ArrayList<User>
        for (java.lang.String line : records) {
            java.lang.String[] elements = line.split(",");
            addSomeUsers(new User(elements[0], elements[1], 0));
        }
    }

    //Add a new Book to the book list
    public void addABook(){
        System.out.println("Add a book to the library!");
        Scanner aab = new Scanner(System.in);
        System.out.println("Please input the name of the book");
        java.lang.String bookName = aab.nextLine();
        System.out.println("Please input the author of the book");
        java.lang.String authorName = aab.nextLine();
        Book oneBook = new Book(bookName,authorName,false,null);
        for (int t = 0; t <getSize();t++){
            if (!getBook(t).getAuthor().equals(authorName)&&getBook(t).getName().equals(bookName)){
                addSomeBooks(oneBook);
                break;
            }
            System.out.println("This book is already exist!");
            return;
        }
        return;
    }

    //Delete a Book from the book list
    public void removeABook(){
        System.out.println("Remove a book from the library!");
        Scanner rab = new Scanner(System.in);
        System.out.println("Please input the name of the book");
        java.lang.String bookName = rab.nextLine();
        System.out.println("Please input the author of the book");
        java.lang.String authorName = rab.nextLine();
        for (int e = 0; e < getSize();e++){
            if(getBook(e).getName().equals(bookName)&getBook(e).getAuthor().equals(authorName)){
                library.remove(e);
                break;
            }
        }
    }

}