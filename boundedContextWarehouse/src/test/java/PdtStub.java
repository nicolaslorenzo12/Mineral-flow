import java.time.LocalDateTime;

class PdtStub {

    private int amountOfTonsDelivered;
    private int amountOfTonsConsumed;
    private boolean allTonsConsumed;
    private final LocalDateTime timeOfDelivery;

    public PdtStub(int amountOfTonsDelivered, LocalDateTime timeOfDelivery) {
        this.amountOfTonsDelivered = amountOfTonsDelivered;
        this.timeOfDelivery = timeOfDelivery;
        this.amountOfTonsConsumed = 0; // initially no tons consumed
        this.allTonsConsumed = false;
    }

    public int removeTonsFromPdt(int amountNeeded) {
        if (amountNeeded < this.amountOfTonsDelivered - this.amountOfTonsConsumed) {
            this.amountOfTonsConsumed += amountNeeded;
            return 0; // no balance remaining
        } else {
            int tonsLeft = this.amountOfTonsDelivered - this.amountOfTonsConsumed;
            this.amountOfTonsConsumed = this.amountOfTonsDelivered;
            this.allTonsConsumed = true;
            return amountNeeded - tonsLeft; // remaining balance after consuming this Pdt's tons
        }
    }

    public boolean isAllTonsConsumed() {
        return this.allTonsConsumed;
    }

    public int getAmountOfTonsConsumed() {
        return this.amountOfTonsConsumed;
    }

    public LocalDateTime getTimeOfDelivery() {
        return this.timeOfDelivery;
    }
}
