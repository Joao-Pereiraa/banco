import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;


public class Banco {
	

	public static void main(String[] args){
		
		String server = "PC-TANIA\\SQLEXPRESS";
		int port = 51734;
		String user = "Joao Luiz";
		String pass = "joaoluiz";
		String database = "academia";
		String jdbcurl = "jdbc:sqlserver://"+server+":"+port+";user="+user+";password="+pass+";databasename="+database+"";
		Connection con = null;
			
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(jdbcurl);
			
			
			
			// Criar Statement
			
			Statement stmt = con.createStatement();
			
			//inserir dados  
			
			//stmt.executeUpdate("insert into ESTOQUE(COD_PRODUTO,NOME,VALOR, QTD_ESTOQUE, FORNECEDOR) values(1, 'romero', 333, NULL, NULL) ");
					
			//atualizar dados //
			
		   stmt.executeUpdate(" update ESTOQUE set NOME = 'NOME ALTERADO','NOME ALTERADO' where COD_PRODUTO = 2 ");
			
			//deletar dados //
			
			// stmt.executeUpdate(" delete from ESTOQUE where NOME = 'NOME ALTERADO' ");
			
			//selecionar as tabelas //
			
		     ResultSet resultado = stmt.executeQuery("Select* from ESTOQUE  ");
			
			while(resultado.next()){
			 System.out.print(resultado.getInt("COD_PRODUTO")+ "\t");
			 System.out.print(resultado.getString("NOME")+ "\t");
			 System.out.print(resultado.getInt("VALOR")+ "\t");
			 System.out.print(resultado.getInt("QTD_ESTOQUE")+ "\t");
			 System.out.println(resultado.getString("FORNECEDOR"));
			}
			
			resultado.close();
			stmt.close();
			con.close();
			
			//       //
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
