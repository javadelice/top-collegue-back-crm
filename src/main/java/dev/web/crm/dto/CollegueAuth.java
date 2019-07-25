package dev.web.crm.dto;

public class CollegueAuth {
    private String email;
    private String password;

    public CollegueAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CollegueAuth() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
