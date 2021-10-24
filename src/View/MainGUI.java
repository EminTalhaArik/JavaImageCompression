package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import Manager.*;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private Helper helper = new Helper();
	private JTextField txtOriginalImage;
	private JTextField txtCompressionImage;
	private JTextField txtCompressionQuality;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public static void CompressJPEGImage(File originalImage, File compressImage, float compressionQuality)
			throws IOException {
		RenderedImage image = ImageIO.read(originalImage);
		ImageWriter jpegwriter = ImageIO.getImageWritersByFormatName("jpg").next();
		ImageWriteParam jpegwriterparam = jpegwriter.getDefaultWriteParam();
		jpegwriterparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegwriterparam.setCompressionQuality(compressionQuality);

		try (ImageOutputStream output = ImageIO.createImageOutputStream(compressImage)) {
			jpegwriter.setOutput(output);
			IIOImage outputimage = new IIOImage(image, null, null);
			jpegwriter.write(null, outputimage, jpegwriterparam);
			jpegwriter.dispose();
		}

	}

	public MainGUI() {
		setTitle("Image Compression");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlUyari = new JPanel();
		pnlUyari.setBackground(Color.RED);
		pnlUyari.setBounds(10, 378, 692, 54);
		contentPane.add(pnlUyari);
		pnlUyari.setLayout(null);
		pnlUyari.setVisible(false);

		JLabel lblUyari = new JLabel("");
		lblUyari.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblUyari.setForeground(Color.WHITE);
		lblUyari.setBackground(Color.WHITE);
		lblUyari.setBounds(10, 10, 590, 34);
		pnlUyari.add(lblUyari);

		JLabel lblNewLabel_1_3 = new JLabel("X");
		lblNewLabel_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pnlUyari.setVisible(false);

			}
		});
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		lblNewLabel_1_3.setBounds(663, 10, 19, 34);
		pnlUyari.add(lblNewLabel_1_3);

		JButton btnBrowseOriginalImage = new JButton("Browse");
		btnBrowseOriginalImage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnBrowseOriginalImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = helper.showFileChooser();

				int result = fileChooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();

					txtOriginalImage.setText(path);

				} else if (result == JFileChooser.CANCEL_OPTION) {

					System.out.println("No File Selected");
					pnlUyari.setVisible(true);
					pnlUyari.setBackground(Color.YELLOW);
					lblUyari.setText("No File Selected");
				}

			}
		});
		btnBrowseOriginalImage.setBounds(517, 119, 139, 40);
		contentPane.add(btnBrowseOriginalImage);

		txtOriginalImage = new JTextField();
		txtOriginalImage.setBounds(134, 123, 360, 34);
		contentPane.add(txtOriginalImage);
		txtOriginalImage.setColumns(10);

		JLabel lblOriginalImage = new JLabel("----- Original Image -----");
		lblOriginalImage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblOriginalImage.setBounds(229, 96, 217, 22);
		contentPane.add(lblOriginalImage);

		JLabel lblCompressionImage = new JLabel("----- Compressed Image -----");
		lblCompressionImage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblCompressionImage.setBounds(229, 169, 217, 22);
		contentPane.add(lblCompressionImage);

		txtCompressionImage = new JTextField();
		txtCompressionImage.setColumns(10);
		txtCompressionImage.setBounds(134, 196, 360, 34);
		contentPane.add(txtCompressionImage);

		JLabel lblNewLabel_1 = new JLabel("Image :");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(50, 192, 74, 40);
		contentPane.add(lblNewLabel_1);

		JButton btnBrowseCompressionImage = new JButton("Browse");
		btnBrowseCompressionImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = helper.showFileChooser();

				int result = fileChooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();

					txtCompressionImage.setText(path);

				} else if (result == JFileChooser.CANCEL_OPTION) {

					System.out.println("No File Selected");

					pnlUyari.setVisible(true);
					pnlUyari.setBackground(Color.YELLOW);
					lblUyari.setText("No File Selected");

				}

			}
		});
		btnBrowseCompressionImage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnBrowseCompressionImage.setBounds(517, 192, 138, 40);
		contentPane.add(btnBrowseCompressionImage);

		JLabel lblNewLabel_2 = new JLabel("Image Compression");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel_2.setBounds(191, 22, 268, 54);
		contentPane.add(lblNewLabel_2);

		JLabel lnlIslemControl = new JLabel("( 0.20, 0.35, 0.50, 0.75, 0.90 vs )");
		lnlIslemControl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lnlIslemControl.setBounds(229, 314, 182, 22);
		contentPane.add(lnlIslemControl);

		JButton btnNewButton = new JButton("Compress Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtOriginalImage.getText().length() == 0 || txtCompressionImage.getText().length() == 0
						|| txtCompressionQuality.getText().length() == 0) {

					pnlUyari.setVisible(true);
					pnlUyari.setBackground(Color.RED);

					lblUyari.setText("please fill in all fields");

				} else {

					File originalImage = new File(txtOriginalImage.getText());
					File compressionImage = new File(txtCompressionImage.getText());
					float compressedQuality = 0;

					try {
						compressedQuality = Float.parseFloat(txtCompressionQuality.getText());

					} catch (Exception ex) {

					}

					if (compressedQuality != 0) {
						try {
							CompressJPEGImage(originalImage, compressionImage, compressedQuality);

							pnlUyari.setVisible(true);
							pnlUyari.setBackground(Color.GREEN);
							lblUyari.setText("Done !");

						} catch (IOException e1) {

							pnlUyari.setVisible(true);
							pnlUyari.setBackground(Color.RED);
							lblUyari.setText("ERROR !");
							e1.printStackTrace();
						}
					} else {

						pnlUyari.setVisible(true);
						pnlUyari.setBackground(Color.BLUE);
						lblUyari.setText("the ratio value is incorrect");

					}

				}

			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(512, 296, 190, 54);
		contentPane.add(btnNewButton);

		txtCompressionQuality = new JTextField();
		txtCompressionQuality.setColumns(10);
		txtCompressionQuality.setBounds(227, 266, 64, 34);
		contentPane.add(txtCompressionQuality);

		JLabel lblNewLabel_1_1 = new JLabel("Enter Ratio :");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(101, 258, 116, 40);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Image :");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(50, 117, 74, 40);
		contentPane.add(lblNewLabel_1_2);

	}
}
