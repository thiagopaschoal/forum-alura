package br.com.tspaschoal.forumalura.support;

import java.util.List;

public interface Converter<T, E> {
    public List<T> converter(List<E> values);
}
