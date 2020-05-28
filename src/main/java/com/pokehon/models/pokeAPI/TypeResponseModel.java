package models.pokeAPI;

public class TypeResponseModel {

    private int slot;
    private TypeObjectResponseModel type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TypeObjectResponseModel getType() {
        return type;
    }

    public void setType(TypeObjectResponseModel type) {
        this.type = type;
    }
}
