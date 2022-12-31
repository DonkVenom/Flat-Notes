import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Notes extends JFrame {


/*
/***
 *     /$$$$$$$$ /$$        /$$$$$$  /$$$$$$$$
 *    | $$_____/| $$       /$$__  $$|__  $$__/
 *    | $$      | $$      | $$  \ $$   | $$
 *    | $$$$$   | $$      | $$$$$$$$   | $$
 *    | $$__/   | $$      | $$__  $$   | $$
 *    | $$      | $$      | $$  | $$   | $$
 *    | $$      | $$$$$$$$| $$  | $$   | $$
 *    |__/      |________/|__/  |__/   |__/
 *
 *
 *
 */




//________________________________________________________________

    final String version = "1.0.0";
    final String license = "MIT";
    final String repositoryURL = "https://github.com/DonkVenom/Flat/tree/dev";
    final int copyrightYear = 2023;

//________________________________________________________________






    private JPanel contentPane;
    private JScrollPane mainPane;
    private JTextPane textPane;
    private JSpinner spinnerFontSize;
    private JPanel menuPane;
    private JComboBox comboBoxFonts;


    final Dimension preferredSize = new Dimension(500,400);
    final Dimension minimumSize = new Dimension(200,200);

    File saveFile;


    final JMenuBar menuBar  = new JMenuBar();

    final JMenu menuFile = new JMenu("File");
    final JMenu menuEdit = new JMenu("Edit");
    final JMenu menuView = new JMenu("View");
    final JMenu menuHelp = new JMenu("Help");
    final JMenu subMenuSize = new JMenu("Window Size");
    final JMenuItem itemSave = new JMenuItem("Save");
    final JMenuItem itemClose = new JMenuItem("Close");
    final JMenuItem itemCopy = new JMenuItem("Copy");
    final JMenuItem item200x200 = new JMenuItem("200x200");
    final JMenuItem item300x300 = new JMenuItem("300x300");
    final JMenuItem item500x500 = new JMenuItem("500x500");
    final JMenuItem item1000x1000 = new JMenuItem("1000x1000");
    final JCheckBoxMenuItem checkBoxItemMinimalMode = new JCheckBoxMenuItem("Slim Mode");
    final JCheckBoxMenuItem checkBoxFullScreen = new JCheckBoxMenuItem("Fullscreen");
    final JMenuItem itemMinimize = new JMenuItem("Minimize");
    final JMenuItem itemPrint = new JMenuItem("Print");
    final JMenuItem itemAbout = new JMenuItem("About");
    final JMenuItem itemCheckForUpdates = new JMenuItem("Check for Updates");


    ImageIcon iconWarning;
    ImageIcon iconInfo;

    final String projectPath = System.getProperty("user.dir");
    final String userHomeDir = System.getProperty("user.home");
    final String userDownloadDir = userHomeDir + "/Downloads";
    final String userDocumentsDir = userHomeDir + "/Documents";


    JPopupMenu rightClickMenu = new JPopupMenu();



    String[] availableFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();



        Dimension lastDimension;

    int currentTextStyle = Font.PLAIN;


    Notes(){

    }

    void setFontSize(int size){

        textPane.setFont(new Font(textPane.getFont().getName(),textPane.getFont().getStyle(), size));

    }
    void copy(){

        if (textPane.getText().isEmpty()|| textPane.getText().isBlank()){
        }
        else {
            StringSelection stringSelection = new StringSelection(textPane.getSelectedText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        }

    }
    void setBold(){

//TODO MIT ITALIC UND FUNKTKONEN EINBAUEN
//
  currentTextStyle = Font.BOLD;
    }
    void setFont(String name){
        textPane.setFont(new Font(name, textPane.getFont().getStyle(), textPane.getFont().getSize()));
    }
    void save(){



        if (saveFile.getName().equals("def___00010391hd3h832")){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        fileChooser.setCurrentDirectory(new File(userHomeDir + "/Downloads")); //WORKS
        int response =  fileChooser.showSaveDialog(null); // RESPONSE 1 OR 0 ; 0-> SAVE ; 1-> CANCEL OR CLOSE

        if (response == JFileChooser.CANCEL_OPTION || response == JFileChooser.ERROR_OPTION){
            System.out.println("Saving aborted . Code " + response);
        } else {
            saveFile = new File(fileChooser.getSelectedFile().getAbsolutePath()); //SET PATH
            System.out.println(saveFile.getName());
            String path = saveFile.getAbsolutePath();


            try {
                saveFile.createNewFile();
                System.out.println(" empty File created");
            } catch (IOException e) {
                throw new RuntimeException(e); //CREATE FILE
            }


            try {
                FileWriter fileWriter = new FileWriter(saveFile);
                String content = textPane.getText().trim();
                fileWriter.write(content); //WRITING
                fileWriter.flush();
                fileWriter.close();


            } catch (IOException e) {
                System.out.println("Error while writing text to file");

                throw new RuntimeException(e);
            }


            setTitle(saveFile.getName());

        }

        }
        else {
            try {
                FileWriter fileWriter = new FileWriter(saveFile);
                String content = textPane.getText().trim();
                fileWriter.write(content); //WRITING
                fileWriter.flush();
                fileWriter.close();


            } catch (IOException e) {
                System.out.println("Error while writing text to file");

                throw new RuntimeException(e);
            }
        }


    } //WORKS




    void close(){
        System.exit(1);
    } //WORKS


    void setEnviorment(){
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(minimumSize);
        setPreferredSize(preferredSize);


        setResizable(true);


        System.out.println("Project path: " + projectPath);





        this.setJMenuBar(menuBar);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

        menuView.add(subMenuSize);


        menuEdit.add(itemCopy);
        menuFile.add(itemSave);
        menuFile.add(itemClose);
        subMenuSize.add(item200x200);
        subMenuSize.add(item300x300);
        subMenuSize.add(item500x500);
        subMenuSize.add(item1000x1000);
        menuView.add(checkBoxItemMinimalMode);
        menuView.add(checkBoxFullScreen);
        menuView.add(itemMinimize);
        menuFile.add(itemPrint);
        menuHelp.add(itemAbout);
        menuHelp.add(itemCheckForUpdates);



        System.out.println("Available Fonts: "  + availableFonts.length);
        for (String availableFont : availableFonts) {

            comboBoxFonts.addItem(availableFont);


        }
        comboBoxFonts.setSelectedItem(String.valueOf(textPane.getFont().getFamily()));
        spinnerFontSize.setValue(textPane.getFont().getSize());




        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        }); //WORKS
        itemClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                iconWarning = new ImageIcon(projectPath+"/src/img/WARNING_ICON.png"); //WORKS

                if(!textPane.getText().isEmpty() || !textPane.getText().isBlank()) {
                    int response = JOptionPane.showConfirmDialog(null, "Do you want to save before exiting?", "ATTENTION", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, iconWarning);

                    if (Integer.valueOf(response).equals(JOptionPane.YES_OPTION)) {
                        save();
                        close();

                    } else if (Integer.valueOf(response).equals(JOptionPane.NO_OPTION)) {
                        close();

                    }

                } //WORKS
                else {
                    close();
                }
            }
        }); //WORKS
        itemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copy();
            }
        }); //WORKS
        spinnerFontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if ((int)spinnerFontSize.getValue() < 1){
                    JOptionPane.showMessageDialog(null,"Font Size must be greater than zero.", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);
                    spinnerFontSize.setValue(textPane.getFont().getSize());
                }
                else {


                    setFontSize((Integer) spinnerFontSize.getValue());
                }

            }
        }); //WORKS
        comboBoxFonts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFont(String.valueOf(comboBoxFonts.getSelectedItem()));

            }
        });
        item200x200.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSize(new Dimension(200,200));
            }
        }); //WORKS
        item300x300.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSize(new Dimension(300,300));
            }
        }); //WORKS
        item500x500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSize(new Dimension(500,500));
            }
        }); //WORKS
        item1000x1000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSize(new Dimension(1000,1000));
            }
        }); //WORKS
        checkBoxItemMinimalMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                if (checkBoxItemMinimalMode.isSelected()){
                    textPane.setFont(new Font("Arial", Font.PLAIN, 13));

                    menuPane.setVisible(false);
                }
                else {
                    menuPane.setVisible(true);
                    textPane.setFont(new Font(String.valueOf(comboBoxFonts.getSelectedItem()),currentTextStyle, (Integer) spinnerFontSize.getValue()));
                }



            }
        }); //WORKS
        checkBoxFullScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (checkBoxFullScreen.isSelected()){
                    lastDimension = getSize();


                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    // Remove the window decorations
                    setUndecorated(true);


                }
                else {

                    setSize(lastDimension);
                    setLocationRelativeTo(null);


                }

            }
        }); //WORKS
        itemMinimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);

            }
        });//WORKS
        itemPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();

                    // Check if the print action is supported
                    if (desktop.isSupported(Desktop.Action.PRINT)) {
                        // Print the file
                        try {
                            save();
                            desktop.print(saveFile);
                        } catch (IOException ex) { //TODO
                            throw new RuntimeException(ex);
                        }
                    } else {
                        System.out.println("Print action is not supported");
                    }
                } else {
                    System.out.println("Desktop API is not supported");
                }
            }
        });//WORKS
        itemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iconInfo = new ImageIcon(projectPath+"/src/img/INFO_ICON.png");

                JOptionPane.showMessageDialog(null,"<html> <center> <b>Flat </b></center> <br> Version:   " + version + " </html>"  , "About",JOptionPane.INFORMATION_MESSAGE, iconInfo);
            }
        });//WORKS
        saveFile = new File("def___00010391hd3h832"); //DEFAULT SETTING EQUALS NOT YET CREATED FILE
        itemCheckForUpdates.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI(repositoryURL));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });



        setTitle("Flat");




    }
    public void launch(){

        setEnviorment();
        pack();
        setVisible(true);
    }


}
