package software.nectar.java.models;

public class Permissions {
    private Long id;
    private String name;
    private String identifier;
    private String ref;
    private String notes;

    public Permissions() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return String.format("Permission { name: %s, identifier: %s, " +
                "ref: %s, notes: %s }\n",
                name, identifier, ref, notes);
    }
}
