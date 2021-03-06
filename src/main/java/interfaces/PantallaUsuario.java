package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classes.Menu;
import classes.Recipe;
import classes.User;
import enums.IMCGradation;
import exceptions.CampoVacioException;
import preferenceEnums.DietaryRestrictions;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

/**
 * Interfaz principal  de usuario donde este recibe información sobre su estado de salud en parámetros biomédicos, 
 * y puede conocer sus menus semanales, se le informa sobre su progreso, y puede acceder a la carga de nuevos productos 
 * @author Candido Vidal
 */
public class PantallaUsuario extends JPanel {

    private Ventana ventana;
    private JButton btnNewProducts;
    private JButton btnLogout;
    private User usuario;
    private Menu menu;
     /**
      * Constructor de la clase Pantalla usuario
      * @param v instancia de la clase ventana de la que dependen todas las ventanas del proyecto 
      * @param u instancia de la clase usuario del usuario actual 
      * @throws CampoVacioException 
      */
    public PantallaUsuario(Ventana v, User u) throws CampoVacioException {
        this.ventana = v;
        this.menu = menu;
        this.usuario = u;
        this.setSize(713, 551);

        v.setResizable(true);
        setLayout(new BorderLayout(0, 0));

        JPanel panelCentral = new JPanel();
        add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(null);
        btnNewProducts = new JButton("cargar nuevos productos");
        btnNewProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ventana.goChargeProducts(u);
                } catch (CampoVacioException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });
        btnNewProducts.setIcon(new ImageIcon("./images/baseline_sync_black_24dp.png"));
        btnNewProducts.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnNewProducts.setBounds(427, 30, 212, 30);
        panelCentral.add(btnNewProducts);

        LocalDate date = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        byte weekNumber = (byte) date.get(woy);

        //datos usuario
        JLabel lblUser = new JLabel("usuario:" + u.getUserName());
        lblUser.setHorizontalAlignment(SwingConstants.LEFT);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblUser.setBounds(41, 44, 129, 16);
        panelCentral.add(lblUser);

        JLabel firstNameLabel = new JLabel("nombre:" + u.getFirstName());
        firstNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        firstNameLabel.setBounds(41, 72, 129, 16);
        panelCentral.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("primer apellido:" + u.getLastName());
        lastNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        lastNameLabel.setBounds(41, 100, 167, 16);
        panelCentral.add(lastNameLabel);

        JLabel emailLabel = new JLabel("email:" + u.getEmail());
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        emailLabel.setBounds(41, 128, 177, 16);
        panelCentral.add(emailLabel);

        JButton btnNewButton;

        JButton searchRecipesButton = new JButton("buscador recetas");
        searchRecipesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    ventana.goSearchRecipes(u);
                } catch (CampoVacioException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        searchRecipesButton.setIcon(new ImageIcon("./images/icon_busqueda.png"));
        searchRecipesButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        searchRecipesButton.setBounds(427, 141, 212, 30);
        panelCentral.add(searchRecipesButton);

        JButton btnFavorites = new JButton("favoritos");
        btnFavorites.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnFavorites.setBounds(427, 100, 212, 30);
        panelCentral.add(btnFavorites);

        //datos biométricos 
        JLabel heightLabel = new JLabel("altura: " + u.getHeight());
        heightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        heightLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        heightLabel.setBounds(41, 299, 85, 16);
        panelCentral.add(heightLabel);

        JLabel weightLabel = new JLabel("peso: " + u.getWeight());
        weightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        weightLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        weightLabel.setBounds(41, 280, 85, 16);
        panelCentral.add(weightLabel);

        float imc = (float) u.calculateIMC(u.getWeight(), u.getHeight());

        IMCGradation gradeIMC = u.gradeIMC(imc);

        JLabel imcLabel = new JLabel("IMC: ");
        imcLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        imcLabel.setBounds(41, 318, 44, 16);
        panelCentral.add(imcLabel);

        String n = "";
        JLabel nutritionLabel = new JLabel("" + " " + n);
        nutritionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nutritionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        nutritionLabel.setBounds(95, 318, 91, 16);
        panelCentral.add(nutritionLabel);

        switch (gradeIMC) {

            case UNDERWEIGHT:
                n += "desnutrición";
                nutritionLabel = new JLabel(imc + "" + n);
                nutritionLabel.setForeground(new Color(189, 0, 0));
                nutritionLabel.setBounds(95, 318, 123, 16);
                panelCentral.add(nutritionLabel);
                break;
            case NORMAL_WEIGHT:
                n += "peso ideal";
                nutritionLabel = new JLabel(imc + "" + " " + n);
                nutritionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                nutritionLabel.setForeground(new Color(112, 223, 0));
                nutritionLabel.setBounds(95, 318, 123, 16);
                panelCentral.add(nutritionLabel);
                break;
            case OVERWEIGHT:
                n += "ligero sobrepeso";
                nutritionLabel = new JLabel(imc + "" + n);
                nutritionLabel.setForeground(new Color(255, 223, 0));
                nutritionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                nutritionLabel.setBounds(95, 318, 123, 16);
                panelCentral.add(nutritionLabel);
                break;
            case OBESE:
                n += "sobrepeso";
                nutritionLabel = new JLabel(imc + "" + n);
                nutritionLabel.setForeground(new Color(200, 0, 0));
                nutritionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                nutritionLabel.setBounds(95, 318, 123, 16);
                panelCentral.add(nutritionLabel);
                break;
            case EXTREMELY_OBESE:
                n += "obesidad";
                nutritionLabel = new JLabel(imc + "" + n);
                nutritionLabel.setForeground(new Color(220, 0, 0));
                nutritionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                nutritionLabel.setBounds(95, 318, 123, 16);
                panelCentral.add(nutritionLabel);
                break;

        }

        JLabel biometicDataLabel = new JLabel("Datos biométricos");
        biometicDataLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        biometicDataLabel.setBounds(41, 228, 155, 16);
        panelCentral.add(biometicDataLabel);

        JLabel lblTipoDeDieta = new JLabel("Tipo de dieta: " + u.getDietaryOptions());
        lblTipoDeDieta.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTipoDeDieta.setBounds(41, 156, 177, 16);
        panelCentral.add(lblTipoDeDieta);

        JLabel ageLabel = new JLabel("edad: " + u.calculateAge(u.getBirthDate()));
        ageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        ageLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        ageLabel.setBounds(41, 259, 85, 16);
        panelCentral.add(ageLabel);

        JLabel mbiLabel = new JLabel("tasa metabólica: " + u.calculateMBI(u.getWeight(), u.getHeight(), u.isGenre(), u.calculateAge(u.getBirthDate())) + "kcal/día");
        mbiLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mbiLabel.setForeground(new Color(207, 168, 43));
            }

            public void mouseExited(MouseEvent e) {
                mbiLabel.setForeground(null);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mbiLabel, "El metabolismo basal representa en un adulto entre e 40% y el 70% del gasto energético."
                        + "\n" + "Esta tasa se ha calculado en función de los datosque nos has proporcionado y podría variar a lo largo del tiempo.");

            }
        });
        mbiLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mbiLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        mbiLabel.setBounds(41, 340, 195, 16);
        panelCentral.add(mbiLabel);

        JLabel lblDatosUsuario = new JLabel("Datos usuario");
        lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDatosUsuario.setBounds(41, 23, 155, 16);
        panelCentral.add(lblDatosUsuario);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(weekNumber);
        progressBar.setMaximum(52);
        progressBar.setBounds(427, 224, 212, 20);
        panelCentral.add(progressBar);

        JLabel titulo = new JLabel("Estás en la semana" + " " + weekNumber + " " + "del año." + " " + "¡Sigue así!");
        titulo.setBounds(389, 203, 286, 17);
        panelCentral.add(titulo);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 10));
        titulo.setForeground(Color.BLACK);
        titulo.setBackground(Color.DARK_GRAY);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnGenerateMenu_1 = new JButton("generar menú");
        btnGenerateMenu_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ventana.DisplayMenu(u);
                ArrayList<Recipe> desayuno = new ArrayList<Recipe>();

                String desayunoImagePath = "";

                try {
                    Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esquematablasproyecto",
                            "root", "UXa19661!");
                    Statement smt = c.createStatement();

                    if (u.getDietaryOptions().contains(DietaryRestrictions.VEGAN)) {
                        ResultSet r = smt.executeQuery(
                                "select * from RECIPE where style = 'BREAKFAST';"
                        );

                        while (r.next()) {
                            String recipeName = r.getString("recipeName");
                            desayunoImagePath = r.getString("imagePath");

                            short caloricDensity = r.getShort("caloricDensity");
                            short carbs = r.getShort("carbs");
                            short fats = r.getShort("fats");
                            short protein = r.getShort("protein");
                            boolean vegan = r.getBoolean("vegan");
                            boolean halal = r.getBoolean("halal");
                            boolean highProtein = r.getBoolean("highProtein");
                            boolean lowCarb = r.getBoolean("lowCarb");

                            Recipe desayunoVegano = new Recipe(recipeName, desayunoImagePath, caloricDensity, carbs, protein, fats, vegan, halal,
                                    highProtein, lowCarb);

                            desayuno.add(desayunoVegano);
                            menu.setPlanningDesayuno(u, desayuno, desayunoImagePath);
                        }

                        smt.close();
                        c.close();

                    } else if (u.getDietaryOptions().contains(DietaryRestrictions.HIGHPROTEIN)) {

                        ResultSet r = smt.executeQuery(
                                "select * from RECIPE where style = 'BREAKFAST';"
                        );

                        while (r.next()) {
                            String recipeName = r.getString("recipeName");
                            desayunoImagePath = r.getString("imagePath");
                            short caloricDensity = r.getShort("caloricDensity");
                            short carbs = r.getShort("carbs");
                            short fats = r.getShort("fats");
                            short protein = r.getShort("protein");
                            boolean vegan = r.getBoolean("vegan");
                            boolean halal = r.getBoolean("halal");
                            boolean highProtein = r.getBoolean("highProtein");
                            boolean lowCarb = r.getBoolean("lowCarb");

                            Recipe desayunoHighProtein = new Recipe(recipeName, desayunoImagePath, caloricDensity, carbs, protein, fats, vegan, halal,
                                    highProtein, lowCarb);

                            desayuno.add(desayunoHighProtein);
                            menu.setPlanningDesayuno(u, desayuno, desayunoImagePath);

                        }

                        smt.close();
                        c.close();

                    } else if (u.getDietaryOptions().contains(DietaryRestrictions.HALAL)) {

                        ResultSet r = smt.executeQuery(
                                "select * from RECIPE where recipeName ='Desayuno halal';"
                        );

                        while (r.next()) {
                            String recipeName = r.getString("recipeName");
                            desayunoImagePath = r.getString("imagePath");
                            short caloricDensity = (short) r.getInt("caloricDensity");
                            short carbs = r.getShort("carbs");
                            short fats = r.getShort("fats");
                            short protein = r.getShort("protein");
                            boolean vegan = r.getBoolean("vegan");
                            boolean halal = r.getBoolean("halal");
                            boolean highProtein = r.getBoolean("highProtein");
                            boolean lowCarb = r.getBoolean("lowCarb");

                            Recipe desayunoHalal = new Recipe(recipeName, desayunoImagePath, caloricDensity, carbs, protein, fats, vegan, halal,
                                    highProtein, lowCarb);

                            desayuno.add(desayunoHalal);
                            System.out.println(DietaryRestrictions.HALAL);

                        }

                        smt.close();
                        c.close();

                    } else {

                        ResultSet r = smt.executeQuery(
                                "select * from RECIPE where style = 'BREAKFAST';"
                        );

                        while (r.next()) {
                            String recipeName = r.getString("recipeName");
                            desayunoImagePath = r.getString("imagePath");
                            short caloricDensity = r.getShort("caloricDensity");
                            short carbs = r.getShort("carbs");
                            short fats = r.getShort("fats");
                            short protein = r.getShort("protein");
                            boolean vegan = r.getBoolean("vegan");
                            boolean halal = r.getBoolean("halal");
                            boolean highProtein = r.getBoolean("highProtein");
                            boolean lowCarb = r.getBoolean("lowCarb");

                            Recipe desayunoComun = new Recipe(recipeName, desayunoImagePath, caloricDensity, carbs, protein, fats, vegan, halal,
                                    highProtein, lowCarb);

                            desayuno.add(desayunoComun);
                            menu.setPlanningDesayuno(u, desayuno, desayunoImagePath);

                        }
                    }

                    smt.close();
                    c.close();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                }

            }
        });

        btnGenerateMenu_1.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnGenerateMenu_1.setBounds(427, 66, 212, 30);
        panelCentral.add(btnGenerateMenu_1);

        JLabel lblNewLabel = new JLabel("");
        if (LocalDate.now().getDayOfYear() >= 354 || LocalDate.now().getDayOfYear() <= 81) {
            lblNewLabel.setIcon(new ImageIcon("./images/invierno.jpeg"));
        } else if (LocalDate.now().getDayOfYear() > 81 || LocalDate.now().getDayOfYear() <= 173) {
            lblNewLabel.setIcon(new ImageIcon("./images/primavera.jpeg"));
        } else if (LocalDate.now().getDayOfYear() > 173 || LocalDate.now().getDayOfYear() <= 264) {
            lblNewLabel.setIcon(new ImageIcon("./images/verano.jpeg"));

        } else if (LocalDate.now().getDayOfYear() > 264 || LocalDate.now().getDayOfYear() <= 354) {
            lblNewLabel.setIcon(new ImageIcon("./images/otono.jpeg"));

        }

        lblNewLabel.setBounds(437, 259, 200, 200);
        panelCentral.add(lblNewLabel);

        JLabel changeDietLabel = new JLabel("he cambiado de dieta");
        changeDietLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        changeDietLabel.setForeground(new Color(51, 51, 204));
        changeDietLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeDietLabel.setForeground(new Color(207, 168, 43));
            }

            public void mouseExited(MouseEvent e) {
                changeDietLabel.setForeground(new Color(51, 51, 204));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                boolean vegan = u.isVegan();
                boolean halal = u.isHalal();
                boolean highProtein = u.isHighProtein();
                boolean lowCarb = u.isLowCarb();
                String diet = JOptionPane.showInputDialog(changeDietLabel, "introduzca su nuevo tipo de dieta: VEGANA, HALAL, HIGHPROTEIN O LOWCARB");
                switch (diet) {
                    case "VEGANA":
                        vegan = true;
                        u.setVegan(vegan);
                        break;
                    case "HALAL":
                        halal = true;
                        u.setHalal(halal);
                        break;
                    case "HIGHPROTEIN":
                        highProtein = true;
                        u.setHighProtein(highProtein);
                        break;
                    case "LOWCARB":
                        lowCarb = true;
                        u.setLowCarb(lowCarb);
                        break;

                }

            }
        });
        changeDietLabel.setBounds(41, 182, 264, 13);
        panelCentral.add(changeDietLabel);

        JPanel panelSouth = new JPanel();
        panelSouth.setBorder(null);
        add(panelSouth, BorderLayout.SOUTH);
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton modifyPassword = new JButton("cambiar contraseña");
        panelSouth.add(modifyPassword);
        modifyPassword.setFont(new Font("SansSerif", Font.BOLD, 12));

        JButton btnLogOut;
        btnLogout = new JButton("LOG OUT");
        btnLogout.setFont(new Font("SansSerif", Font.BOLD, 12));
        panelSouth.add(btnLogout);

    }
}
