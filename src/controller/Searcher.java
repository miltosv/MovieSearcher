package controller;

import org.apache.lucene.document.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Searcher extends Command {
	
	private IndexSearcher searcher = null;
    private QueryParser parser = null;
    private String indexPath="indexDir";
    //private Controller controller;
    static final int NUMBEROFRESULTS = 10;
    private TopDocs TopResults;
    /*
    public Searcher(){
        //searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexPath))));
        parser = new QueryParser("Plot", new EnglishAnalyzer());
    }
    */
    public Searcher(Controller ct) {
    	super(ct);
    	String [] fields = {"Plot","Genre","Director","Cast","Title","Year","Origin"};
    	parser = new MultiFieldQueryParser(fields, new EnglishAnalyzer());
    	//parser.setSplitOnWhitespace(true);
    	//parser.setAutoGeneratePhraseQueries(true);
    	
    }
    
    @Deprecated
    private TopDocs search(String q,int topN){
    	try {
			searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexPath))));
			System.out.println("Searching for"+q);
	    	Query query = parser.parse(q);
	        return searcher.search(query, topN);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    @Deprecated
    private TopDocs searchNextResults(String q,int topN,ScoreDoc lastTop) {
    	
    	try {
			searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexPath))));
			//System.out.println("Searching for"+q);
	    	Query query = parser.parse(q);
	        return searcher.searchAfter(lastTop, query, topN);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
    	
    	
    }
    
    
    public void execute(String options[]) {
    	String ordr = controller.getOrder();
    	Sort order = new Sort();
    	//order.setSort(new SortField[] { SortField.FIELD_SCORE, SortField.FIELD_DOC });
    	if(ordr.equals("RELEVANCE")) {
    		order = new Sort();
    		order= Sort.RELEVANCE;    		
    	}else {
    		order = new Sort();
    		order = Sort.INDEXORDER;
    	}
    	
    	
    	String q=options[1];
    	int page=Integer.parseInt(options[2]);
    	try {
    		searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexPath))));
    		searcher.setSimilarity(new BM25Similarity());
    		Query query = parser.parse(q);
    		System.out.println(query.toString());
    		if (page == 0) {
    			System.out.println("Searching for"+q);
    			TopResults = searcher.search(query, NUMBEROFRESULTS/*,order*/);
    			controller.setSearchResult(TopResults);    		
    		}else {
    			ScoreDoc after=null;
        		for (ScoreDoc sd : TopResults.scoreDocs) {
        			after=sd;
        			
        		}
        		TopResults = searcher.searchAfter(after, query, NUMBEROFRESULTS/*,order*/);
        		controller.setSearchResult(TopResults);
        		
        		
    		}
    	} catch (IOException | ParseException e) {
    		e.printStackTrace();
    	}
    	
    	/*
    	int page=Integer.parseInt(options[2]);
    	System.out.println(page);
    	if (page == 0) {
    		TopResults = this.search(options[1],NUMBEROFRESULTS);
    		controller.setSearchResult(TopResults);
    	}
    	else{
    		ScoreDoc after=null;
    		for (ScoreDoc sd : TopResults.scoreDocs) {
    			after=sd;
    			
    		}
    		TopResults = this.searchNextResults(options[1],NUMBEROFRESULTS,after);
    		controller.setSearchResult(TopResults);
    		
    		}
    	
    	
    	*/
    	
    }
    
    

}
