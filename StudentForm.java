import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudentForm extends JFrame {
    private JTextField nameField, fatherField, motherField, phoneField, emailField, idField, batchField, currentAddressField, permanentAddressField;
    private JComboBox<String> departmentBox, semesterComboBox;
    private JCheckBox sameAddressCheck, marriedCheck;
    private JTextField spouseNameField, spousePhoneField, spouseEmailField;
    private JLabel spouseNameLabel, spousePhoneLabel, spouseEmailLabel;
    private JRadioButton maleButton, femaleButton, otherButton, singleButton, marriedButton;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JRadioButton bloodGroupAPlus, bloodGroupAMinus, bloodGroupBPlus, bloodGroupBMinus, bloodGroupOPlus, bloodGroupOMinus, bloodGroupABPlus, bloodGroupABMinus;
    private JDateChooser dobChooser;
    private static final String FILE_NAME = "studentinfo.ser";
    private ArrayList<Student> studentList = new ArrayList<>();

    public StudentForm() {
        super("Student Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        addField(formPanel, gbc, "Name:", nameField = new JTextField(20));
        addField(formPanel, gbc, "Father's Name:", fatherField = new JTextField(20));
        addField(formPanel, gbc, "Mother's Name:", motherField = new JTextField(20));

        dobChooser = new JDateChooser();
        dobChooser.setDateFormatString("yyyy-MM-dd");
        addField(formPanel, gbc, "Date of Birth:", dobChooser);

        addField(formPanel, gbc, "Phone Number:", phoneField = new JTextField(20));
        addField(formPanel, gbc, "Email Address:", emailField = new JTextField(20));

        String[] departments = {
                "Computer Science Engineering", "Software Engineering", "Electrical and Electronic Engineering",
                "Bachelor of Business Administration", "Law and Justice", "English"};
        departmentBox = new JComboBox<>(departments);

        addField(formPanel, gbc, "Department:", departmentBox);
        addField(formPanel, gbc, "Student ID:", idField = new JTextField(20));
        addField(formPanel, gbc, "Batch:", batchField = new JTextField(20));


        String[] semesters = {"Spring", "Summer", "Fall"};
        semesterComboBox = new JComboBox<>(semesters);
        addField(formPanel, gbc, "Semester:", semesterComboBox);

        currentAddressField = new JTextField(20);
        addField(formPanel, gbc, "Current Address:", currentAddressField);
        sameAddressCheck = new JCheckBox("Same as Current Address");
        sameAddressCheck.setFocusPainted(false);
        sameAddressCheck.addActionListener(e -> {
            if (sameAddressCheck.isSelected()) {
                permanentAddressField.setText(currentAddressField.getText());
                permanentAddressField.setEditable(false);
            } else {
                permanentAddressField.setEditable(true);
            }
        });
        permanentAddressField = new JTextField(20);
        JPanel permAddressPanel = new JPanel(new BorderLayout());
        permAddressPanel.add(permanentAddressField, BorderLayout.CENTER);
        permAddressPanel.add(sameAddressCheck, BorderLayout.EAST);
        addField(formPanel, gbc, "Permanent Address:", permAddressPanel);

        maleButton = new JRadioButton("Male");
        maleButton.setFocusPainted(false);
        femaleButton = new JRadioButton("Female");
        femaleButton.setFocusPainted(false);
        otherButton = new JRadioButton("Other");
        otherButton.setFocusPainted(false);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);
        addField(formPanel, gbc, "Gender:", genderPanel);

        bloodGroupAPlus = new JRadioButton("A+");
        bloodGroupAPlus.setFocusPainted(false);
        bloodGroupAMinus = new JRadioButton("A-");
        bloodGroupAMinus.setFocusPainted(false);
        bloodGroupBPlus = new JRadioButton("B+");
        bloodGroupBPlus.setFocusPainted(false);
        bloodGroupBMinus = new JRadioButton("B-");
        bloodGroupBMinus.setFocusPainted(false);
        bloodGroupOPlus = new JRadioButton("O+");
        bloodGroupOPlus.setFocusPainted(false);
        bloodGroupOMinus = new JRadioButton("O-");
        bloodGroupOMinus.setFocusPainted(false);
        bloodGroupABPlus = new JRadioButton("AB+");
        bloodGroupABPlus.setFocusPainted(false);
        bloodGroupABMinus = new JRadioButton("AB-");
        bloodGroupABMinus.setFocusPainted(false);
        ButtonGroup bloodGroupGroup = new ButtonGroup();
        bloodGroupGroup.add(bloodGroupAPlus);
        bloodGroupGroup.add(bloodGroupAMinus);
        bloodGroupGroup.add(bloodGroupBPlus);
        bloodGroupGroup.add(bloodGroupBMinus);
        bloodGroupGroup.add(bloodGroupOPlus);
        bloodGroupGroup.add(bloodGroupOMinus);
        bloodGroupGroup.add(bloodGroupABPlus);
        bloodGroupGroup.add(bloodGroupABMinus);

        JPanel bloodGroupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        bloodGroupPanel.add(bloodGroupAPlus);
        bloodGroupPanel.add(bloodGroupAMinus);
        bloodGroupPanel.add(bloodGroupBPlus);
        bloodGroupPanel.add(bloodGroupBMinus);
        bloodGroupPanel.add(bloodGroupOPlus);
        bloodGroupPanel.add(bloodGroupOMinus);
        bloodGroupPanel.add(bloodGroupABPlus);
        bloodGroupPanel.add(bloodGroupABMinus);
        addField(formPanel, gbc, "Blood Group:", bloodGroupPanel);

        gbc.gridy++;
        singleButton = new JRadioButton("Single", true); // Default to Single
        marriedButton = new JRadioButton("Married");
        ButtonGroup maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(singleButton);
        maritalStatusGroup.add(marriedButton);
        JPanel maritalStatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        maritalStatusPanel.add(singleButton);
        maritalStatusPanel.add(marriedButton);
        addField(formPanel, gbc, "Marital Status:", maritalStatusPanel);

        spouseNameLabel = new JLabel("Spouse Name:");
        spousePhoneLabel = new JLabel("Spouse Phone:");
        spouseEmailLabel = new JLabel("Spouse Email:");
        spouseNameField = new JTextField(20);
        spousePhoneField = new JTextField(20);
        spouseEmailField = new JTextField(20);

        setSpouseFieldsEditable(false);

        gbc.gridy++;
        addField(formPanel, gbc, spouseNameLabel.getText(), spouseNameField);
        addField(formPanel, gbc, spousePhoneLabel.getText(), spousePhoneField);
        addField(formPanel, gbc, spouseEmailLabel.getText(), spouseEmailField);

        marriedButton.addActionListener(e -> setSpouseFieldsEditable(true));
        singleButton.addActionListener(e -> setSpouseFieldsEditable(false));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusPainted(false);
        submitButton.setBackground(new Color(0, 100, 0));
        submitButton.setForeground(new Color(255, 255, 255));
        submitButton.addActionListener(e -> {
            saveFormData();
            loadFromFile();
        });

        JButton clearButton = new JButton("Clear");
        clearButton.setFocusPainted(false);
        clearButton.setBackground(new Color(0, 0, 255));
        clearButton.setForeground(new Color(255, 255, 255));
        clearButton.addActionListener(e -> clearForm());

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(new Color(255, 0, 0));
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.addActionListener(e -> {
            deleteSelectedRow();
            loadFromFile();
        });
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);

        gbc.gridy++;
        formPanel.add(buttonPanel, gbc);

        tableModel = new DefaultTableModel(new String[]{
                "Name", "Father", "Mother", "DOB", "Phone", "Email", "Department", "ID", "Batch",
                "Semester", "Current Address", "Permanent Address", "Gender",
                "Blood Group", "Spouse Name", "Spouse Phone", "Spouse Email"}, 0);

        dataTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(dataTable);
        tableScrollPane.setPreferredSize(new Dimension(800, 175));

        loadFromFile();
        mainPanel.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        int dotIndex = email.indexOf('.', atIndex + 1);
        return atIndex > 0 && dotIndex > atIndex;
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{11}");
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field) {
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void setSpouseFieldsEditable(boolean editable) {
        spouseNameField.setEditable(editable);
        spousePhoneField.setEditable(editable);
        spouseEmailField.setEditable(editable);
        spouseNameField.setText("");
        spousePhoneField.setText("");
        spouseEmailField.setText("");
    }

    private void saveFormData() {
        String phone = phoneField.getText();
        if (!isValidPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(this, "Invalid phone number. It must be 11 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sPhone = spousePhoneField.getText();
        if (!isValidPhoneNumber(sPhone) && marriedButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Invalid spouse phone number. It must be 11 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String email = emailField.getText();
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email address. Please provide a valid email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sEmail = spouseEmailField.getText();
        if (!isValidEmail(sEmail) && marriedButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Invalid spouse email address. Please provide a valid email.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String semester = (String) semesterComboBox.getSelectedItem();
            String bloodGroup = getSelectedBloodGroup();
            String gender = getSelectedGender();
            Date dob = dobChooser.getDate();
            String formattedDob = dob != null ? new SimpleDateFormat("yyyy-MM-dd").format(dob) : "";

            Student student = new Student(nameField.getText(), fatherField.getText(),
                    motherField.getText(), formattedDob, phoneField.getText(),
                    emailField.getText(), (String) departmentBox.getSelectedItem(),
                    idField.getText(), batchField.getText(), semester,
                    currentAddressField.getText(), permanentAddressField.getText(), gender,
                    bloodGroup, spouseNameField.getText(), spousePhoneField.getText(),
                    spouseEmailField.getText());

            studentList.add(student);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                oos.writeObject(studentList);
                JOptionPane.showMessageDialog(this, "Data saved successfully.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this form?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        studentList.remove(selectedRow);
        tableModel.removeRow(selectedRow);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(studentList);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error updating file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            studentList = (ArrayList<Student>) ois.readObject();
            updateTable();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error loading data: " + ex.getMessage());
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Student student : studentList) {
            tableModel.addRow(new Object[]{student.getName(), student.getFatherName(),
                    student.getMotherName(), student.getDateOfBirth(), student.getPhone(),
                    student.getEmail(), student.getDepartment(), student.getId(),
                    student.getBatch(), student.getSemester(), student.getCurrentAddress(),
                    student.getPermanentAddress(), student.getGender(),
                    student.getBloodGroup(), student.getSpouseName(),
                    student.getSpousePhone(), student.getSpouseEmail()});
        }
    }

    private void clearForm() {
        nameField.setText("");
        fatherField.setText("");
        motherField.setText("");
        dobChooser.setDate(null);
        phoneField.setText("");
        emailField.setText("");
        departmentBox.setSelectedIndex(0);
        semesterComboBox.setSelectedIndex(0);
        idField.setText("");
        batchField.setText("");
        currentAddressField.setText("");
        permanentAddressField.setText("");
        maleButton.setSelected(true);
        femaleButton.setSelected(false);
        otherButton.setSelected(false);
        bloodGroupAPlus.setSelected(true);
        bloodGroupAMinus.setSelected(false);
        bloodGroupBPlus.setSelected(false);
        bloodGroupBMinus.setSelected(false);
        bloodGroupOPlus.setSelected(false);
        bloodGroupOMinus.setSelected(false);
        bloodGroupABPlus.setSelected(false);
        bloodGroupABMinus.setSelected(false);
        singleButton.setSelected(true);
        marriedButton.setSelected(false);
        spouseNameField.setText("");
        spousePhoneField.setText("");
        spouseEmailField.setText("");
        setSpouseFieldsEditable(false);
        sameAddressCheck.setSelected(false);
    }



    private String getSelectedBloodGroup() {
        if (bloodGroupAPlus.isSelected()) return "A+";
        else if (bloodGroupAMinus.isSelected()) return "A-";
        else if (bloodGroupBPlus.isSelected()) return "B+";
        else if (bloodGroupBMinus.isSelected()) return "B-";
        else if (bloodGroupOPlus.isSelected()) return "O+";
        else if (bloodGroupOMinus.isSelected()) return "O-";
        else if (bloodGroupABPlus.isSelected()) return "AB+";
        else if (bloodGroupABMinus.isSelected()) return "AB-";
        else return "";
    }

    private String getSelectedGender() {
        if (maleButton.isSelected()) return "Male";
        else if (femaleButton.isSelected()) return "Female";
        else if (otherButton.isSelected()) return "Other";
        else return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentForm form = new StudentForm();
            form.setVisible(true);
        });
    }
}