package br.com.zup.propostas.proposta;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
public class Proposta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(unique = true, nullable = false)
   private String documento;
   @Column(unique = true, nullable = false)
   private String email;
   @Column(nullable = false)
   private String nome;
   @Column(nullable = false)
   private String endereco;
   @Column(nullable = false) @Min(1)
   private BigDecimal salario;

   @Deprecated
   public Proposta() {
   }

   public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
      this.documento = documento;
      this.email = email;
      this.nome = nome;
      this.endereco = endereco;
      this.salario = salario;
   }

   public Long getId() {
      return id;
   }
}
