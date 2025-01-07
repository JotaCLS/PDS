
public class main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(new StringBuilder());
        CommandInterface add = new Add(textEditor, "Hello");
        CommandInterface add2 = new Add(textEditor, " World");

        add.execute();
        add2.execute();

        System.out.println(textEditor.getText());

        CommandInterface remove = new Remove(textEditor, " World");

        remove.execute();

        System.out.println(textEditor.getText());

    }

}
