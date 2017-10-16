package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity

public class SaleTransactionLineItemEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleTransactionLineItemId;
    private Integer serialNumber;
    @ManyToOne
    private ProductEntity productEntity;
    private Integer quantity;
    @Column(precision = 11, scale = 2)
    private BigDecimal unitPrice;
    @Column(precision = 11, scale = 2)
    private BigDecimal subTotal;

    
    
    public SaleTransactionLineItemEntity()
    {
    }
    
    
    
    public SaleTransactionLineItemEntity(Integer serialNumber, ProductEntity productEntity, Integer quantity, BigDecimal unitPrice, BigDecimal subTotal)
    {
        this.serialNumber = serialNumber;
        this.productEntity = productEntity;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }
    
    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (this.saleTransactionLineItemId != null ? this.saleTransactionLineItemId.hashCode() : 0);
        
        return hash;
    }

    
    
    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof SaleTransactionLineItemEntity)) 
        {
            return false;
        }
        
        SaleTransactionLineItemEntity other = (SaleTransactionLineItemEntity) object;
        
        if ((this.saleTransactionLineItemId == null && other.saleTransactionLineItemId != null) || (this.saleTransactionLineItemId != null && !this.saleTransactionLineItemId.equals(other.saleTransactionLineItemId))) 
        {
            return false;
        }
        
        return true;
    }

    
    
    @Override
    public String toString() 
    {
        return "entity.SaleTransactionLineItemEntity[ saleTransactionLineItemId=" + this.saleTransactionLineItemId + " ]";
    }

    
    
    public Long getSaleTransactionLineItemId() {
        return saleTransactionLineItemId;
    }

    public void setSaleTransactionLineItemId(Long saleTransactionLineItemId) {
        this.saleTransactionLineItemId = saleTransactionLineItemId;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }   
}