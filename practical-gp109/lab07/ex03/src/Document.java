

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Document {

    private String name;
    private String content;

    public Document(String name) {
        this.name = name;
        this.content = readContentFromFile(name);
    }

    private String readContentFromFile(String name) {
        StringBuilder sb = new StringBuilder();
        String filePath = name; // Altere o caminho conforme necessário
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Lida com possíveis erros de leitura do arquivo
        }
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }
    
}
