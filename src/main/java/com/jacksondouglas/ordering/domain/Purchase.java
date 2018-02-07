package com.jacksondouglas.ordering.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchase")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.purchase")
    private Set<PurchaseItem> items = new HashSet<>();

    public Purchase() {
    }

    public Purchase(Integer id, Date instant, Client client, Address deliveryAddress) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getTotal() {
        return items.stream().map(v -> v.getSubTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<PurchaseItem> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public void addItem(PurchaseItem item) {
        this.items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuffer sb = new StringBuffer();
        sb.append("Purchase number: ");
        sb.append(getId());
        sb.append(", Instant: ");
        sb.append(sdf.format(getInstant()));
        sb.append(", Client: ");
        sb.append(getClient().getName());
        sb.append(", Payment State: ");
        sb.append(getPayment().getState().getDesc());
        sb.append("\nDetails:\n");

        for (PurchaseItem item : getItems()) {
            sb.append(item.toString());
        }

        sb.append("Total: ");
        sb.append(nf.format(getTotal()));
        sb.append("\n");
        return sb.toString();
    }
}
