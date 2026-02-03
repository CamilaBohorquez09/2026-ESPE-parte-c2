package es.upm.grise.profundizacion.order;

public class Product {
	
    long id;

    void setId(long id) {
        this.id = id;
    }

    long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return id == other.id;
    }
}
