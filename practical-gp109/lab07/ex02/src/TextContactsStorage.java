import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextContactsStorage implements ContactsStorageInterface {
    private String filePath;

    public TextContactsStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Contact> loadContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                contacts.add(new Contact(parts[0], parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Contact contact : list) {
                writer.println(contact.getName() + "\t" + contact.getNumber());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
