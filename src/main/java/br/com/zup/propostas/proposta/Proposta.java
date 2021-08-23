package br.com.zup.propostas.proposta;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.proposta.analise.AnaliseResponse;
import br.com.zup.propostas.proposta.analise.AnaliseStatus;

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
   @Column(nullable = false) @Min(0)
   private BigDecimal salario;
   @Column
   private PropostaEstado estado;

   @OneToOne(mappedBy = "proposta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private Cartao cartao;

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

   public String getDocumento() {
      return documento;
   }

   public String getNome() {
      return nome;
   }

   public PropostaEstado getEstado() {
      return estado;
   }

   public void adicionaEstado(AnaliseResponse analiseResponse) {
      if(analiseResponse.getResultadoSolicitacao().equals(AnaliseStatus.COM_RESTRICAO)){
         this.estado = PropostaEstado.NAO_ELEGIVEL;
      }
      else{
         this.estado = PropostaEstado.ELEGIVEL;
      }
   }
}
