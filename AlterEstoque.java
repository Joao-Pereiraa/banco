import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

public class AlterEstoque extends JFrame {

	private JPanel contentPane;
	private static JTable tblDados;
	private JTextField txt_codProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterEstoque frame = new AlterEstoque();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
private static void preencherTabela() {
	
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
		Statement stmt = con.createStatement();
	
	    ResultSet resultado = stmt.executeQuery("Select* from ESTOQUE  ");
		
	    DefaultTableModel dtm = (DefaultTableModel) tblDados.getModel();
	    
	    dtm.setNumRows(0);
	    
	    while(resultado.next()){
	    	dtm.addRow(new Object[]{
	    			
	    		resultado.getInt("COD_PRODUTO"),
	    		resultado.getString("NOME"),	
	    		resultado.getInt("VALOR"),	
	    		resultado.getInt("QTD_ESTOQUE"),	
	    		resultado.getString("FORNECEDOR")	

	    	});   
	    }
	    
	    tblDados.setModel(dtm);
		
		stmt.close();
		con.close();
		

	} catch (SQLException e) {
		e.printStackTrace();
	}}

/**
	 * Create the frame.
	 */
	public AlterEstoque() {
		setTitle("Alterar Dados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Carregar Dados");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				preencherTabela();	
			}
		});
		btnNewButton.setBounds(209, 11, 149, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 534, 227);
		contentPane.add(scrollPane);
		
		tblDados = new JTable();
		scrollPane.setViewportView(tblDados);
		tblDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"COD_PRODUTO", "NOME", "VALOR", "QTD_ESTOQUE", "FORNECEDOR"
			}
		));
		
		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
						UpESTOQUE up = new UpESTOQUE();
						up.setVisible(true);		
		
			}});
		btnNewButton_1.setBounds(338, 310, 101, 31);
		contentPane.add(btnNewButton_1);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
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
					Statement stmt = con.createStatement();
					
					if(txt_codProduto.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane()," Favor preencher o campo código");
					}
					else{
						 stmt.executeUpdate(" delete from ESTOQUE where COD_PRODUTO = '"+(txt_codProduto.getText())+"' ");
						JOptionPane.showMessageDialog(getContentPane()," cliente excluido com sucesso ! ");	
						
						txt_codProduto.setText("");	
					}				
					
					stmt.close();
					con.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}		
										
			}
		});
		btnExcluir.setBounds(326, 373, 101, 31);
		contentPane.add(btnExcluir);
		
		JLabel label = new JLabel("Cod_Produto");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(148, 372, 78, 31);
		contentPane.add(label);
		
		txt_codProduto = new JTextField();
		txt_codProduto.setColumns(10);
		txt_codProduto.setBounds(230, 372, 86, 31);
		contentPane.add(txt_codProduto);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Principal2 Inserir = new Principal2();
				Inserir.setVisible(true);
			}
		});
		btnInserir.setBounds(125, 310, 101, 31);
		contentPane.add(btnInserir);
	}
}
