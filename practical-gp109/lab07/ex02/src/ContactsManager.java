import java.util.ArrayList;
import java.util.List;

public class ContactsManager implements ContactsInterface {
    private List<Contact> contacts;
    private ContactsStorageInterface storage;

    public ContactsManager(ContactsStorageInterface storage) {
        this.storage = storage;
        this.contacts = new ArrayList<>();
    }

    @Override
    public void openAndLoad(ContactsStorageInterface store) {
        this.storage = store;
        this.contacts = store.loadContacts();
    }

    @Override
    public void saveAndClose() {
        saveAndClose(this.storage);
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(this.contacts);
        this.contacts.clear();
    }

    @Override
    public boolean exist(Contact contact) {
        return this.contacts.contains(contact);
    }

    @Override
    public Contact getByName(String name) {
        for (Contact contact : this.contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public boolean add(Contact contact) {
        if (!exist(contact)) {
            this.contacts.add(contact);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Contact contact) {
        return this.contacts.remove(contact);
    }
}
