package DataBase;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by george on 10/10/2015.
 */
@DatabaseTable
public class User {


    @DatabaseField
    private String Name;

    @DatabaseField
    private String Username;

    @DatabaseField
    private String password;
    @DatabaseField(generatedId = true)
    private Long id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
