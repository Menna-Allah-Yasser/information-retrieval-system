package ir.project.processing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tartarus.snowball.ext.PorterStemmer;

public class Stemming extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public Stemming() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Stemming");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Stem");

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void stemFiles(List<File> files) {
        PorterStemmer stemmer = new PorterStemmer();
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        stemmer.setCurrent(word);
                        stemmer.stem();
                        String stemmedWord = stemmer.getCurrent();
                        tableModel.addRow(new Object[]{stemmedWord});
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
            Stemming gui = new Stemming();
            gui.stemFiles(getFileList());
            gui.setVisible(true);
        });
    }
}