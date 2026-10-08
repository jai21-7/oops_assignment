package exceptions.partA.q08;

/*
 * Lab: accept a password; throw AuthenticationFailure if it is wrong.
 */

class AuthenticationFailure extends Exception {
    AuthenticationFailure(String message) {
        super(message);
    }
}

public class Q08_PasswordAuth {
    static final String CORRECT = "java123";

    static void login(String password) throws AuthenticationFailure {
        if (!CORRECT.equals(password)) {
            throw new AuthenticationFailure("Authentication failure: wrong password.");
        }
        System.out.println("Login successful.");
    }

    public static void main(String[] args) {
        try {
            login("java123");
            login("wrong");
        } catch (AuthenticationFailure e) {
            System.out.println(e.getMessage());
        }
    }
}
