package rf.digitworld.simpleinstagramclient.models;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Дмитрий on 13.07.2015.
 */
@Table(name="users")
public  class User extends Model {
    @Expose
    @Column(name = "username")
    private String username;
    private String firstName;
    @SerializedName("profile_picture")
    @Expose
    @Column(name = "profile_picture")
    private String profilePicture;
    @Expose
    @Column(name = "user_id")
    private int id;
    private String lastName;
    @SerializedName("full_name")
    @Expose
    @Column(name = "full_name")
    private String fullName;

    /*    this.username=user.getString("usename");
        this.profilePicture=user.getString("profile_picture");
        this.id=user.getInt("id");
        this.fullName=user.getString("full_name");

*/
    public User(){
        super();
    }
    public String getUsername() {

        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public String getFullName() {

        return fullName;
    }


    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getProfilePicture() {
        return profilePicture;
    }


    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public int getUserId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}