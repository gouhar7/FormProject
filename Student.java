import java.io.Serializable;

class Student implements Serializable {
    private String name, fatherName, motherName, dateOfBirth, phone, email, department, id,
            batch, semester, currentAddress, permanentAddress, gender, bloodGroup, spouseName,
            spousePhone, spouseEmail;

    public Student(String name, String fatherName, String motherName, String dateOfBirth,
                   String phone, String email, String department, String id, String batch,
                   String semester, String currentAddress, String permanentAddress, String gender,
                   String bloodGroup, String spouseName, String spousePhone, String spouseEmail) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.id = id;
        this.batch = batch;
        this.semester = semester;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.spouseName = spouseName;
        this.spousePhone = spousePhone;
        this.spouseEmail = spouseEmail;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getId() {
        return id;
    }

    public String getBatch() {
        return batch;
    }

    public String getSemester() {
        return semester;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public String getSpousePhone() {
        return spousePhone;
    }

    public String getSpouseEmail() {
        return spouseEmail;
    }
}
