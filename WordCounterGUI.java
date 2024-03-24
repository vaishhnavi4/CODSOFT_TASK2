import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class WordCounterGUI extends JFrame implements ActionListener {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel("Enter text or file path:");

        inputTextArea = new JTextArea();
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);

        // Output panel
        JPanel outputPanel = new JPanel(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton countButton = new JButton("Count Words");

        countButton.addActionListener(this);
        buttonPanel.add(countButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Count Words")) {
            String inputText = inputTextArea.getText();
            String[] words = inputText.split("[\\s\\p{Punct}]+");
            int wordCount = 0;
            Map<String, Integer> wordFrequency = new HashMap<>();

            for (String word : words) {
                if (!word.isEmpty()) {
                    wordCount++;
                    wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
                }
            }

            StringBuilder outputText = new StringBuilder();
            outputText.append("Total number of words: ").append(wordCount).append("\n");
            outputText.append("Word Frequency:\n");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                outputText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            outputTextArea.setText(outputText.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordCounterGUI::new);
    }
}
