package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import org.apache.lucene.search.Sort;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.StringTokenizer;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainWindow {

	private JFrame frame;
	private JComboBox queryField;
	private Controller controller;
	private Vector<String> history;
	private int page=0;
	private JTextArea textArea;
	private String Order;
    public String getOrder() {
		return Order;
	}
	private static final long serialVersionUID = 1L;
//    private Highlighter.HighlightPainter cyanPainter;
    private Highlighter.HighlightPainter redPainter;
    private final ButtonGroup buttonGroup = new ButtonGroup();

	
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		history=new Vector<String>();
		new Sort();
		Order="RELEVANCE";
		initialize();
		controller=new Controller(this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 828, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JButton Title = new JButton("Title:");
		Title.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				queryField.getEditor().setItem("Title:"+queryField.getEditor().getItem().toString());
				
			}
		});
		GridBagConstraints gbc_Title = new GridBagConstraints();
		gbc_Title.insets = new Insets(0, 0, 5, 5);
		gbc_Title.gridx = 4;
		gbc_Title.gridy = 0;
		frame.getContentPane().add(Title, gbc_Title);
		
		JButton Origin = new JButton("Origin:");
		Origin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryField.getEditor().setItem("Origin:"+queryField.getEditor().getItem().toString());
				
			}
		});
		GridBagConstraints gbc_Origin = new GridBagConstraints();
		gbc_Origin.insets = new Insets(0, 0, 5, 5);
		gbc_Origin.gridx = 5;
		gbc_Origin.gridy = 0;
		frame.getContentPane().add(Origin, gbc_Origin);
		
		JButton Year = new JButton("Year:");
		Year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				queryField.getEditor().setItem("Year:"+queryField.getEditor().getItem().toString());
				
				
			}
		});
		GridBagConstraints gbc_Year = new GridBagConstraints();
		gbc_Year.insets = new Insets(0, 0, 5, 5);
		gbc_Year.gridx = 8;
		gbc_Year.gridy = 0;
		frame.getContentPane().add(Year, gbc_Year);
		
		JButton Director = new JButton("Director:");
		Director.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryField.getEditor().setItem("Director:"+queryField.getEditor().getItem().toString());
				
			}
		});
		GridBagConstraints gbc_Director = new GridBagConstraints();
		gbc_Director.insets = new Insets(0, 0, 5, 5);
		gbc_Director.gridx = 9;
		gbc_Director.gridy = 0;
		frame.getContentPane().add(Director, gbc_Director);
		
		JButton Cast = new JButton("Cast:");
		Cast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryField.getEditor().setItem("Cast:"+queryField.getEditor().getItem().toString());
				
			}
		});
		GridBagConstraints gbc_Cast = new GridBagConstraints();
		gbc_Cast.insets = new Insets(0, 0, 5, 5);
		gbc_Cast.gridx = 11;
		gbc_Cast.gridy = 0;
		frame.getContentPane().add(Cast, gbc_Cast);
		
		JButton Genre = new JButton("Genre:");
		Genre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				queryField.getEditor().setItem("Genre:"+queryField.getEditor().getItem().toString());
				
			}
		});
		GridBagConstraints gbc_Genre = new GridBagConstraints();
		gbc_Genre.insets = new Insets(0, 0, 5, 0);
		gbc_Genre.gridx = 12;
		gbc_Genre.gridy = 0;
		frame.getContentPane().add(Genre, gbc_Genre);
		
		queryField = new JComboBox(history);
		queryField.setEditable(true);
		GridBagConstraints gbc_queryField = new GridBagConstraints();
		gbc_queryField.insets = new Insets(0, 0, 5, 0);
		gbc_queryField.gridwidth = 13;
		gbc_queryField.fill = GridBagConstraints.HORIZONTAL;
		gbc_queryField.gridx = 0;
		gbc_queryField.gridy = 1;
		frame.getContentPane().add(queryField, gbc_queryField);
		
		JButton GObttn = new JButton("GO");
		GObttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryString=(String) queryField.getSelectedItem();				
				page=0;			
				history.add((String) queryField.getSelectedItem());
					controller.execute(new String[] {"Search",queryField.getEditor().getItem().toString(),Integer.toString(page)});
					chromatiseWords(queryField.getEditor().getItem().toString());
					//System.out.println(queryField.getEditor().getItem().toString());
				
				
			}
		});
		GridBagConstraints gbc_GObttn = new GridBagConstraints();
		gbc_GObttn.fill = GridBagConstraints.HORIZONTAL;
		gbc_GObttn.gridwidth = 5;
		gbc_GObttn.insets = new Insets(0, 0, 5, 5);
		gbc_GObttn.gridx = 0;
		gbc_GObttn.gridy = 2;
		frame.getContentPane().add(GObttn, gbc_GObttn);
		
		JButton Nxt10 = new JButton("Next10");
		Nxt10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				page++;
				controller.execute(new String[] {"Search",queryField.getEditor().getItem().toString(),Integer.toString(page)});
				chromatiseWords(queryField.getEditor().getItem().toString());

			}
		});
		GridBagConstraints gbc_Nxt10 = new GridBagConstraints();
		gbc_Nxt10.insets = new Insets(0, 0, 5, 5);
		gbc_Nxt10.gridx = 5;
		gbc_Nxt10.gridy = 2;
		frame.getContentPane().add(Nxt10, gbc_Nxt10);
		
		JRadioButton ByRelevance = new JRadioButton("By Relevance");
		ByRelevance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (ByRelevance.isSelected()) {
					Order="RELEVANCE";
				}else {
					Order="INDEXORDER";
				}
				
			}
		});
		ByRelevance.setSelected(true);
		buttonGroup.add(ByRelevance);
		GridBagConstraints gbc_ByRelevance = new GridBagConstraints();
		gbc_ByRelevance.insets = new Insets(0, 0, 5, 5);
		gbc_ByRelevance.gridx = 8;
		gbc_ByRelevance.gridy = 2;
		frame.getContentPane().add(ByRelevance, gbc_ByRelevance);
		
		JRadioButton ByIndex = new JRadioButton("By Index");
		buttonGroup.add(ByIndex);
		GridBagConstraints gbc_ByIndex = new GridBagConstraints();
		gbc_ByIndex.insets = new Insets(0, 0, 5, 5);
		gbc_ByIndex.gridx = 9;
		gbc_ByIndex.gridy = 2;
		frame.getContentPane().add(ByIndex, gbc_ByIndex);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 13;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Vector<String> getHistory() {
		return history;
	}

	public void setHistory(Vector<String> history) {
		this.history = history;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JComboBox getTextField() {
		return queryField;
	}

	public void setTextField(JComboBox textField) {
		this.queryField = textField;
	}
	public void chromatiseWords(String Query)  {
		
		
		//cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
        redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
        StringTokenizer token = new StringTokenizer(Query);
        int startINDX=0;
        while(token.hasMoreTokens()) {
        	String compare = token.nextToken();
        	int end=0;
        	int length=compare.length();
        	while (true) {
        		int indexOf=(textArea.getText()).indexOf(compare,end);
        		if (indexOf== -1) {
        			break;
        		}
        		try {
					textArea.getHighlighter().addHighlight(indexOf,indexOf+length, redPainter);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		end=indexOf+length;
        		
        		
        		
        	}
		
        }
	}
	
	
}
