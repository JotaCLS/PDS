import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criação de contatos estáticos
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Doe", "123456789"));
        contacts.add(new Contact("Jane Doe", "987654321"));

        // Salvando contatos em formato texto
        ContactsStorageInterface textStorage = new TextContactsStorage("contacts.txt");
        textStorage.saveContacts(contacts);

        // Salvando contatos em formato binário
        ContactsStorageInterface binaryStorage = new BinaryContactsStorage("contacts.bin");
        binaryStorage.saveContacts(contacts);

        // Carregando e manipulando contatos
        ContactsManager manager = new ContactsManager(textStorage);
        manager.openAndLoad(textStorage);

        // Adicionando um novo contato
        Contact newContact = new Contact("Alice", "1122334455");
        manager.add(newContact);
        System.out.println("Contato adicionado: " + newContact.getName());

        // Salvando e fechando
        manager.saveAndClose();
    }
}
