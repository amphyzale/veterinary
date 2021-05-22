package net.courseproject.alex.veterinary.domain;

import net.courseproject.alex.veterinary.util.GUIDGenerator;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet_data")
public class Pet extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "guid")
    @GeneratorType(type = GUIDGenerator.class, when = GenerationTime.INSERT)
    private String guid;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "pet_pic")
    private String petPic;
    @Column(name = "kind")
    private String kind;
    @Column(name = "breed")
    private String breed;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPetPic() {
        return petPic;
    }

    public void setPetPic(String petPic) {
        this.petPic = petPic;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
