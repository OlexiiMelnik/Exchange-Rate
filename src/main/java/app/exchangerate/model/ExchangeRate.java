package app.exchangerate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Table(name = "exchange_rates")
@SQLDelete(sql = "UPDATE exchange_rates SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "base_currency", nullable = false)
    private String baseCurrency;
    @Column(name = "currency_depended", nullable = false)
    private String currencyDepended;
    @Column(name = "rate_sell", nullable = false)
    private BigDecimal rateBuy;
    @Column(name = "rate_buy", nullable = false)
    private BigDecimal rateSell;
    @Column(name = "source", nullable = false)
    @Enumerated(EnumType.STRING)
    private Source source;
    @Column(name = "local_date", nullable = false)
    private LocalDateTime localDate;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum Source {
        MONO,
        PRIVAT
    }
}
