package ir.project.index;


import static ir.project.index.IncidenceMatrix.getFileList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class InvertedIndex extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, Set<Integer>> invertedIndex;
    private Map<String, Set<String>> map;
    private JTextField queryTextField;

    public InvertedIndex() {
        initializeGUI();
        invertedIndex = new HashMap<>();
        map = new HashMap<>();
    }

    private void initializeGUI() {
        setTitle("Inverted Index");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Word");
        tableModel.addColumn("Document IDs");

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel queryPanel = new JPanel();
        JLabel queryLabel = new JLabel("Query:");
        queryTextField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryTextField.getText();
                searchQuery(query);
            }
        });

        queryPanel.add(queryLabel);
        queryPanel.add(queryTextField);
        queryPanel.add(searchButton);
        getContentPane().add(queryPanel, BorderLayout.SOUTH);
    }

    public void buildIndex(List<File> files) {
        for (int docId = 0; docId < files.size(); docId++) {
            File file = files.get(docId);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        if (!invertedIndex.containsKey(word)) {
                            invertedIndex.put(word, new HashSet<>());
                            map.put(word, new HashSet<>());
                        }
                        invertedIndex.get(word).add(docId);
                        map.get(word).add(file.getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayIndex() {
        for (Map.Entry<String, Set<Integer>> entry : invertedIndex.entrySet()) {
            String word = entry.getKey();
            Set<Integer> docIds = entry.getValue();
            String docIdsString = docIds.toString().replaceAll("[\\[\\]]", "");
            tableModel.addRow(new Object[]{word, docIdsString});
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
    
    public void searchQuery(String query) {
        String[] keywords = query.split("\\s+and\\s+|\\s+or\\s+");
        List<String> queryWords = new ArrayList<>(Arrays.asList(keywords));

        Set<String> resultFileNames = new HashSet<>();
        boolean isAndQuery = query.contains("and");

        if (isAndQuery) {
            boolean firstWord = true;
            for (String word : queryWords) {
                word = word.toLowerCase();
                if (firstWord) {
                    resultFileNames.addAll(map.getOrDefault(word, new HashSet<>()));
                    firstWord = false;
                } else {
                    resultFileNames.retainAll(map.getOrDefault(word, new HashSet<>()));
                }
            }
        } else {
            for (String word : queryWords) {
                word = word.toLowerCase();
                resultFileNames.addAll(map.getOrDefault(word, new HashSet<>()));
            }
        }

        if (resultFileNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No documents found for the givenquery.");
        } else {
            JOptionPane.showMessageDialog(this, "Documents matching the query: " + resultFileNames);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvertedIndex gui = new InvertedIndex();
            List<File> files = getFileList();
            gui.buildIndex(files);
            gui.displayIndex();
            gui.setVisible(true);
        });
    }
}


//import static ir.project.index.IncidenceMatrix.getFileList;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//import java.util.List;
//
//public class InvertedIndex extends JFrame {
//    private JTable table;
//    private DefaultTableModel tableModel;
//    private Map<String, Set<Integer>> invertedIndex;
//    private Map<String, Set<String>> map;
//
//    public InvertedIndex() {
//        initializeGUI();
//        invertedIndex = new HashMap<>();
//        map = new HashMap<>();
//    }
//
//    private void initializeGUI() {
//        setTitle("Inverted Index");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(600, 400);
//        setLocationRelativeTo(null);
//
//        tableModel = new DefaultTableModel();
//        tableModel.addColumn("Word");
//        tableModel.addColumn("Document IDs");
//
//        table = new JTable(tableModel);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        JScrollPane scrollPane = new JScrollPane(table);
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//        
//    }
//
//    public void buildIndex(List<File> files) {
//        for (int docId = 0; docId < files.size(); docId++) {
//            File file = files.get(docId);
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] words = line.split("\\s+");
//                    for (String word : words) {
//                        word = word.toLowerCase();
//                        if (!invertedIndex.containsKey(word)) {
//                            invertedIndex.put(word, new HashSet<>());
//                            map.put(word, new HashSet<>());
//                        }
//                        invertedIndex.get(word).add(docId);
//                        map.get(word).add(file.getName());
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void displayIndex() {
//        for (Map.Entry<String, Set<Integer>> entry : invertedIndex.entrySet()) {
//            String word = entry.getKey();
//            Set<Integer> docIds = entry.getValue();
//            String docIdsString = docIds.toString().replaceAll("[\\[\\]]", "");
//            tableModel.addRow(new Object[]{word, docIdsString});
//        }
//    }
//    
//    public static List<File> getFileList() {
//        // Modify this method to return your specific list of files
//        File file1 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt");
//        File file2 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_2.txt");
//        File file3 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_3.txt");
//        
//        List<File> fileList = new ArrayList<>();
//        fileList.add(file1);
//        fileList.add(file2);
//        fileList.add(file3);
//        return fileList;
//    }
//
////    public static void main(String[] args) {
////        SwingUtilities.invokeLater(() -> {
////            InvertedIndex gui = new InvertedIndex();
////            File file1 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt");
////            File file2 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_2.txt");
////            File file3 = new File("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_3.txt");
////        
////            List<File> files = Arrays.asList(file1 , file2 , file3);
////            gui.buildIndex(files);
////            gui.displayIndex();
////            gui.setVisible(true);
////        });
////    }
//}
//
