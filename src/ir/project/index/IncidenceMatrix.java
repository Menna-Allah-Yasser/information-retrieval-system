package ir.project.index;

import ir.project.search.MatchedFiles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Set;
import java.util.List;

public class IncidenceMatrix {

    private JTextArea textArea;
    private JFrame frame;
    private JTextArea matrixTextArea;
    private Map<String, Set<String>> wordToFileMap = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                IncidenceMatrix incidenceMatrix = new IncidenceMatrix();
                incidenceMatrix.createAndShowGUI();
                incidenceMatrix.generateIncidenceMatrix(getFileList());
               
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Incidence Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        matrixTextArea = new JTextArea();
        matrixTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(matrixTextArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new FlowLayout());
        JTextField queryField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getText();
                executeQuery(query);
            }
        });
        queryPanel.add(queryField);
        queryPanel.add(searchButton);

        frame.getContentPane().add(queryPanel, BorderLayout.NORTH);

        int width = 900;
        int height = 900;
        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void executeQuery(String query) {
        
           MatchedFiles frame = new MatchedFiles(wordToFileMap);
           frame.setVisible(true);
            frame.displayMatchedFiles(query);
    }

    private void displaySearchResult(Set<String> resultFiles) {
        if (resultFiles.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No matching files found.");
        } else {
            StringBuilder message = new StringBuilder("Matching Files:\n");
            for (String file : resultFiles) {
                message.append(file).append("\n");
            }
            JOptionPane.showMessageDialog(frame, message.toString());
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
     
      public void generateIncidenceMatrix(List<File> files) {
        StringBuilder matrixBuilder = new StringBuilder();

        Set<String> uniqueWords = extractUniqueWords(files);
        int numFiles = files.size();
        int numWords = uniqueWords.size();
        int[][] incidenceMatrix = new int[numWords][numFiles];

        // Create a map to store the files in which each word exists
        // Append column headers (file names)
        matrixBuilder.append("\t");
        for (File file : files) {
            matrixBuilder.append(file.getName()).append("\t");
        }
        matrixBuilder.append("\n");

        // Iterate over words
        int rowIndex = 0;
        for (String word : uniqueWords) {
            // Append row header (word)
            matrixBuilder.append(word).append("\t");

            // Create a set to store the files in which the current word exists
            Set<String> filesContainingWord = new HashSet<>();

            // Iterate over files
            for (int colIndex = 0; colIndex < numFiles; colIndex++) {
                File file = files.get(colIndex);
                int incidence = isWordPresent(file, word) ? 1 : 0;

                incidenceMatrix[rowIndex][colIndex] = incidence;

                // Add the file name to the set if the word is present in the file
                if (incidence == 1) {
                    filesContainingWord.add(file.getName());
                }

                // Append incidence value
                matrixBuilder.append(incidence).append("\t");
            }

            // Add the set of files to the map for the current word
            wordToFileMap.put(word, filesContainingWord);

            matrixBuilder.append("\n");
            rowIndex++;
        }

        matrixTextArea.setText(matrixBuilder.toString());

        // Print the word-to-file map
       // printMap(wordToFileMap);
    }

       public Map<String, Set<String>> printMap() {
        return wordToFileMap;
//        System.out.println("Word to File Map:");
//        for (Map.Entry<String, Set<String>> entry : wordToFileMap.entrySet()) {
//            String word = entry.getKey();
//            Set<String> filesContainingWord = entry.getValue();
//            System.out.println(word + ": " + filesContainingWord);
//        }
    }

    private Set<String> extractUniqueWords(List<File> files) {
        Set<String> uniqueWords = new HashSet<>();

        for (File file : files) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    uniqueWords.add(word);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return uniqueWords;
    }

    private boolean isWordPresent(File file, String word) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String fileWord = scanner.next();
                if (fileWord.equals(word)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

}


//import ir.project.search.MatchedFiles;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.*;
//import java.util.Set;
//import java.util.List;
//
//public class IncidenceMatrix {
//
//    
//    private JTextArea textArea;
//    private JFrame frame;
//    private JTextArea matrixTextArea;
//    private Map<String, Set<String>> wordToFileMap = new HashMap<>();
//
//    public static void main(String[] args) {
//      
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                IncidenceMatrix incidenceMatrix = new IncidenceMatrix();
//                incidenceMatrix.createAndShowGUI();
//            }
//        });
//    }
//
//    public void createAndShowGUI() {
//        frame = new JFrame("Incidence Matrix");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        matrixTextArea = new JTextArea();
//        matrixTextArea.setEditable(false);
//
//        JScrollPane scrollPane = new JScrollPane(matrixTextArea);
//        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//
//        JButton generateButton = new JButton("Generate Matrix");
//        generateButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                List<File> fileList = getFileList(); // Modify this method to return your specific list of files
//                generateIncidenceMatrix(fileList);
//            }
//        });
//
//        frame.getContentPane().add(generateButton, BorderLayout.SOUTH);
//
//        int width = 900;
//        int height = 900;
//        frame.setPreferredSize(new Dimension(width, height));
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
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
//    public void generateIncidenceMatrix(List<File> files) {
//        StringBuilder matrixBuilder = new StringBuilder();
//
//        Set<String> uniqueWords = extractUniqueWords(files);
//        int numFiles = files.size();
//        int numWords = uniqueWords.size();
//        int[][] incidenceMatrix = new int[numWords][numFiles];
//
//        // Create a map to store the files in which each word exists
//        // Append column headers (file names)
//        matrixBuilder.append("\t");
//        for (File file : files) {
//            matrixBuilder.append(file.getName()).append("\t");
//        }
//        matrixBuilder.append("\n");
//
//        // Iterate over words
//        int rowIndex = 0;
//        for (String word : uniqueWords) {
//            // Append row header (word)
//            matrixBuilder.append(word).append("\t");
//
//            // Create a set to store the files in which the current word exists
//            Set<String> filesContainingWord = new HashSet<>();
//
//            // Iterate over files
//            for (int colIndex = 0; colIndex < numFiles; colIndex++) {
//                File file = files.get(colIndex);
//                int incidence = isWordPresent(file, word) ? 1 : 0;
//
//                incidenceMatrix[rowIndex][colIndex] = incidence;
//
//                // Add the file name to the set if the word is present in the file
//                if (incidence == 1) {
//                    filesContainingWord.add(file.getName());
//                }
//
//                // Append incidence value
//                matrixBuilder.append(incidence).append("\t");
//            }
//
//            // Add the set of files to the map for the current word
//            wordToFileMap.put(word, filesContainingWord);
//
//            matrixBuilder.append("\n");
//            rowIndex++;
//        }
//
//        matrixTextArea.setText(matrixBuilder.toString());
//
//        // Print the word-to-file map
//       // printMap(wordToFileMap);
//    }
//
//    public Map<String, Set<String>> printMap() {
//        return wordToFileMap;
////        System.out.println("Word to File Map:");
////        for (Map.Entry<String, Set<String>> entry : wordToFileMap.entrySet()) {
////            String word = entry.getKey();
////            Set<String> filesContainingWord = entry.getValue();
////            System.out.println(word + ": " + filesContainingWord);
////        }
//    }
//
//    private Set<String> extractUniqueWords(List<File> files) {
//        Set<String> uniqueWords = new HashSet<>();
//
//        for (File file : files) {
//            try (Scanner scanner = new Scanner(file)) {
//                while (scanner.hasNext()) {
//                    String word = scanner.next();
//                    uniqueWords.add(word);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return uniqueWords;
//    }
//
//    private boolean isWordPresent(File file, String word) {
//        try (Scanner scanner = new Scanner(file)) {
//            while (scanner.hasNext()) {
//                String fileWord = scanner.next();
//                if (fileWord.equals(word)) {
//                    return true;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//}



