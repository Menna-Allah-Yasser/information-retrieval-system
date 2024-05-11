package ir.project.search;

import ir.project.index.IncidenceMatrix;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

public class MatchedFiles extends JFrame {

    private JTextArea textArea;
    private Map<String, Set<String>> wordToFileMap;

    public MatchedFiles(Map<String, Set<String>> wordToFileMap) {
        this.wordToFileMap = wordToFileMap;

        setTitle("Matched Files");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
        int width = 300;
        int height = 300;
        setPreferredSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
       
    }

    public void displayMatchedFiles(String query) {
        Set<String> res = new HashSet<>();

        String[] keywords = query.split("and|or");
        for (String keyword : keywords) {
            keyword = keyword.trim();
            if (!keyword.isEmpty()) {
                Set<String> files = wordToFileMap.getOrDefault(keyword, new HashSet<>());
                if (res.isEmpty()) {
                    res.addAll(files);
                } else if (query.contains("and")) {
                    res.retainAll(files);
                } else if (query.contains("or")) {
                    res.addAll(files);
                }
            }
        }
        SwingUtilities.invokeLater(() -> {
            textArea.setText("");
            for (String file : res) {
                textArea.append(file + "\n");
            }
        });
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

//    public static void main(String[] args) {
//
////        Map<String, Set<String>> wordToFileMap = new HashMap<>();
////        wordToFileMap.put("apple", new HashSet<>(Arrays.asList("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt", "E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_2.txt")));
////        wordToFileMap.put("banana", new HashSet<>(Arrays.asList("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_2.txt", "E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_3.txt")));
////        wordToFileMap.put("orange", new HashSet<>(Arrays.asList("E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_1.txt", "E:\\FCI\\4th year\\2nd _term\\IR\\project\\Ir-Startup-main\\Ir-Startup-main\\technologie\\technologie_3.txt")));
//
//        IncidenceMatrix im = new IncidenceMatrix();
//        im.generateIncidenceMatrix(getFileList());
//        Map<String, Set<String>> wordToFileMap = im.printMap();
//        
//        SwingUtilities.invokeLater(() -> {
//            MatchedFiles frame = new MatchedFiles(wordToFileMap);
//            frame.setVisible(true);
//            frame.displayMatchedFiles("apple and banana");
//        });
//    }
}
