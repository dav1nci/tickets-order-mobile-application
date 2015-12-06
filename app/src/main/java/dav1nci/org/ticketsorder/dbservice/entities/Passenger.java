package dav1nci.org.ticketsorder.dbservice.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by dav1nci on 02.12.15.
 */
public class Passenger
{
    private String passportSerial;
    private Cabin cabin;
    private String nameSurname;
    private Date birthDate;
    private Table table;
    private Auto auto;

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
