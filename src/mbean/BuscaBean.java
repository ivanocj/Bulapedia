package mbean;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import dao.ConsultaRemedio;

import tradutor.Fragmentos;

public class BuscaBean {
	String textoBusca;
	String resultadoBusca;
	static String BUSCA_VAZIA = "Por favor volte e escreve algo na sua procura.";

	public String getresultadoBusca() throws Exception {
		if (!textoBusca.isEmpty()) {
			return search(textoBusca);
		}
		return BUSCA_VAZIA;
	}

	List<String> lista = new ArrayList<String>();

	Calendar now = Calendar.getInstance();

	// construtor da lista de conteudo
	public BuscaBean() throws SQLException {
		ConsultaRemedio consultaRemedio = new ConsultaRemedio();
		for (String elem : consultaRemedio.consulta()) {
			lista.add(elem);
		}
	}

	public List<String> autocompletar(Object suggest) {
		String pref = (String) suggest;
		ArrayList<String> result = new ArrayList<String>();

		for (String elem : lista) {
			if ((elem.toLowerCase().indexOf(pref.toLowerCase()) == 0)) {
				result.add(elem);
			}
		}
		return result;
	}

	public String getTextobusca() throws Exception {
		return textoBusca;
	}

	public void setTextobusca(String textobusca) {
		this.textoBusca = textobusca;
	}

	public String search(String q) throws Exception {
		String caminhoIndexacao = "C:/Users/costajui/workspace/bulapedia/indice";
		File indexDir = new File(caminhoIndexacao);
		Directory fsDir = FSDirectory.open(indexDir);
		IndexSearcher is = new IndexSearcher(fsDir);
		BrazilianAnalyzer standardAnalyzer = new BrazilianAnalyzer(
				Version.LUCENE_34);
		QueryParser parser = new QueryParser(Version.LUCENE_34, "contents",
				standardAnalyzer);
		Query query = parser.parse(q);
		int numHits = 1000000;
		long start = new Date().getTime();
		TopScoreDocCollector collector = TopScoreDocCollector.create(numHits,
				true);
		is.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		long end = new Date().getTime();

		String numFound = "<p><h3>Encontrado(s) " + hits.length
				+ " documento(s) (em " + (end - start)
				+ " milisegundos) que correspondem a procura '" + q
				+ "':</h3></p>";

		Fragmentos frag = new Fragmentos();

		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			Document doc = is.doc(docId);
			String nomeArquivo = doc.get("nomedoarquivo").toString();
			numFound += "<br><strong>Arquivo: </strong>" + nomeArquivo + "<br>";
			numFound += frag.extraiFragmento(nomeArquivo, q) + "<br>";
		}
		return numFound;
	}

}
