import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextReader implements InterfaceTextReader{

    private String fileName; 
    
    public TextReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean hasNext() {
        Scanner file = readFile(fileName);
        return file.hasNext();
    }

    @Override
    public String next() {
        Scanner file = readFile(fileName);
        String result = "";

        while(file.hasNext()){
            result += file.nextLine() + "\n";
        }

        return result;
    }


    public Scanner readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            return fileScanner;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}