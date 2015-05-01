package client.cntl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Loads and saves files.
 */
public class FileCntl {

    private final JTextArea mainTextArea;
    private final JFileChooser fileChooser;
    private final JFrame frame;

    private File loadedFile;

    public FileCntl(JFrame frame, JTextArea mainTextArea) {
        this.frame = frame;
        this.mainTextArea = mainTextArea;

        fileChooser = new JFileChooser();
    }

    /**
     *
     * @param dialogType JFileChooser.OPEN_DIALOG or JFileChooser.SAVE_DIALOG
     * @return the file if chosen or null if canceled
     */
    public File getFileFromChooser(int dialogType) {
        File file = null;

        fileChooser.setDialogType(dialogType);
        
        if (dialogType == JFileChooser.SAVE_DIALOG) {
            fileChooser.setDialogTitle("Save");
            fileChooser.setApproveButtonText("Save");
        }
        
        int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        return file;
    }

    /**
     * Loads the file chosen into the text area.
     *
     * @param file the file to load from
     */
    public void loadFile(File file) {
        ArrayList<String> lines = new ArrayList<>();

        // Save the loadedFile for saving later.
        loadedFile = file;

        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                lines.add(in.nextLine());
            }

            // Copy conent from file to text area.
            String fileContents = "";
            for (String line : lines) {
                fileContents += line + "\n";
            }
            mainTextArea.setText(fileContents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        if (loadedFile == null) {
            loadedFile = getFileFromChooser(JFileChooser.SAVE_DIALOG);
            if (loadedFile != null) {
                saveFile();
            }
        } else {
            try (PrintWriter printwriter = new PrintWriter(loadedFile)) {
                printwriter.print(mainTextArea.getText());
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
