package tradutor;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;

public class Fragmentos {

	public static String highlight(String pText, String pQuery)
			throws Exception {
		BrazilianAnalyzer analyzer = new BrazilianAnalyzer(Version.LUCENE_34);
		QueryParser parser = new QueryParser(Version.LUCENE_34, "contents",
				analyzer);

		Scorer scorer = new QueryScorer(parser.parse(pQuery));
		Formatter formatter = new SimpleHTMLFormatter("<des>", "</des>");
		Highlighter highlighter = new Highlighter(formatter, scorer);

		Fragmenter fragmenter = new SimpleFragmenter(100);
		highlighter.setTextFragmenter(fragmenter);

		String text = highlighter.getBestFragment(analyzer, "contents", pText);

		if (text != null) {
			return text;
		}
		return pText;
	}

	public String extraiFragmento(String nomeArquivo, String texto) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(nomeArquivo);
			String data = IOUtils.toString(inputStream);
			return highlight(data, texto);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return "Fragmento vazio";
	}
}