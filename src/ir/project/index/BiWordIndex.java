package ir.project.index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class BiWordIndex {

    private Map<String, Set<String>> biWordIndex;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Bi-Word Index Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create GUI components
        JTextArea queryTextArea = new JTextArea();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = queryTextArea.getText();
                if (query.isEmpty()) {
                    displayErrorMessage("Query cannot be empty.");
                } else {
                    try {
                        BiWordIndex biWordIndex = new BiWordIndex();
                        biWordIndex.generateBiWordIndex(getFileList());
                        Set<String> queryResult = biWordIndex.queryBiWordIndex(query);
                        displayQueryResult(query, queryResult);
                    } catch (IllegalArgumentException ex) {
                        displayErrorMessage(ex.getMessage());
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(queryTextArea), BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);

        int width = 400;
        int height = 300;
        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static List<File> getFileList() {
        // Modify this method to return the list of files to be indexed
        File file1 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt");
        File file2 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_2.txt");
        File file3 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_3.txt");

        List<File> fileList;
        fileList = Arrays.asList(file1, file2, file3);

        return fileList;
    }

    public void generateBiWordIndex(List<File> files) {
        biWordIndex = new HashMap<>();

        for (File file : files) {
            try (Scanner scanner = new Scanner(file)) {
                Set<String> fileWords = new HashSet<>();
                while (scanner.hasNext()) {
                    String currentWord = scanner.next().toLowerCase();
                    fileWords.add(currentWord);
                }

                for (String word : fileWords) {
                    for (String otherWord : fileWords) {
                        if (!word.equals(otherWord)) {
                            String biWord = word + " " + otherWord;
                            if (!biWordIndex.containsKey(biWord)) {
                                biWordIndex.put(biWord, new HashSet<>());
                            }
                            biWordIndex.get(biWord).add(file.getName());
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<String> queryBiWordIndex(String query) throws IllegalArgumentException {
        Set<String> result = new HashSet<>();

        String[] queryWords = query.toLowerCase().split("\\s+");
        if (queryWords.length < 2) {
            throw new IllegalArgumentException("Query must consist of at least two words.");
        }

        for (String biWord : biWordIndex.keySet()) {
            boolean match = true;
            for (String word : queryWords) {
                if (!biWord.contains(word)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.addAll(biWordIndex.get(biWord));
            }
        }

        return result;
    }

    private static void displayQueryResult(String query, Set<String> queryResult) {
        String title = "Query Result";
        StringBuilder content = new StringBuilder();
        content.append("Query: ").append(query).append("\n\n");

        if (queryResult.isEmpty()) {
            content.append("No matching results found.");
        } else {
            content.append("Matching files:\n");
            for (String file : queryResult) {
                content.append(file).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, content.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayErrorMessage(String errorMessage) {
        String title = "Error";
        JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
    }
}

