package client.cntl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 * Loads and saves files.
 */
public class FileCntl {

    private final JTextArea mainTextArea;
    private final JFileChooser fileChooser;
    private final JList fileList;
    private final JFrame frame;
   
    private File loadedFile;

    public FileCntl(JFileChooser fileChooser, JList fileList, JFrame frame, JTextArea mainTextArea) {
        this.fileChooser = fileChooser;
        this.fileList = fileList;
        this.frame = frame;
        this.mainTextArea = mainTextArea;        
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
        // Update the file list.
        addFileToList(file);
        
        if (file.isFile() == true) {
            ArrayList<String> lines = new ArrayList<>();

            // Save the loadedFile for saving later.
            loadedFile = file;
            
            // Copy conent from file to text area.
            try (Scanner in = new Scanner(file)) {
                while (in.hasNext()) {
                    lines.add(in.nextLine());
                }

                String fileContents = "";
                for (String line : lines) {
                    fileContents += line + "\n";
                }
                mainTextArea.setText(fileContents);  
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * The method called by the SaveActionListener.
     */
    public void onSaveFileButtonPress() {
        if (loadedFile == null) {
            loadedFile = getFileFromChooser(JFileChooser.SAVE_DIALOG);
            if (loadedFile != null) {
                saveFile();
            }
        }
        else {
            saveFile();
        }
    }
    
    /**
     * Saves the loaded file.
     */
    public void saveFile() {
        if (loadedFile != null) {
            try (PrintWriter printwriter = new PrintWriter(loadedFile)) {
                printwriter.print(mainTextArea.getText());
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }  
        } 
    }
    
    /**
     * Adds a File to the list.
     * @param file an object of type File, includes directory.
     */
    private void addFileToList(File file) {
        DefaultListModel model = ((DefaultListModel)(fileList.getModel()));
        
        // Clear the list first.
        model.clear();
        
        boolean isDirectory = file.isDirectory();
        if (isDirectory == true) {
            File[] files = file.listFiles();
            for(File f: files) {
                 model.addElement(f.getName());
            }
        }
        else {
            model.addElement(file.getName());
        }
    }
}
