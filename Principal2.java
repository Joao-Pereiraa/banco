import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DatabaseMetaData;
import java.sql.Statement;


public class Principal2 extends JFrame {

	private JPanel caixa;
	private static JTextField txt_codProduto;
	private static JTextField txtNome;
	private JLabel label_3;
	private static JTextField txtFornecedor;
	private JLabel label_2;
	private JLabel label_4;
	private JLabel label_5;
	private static JTextField txtValor;
	private static JTextField txtQtdEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal2 frame = new Principal2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private  static void limparTela() {
		
		txt_codProduto.setText("");		
		txtNome.setText("");	
		txtFornecedor.setText("");
		txtQtdEstoque.setText("");
		txtValor.setText("");
		
	}
	
	/**
	 * Create the frame.
	 */
	public Principal2() {
		setTitle("Cadastrar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 300);
		caixa = new JPanel();
		caixa.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(caixa);
		caixa.setLayout(null);
		
		JLabel label = new JLabel("COD_PRODUTO");
		label.setBounds(21, 31, 80, 23);
		caixa.add(label);
		
		txt_codProduto = new JTextField();
		txt_codProduto.setColumns(10);
		txt_codProduto.setBounds(100, 31, 61, 22);
		caixa.add(txt_codProduto);
		
		JLabel label_1 = new JLabel("NOME");
		label_1.setBounds(21, 82, 61, 14);
		caixa.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(100, 79, 150, 20);
		caixa.add(txtNome);
		
		label_3 = new JLabel("FORNECEDOR");
		label_3.setBounds(21, 120, 80, 14);
		caixa.add(label_3);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setColumns(10);
		txtFornecedor.setBounds(100, 117, 154, 20);
		caixa.add(txtFornecedor);
		
		JButton btnCadastrar = new JButton("Cadastrar ");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					
					if(txt_codProduto.getText().equals("") || txtNome.getText().equals("") || txtFornecedor.getText().equals("") || txtQtdEstoque.getText().equals("") || txtValor.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane()," Favor preencher todos os campos ");
					}
					else{
						stmt.executeUpdate("insert into ESTOQUE(COD_PRODUTO,NOME,VALOR, QTD_ESTOQUE, FORNECEDOR) values('"+(txt_codProduto.getText())+"','"+(txtNome.getText())+"','"+(txtValor.getText())+"','"+(txtQtdEstoque.getText())+"','"+(txtFornecedor.getText())+"')" );
						JOptionPane.showMessageDialog(getContentPane()," cliente inserido com sucesso ! ");
							
						limparTela();
						
					}				
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}
	
		});
		btnCadastrar.setBounds(76, 196, 109, 23);
		caixa.add(btnCadastrar);
	
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				limparTela();
			
				txt_codProduto.setText("");		
				txtNome.setText("");	
				txtFornecedor.setText("");
				txtQtdEstoque.setText("");
				txtValor.setText("");
			}
		});
		btnLimpar.setBounds(244, 196, 109, 23);
		caixa.add(btnLimpar);
		
		label_2 = new JLabel("");
		label_2.setBounds(292, 82, 46, 14);
		caixa.add(label_2);
		
		label_4 = new JLabel("VALOR");
		label_4.setBounds(312, 82, 46, 14);
		caixa.add(label_4);
		
		label_5 = new JLabel("QTD_ESTOQUE");
		label_5.setBounds(292, 120, 80, 14);
		caixa.add(label_5);
		
		txtValor = new JTextField();
		txtValor.setBounds(376, 79, 80, 20);
		caixa.add(txtValor);
		txtValor.setColumns(10);
		
		txtQtdEstoque = new JTextField();
		txtQtdEstoque.setColumns(10);
		txtQtdEstoque.setBounds(376, 117, 80, 20);
		caixa.add(txtQtdEstoque);
	}
}
