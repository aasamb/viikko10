package com.example.viikko10ver2;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserStorage {

    private final String FILENAME = "users.data";

    private ArrayList<User> users = new ArrayList<>();

    private static UserStorage singleton = null;

    private UserStorage()   {
    }

    public static UserStorage getInstance() {
        if (singleton == null)  {
            singleton = new UserStorage();
        }
        return singleton;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user)  {
        users.add(user);
        //users.sort((o1, o2) -> o1.getLastName().compareToIgnoreCase(o2.getLastName()));
        Collections.sort(users, Comparator.comparing(User::getLastName).thenComparing(User::getFirstName));
    }

    public void removeUser(int id)  {
        users.remove(id);
    }

    public void saveUsers(Context context)  {
        try {
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            userWriter.writeObject(users);
            System.out.println("Käyttäjä kirjoitettu tiedstoon.");
            userWriter.close();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Virhe tiedostoon kirjoitettaessa.");
            e.printStackTrace();
        }
    }

    public void loadUsers(Context context)  {
        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput(FILENAME));
            users = (ArrayList<User>) userReader.readObject();
            userReader.close();
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println("Tiedostoa ei löydy.");
            e.printStackTrace();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Virhe tiedoston lukemisessa.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println("Tiedoston data väärässä formaatissa.");
            e.printStackTrace();
        }
    }

}


