package ua.com.brdo.business.constructor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "option_")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"title"})
@JsonIgnoreProperties(value = {"questions"})
@Validated
public class Option {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max=500, message = "Maximum length of option is 500 characters.")
    @Column(unique = true, nullable = false, length = 500)
    private String title;

    @OneToMany(mappedBy = "option")
    private Set<QuestionOption> questions = new HashSet<>();
}
