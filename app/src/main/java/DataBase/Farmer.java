package DataBase;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by george on 10/10/2015.
 */
@DatabaseTable
public class Farmer {



    @DatabaseField(id = true, canBeNull = false, columnName = "id")
    private UUID id;

    @DatabaseField(index = true)
    private int index;
    @DatabaseField
    private String firstNAme;

    @DatabaseField
    private String otherNames;

    @DatabaseField
    private Date dateCreated;

    @DatabaseField
    private Date DOB;
    @DatabaseField
    private  int phoneNumber;

    @DatabaseField
    private  int idNumer;

    public String getFirstNAme() {
        return firstNAme;
    }

    public void setFirstNAme(String firstNAme) {
        this.firstNAme = firstNAme;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIdNumer() {
        return idNumer;
    }

    public void setIdNumer(int idNumer) {
        this.idNumer = idNumer;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
