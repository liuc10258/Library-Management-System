import java.io.*;
import java.util.*;

public class IO {
    private final SortedArrayList s;
    IO() throws FileNotFoundException { this.s = new SortedArrayList(); }

    PrintWriter output = new PrintWriter("/Users/xxhh/Documents/MSc CS/CSC8012/CW/newnewnew/src/output.txt");

    //Borrow books operation
    public void BorrowABook(){
        //Handle the case where the library is empty
        if (s.library == null) {
            System.out.println("There is no book in the library!");
            output.println("There is no book in the library!\n");
            return;
        }
        System.out.println("Borrow a Book!");
        output.append("Borrow a Book!\n");
        //Gets the user's surname and first name entered
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your first name:");
        output.append("Please enter your first name:\n");
        java.lang.String firstName = in.nextLine();
        output.append(firstName + "\n");
        System.out.println("Please enter your surname:");
        output.append("Please enter your surname:\n");
        java.lang.String surname = in.nextLine();
        output.append(surname + "\n");
        int size = s.getSize();
        int sizeU = s.getUSize();
        //Find the user's registration information
        for (int iU = 0; iU < sizeU; iU++) {
            if (firstName.equals(s.getUser(iU).getFirstName()) && surname.equals(s.getUser(iU).getSurname())) {//Compare the first name and surname with registered information
                if (s.checkNOBMax(s.getUser(iU).numberOfBook)) {//Determine if the user has reached the borrowing limit
                    System.out.println("Please enter the full name of the book:");
                    output.append("Please enter the full name of the book:\n");
                    java.lang.String name = in.nextLine();
                    output.append(name + "\n");
                    //Look up the book
                    for (int i = 0; i < size; i++) {
                        if (name.equals(s.getBook(i).getName())) {  //Find the book that matches the id by book name
                            //Handle whether or not a book has been borrowed
                            if (s.getBook(i).getBorrowed()) {
                                System.out.println("Sorry,this book has been borrowed.");
                                output.append("Sorry,this book has been borrowed.\n");
                                System.out.println("Dear " + s.getBook(i).getWhoBorrowed() + ", please return the book: '" + s.getBook(i).getName() + "' as soon as possible,, there are other people who want to borrow this book at present.");
                                output.println("Dear " + s.getBook(i).getWhoBorrowed() + ", please return the book: '" + s.getBook(i).getName() + "' as soon as possible, there are other people who want to borrow this book at present.");
                            } else {
                                System.out.println("You have borrowed the book successfully!");
                                output.append("You have borrowed the book successfully!\n");
                                //Change the status of the book to borrowed and assign the name of the borrower to the parameter
                                s.getBook(i).setBorrowed(!s.getBook(i).getBorrowed());
                                s.getBook(i).addWhoBorrowed(firstName, surname);
                                s.getUser(iU).bNumberOfBook();//Add 1 to the number of books borrowed by the borrower
                            }
                            return;
                        }
                    }
                    System.out.println("Sorry, this book is not in the library.");
                    output.println("Sorry, this book is not in the library.\n");
                    return;
                }
                System.out.println("You have reached the maximum number of books you can borrow.\n Please return the books before you borrow any more.");
                output.println("You have reached the maximum number of books you can borrow.\n Please return the books before you borrow any more.\n");
                return;
            }
        }
        System.out.println("Sorry, your registration information can not be found.");
        output.println("Sorry, your registration information can not be found.\n");
        output.close();
        return;
    }

