import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryContactsStorage implements ContactsStorageInterface {
    private String filePath;

    public BinaryContactsStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            contacts = (List<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
