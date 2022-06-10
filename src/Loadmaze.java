import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.DBData;
import database.SaveFile;

//Layout and setup adapted from CAB302 Week 6 Practical
public class Loadmaze extends JFrame{
	private static final long serialVersionUID = 1L;

	private JList nameList;
	
	private JTextField MazeName;	
	private JTextField Author;	
	private JTextField Company;	
	private JTextField CreationDate;	
	private JTextField EditedDate;
	
	private JButton newButton;	
	private JButton loadButton;	
	private JButton deleteButton;	 
	
	private DBData data;
	private EditWindow editWindow;
	private LaunchPage launchPage;
    

    public Loadmaze(DBData data){
    	this.data = data;
        initUI();
        checkListSize();

        // add listeners to interactive components
        addButtonListeners(new ButtonListener());
        addNameListListener(new NameListListener());
        addClosingListener(new ClosingListener());

        // decorate the frame and make it visible
        setTitle("Address Book");
        setMinimumSize(new Dimension(400, 300));
        pack();
        setVisible(true);        
    }
    
    public void setWindows(EditWindow editWindow, LaunchPage launchPage) {
    	this.editWindow = editWindow;
    	this.launchPage = launchPage;
    }
    
    public void showWindow() {
    	setVisible(true);
    	editWindow.setVisible(false);
    	launchPage.setVisible(false);
    }
    
    private void initUI() {
    	Container contentPane = this.getContentPane();
    	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(makeDetailsPanel());
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(makeButtonsPanel());
        contentPane.add(Box.createVerticalStrut(20));
    }
    
    private JPanel makeDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeNameListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeAddressFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));
        return detailsPanel;
     }
    
    private JScrollPane makeNameListPane() {
        nameList = new JList(data.getModel());
        nameList.setFixedCellWidth(200);

        JScrollPane scroller = new JScrollPane(nameList);
        scroller.setViewportView(nameList);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));

        return scroller;
     }
    
    private JPanel makeAddressFieldsPanel() {
        JPanel addressPanel = new JPanel();
        GroupLayout layout = new GroupLayout(addressPanel);
        addressPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);

        layout.setAutoCreateContainerGaps(true);

        JLabel mazeNameLabel = new JLabel("Maze Name");
        JLabel authorLabel = new JLabel("Author");
        JLabel companyLabel = new JLabel("Company");
        JLabel creationLabel = new JLabel("Created On");
        JLabel editedLabel = new JLabel("Edited On");

        MazeName = new JTextField(20);
        Author = new JTextField(20);
        Company = new JTextField(20);
        CreationDate = new JTextField(20);
        EditedDate = new JTextField(20);
        setFieldsEditable(false);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup().addComponent(mazeNameLabel)
              .addComponent(authorLabel).addComponent(companyLabel).addComponent(
                    creationLabel).addComponent(editedLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(MazeName)
              .addComponent(Author).addComponent(Company).addComponent(CreationDate)
              .addComponent(EditedDate));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();


        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
              .addComponent(mazeNameLabel).addComponent(MazeName));

        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
              .addComponent(authorLabel).addComponent(Author));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
              .addComponent(companyLabel).addComponent(Company));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
              .addComponent(creationLabel).addComponent(CreationDate));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
              .addComponent(editedLabel).addComponent(EditedDate));
        layout.setVerticalGroup(vGroup);

        return addressPanel;
     }
    
    private JPanel makeButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        newButton = new JButton("New");
        loadButton = new JButton("Load");
        loadButton.setEnabled(false);
        deleteButton = new JButton("Delete");
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(newButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        return buttonPanel;
     }
    
    private void addButtonListeners(ActionListener listener) {
        newButton.addActionListener(listener);
        loadButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
     }
    
    private void addNameListListener(ListSelectionListener listener) {
        nameList.addListSelectionListener(listener);
     }
    
    private void addClosingListener(WindowListener listener) {
        addWindowListener(listener);
     }
    
    private void clearFields() {
        MazeName.setText("");
        Author.setText("");
        Company.setText("");
        CreationDate.setText("");
        EditedDate.setText("");
     }
    
    private void setFieldsEditable(boolean editable) {
        MazeName.setEditable(editable);
        Author.setEditable(editable);
        Company.setEditable(editable);
        CreationDate.setEditable(editable);
        EditedDate.setEditable(editable);
     }
    
    private void display(SaveFile File) {
        if (File != null) {
           MazeName.setText(File.getFileName());
           Author.setText(File.getAuthor());
           Company.setText(File.getCompany());
           CreationDate.setText(File.getCreated_on());
           EditedDate.setText(File.getEdited_on());
        }
     }
    
    private void checkListSize() {
        deleteButton.setEnabled(data.getSize() != 0);
     }
    
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           JButton source = (JButton) e.getSource();
           if (source == newButton) {
              newPressed();
           } else if (source == loadButton) {
              LoadPressed();
           } else if (source == deleteButton) {
              deletePressed();
           }
        }

        private void newPressed() {
        	launchPage.showWindow();
        }

        private void LoadPressed() {
           if (MazeName.getText() != null && !MazeName.getText().equals("")) {
	            String FileName = MazeName.getText();
	            SaveFile file = data.get(FileName);
	          	SwingUtilities.invokeLater(new Runnable() {
	  	   	    public void run() {
		            editWindow.LoadMaze(file.getData(), FileName);
	  	   	    }
	          });
           }
        }

        private void deletePressed() {
           int index = nameList.getSelectedIndex();
           data.remove(nameList.getSelectedValue());
           clearFields();
           index--;
           if (index == -1) {
              if (data.getSize() != 0) {
                 index = 0;
              }
           }
           nameList.setSelectedIndex(index);
           checkListSize();
        }
     }
    
    private class NameListListener implements ListSelectionListener {
    	
        public void valueChanged(ListSelectionEvent e) {
           if (nameList.getSelectedValue() != null
                 && !nameList.getSelectedValue().equals("")) {
              display(data.get((String)nameList.getSelectedValue()));
              loadButton.setEnabled(true);
           }
        }
     }
    
    private class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
           data.persist();
           System.exit(0);
        }
     }
}
