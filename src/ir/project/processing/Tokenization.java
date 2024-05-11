package ir.project.processing;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokenization extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public Tokenization() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Tokenization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Token");

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void tokenizeFiles(List<File> files) {
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {
                        tableModel.addRow(new Object[]{token});
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
            Tokenization gui = new Tokenization();
            gui.tokenizeFiles(getFileList());
            gui.setVisible(true);
        });
    }
}
