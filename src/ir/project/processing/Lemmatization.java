package ir.project.processing;
        
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lemmatization extends JFrame {
    private JTextArea textArea;

    public Lemmatization() {
        super("Verb To Be Lemmatizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);

        // Create GUI components
        JButton processButton = new JButton("Process");
        textArea = new JTextArea();

        // Set layout
        JPanel panel = new JPanel();
        panel.add(processButton);
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Add action listener to processButton
        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processFiles();
            }
        });
    }

    private void processFiles() {
        String filePath = "E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt"; // Replace with the actual file path
        File file = new File(filePath);

        if (file.exists()) {
            List<String> lemmatizedSentences = processFile(file);
            displayLemmatizedSentences(lemmatizedSentences);
        } else {
            JOptionPane.showMessageDialog(this, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<String> processFile(File file) {
        List<String> lemmatizedSentences = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lemmatizedSentence = lemmatizeVerbsToBe(line);
                lemmatizedSentences.add(lemmatizedSentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lemmatizedSentences;
    }

    private String lemmatizeVerbsToBe(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder lemmatizedSentence = new StringBuilder();

        for (String word : words) {
            if (word.equalsIgnoreCase("am") || word.equalsIgnoreCase("are") || word.equalsIgnoreCase("is") ||
                    word.equalsIgnoreCase("was") || word.equalsIgnoreCase("were")) {
                lemmatizedSentence.append("be");
            } else {
                lemmatizedSentence.append(word);
            }
            lemmatizedSentence.append(" ");
        }

        return lemmatizedSentence.toString().trim();
    }

    private void displayLemmatizedSentences(List<String> lemmatizedSentences) {
        StringBuilder sb = new StringBuilder();
        for (String sentence : lemmatizedSentences) {
            sb.append(sentence).append("\n");
        }
        textArea.setText(sb.toString());
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                Lemmatization gui = new Lemmatization();
//                gui.setVisible(true);
//            }
//        });
//    }
}