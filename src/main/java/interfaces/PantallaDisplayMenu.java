package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classes.Menu;
import classes.User;
import enums.DayofWeek;
import enums.IMCGradation;
import exceptions.CampoVacioException;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;

public class PantallaDisplayMenu  extends JPanel{
	private Ventana ventana;
	private User usuario;
	private Menu menu;
	private JTable table;
	private JLabel comidaMiercoles;
	
	
	public PantallaDisplayMenu(Ventana v, User u)  {
		this.ventana=v;
		this.menu = menu;
		this.usuario=u;
		this.setSize(1154, 558);
	

		v.setResizable(false);
		setLayout(null);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(0, 0, 0, 0);
		add(panelCentral);
		panelCentral.setLayout(null);
		
		JLabel labelLunes = new JLabel("LUNES");
		labelLunes.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelLunes.setBounds(145, 48, 52, 25);
		add(labelLunes);
		
		JLabel lblMartes = new JLabel("MARTES");
		lblMartes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMartes.setBounds(268, 48, 59, 25);
		add(lblMartes);
		
		JLabel lblMiercoles = new JLabel("MIÉRCOLES");
		lblMiercoles.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMiercoles.setBounds(409, 48, 86, 25);
		add(lblMiercoles);
		
		JLabel lblJueves = new JLabel("JUEVES");
		lblJueves.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJueves.setBounds(577, 48, 65, 25);
		add(lblJueves);
		
		JLabel lblViernes = new JLabel("VIERNES");
		lblViernes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblViernes.setBounds(724, 48, 65, 25);
		add(lblViernes);
		
		JLabel lblSabado = new JLabel("SÁBADO");
		lblSabado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSabado.setBounds(871, 48, 65, 25);
		add(lblSabado);
		
		JLabel lblDomingo = new JLabel("DOMINGO");
		lblDomingo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDomingo.setBounds(1018, 48, 75, 25);
		add(lblDomingo);
		
		JLabel lblDesayuno = new JLabel("DESAYUNO");
		lblDesayuno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDesayuno.setBounds(21, 141, 86, 25);
		add(lblDesayuno);
		
		JLabel lblComida = new JLabel("COMIDA");
		lblComida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblComida.setBounds(21, 281, 75, 25);
		add(lblComida);
		
		JLabel lblCena = new JLabel("CENA");
		lblCena.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCena.setBounds(21, 437, 52, 25);
		add(lblCena);
		
		JLabel desayunoLunes = new JLabel("0");
		desayunoLunes.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoLunes.setBackground(Color.MAGENTA);
		desayunoLunes.setBounds(134, 118, 75, 75);
		add(desayunoLunes);
		
		JLabel comidaLunes = new JLabel("0");
		comidaLunes.setHorizontalAlignment(SwingConstants.CENTER);
		comidaLunes.setBackground(Color.MAGENTA);
		comidaLunes.setBounds(134, 258, 75, 75);
		add(comidaLunes);
		
		JLabel cenaLunes = new JLabel("0");
		cenaLunes.setHorizontalAlignment(SwingConstants.CENTER);
		cenaLunes.setBackground(Color.MAGENTA);
		cenaLunes.setBounds(134, 414, 75, 75);
		add(cenaLunes);
		
		JLabel desayunoMartes = new JLabel("0");
		desayunoMartes.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoMartes.setBackground(Color.MAGENTA);
		desayunoMartes.setBounds(260, 118, 75, 75);
		add(desayunoMartes);
		
		JLabel comidaMartes = new JLabel("0");
		comidaMartes.setHorizontalAlignment(SwingConstants.CENTER);
		comidaMartes.setBackground(Color.MAGENTA);
		comidaMartes.setBounds(260, 258, 75, 75);
		add(comidaMartes);
		
		JLabel cenaMartes = new JLabel("0");
		cenaMartes.setHorizontalAlignment(SwingConstants.CENTER);
		cenaMartes.setBackground(Color.MAGENTA);
		cenaMartes.setBounds(260, 414, 75, 75);
		add(cenaMartes);
		
		JLabel desayunoMiercoles = new JLabel("0");
		desayunoMiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoMiercoles.setBackground(Color.MAGENTA);
		desayunoMiercoles.setBounds(415, 118, 75, 75);
		add(desayunoMiercoles);
		
		comidaMiercoles = new JLabel("0");
		comidaMiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		comidaMiercoles.setBackground(Color.MAGENTA);
		comidaMiercoles.setBounds(415, 258, 75, 75);
		add(comidaMiercoles);
		
		JLabel cenaMiercoles = new JLabel("0");
		cenaMiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		cenaMiercoles.setBackground(Color.MAGENTA);
		cenaMiercoles.setBounds(415, 414, 75, 75);
		add(cenaMiercoles);
		
		JLabel desayunoJueves = new JLabel("0");
		desayunoJueves.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoJueves.setBackground(Color.MAGENTA);
		desayunoJueves.setBounds(572, 118, 75, 75);
		add(desayunoJueves);
		
		JLabel comidaJueves = new JLabel("0");
		comidaJueves.setHorizontalAlignment(SwingConstants.CENTER);
		comidaJueves.setBackground(Color.MAGENTA);
		comidaJueves.setBounds(1018, 403, 75, 75);
		add(comidaJueves);
		
		JLabel cenaJueves = new JLabel("0");
		cenaJueves.setHorizontalAlignment(SwingConstants.CENTER);
		cenaJueves.setBackground(Color.MAGENTA);
		cenaJueves.setBounds(572, 414, 75, 75);
		add(cenaJueves);
		
		JLabel desayunoViernes = new JLabel("0");
		desayunoViernes.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoViernes.setBackground(Color.MAGENTA);
		desayunoViernes.setBounds(719, 118, 75, 75);
		add(desayunoViernes);
		
		JLabel comidaViernes = new JLabel("0");
		comidaViernes.setHorizontalAlignment(SwingConstants.CENTER);
		comidaViernes.setBackground(Color.MAGENTA);
		comidaViernes.setBounds(719, 258, 75, 75);
		add(comidaViernes);
		
		JLabel cenaViernes = new JLabel("0");
		cenaViernes.setHorizontalAlignment(SwingConstants.CENTER);
		cenaViernes.setBackground(Color.MAGENTA);
		cenaViernes.setBounds(719, 414, 75, 75);
		add(cenaViernes);
		
		JLabel desayunoSabado = new JLabel("0");
		desayunoSabado.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoSabado.setBackground(Color.MAGENTA);
		desayunoSabado.setBounds(866, 118, 75, 75);
		add(desayunoSabado);
		
		JLabel comidaSabado = new JLabel("0");
		comidaSabado.setHorizontalAlignment(SwingConstants.CENTER);
		comidaSabado.setBackground(Color.MAGENTA);
		comidaSabado.setBounds(866, 258, 75, 75);
		add(comidaSabado);
		
		JLabel cenaSabado = new JLabel("0");
		cenaSabado.setHorizontalAlignment(SwingConstants.CENTER);
		cenaSabado.setBackground(Color.MAGENTA);
		cenaSabado.setBounds(866, 414, 75, 75);
		add(cenaSabado);
		
		JLabel desayunoDomingo = new JLabel("0");
		desayunoDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		desayunoDomingo.setBackground(Color.MAGENTA);
		desayunoDomingo.setBounds(1018, 118, 75, 75);
		add(desayunoDomingo);
		
		JLabel comidaDomingo = new JLabel("0");
		comidaDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		comidaDomingo.setBackground(Color.MAGENTA);
		comidaDomingo.setBounds(1018, 258, 75, 75);
		add(comidaDomingo);
		
		JLabel cenaDomingo = new JLabel("0");
		cenaDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		cenaDomingo.setBackground(Color.MAGENTA);
		cenaDomingo.setBounds(0, 0, 1154, 558);
		add(cenaDomingo);
		String imagePath = "";
	

		v.setResizable(false);
		
		LocalDate date = LocalDate.now();
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(); 
		byte weekNumber = (byte) date.get(woy);
	
		
		
	}
}

