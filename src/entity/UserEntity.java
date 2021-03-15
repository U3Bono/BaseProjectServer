package entity;

public class UserEntity {

    int user_id;
    String user_account;
    String user_password;
    String user_phone;
    String user_ic;

    public String getUser_ic() {
        return user_ic;
    }

    public void setUser_ic(String user_ic) {
        this.user_ic = user_ic;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
