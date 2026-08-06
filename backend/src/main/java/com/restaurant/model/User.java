package com.restaurant.model;
import jakarta.persistence.*;

/** INHERITANCE from BaseEntity. ENCAPSULATION via private fields. */
@Entity @Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable=false) private String name;
    @Column(nullable=false,unique=true) private String email;
    @Column(nullable=false) private String password;
    @Column(nullable=false) private String phone;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role = Role.CUSTOMER;

    public enum Role { CUSTOMER, ADMIN }

    public User() {}
    // CONSTRUCTOR OVERLOADING
    public User(String name, String email, String password, String phone) {
        this.name=name; this.email=email; this.password=password; this.phone=phone; this.role=Role.CUSTOMER;
    }
    public User(String name, String email, String password, String phone, Role role) {
        this.name=name; this.email=email; this.password=password; this.phone=phone; this.role=role;
    }

    public String getName() { return name; } public void setName(String n) { name=n; }
    public String getEmail() { return email; } public void setEmail(String e) { email=e; }
    public String getPassword() { return password; } public void setPassword(String p) { password=p; }
    public String getPhone() { return phone; } public void setPhone(String p) { phone=p; }
    public Role getRole() { return role; } public void setRole(Role r) { role=r; }
    @Override public String toString() { return "User{id="+getId()+",name="+name+",role="+role+"}"; }
}