    //Return books operation
    public void ReturnABook(){
        System.out.println("Return a book!");
        output.append("Return a book!\n");
        //Gets the user's surname and first name entered
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your first name:");
        output.append("Please enter your first name:\n");
        java.lang.String firstName = in.nextLine();
        output.append(firstName + "\n");
        System.out.println("Please enter your surname:");
        output.append("Please enter your surname:\n");
        java.lang.String surname = in.nextLine();
        output.append(surname + "\n");
        int size = s.getSize();
        int sizeU = s.getUSize();
        //Find the user's registration information
        for (int iU = 0; iU < sizeU; iU++) {
            //Compare the first name and surname with registered information
            if (firstName.equals(s.getUser(iU).getFirstName()) && surname.equals(s.getUser(iU).getSurname())) {
                if (s.checkNOBMin(s.getUser(iU).numberOfBook)) {//Determine if the user has borrowed a book
                    System.out.println("Please enter the full name of the book:");
                    output.append("Please enter the full name of the book:\n");
                    java.lang.String name = in.nextLine();
                    output.append(name + "\n");
                    for (int i = 0; i < size; i++) {
                        if (name.equals(s.getBook(i).getName())) { //Find the book that matches the id by book name
                            //Handle whether or not a book has been returned
                            if (!s.getBook(i).getBorrowed()) {
                                System.out.println("Sorry,this book has been returned.");
                                output.append("Sorry,this book has been returned.\n");
                                output.close();
                            } else {
                                System.out.println("The book is returned successfully.");
                                output.append("The book is returned successfully.\n");
                                //Change the status of the book to borrowed and empty the name of the borrower to the parameter
                                s.getBook(i).setBorrowed(!s.getBook(i).getBorrowed());
                                s.getBook(i).clearWhoBorrowed();
                                s.getUser(iU).rNumberOfBook();//Subtract 1 to the number of books borrowed by the borrower
                                output.close();
                            }
                            output.close();
                            return;
                        }
                    }
                    System.out.println("Sorry, this book is not in the library.");
                    output.append("Sorry, this book is not in the library.\n");
                    return;
                }
                System.out.println("You have not borrowed any book, please borrow some before returning them.");
                output.append("You have not borrowed any book, please borrow some before returning them.\n");
                return;
            }
        }
        System.out.println("Sorry, your registration information can not be found.");
        output.append("Sorry, your registration information can not be found.\n");
        return;
    }

    //Menu of the System
    public void menu() throws FileNotFoundException {
        System.out.println("\nLibrary Management System");
        System.out.println("\n\t\tMain Menu");
        System.out.println("\tf. running the program.");
        System.out.println("\tb. display all the books");
        System.out.println("\tu. display all the users");
        System.out.println("\ti. Borrow a book");
        System.out.println("\tr. Return a book");
        System.out.println("\tab. Add a book");
        System.out.println("\trb. Remove a book");
        System.out.println("Please input a character of the options");
        output.println("\nLibrary Management System");
        output.println("\n\t\tMain Menu");
        output.println("\tf. running the program.");
        output.println("\tb. display all the books");
        output.println("\tu. display all the users");
        output.println("\ti. Borrow a book");
        output.println("\tr. Return a book");
        output.println("\tab. Add a book");
        output.println("\trb. Remove a book");
        output.println("Please input a character of the options");

        //Get the TXT file address to read the data
        File sampleBooks = new File("src/Samples");
        BufferedReader bR0 = new BufferedReader(new FileReader(sampleBooks));
        s.parserBook(s.readTxt(bR0));
        File sampleUsers = new File("src/Samples");
        BufferedReader bR1 = new BufferedReader(new FileReader(sampleUsers));
        s.parserUser(s.readTxt(bR1));

        String ch;
        Scanner k = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            do {
                while (!k.hasNextLine()) {
                    k.nextLine();
                }
                ch = k.nextLine();
                output.append("\n" + ch + "\n");
            } while (ch == null);
            switch (ch) {
                case "f": {
                    System.out.println("this program exited!");
                    output.println("\nthis program exited!");
                    flag = false;
                    output.close();
                    break;
                }
                case "b": {
                    System.out.println(s.library.size());
                    output.println(s.library.size());
                    s.library.stream().forEach(System.out::println);
                    s.library.stream().forEach(output::println);
                    break;
                }
                case "u": {
                    System.out.println(s.users.size());
                    output.println(s.users.size());
                    s.users.stream().forEach(System.out::println);
                    s.users.stream().forEach(output::println);
                    break;
                }
                case "i": {
                    BorrowABook();
                    break;
                }
                case "r": {
                    ReturnABook();
                    break;
                }
                case "ab": {
                    s.addABook();
                    s.library.stream().forEach(System.out::println);
                    break;
                }
                case "rb": {
                    s.removeABook();
                    s.library.stream().forEach(System.out::println);
                    break;
                }
                default: {
                    System.out.println("Not a valid input");
                    output.println("Not a valid input");
                    menu();
                    break;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new IO().menu();
    }
}
