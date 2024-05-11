package ir.project.processing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class StopWord extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, Integer> wordFrequency;

    public StopWord() {
        initializeGUI();
        wordFrequency = new HashMap<>();
    }

    private void initializeGUI() {
        setTitle("Stop Words");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Word");
        tableModel.addColumn("Collection Frequency");

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void countWordFrequency(List<File> files) {
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        if (!isStopWord(word)) {
                            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isStopWord(String word) {
        // Add your stop word logic here
        // Return true if the word is a stop word, false otherwise
        return false;
    }

    public void displayStopWords(int threshold) {
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();
            if (frequency > threshold) {
                tableModel.addRow(new Object[]{word, frequency});
            }
        }
    }
    
     public static List<File> getFileList() {
        // Modify this method to return your specific list of files
        File file1 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt");
        File file2 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_2.txt");
        File file3 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_3.txt");
        
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        return fileList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StopWord gui = new StopWord();
           
            gui.countWordFrequency(getFileList());
            int threshold = 10; // Set your desired threshold
            gui.displayStopWords(threshold);
            gui.setVisible(true);
        });
    }
}

//import static ir.project.processing.Normalization.normalize;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.AbstractSet;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class StopWord {
//    
//    public static List<String> getStopWordsByCollectionFreq(List<File> files, int threshold) {
//        Map<String, Integer> wordFreqMap = new HashMap<>();
//
//        // Step 1: Calculate collection frequencies
//        for (File file : files) {
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] words = line.split("\\s+");
//                    Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
//
//                    for (String word : uniqueWords) {
//                        wordFreqMap.put(word, wordFreqMap.getOrDefault(word, 0) + 1);
//                    }
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Step 2: Retrieve stop words
//        List<String> stopWords = new ArrayList<>();
//        for (Map.Entry<String, Integer> entry : wordFreqMap.entrySet()) {
//            if (entry.getValue() > threshold) {
//                stopWords.add(entry.getKey());
//            }
//        }
//
//        return stopWords;
//    }
//
//    
//}
