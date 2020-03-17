package FTP_Grafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frame;
	private JTextField txtServer;
	private JTextField txtUser;
	private JTextField txtPass;
	private FTPClient cliente;
	private String server;
	private String user;
	private String pass = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 476, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblServidorFtp = new JLabel("Servidor FTP: ");
		lblServidorFtp.setForeground(Color.BLUE);
		lblServidorFtp.setFont(new Font("Arial", Font.BOLD, 12));
		lblServidorFtp.setBounds(10, 11, 86, 20);
		frame.getContentPane().add(lblServidorFtp);

		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setForeground(Color.BLUE);
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		lblUsuario.setBounds(10, 42, 86, 20);
		frame.getContentPane().add(lblUsuario);

		txtServer = new JTextField();
		txtServer.setForeground(Color.BLUE);
		txtServer.setBounds(106, 12, 119, 20);
		frame.getContentPane().add(txtServer);
		txtServer.setColumns(10);

		txtUser = new JTextField();
		txtUser.setForeground(Color.BLUE);
		txtUser.setColumns(10);
		txtUser.setBounds(106, 43, 119, 20);
		frame.getContentPane().add(txtUser);

		JTextArea txtInfo = new JTextArea();
		txtInfo.setBounds(10, 73, 287, 437);
		frame.getContentPane().add(txtInfo);

		JButton btnConnect = new JButton("Conectar");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = new FTPClient();
				server = txtServer.getText();
				user = txtUser.getText();
				pass = txtPass.getText();
				try {
					cliente.connect(server);
					boolean login = cliente.login(user, pass);
					if (login) {
						JOptionPane.showMessageDialog(null,
								"Se ha concedido la conectividad de la sesión complacientemente");
						txtInfo.append(cliente.getReplyString());
					} else {
						System.out.println("El usuario y/o contraseña es incorrecto");
					}

				} catch (SocketException e1) {
					JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor: " + e1.getMessage());
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor: " + e2.getMessage());
				} // fin bloque try/catch

			} // fin metodo actionPerformed()
		});
		btnConnect.setFont(new Font("Arial", Font.BOLD, 12));
		btnConnect.setBounds(235, 11, 197, 23);
		frame.getContentPane().add(btnConnect);

		JLabel lblClave = new JLabel("Clave: ");
		lblClave.setForeground(Color.BLUE);
		lblClave.setFont(new Font("Arial", Font.BOLD, 12));
		lblClave.setBounds(235, 46, 86, 20);
		frame.getContentPane().add(lblClave);

		txtPass = new JTextField();
		txtPass.setForeground(Color.BLUE);
		txtPass.setColumns(10);
		txtPass.setBounds(286, 43, 146, 20);
		frame.getContentPane().add(txtPass);

		JButton btnSubir = new JButton("Subir fichero");
		btnSubir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubir.setBounds(307, 142, 143, 23);
		frame.getContentPane().add(btnSubir);

		JButton btnDescargar = new JButton("Descargar fichero");
		btnDescargar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDescargar.setBounds(307, 189, 143, 23);
		frame.getContentPane().add(btnDescargar);

		JButton btnEliminar = new JButton("Eliminar fichero");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setBounds(307, 242, 143, 23);
		frame.getContentPane().add(btnEliminar);

		JButton btnCrear = new JButton("Crear fichero");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrear.setBounds(307, 293, 143, 23);
		frame.getContentPane().add(btnCrear);

		JButton btnElimCarp = new JButton("Eliminar carpeta");
		btnElimCarp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnElimCarp.setBounds(307, 340, 143, 23);
		frame.getContentPane().add(btnElimCarp);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cliente.disconnect();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "No se ha podido conectar al servidor");
				} // fin bloque try/catch
				System.exit(1);
			} // fin metodo actionPerformed()
		});
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBounds(307, 388, 143, 23);
		frame.getContentPane().add(btnSalir);
	}
}
