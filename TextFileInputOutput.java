/*
* License d4t4k1ng
* Github: https://github.com/d4t4k1ng
* */

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;




public class TextFileInputOutput extends JFrame implements ActionListener {




    private JPanel myPanel;
    private JTextArea keimeno;
    private JLabel enterText;
    private JButton Import;
    private JButton Export;
    private JButton Clear;

    public TextFileInputOutput()
    {

        this.setSize(320,400);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {

        });

        initializePanel();
        setVisible(true);

    }

    private void initializePanel()
    {
        myPanel = new JPanel();
        keimeno = new JTextArea(10,20);
        JScrollPane Scroll= new JScrollPane(keimeno, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        enterText=new JLabel("");
        Import=new JButton("Import");
        Export=new JButton("Export");
        Clear=new JButton("Clear");

        this.setTitle("Welcome to File I/O");

        JLabel enterText =  new JLabel("Type here your text:");
        myPanel.add(enterText);
        myPanel.add(Scroll);
        myPanel.add(Import);
        myPanel.add(Export);
        myPanel.add(Clear);
        Import.addActionListener(this);
        Export.addActionListener(this);
        Clear.addActionListener(this);


        JMenuBar myBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem itemImport = new JMenuItem("Import");
        JMenuItem itemExport = new JMenuItem("Export");
        JMenuItem itemExit = new JMenuItem("Exit");
        itemImport.addActionListener(this);
        itemExport.addActionListener(this);
        itemExit.addActionListener(this);
        fileMenu.add(itemImport);
        fileMenu.add(itemExport);
        fileMenu.addSeparator();
        fileMenu.add(itemExit);
        myBar.add(fileMenu);
        setJMenuBar(myBar);

        Import.setToolTipText("Press to choose file to import!");
        Export.setToolTipText("Press to choose file to export!");
        Clear.setToolTipText("Press to clear text!");
        this.getContentPane().add(myPanel);

    }



    public static void main(String[] args)
    {
        TextFileInputOutput ap=new TextFileInputOutput();
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Clear"))
        {
            keimeno.setText("");
        }

        else if(e.getActionCommand().equals("Import"))
        {
            importing();


        }

        else if(e.getActionCommand().equals("Export"))
        {
            exporting();

        }

        else if(e.getActionCommand().equals("itemExport"))
        {
            exporting();

        }

        else if(e.getActionCommand().equals("itemImport"))
        {

            importing();
        }

        else if(e.getActionCommand().equals("Exit"))
        {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to EXIT the program?", "Exit Pressed!",JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION)
            {

                int confirmed2= JOptionPane.showConfirmDialog(null,
                        "Do you want to save your data before exit?", "Save your data?",JOptionPane.YES_NO_OPTION);
                if(confirmed2==JOptionPane.YES_OPTION)
                {
                    exporting();
                    dispose();
                }
                else
                {
                    dispose();
                }
            }

        }


    }

    private void exporting()
    {




        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text file","txt"));
        int result = fileChooser.showDialog(this,"Export");
        File file = fileChooser.getSelectedFile();

        if (result == JFileChooser.APPROVE_OPTION)
        {


            try {
                BufferedWriter wr = new BufferedWriter( new FileWriter( file.getAbsolutePath()));
                String text =keimeno.getText();
                JOptionPane.showMessageDialog(myPanel, "Your text saved to file!","Success", JOptionPane.INFORMATION_MESSAGE);
                wr.write(text);
                wr.close();

            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(myPanel,
                        "File not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(myPanel,
                        "Fail", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    private void importing()
    {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text file","txt"));
        int result = fileChooser.showDialog(this,"Import");
        if (result==JFileChooser.APPROVE_OPTION)
        {
            File file=fileChooser.getSelectedFile();


            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile())))
            {
                keimeno.setText(null);
                String text = null;
                while ((text = br.readLine()) != null)
                {
                    keimeno.append(text);
                    keimeno.append("\n");
                }

            }
            catch (IOException exc) {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(myPanel,
                        "Failed to read file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


}
