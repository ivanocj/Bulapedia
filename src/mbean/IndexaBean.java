package mbean;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexaBean {
	String results = "Nenhum arquivo foi indexado ou erro não foi previsto.";

	public String getResults() {
		String caminhoIndexacao = "C:/Users/costajui/workspace/bulapedia/indice";
		String caminhoDados = "C:/Users/costajui/workspace/bulapedia/dados";
		try {
			results = retornaNumArquivosIndexados(caminhoIndexacao,
					caminhoDados);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public String retornaNumArquivosIndexados(String dirPath, String dataPath)
			throws IOException {
		File indexDir = new File(dirPath);
		File dataDir = new File(dataPath);
		int numIndexed = index(indexDir, dataDir);
		return "Foram indexados " + numIndexed + " arquivos com sucesso.";
	}

	// Abre um indice static int e começa a travessia de diretórios
	public static int index(File indexDir, File dataDir) throws IOException {
		BrazilianAnalyzer analyzer = new BrazilianAnalyzer(Version.LUCENE_34);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34,
				analyzer);
		Directory fsDir = FSDirectory.open(indexDir);
		IndexWriter writer = new IndexWriter(fsDir, config);
		indexDirectory(writer, dataDir);
		int numIndexed = writer.maxDoc();
		writer.optimize();
		writer.close();
		return numIndexed;
	}

	// método recursivo que chama a si mesmo quando descobre um diretório
	private static void indexDirectory(IndexWriter writer, File dir)
			throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f);
			} else if (f.getName().endsWith(".txt")) {
				indexFile(writer, f);
			}
		}

	}

	// método para indexar um arquivo
	private static void indexFile(IndexWriter writer, File f)
			throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}

		System.out.println("Indexando " + f.getCanonicalPath());

		Document doc = new Document();
		doc.add(new Field("contents", new FileReader(f)));
		doc.add(new Field("nomedoarquivo", f.getCanonicalPath(),
				Field.Store.YES, Field.Index.ANALYZED));
		writer.addDocument(doc);
	}

}
