package org.example.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransferHistory {
    private int id;
    private int transferId;
    private LocalDateTime dateTransfert;

    @Override
    public String toString() {
        return "TransferHistory{" +
                "id=" + id +
                ", transferId=" + transferId +
                ", dateTransfert=" + dateTransfert +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferHistory that)) return false;
        return id == that.id && transferId == that.transferId && Objects.equals(dateTransfert, that.dateTransfert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transferId, dateTransfert);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public LocalDateTime getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(LocalDateTime dateTransfert) {
        this.dateTransfert = dateTransfert;
    }
}
