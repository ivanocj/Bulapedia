package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRemedio {

	static String CONSULTA_REMEDIO_POR_NOME = "SELECT * FROM test.medicamentos ORDER BY nome";

	public List<String> consulta() throws SQLException {
		//abre conexao
		ConectaDB conectaDB = new ConectaDB();
		Connection conexao = conectaDB.conecta();
		//cria a consulta
		List<String> lista = new ArrayList<String>();
		Statement statement = conexao.createStatement();
		ResultSet rs = statement.executeQuery(CONSULTA_REMEDIO_POR_NOME);
		while (rs.next()){
			lista.add(rs.getString("nome")+" - "+ rs.getString("principio"));
			//System.out.println(rs.getString("nome")+" - "+ rs.getString("principio"));
		}
		//fecha conexao
		conectaDB.desconecta(conexao);
		return lista;
	}
}
