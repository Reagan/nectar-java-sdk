package software.nectar.java.models;

import java.time.Instant;
import java.util.List;

public class Credits {

    private Double credits;
    private List<Purchase> purchases;
    private List<Consumption> consumptions;

    public Credits(Double credits,
                   List<Purchase> purchases,
                   List<Consumption> consumptions) {
        setCredits(credits);
        setPurchases(purchases);
        setConsumptions(consumptions);
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }


    public static class Purchase {
        private String ref;
        private String userRef;
        private Double value;
        private Double units;
        private String currency;
        private Instant purchaseDate;

        public Purchase(String ref, String userRef, Double value,
                        Double units, String currency, Instant purchaseDate) {
            setRef(ref);
            setUserRef(userRef);
            setValue(value);
            setUnits(units);
            setCurrency(currency);
            setPurchaseDate(purchaseDate);
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getUserRef() {
            return userRef;
        }

        public void setUserRef(String userRef) {
            this.userRef = userRef;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Double getUnits() {
            return units;
        }

        public void setUnits(Double units) {
            this.units = units;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Instant getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(Instant purchaseDate) {
            this.purchaseDate = purchaseDate;
        }
    }

    public static class Consumption {
        private String ref;
        private Double units;
        private Instant consumptionDate;
        private String userRef;
        private String tokenRef;

        public Consumption(String ref, Double units, Instant consumptionDate,
                           String userRef, String tokenRef) {
            setRef(ref);
            setUnits(units);
            setConsumptionDate(consumptionDate);
            setUserRef(userRef);
            setTokenRef(tokenRef);
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public Double getUnits() {
            return units;
        }

        public void setUnits(Double units) {
            this.units = units;
        }

        public Instant getConsumptionDate() {
            return consumptionDate;
        }

        public void setConsumptionDate(Instant consumptionDate) {
            this.consumptionDate = consumptionDate;
        }

        public String getUserRef() {
            return userRef;
        }

        public void setUserRef(String userRef) {
            this.userRef = userRef;
        }

        public String getTokenRef() {
            return tokenRef;
        }

        public void setTokenRef(String tokenRef) {
            this.tokenRef = tokenRef;
        }
    }
}
