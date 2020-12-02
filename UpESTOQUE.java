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
import java.sql.SQLException;
import java.sql.Statement;


public class UpESTOQUE extends JFrame {

	private JPanel contentPane;
	private JTextField txt_codProduto;
	private JTextField txtNome;
	private JTextField txtFornecedor;
	private JTextField txtValor;
	private JTextField txtQtdEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpESTOQUE frame = new UpESTOQUE();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpESTOQUE() {
		setTitle("Alterar dados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(-14, 11, 486, 261);
		contentPane.add(panel);
		
		JLabel label = new JLabel("COD_PRODUTO");
		label.setBounds(21, 31, 80, 23);
		panel.add(label);
		
		txt_codProduto = new JTextField();
		txt_codProduto.setColumns(10);
		txt_codProduto.setBounds(100, 31, 61, 22);
		panel.add(txt_codProduto);
		
		JLabel label_1 = new JLabel("NOME");
		label_1.setBounds(21, 82, 61, 14);
		panel.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(100, 79, 150, 20);
		panel.add(txtNome);
		
		JLabel label_2 = new JLabel("FORNECEDOR");
		label_2.setBounds(21, 120, 80, 14);
		panel.add(label_2);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setColumns(10);
		txtFornecedor.setBounds(100, 117, 154, 20);
		panel.add(txtFornecedor);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txt_codProduto.setText("");		
				txtNome.setText("");	
				txtFornecedor.setText("");
				txtQtdEstoque.setText("");
				txtValor.setText("");
			}
		});
		btnCancelar.setBounds(292, 195, 109, 23);
		panel.add(btnCancelar);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(292, 82, 46, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("VALOR");
		label_4.setBounds(312, 82, 46, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("QTD_ESTOQUE");
		label_5.setBounds(292, 120, 80, 14);
		panel.add(label_5);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(376, 79, 80, 20);
		panel.add(txtValor);
		
		txtQtdEstoque = new JTextField();
		txtQtdEstoque.setColumns(10);
		txtQtdEstoque.setBounds(376, 117, 80, 20);
		panel.add(txtQtdEstoque);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
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
						 stmt.executeUpdate(" update ESTOQUE set NOME = '"+(txtNome.getText())+"' where COD_PRODUTO = '"+(txt_codProduto.getText())+"' ");
						 stmt.executeUpdate(" update ESTOQUE set VALOR = '"+(txtValor.getText())+"' where COD_PRODUTO = '"+(txt_codProduto.getText())+"' ");
						 stmt.executeUpdate(" update ESTOQUE set QTD_ESTOQUE = '"+(txtQtdEstoque.getText())+"' where COD_PRODUTO = '"+(txt_codProduto.getText())+"' ");
						 stmt.executeUpdate(" update ESTOQUE set FORNECEDOR = '"+(txtFornecedor.getText())+"' where COD_PRODUTO = '"+(txt_codProduto.getText())+"' ");
							
						 JOptionPane.showMessageDialog(getContentPane()," Dados atualizado com sucesso ! ");
					
						 txt_codProduto.setText("");	
						 txtNome.setText("");	
						 txtValor.setText("");	
						 txtQtdEstoque.setText("");	
						 txtFornecedor.setText("");
					
					}
					
				 
					 stmt.close();
						con.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}	
			
		});
		btnAtualizar.setBounds(111, 195, 109, 23);
		panel.add(btnAtualizar);
	}
}
