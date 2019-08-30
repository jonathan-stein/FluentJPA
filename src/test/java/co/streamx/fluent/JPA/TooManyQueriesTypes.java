package co.streamx.fluent.JPA;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

public interface TooManyQueriesTypes {

    @Entity
    @Table(name = "cd_person", schema = "cd")
    @Data
    @NoArgsConstructor
    public class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "password_id")
//        @Fetch(FetchMode.JOIN)
        private Password password;

        @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
        @JoinTable(name = "cd_person_role", schema = "cd", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//        @Fetch(FetchMode.JOIN)
        private Set<Role> roles = new HashSet<>();
    }

    @Entity
    @Table(name = "cd_password", schema = "cd")
    @Data
    @NoArgsConstructor
    public class Password {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private Long id;

        @Column(name = "password_hash", nullable = false)
        private String passwordHash;
    }

    @Entity
    @Table(name = "cd_role", schema = "cd")
    @Data
    @NoArgsConstructor
    public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "role_type")
        @Enumerated(EnumType.STRING)
        private RoleType roleType;
    }

    enum RoleType {
        TYPE1, TYPE2
    }
}
