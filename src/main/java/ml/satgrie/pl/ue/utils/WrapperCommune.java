package ml.satgrie.pl.ue.utils;

import java.util.Objects;

import ml.satgrie.pl.ue.model.Commune;

public class WrapperCommune {
    private Commune e;

    public WrapperCommune(Commune e) {
        this.e = e;
    }

    public Commune unwrap() {
        return this.e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperCommune that = (WrapperCommune) o;
        return Objects.equals(e.getId(), that.e.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(e.getId());
    }
}