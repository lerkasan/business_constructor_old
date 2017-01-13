package ua.com.brdo.business.constructor.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "business_type")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"codeKved"})
@JsonInclude(NON_NULL)
public class BusinessType {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotEmpty(message = "Title field is required.")
  @Size(max = 1000, message = "Maximum length of title is 1000 characters.")
  @Column(nullable = false, length = 1000)
  private String title;

  @NotEmpty(message = "KVED code field is required.")
  @Pattern(regexp = "..+\\.+..", message = "Format of KVED code must be a pair of two-digit numbers separated by dot. Example: 62.21")
  @Size(min = 5, max = 5, message = "Maximum length of KVED code is 5 characters.")
  @Column(nullable = false, length = 5, name = "code_kved")
  private String codeKved;
}
