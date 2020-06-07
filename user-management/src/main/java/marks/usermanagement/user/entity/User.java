package marks.usermanagement.user.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "User")
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String title;
    private String initials;
    private String fullName;
    private String lastName;

    private String gender;
    private String race;
    private Date birthDate;

    private String nationality;
    private String idNumber;
    private String passportNumber;

    private String emailAddress;
    private String phoneNumber;

    //Physical Address
    private String houseNo;
    private String StreetNo;
    private String cityCode;
    private String city;
    private String province;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = Boolean.TRUE;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetNo() {
        return StreetNo;
    }

    public void setStreetNo(String streetNo) {
        StreetNo = streetNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public enum Role {
        Admin, Teacher, Learner
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                userNumber.equals(user.userNumber) &&
                role == user.role &&
                title.equals(user.title) &&
                initials.equals(user.initials) &&
                fullName.equals(user.fullName) &&
                lastName.equals(user.lastName) &&
                gender.equals(user.gender) &&
                race.equals(user.race) &&
                birthDate.equals(user.birthDate) &&
                nationality.equals(user.nationality) &&
                idNumber.equals(user.idNumber) &&
                passportNumber.equals(user.passportNumber) &&
                emailAddress.equals(user.emailAddress) &&
                phoneNumber.equals(user.phoneNumber) &&
                Objects.equals(houseNo, user.houseNo) &&
                Objects.equals(StreetNo, user.StreetNo) &&
                Objects.equals(cityCode, user.cityCode) &&
                city.equals(user.city) &&
                province.equals(user.province) &&
                active.equals(user.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userNumber, role, title, initials, fullName, lastName, gender, race, birthDate, nationality, idNumber, passportNumber, emailAddress, phoneNumber, houseNo, StreetNo, cityCode, city, province, active);
    }
}