package ir.project.index;


 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class PositionalIndex {

    private Map<String, Map<String, List<Integer>>> positionalIndex;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Positional Index Search");
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
                        PositionalIndex positionalIndex = new PositionalIndex();
                        positionalIndex.generatePositionalIndex(getFileList());
                        List<String> queryResult = positionalIndex.queryPositionalIndex(query);
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
        
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        return fileList;
    }

    public void generatePositionalIndex(List<File> files) {
        positionalIndex = new HashMap<>();

        for (File file : files) {
            try (Scanner scanner = new Scanner(file)) {
                int position = 1;
                while (scanner.hasNext()) {
                    String currentWord = scanner.next().toLowerCase();
                    if (!positionalIndex.containsKey(currentWord)) {
                        positionalIndex.put(currentWord, new HashMap<>());
                    }
                    Map<String, List<Integer>> filePositions = positionalIndex.get(currentWord);
                    if (!filePositions.containsKey(file.getName())) {
                        filePositions.put(file.getName(), new ArrayList<>());
                    }
                    List<Integer> positions = filePositions.get(file.getName());
                    positions.add(position);
                    position++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> queryPositionalIndex(String query) throws IllegalArgumentException {
        List<String> result = new ArrayList<>();

        String[] queryWords = query.toLowerCase().split("\\s+");
        if (queryWords.length < 2) {
            throw new IllegalArgumentException("Query must consist of at least 2 words.");
        }

        Map<String, List<Integer>> firstWordPositions = positionalIndex.get(queryWords[0]);
        if (firstWordPositions == null) {
            return result;
        }

        for (Map.Entry<String, List<Integer>> entry : firstWordPositions.entrySet()) {
            String fileName = entry.getKey();
            List<Integer> firstWordPosList = entry.getValue();
            List<List<Integer>> otherWordPosLists = new ArrayList<>();

            for (int i = 1; i < queryWords.length; i++) {
                Map<String, List<Integer>> wordPositions = positionalIndex.get(queryWords[i]);
                if (wordPositions == null || !wordPositions.containsKey(fileName)) {
                    return result;
                }
                otherWordPosLists.add(wordPositions.get(fileName));
            }

            for (int pos : firstWordPosList) {
                boolean match = true;
                for (List<Integer> otherPosList : otherWordPosLists) {
                    if (!otherPosList.contains(pos + 1)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    result.add(fileName);
                    break;
                }
            }
        }

        return result;
    }

//    public List<String> queryPositionalIndex(String query) throws IllegalArgumentException {
//    List<String> result = new ArrayList<>();
//
//    String[] queryWords = query.toLowerCase().split("\\s+");
//    if (queryWords.length < 2) {
//        throw new IllegalArgumentException("Query must consist of at least 2 words.");
//    }
//
//    for (Map.Entry<String, Map<String, List<Integer>>> entry : positionalIndex.entrySet()) {
//        String fileName = entry.getKey();
//        Map<String, List<Integer>> filePositions = entry.getValue();
//
//        List<Integer> firstWordPositions = filePositions.get(queryWords[0]);
//        if (firstWordPositions == null) {
//            continue; // This file doesn't contain the first word, move to the next file
//        }
//
//        boolean allWordsFound = true;
//        for (int i = 1; i < queryWords.length; i++) {
//            List<Integer> otherWordPositions = filePositions.get(queryWords[i]);
//            if (otherWordPositions == null) {
//                allWordsFound = false;
//                break; // This file doesn't contain one of the query words, move to the next file
//            }
//
//            boolean found = false;
//            for (int pos : firstWordPositions) {
//                if (otherWordPositions.contains(pos + 1)) {
//                    found = true;
//                    break; // Move to the next query word
//                }
//            }
//            if (!found) {
//                allWordsFound = false;
//                break; // One of the query words doesn't follow the first word, move to the next file
//            }
//        }
//
//        if (allWordsFound) {
//            result.add(fileName);
//        }
//    }
//
//    return result;
//}

    private static void displayQueryResult(String query, List<String> queryResult) {
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
        String title= "Error";
        JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
    }
}