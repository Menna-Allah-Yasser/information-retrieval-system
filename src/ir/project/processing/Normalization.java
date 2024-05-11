package ir.project.processing;

import org.apache.commons.codec.language.Soundex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  
import java.util.*;
import java.util.List;
import java.util.Map;

public class Normalization extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, List<String>> wordMap;

    public Normalization() {
        initializeGUI();
        wordMap = new HashMap<>();
    }

    private void initializeGUI() {
        setTitle("Normalization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Normalized Word");

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void normalizeFiles(List<File> files) {
        Soundex soundex = new Soundex();

        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        String normalizedWord = soundex.encode(word);
                        if (!wordMap.containsKey(normalizedWord)) {
                            wordMap.put(normalizedWord, new ArrayList<>(Collections.singletonList(word)));
                            tableModel.addRow(new Object[]{normalizedWord});
                        } else {
                            List<String> existingWords = wordMap.get(normalizedWord);
                            if (!existingWords.contains(word)) {
                                existingWords.add(word);
                                wordMap.put(normalizedWord, existingWords);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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
            Normalization gui = new Normalization();
            gui.normalizeFiles(getFileList());
            gui.setVisible(true);
        });
    }
}