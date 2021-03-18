package springcourse.homeworksecurity2.entity;

import javax.persistence.*;

@Entity
public class VerificationTokenAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToOne
    private MyAppUser myAppUser;

    public VerificationTokenAdmin(MyAppUser myAppUser, String value) {
        this.myAppUser = myAppUser;
        this.value = value;
    }

    public VerificationTokenAdmin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MyAppUser getMyAppUser() {
        return myAppUser;
    }

    public void setMyAppUser(MyAppUser myAppUser) {
        this.myAppUser = myAppUser;
    }
}

