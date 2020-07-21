package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.lucene.search.*;
//import org.apache.lucene.search.TopDocs;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import view.MainWindow;
import model.*;

public class Controller {

	
	private MainWindow window;
	private HashMap<String,Command>commands;
	private TopDocs searchResult;
	private int i;
	private String order;
	public Controller(MainWindow win) {
		window=win;
		order=win.getOrder();
		commands=new HashMap<String,Command>();
		createCommands();
		initIndex();
	}
	
	public String getOrder() {
		return order;
	}

	private void createCommands() {
		CommandFactory fact=new CommandFactory(this);
		commands.put("Search",fact.createCommand("SearchCommand"));
	}
	
	public void execute(String [] queryString) {
		System.out.println("DEBUG:CONTROLLER"+queryString[1]);
		if(queryString[0].equalsIgnoreCase("Search")) {
			commands.get(queryString[0]).execute(queryString);
			i=0;
			try {
				refreshWindow();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	public void initIndex() {
		Indexer indx= new Indexer();
		indx.executeIndexing();
		
	}

	public void refreshWindow() throws IOException {
		String result="";
		int page = window.getPage();
		DirectoryReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("indexDir")));
		for (ScoreDoc sd: searchResult.scoreDocs) {
			Document document = reader.document(sd.doc);
			result=result+"ID"+Integer.toString(sd.doc)+"\n"+"Score"+Float.toString(sd.score)+"\n"
			+document.get("Link")
			+document.get("Title")+" "+document.get("Year")+" "+document.get("Plot")+"\n  \n";
			
		}
		
		
		window.getTextArea().setText(result);		
		
	}
	
	public TopDocs getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(TopDocs searchResult) {
		this.searchResult = searchResult;
	}

}
