package model;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.index.*;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.store.*;
import org.apache.lucene.util.Version;
import org.eclipse.jetty.http.HttpParser.FieldState;
import org.apache.lucene.document.*;

public class Indexer {
	private IndexWriter writer;
	private Directory dir;
	private Reader csvFile;
	private String indexPath="indexDir";
	private String dataPath="data.csv";
	//private EnglishAnalyzer analyser;
	//private Iterable<CSVRecord> records;
	private ArrayList<Document> parsedArticles;
	
	public Indexer(){
		try {
			csvFile= new FileReader(dataPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//analyser = new EnglishAnalyzer();
		System.out.println(Paths.get(indexPath));
		try {
			dir = FSDirectory.open(Paths.get(indexPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			IndexWriterConfig indxCnfg= new IndexWriterConfig(new EnglishAnalyzer());
			indxCnfg.setSimilarity(new BM25Similarity());
			writer=new IndexWriter(dir,indxCnfg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parsedArticles=new ArrayList<Document>();
		
	}
	
	public void parse() throws IOException {
		CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';').parse(csvFile);
		for (CSVRecord record : csvParser) {
			Document doc = new Document();
			doc.add(new TextField("Title",record.get("Title"),Field.Store.YES));
			doc.add(new TextField("Origin",record.get("Origin/Ethnicity"),Field.Store.YES));
			doc.add(new TextField("Plot",record.get("Plot"),Field.Store.YES));
			doc.add(new StringField("Year",record.get("ReleaseYear"),Field.Store.YES)); //
			doc.add(new TextField("Cast",record.get("Cast"),Field.Store.YES));
			doc.add(new TextField("Director",record.get("Director"),Field.Store.YES));
			doc.add(new StringField("Link",record.get("WikiPage"),Field.Store.YES));
			doc.add(new TextField("Genre",record.get("Genre"),Field.Store.YES));
		    parsedArticles.add(doc);
		    
		}
	}
	
	public void indexDocs() throws IOException {
		
		for (Document doc : parsedArticles) {
			
			writer.addDocument(doc);
		}
		writer.close();
		parsedArticles.clear();
		
		
	}
	public void executeIndexing() {
		System.out.println("indexing starting");
		try {
			this.parse();
			this.indexDocs();
		} catch (IOException e) {
			System.out.println("Error while indexing");
			e.printStackTrace();
		}
		
		
	}

}
