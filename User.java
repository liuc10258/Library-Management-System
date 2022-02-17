public class User{
    public String firstName;
    public String surname;
    public int numberOfBook;

    public User(String firstName, String surname, int numberOfBook) {
        this.firstName = firstName;
        this.surname = surname;
        this.numberOfBook = numberOfBook;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    //Set the number of books +1 after successful borrowing
    public void bNumberOfBook() {
        this.numberOfBook += 1;
    }

    //Set the number of books -1 after successful returning
    public void rNumberOfBook() {
        this.numberOfBook -= 1;
    }

    @Override
    public String toString() {
        return firstName + ' ' + surname;
    }

}


