package citivelociti.backend.Models;

import citivelociti.backend.Enums.TradeStatus;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "trade")
public class Trade implements ITrade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer strategyId;     // The strategy which this trade is using
    private Boolean buy;            // True if the trader is buying, o.w. false -> trader is selling
    private Double price;           // Price of stock                   
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date openDate;          // Date which the strategy started
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeDate;         // Date which the strategy ended
    @Enumerated
    private TradeStatus status;     // OPEN, FILLED, REJECTED

    public Trade(int strategyId, boolean buy, double price) {
        this.strategyId = strategyId;
        this.buy = buy;
        this.price = price;
        this.openDate = Calendar.getInstance().getTime();
        this.status = TradeStatus.OPEN;
    }

    // mutators
    public void setId(Integer id) {
        this.id = id;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public void setBuy(Boolean buy) {
        this.buy = buy;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public void setCloseDate() {
        this.closeDate = Calendar.getInstance().getTime();
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }

    // accessors
    public Integer getId() {
        return this.id;
    }

    public Integer getStrategyId() {
        return this.strategyId;
    }

    public Boolean getBuy() {
        return this.buy;
    }

    public Double getPrice() {
        return this.price;
    }

    public Date getOpenDate() {
        return this.openDate;
    }

    public Date getCloseDate() {
        return this.closeDate;
    }

    public TradeStatus getTradeStatus() {
        return this.status;
    }
}